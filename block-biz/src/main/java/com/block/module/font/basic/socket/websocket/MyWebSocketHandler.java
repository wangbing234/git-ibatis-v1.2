package com.block.module.font.basic.socket.websocket;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.alibaba.fastjson.JSONObject;
import com.block.module.font.basic.socket.entity.Message;
import com.block.module.font.basic.socket.entity.PushMessage;

/**
 * Socket处理器
 * 
 * @author Goofy
 * @Date 2015年6月11日 下午1:19:50
 */
@Component
public class MyWebSocketHandler implements WebSocketHandler {
	public static final Map<Integer, WebSocketSession> userSocketSessionMap;
	//日志打印类
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	static {
		userSocketSessionMap = new HashMap<Integer, WebSocketSession>();
	}
	/**
	 * 建立连接后
	 */
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		Integer uid = (Integer) session.getAttributes().get("uid");
		if (userSocketSessionMap.get(uid) == null) {
			userSocketSessionMap.put(uid, session);
		}
	}
	/**
	 * 消息处理，在客户端通过Websocket API发送的消息会经过这里，然后进行相应的处理
	 */
	public void handleMessage(WebSocketSession session,WebSocketMessage<?> message) throws Exception {
		if (message.getPayloadLength() == 0)
			return;
		String _msgStr=message.getPayload().toString();
		Message msg = JSONObject.parseObject(_msgStr,Message.class);
		if(PushMessage.PING.equalsIgnoreCase(msg.getType())){
			sendMessageToUser(msg.getFrom(), new TextMessage("pong"));
		}
		else{
			msg.setDate(new Date());
			sendMessageToUser(msg.getTo(), new TextMessage(JSONObject.toJSONString(msg)));
		}
	}

	/**
	 * 消息传输错误处理
	 */
	public void handleTransportError(WebSocketSession session,Throwable exception) throws Exception {
		if (session.isOpen()) {
			session.close();
		}
		Iterator<Entry<Integer, WebSocketSession>> it = userSocketSessionMap.entrySet().iterator();
		// 移除Socket会话
		while (it.hasNext()) {
			Entry<Integer, WebSocketSession> entry = it.next();
			if (entry.getValue().getId().equals(session.getId())) {
				userSocketSessionMap.remove(entry.getKey());
				System.out.println("Socket会话已经移除:用户ID" + entry.getKey());
				break;
			}
		}
	}

	/**
	 * 关闭连接后
	 */
	public void afterConnectionClosed(WebSocketSession session,CloseStatus closeStatus) throws Exception {
		Iterator<Entry<Integer, WebSocketSession>> it = userSocketSessionMap.entrySet().iterator();
		// 移除Socket会话
		while (it.hasNext()) {
			Entry<Integer, WebSocketSession> entry = it.next();
			if (entry.getValue().getId().equals(session.getId())) {
				userSocketSessionMap.remove(entry.getKey());
				System.out.println("Socket会话已经移除:用户ID" + entry.getKey());
				break;
			}
		}
		System.out.println("Websocket:" + session.getId() + "已经关闭");
	}

	public boolean supportsPartialMessages() {
		return false;
	}

	/**
	 * 给所有在线用户发送消息
	 * @param message
	 * @throws IOException
	 */
	public void broadcast(final TextMessage message) throws IOException {
		Iterator<Entry<Integer, WebSocketSession>> it = userSocketSessionMap.entrySet().iterator();
		// 多线程群发
		while (it.hasNext()) {
			Entry<Integer, WebSocketSession> entry = it.next();
			final WebSocketSession webSocketSession = entry.getValue();
			if (webSocketSession.isOpen()) {
				new Thread(new Runnable() {
					public void run() {
						try {
							if (webSocketSession.isOpen()) {
								webSocketSession.sendMessage(message);
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}).start();
			}
		}
	}
	
	/**
	 * 发送给谁
	 * @param msg
	 * @throws IOException
	 */
	public void sendMessageToUser(Integer toUser,PushMessage msg) throws IOException {
		sendMessageToUser(toUser,new TextMessage(JSONObject.toJSONString(msg)));
	}

	/**
	 * 给某个用户发送消息
	 * @param userName
	 * @param message
	 * @throws IOException
	 */
	private void sendMessageToUser(Integer uid, TextMessage message)throws IOException {
		WebSocketSession session = userSocketSessionMap.get(uid);
		if (session != null && session.isOpen()) {
			session.sendMessage(message);
		}
		else{
			logger.info("webSocket:找不到在线用户！"); 
		}
	}
}
package com.block.module.font.basic.socket.controller;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.block.module.font.basic.socket.websocket.MyWebSocketHandler;
/**
 * http://blog.csdn.net/qq_28988969/article/details/76057789
 * @author bing.wang
 *
 */
@Controller
@RequestMapping("/msg")
public class MsgController {
	@Resource
	MyWebSocketHandler handler;
//	Map<Long, User> users = new HashMap<Long, User>();
	// 模拟一些数据
//	@ModelAttribute
//	public void setReqAndRes() {
//		User u1 = new User();
//		u1.setId(1L);
//		u1.setName("张三");
//		users.put(u1.getId(), u1);
//		
//		
//		User u2 = new User();
//		u2.setId(2L);
//		u2.setName("李四");
//		users.put(u2.getId(), u2);
//	}
//	// 用户登录
//	@RequestMapping(value = "login", method = RequestMethod.POST)
//	public void doLogin(User user, HttpServletRequest request) {
//		System.out.println("登录：uid:"+user.getId()+"   name"+users.get(user.getId()).getName());
//		request.getSession().setAttribute("uid", user.getId());
//		request.getSession().setAttribute("name",users.get(user.getId()).getName());
//	}

//	// 发布系统广播（群发）
//	@ResponseBody
//	@RequestMapping(value = "broadcast", method = RequestMethod.POST)
//	public void broadcast(String text) throws IOException {
//		Message msg = new Message();
//		msg.setDate(new Date());
//		msg.setFrom(-1);
//		msg.setType("1");
//		msg.setTo(0);
//		msg.setText(text);
//		handler.broadcast(new TextMessage(JSONObject.toJSONString(msg)));
//	}
}
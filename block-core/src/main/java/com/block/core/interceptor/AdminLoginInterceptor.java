package com.block.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.block.core.beanutils.annotation.NoNeedAuth;
import com.block.core.domain.SystemCache;
import com.block.core.framework.dto.ResultBean;
import com.block.core.framework.dto.ResultEnum;
import com.block.core.redis.RedisSevice;
import com.block.core.redis.baseuser.UserCenter;


/**
 * @author bing.wang
 *
 */
public class AdminLoginInterceptor implements HandlerInterceptor {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if(handler instanceof HandlerMethod) {
			HandlerMethod hm = (HandlerMethod)handler;
			Boolean authRequired=hm.getMethod().isAnnotationPresent(NoNeedAuth.class);
			//如果需要验证
	        if(!authRequired) {
//	        	//如果请求合法
	        	UserCenter user = getUser(request);
	        	if(null!=user) {
	        		SystemCache.setLoaclUser(user);
	        	}
	        	else {
	        		printError(response, JSON.toJSONString(new ResultBean(ResultEnum.NO_LOGIN_ERROR.getCode(),"您未登录，请重新登录！")));
	        		return false;
	        	}
	        	logger.info("成功登录的用户名:"+user.getName() +"用户名ID:"+user.getSystemId());
	        }
		}
		else{
		    logger.info("无效的请求！"); 
			printError(response, "无效的请求！!");
			return false;
		}
		return true;
	}
	
	private UserCenter getUser(HttpServletRequest request){
		WebApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
		RedisSevice redisSevice=(RedisSevice)ctx.getBean("redisSevice");
		String _token=redisSevice.getToken(request);
		UserCenter mUser = redisSevice.getUserByToken(_token);
		SystemCache.setToken(_token);
		return mUser; 
	}
	
	private void printError(HttpServletResponse response,String str)throws Exception
	{
		response.setCharacterEncoding("UTF-8");
		response.getWriter().println(str);
		response.getWriter().close();
	}


	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
    }

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
//        //释放资源 避免内存溢出
//        HandlerMethod hm = (HandlerMethod)handler;
//        Object hmBean = hm.getBean();
//        logger.info("ContextInterceptor: "+hmBean.toString());
//        if(	hmBean instanceof UserController) {
//            ControllerContext.remove();
//        }

	}
	
}

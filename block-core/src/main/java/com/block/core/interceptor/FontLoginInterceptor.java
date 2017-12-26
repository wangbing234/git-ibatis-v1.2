package com.block.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.block.core.beanutils.annotation.NoNeedAuth;
import com.block.core.domain.FontCache;
import com.block.core.framework.dto.ResultBean;
import com.block.core.framework.dto.ResultEnum;
import com.block.core.jwt.Jwt;
import com.block.core.jwt.JwtValidateResult;
import com.block.core.jwt.TokenState;
import com.block.core.redis.RedisSevice;
import com.block.core.redis.baseuser.JwtUserInfo;
import com.block.core.redis.baseuser.UserCenter;

public class FontLoginInterceptor implements HandlerInterceptor{
	private static Logger  logger = LoggerFactory.getLogger(FontLoginInterceptor.class);
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		//System.out.println("loginInterceptor-afterCompletion");
	}
	
	/**
	 * 获取jwt用户信息
	 * @param response
	 * @param _token
	 * @return
	 * @throws Exception
	 */
	public static JwtUserInfo getJwtUser(String _token) throws Exception {
		if(StringUtils.isBlank(_token)){
			return null;
		}
		JwtValidateResult<JwtUserInfo> jwtValidateResult = Jwt.validToken(_token);
		if(null!=jwtValidateResult && TokenState.VALID.getState().equals(jwtValidateResult.getState().getState())) {
			JwtUserInfo jwtUserInfo=jwtValidateResult.getData();
//			logger.info("登录的用户名:"+jwtUserInfo.getName() +"用户名ID:"+jwtUserInfo.getId());
			return jwtUserInfo;
		}
		return null;
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if(handler instanceof HandlerMethod) {
			HandlerMethod hm = (HandlerMethod)handler;
			Boolean authRequired=hm.getMethod().isAnnotationPresent(NoNeedAuth.class);
			//如果需要验证
	        if(!authRequired) {
//	        	//如果请求合法
	        	WebApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
	    		RedisSevice redisSevice=(RedisSevice)ctx.getBean("redisSevice");
	    		String _token=redisSevice.getToken(request);
	    		JwtUserInfo jwtUserInfo=getJwtUser(_token);
	        	if(null!=jwtUserInfo) {
	        		FontCache.setToken(_token);
	        		FontCache.setLoaclUser(jwtUserInfo);
	        	}
	        	else {
	        		printError(response, JSON.toJSONString(new ResultBean(ResultEnum.NO_LOGIN_ERROR.getCode(),"您未登录，请重新登录！")));
	        		return false;
	        	}
	        }
		}
		else{
		    logger.info("无效的请求！"); 
			printError(response, "无效的请求！!");
			return false;
		}
		return true;
	}
	
	
	private void printError(HttpServletResponse response,String str)throws Exception
	{
		response.setCharacterEncoding("UTF-8");
		response.getWriter().println(str);
		response.getWriter().close();
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	 

}

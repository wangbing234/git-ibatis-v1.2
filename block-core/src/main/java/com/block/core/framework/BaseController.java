package com.block.core.framework;

import com.block.core.framework.dto.ResultBean;
import com.block.core.framework.dto.ResultEnum;

public class BaseController {
	
	protected ResultBean success(Object data){
		return new ResultBean(ResultEnum.SUCCESS).setData(data);
	}
	
	protected ResultBean success(){
		return new ResultBean(ResultEnum.SUCCESS);
	}
	
	/**
	 * 参数错误
	 * @param msg
	 * @return
	 */
	protected ResultBean fail(String msg){
		return new ResultBean(ResultEnum.CLIENT_ERROR.getCode(),msg);
	}
	
	/**
	 * 参数错误
	 * @param msg
	 * @return
	 */
	protected ResultBean noLogin(){
		ResultEnum result = ResultEnum.NO_LOGIN_ERROR;
		return new ResultBean(result.getCode(),result.getMsg());
	}
}

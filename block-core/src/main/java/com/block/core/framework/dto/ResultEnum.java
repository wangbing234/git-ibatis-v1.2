package com.block.core.framework.dto;

public enum ResultEnum {
	//成功
	SUCCESS("000","success"),  
	NO_LOGIN_ERROR("403","用户未登录"), 
	//客户端
	CLIENT_ERROR("404","参数错误"), 
	//服务器
	SERVER_ERROR( "500","服务器异常"), 
	//会员状态不符合要求
	MEB_STATE_ERROR("501","会员状态不符合认证要求！"),
	//认证会员出现异常
	AUTH_MEB_EXCEPTION("502","认证会员产生异常！"),
	//待注销会员必须是冻结状态
	UNREG_MEB_EXCEPTION("503","待注销会员必须是冻结状态！"),
	//注销会员失败
	UNREG_MEB_ERROR("504","注销会员失败！"),
	//冻结：会员状态不符
	FREEZE_MEB_ERROR("505","冻结：会员状态不符"),
	//解冻：会员状态不符
	UNFREEZE_MEB_ERROR("506","解冻：会员状态不符"),
	//未查询到该用户
	USER_NOT_EXIT("507","未查询到该用户"),
	//修改密码：旧密码错误
	USER_PASSWORD_ERROR("508","密码错误"),
	//修改密码错误：更新数据异常
	UPDATE_PASSWORD_ERROR("509","修改密码失败");
	
	private String code;
	private String msg;
	 // 构造方法  
  private ResultEnum(String code, String msg) {
      this.code = code;  
      this.msg = msg;  
  }  
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
    
    
	
}

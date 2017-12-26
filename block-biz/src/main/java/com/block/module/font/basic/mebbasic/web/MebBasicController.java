/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 系统设置-SystemConf
 * @date 2017-11-04 15:50:23
 * @version V1.0
 **/
package com.block.module.font.basic.mebbasic.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.block.core.beanutils.BlockBeanUtils;
import com.block.core.beanutils.annotation.NoNeedAuth;
import com.block.core.common.CoreParamCache;
import com.block.core.domain.Busi;
import com.block.core.domain.FontCache;
import com.block.core.framework.BaseController;
import com.block.core.framework.dto.ResultBean;
import com.block.core.ibatis.beans.PagerModel;
import com.block.core.ibatis.sql.criteria.And;
import com.block.core.ibatis.sql.criteria.Restrictions;
import com.block.core.ibatis.sql.order.Order;
import com.block.core.jwt.Jwt;
import com.block.core.redis.baseuser.JwtUserInfo;
import com.block.core.smsinfo.service.SmsInfoService;
import com.block.core.smsinfo.service.impl.SmsInfoServiceImpl;
import com.block.module.common.enums.CommonManager;
import com.block.module.font.basic.invite.entity.Invite;
import com.block.module.font.basic.invite.service.InviteService;
import com.block.module.font.basic.mebbasic.entity.MebBasic;
import com.block.module.font.basic.mebbasic.entity.MebBasicWalletInfo;
import com.block.module.font.basic.mebbasic.service.MebBasicService;
import com.block.module.font.basic.mebwallet.entity.MebWallet;
import com.block.module.font.basic.mebwallet.service.MebWalletService;
import com.block.module.font.basic.mebwalletlog.entity.MebWalletLog;
import com.block.module.font.basic.mebwalletlog.service.MebWalletLogService;
import com.block.module.font.basic.orderment.service.OrdermentService;
import com.block.module.font.basic.wxpay.util.WXAppletUserInfo;

import net.sf.json.JSONObject;


/**
 * 系统设置Controller前端接口类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
public class MebBasicController  extends BaseController{
	
	// 默认验证码
	public static final String DEFAUL_SMS_CODE = "9999";
	
	//日志打印类
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//注入mebWalletService
	@Resource(name="mebWalletServiceBasic")
	protected MebWalletService mebWalletService;
	

	@Resource(name="smsInfoServiceSystem")
	protected SmsInfoService smsInfoService;
	
	//注入MebBasicService
	@Resource(name="mebBasicServiceBasic")
	protected MebBasicService mebBasicService;
	
	//注入TenantPublishService
	@Resource(name="inviteServiceBasic")
	protected InviteService inviteService;
	
	@Resource(name="coreParamCache")
	protected CoreParamCache coreParamCache;
	
	@Resource(name="mebWalletLogServiceBasic")
	protected MebWalletLogService mebWalletLogService;
	
	@Resource(name="ordermentServiceBasic")
	protected OrdermentService ordermentService;
//	/**
//	 * 服务类型字典查询
//	 * @return
//	 */
	@RequestMapping(value = "/serviceType", method = RequestMethod.POST)
	protected ResultBean serviceType() {
		return success(CommonManager.serviceTypeList);
	}
	
//	/**
//	 * 用户修改密码
//	 * @param userExtendId 主键id
//	 * @return
//	 */
//	@RequestMapping(value = "/changePwd", method = RequestMethod.POST)
//	protected ResultBean changePwd(@RequestParam(value = "oldPassword") String oldPassword,
//			@RequestParam(value = "newPassword") String newPassword,
//			@RequestHeader(value=Busi.TOKEN) String token) {
//		logger.info("修改密码！"); 
//		UserCenter _user=redisSevice.getUserByToken(token);
//		if(_user==null){
//			return fail("找不到登录用户！"); 
//		}
//		ResultBean result = UserCenterClient.changePwd(oldPassword, newPassword, _user.getSystemType(), token);
//		return result;
//	}
	
	
	/**
	 * 查询用户基础信息表(User)列表
	 *@param user User对象
	 * @return
	 */
	@NoNeedAuth
	@RequestMapping(value = "/yzm/{phone}", method = RequestMethod.POST)
	protected ResultBean yzm(@PathVariable(value = "phone") String phone) {
		if(Busi.YES.equals(coreParamCache.getString("isOpenSms"))){
			smsInfoService.sendcode(0, phone, SmsInfoServiceImpl.SMS_TYPE_MEMBERREG, "A");
		}
		else{
			return success("校验校验码为："+DEFAUL_SMS_CODE);
		}
		return success("发送校验码成功！");
	}
	
	/**
	 * 用户注册
	 * @param userExtendId 主键id
	 * @return
	 */
	@NoNeedAuth
	@RequestMapping(value = "/wxlogin", method = RequestMethod.POST)
	protected ResultBean wxlogin(@RequestBody MebBasic mebBasic) {
		//字段规则校验
		if(StringUtils.isBlank(mebBasic.getCode())){
			return fail("参数错误！");
		}
		Map<String, Object> data=null;
		try {
			data = mebBasicService.wxlogin(mebBasic);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return fail("登录错误！");
		}
		return success(data);
	}
	
	/**
	 * 用户注册@RequestParam("file") CommonsMultipartFile[] files,HttpServletRequest request
	 * @param userExtendId 主键id
	 * @return
	 */
	@RequestMapping(value = "/wxloginTenantUpdate")
	protected ResultBean wxloginUpdate(@RequestParam(value = "file",required=false) CommonsMultipartFile file,HttpServletRequest request) {
		MebBasic mebBasic=new MebBasic();
		try {
			BeanUtils.populate(mebBasic,request.getParameterMap());
			mebBasic.setFile(file);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return fail("参数错误！");
		} 
		return doWxLoginUpdate(mebBasic);
	}
	
	/**
	 * 用户注册
	 * @param userExtendId 主键id
	 * @return
	 */
	@RequestMapping(value = "/wxloginUpdate", method = RequestMethod.POST)
	protected ResultBean wxloginUpdate(@RequestBody MebBasic mebBasic) {
		return doWxLoginUpdate(mebBasic);
	}

	/**
	 * 时间执行微信登录的操作
	 * @param mebBasic
	 * @return
	 */
	private ResultBean doWxLoginUpdate(MebBasic mebBasic) {
		//字段规则校验
		if(null==mebBasic.getType()){
			return fail("参数错误！");
		}
		Map<String, Object> data=null;
		try {
			mebBasic.setOpenid(FontCache.getLoaclUser().getOpenid());
			data = mebBasicService.wxloginUpdate(mebBasic);
		} catch (Exception e) {
			e.printStackTrace();
			return fail("登录错误！");
		}
		return success(data);
	}
	
	
	/**
	 * 查询个人信息
	 * @param guestsExtendId 主键id
	 * @return
	 */
	@RequestMapping(value = "/info", method = RequestMethod.POST)
	protected ResultBean get() {
		MebBasic guestsExtend = mebBasicService.get(FontCache.getLoaclUser().getId());
		logger.info("查询个人信息成功");
		return success(guestsExtend);
	}
	
	/**
	 * 个人统计信息
	 * @param guestsExtendId 主键id
	 * @return
	 */
	@RequestMapping(value = "/statistics", method = RequestMethod.POST)
	protected ResultBean statistics() {
		And and =new And("userId", FontCache.getLoaclUser().getId(),Restrictions.EQ);
		MebWallet guestsExtend = mebWalletService.get(and);
		logger.info("查询个人统计信息成功");
		return success(guestsExtend);
	}
	
//	/**
//	 * 查询用户基础信息表(User)列表
//	 *@param user User对象
//	 * @return
//	 */
//	@RequestMapping(value = "/verifycode", method = RequestMethod.POST)
//	protected ResultBean verifycode(@RequestParam(value = "phone") String phone,@RequestParam(value = "yzm") String yzm) {
//		ResultBean result =UserCenterClient.verifycode(phone, yzm);
//		return result;  
//	}
	
	@RequestMapping(value = "/jwt", method = RequestMethod.GET)
	@NoNeedAuth
	protected String testJwt(@RequestParam(value = "id") Integer id,@RequestParam(value = "nickname") String nickname,
			@RequestParam(value = "type") Integer type) {
		//创建用户信息
		return createJwtInfo(id, nickname, type);
	}

	private String createJwtInfo(Integer id, String nickname, Integer type) {
		JwtUserInfo jwtUserInfo=new JwtUserInfo();
		MebBasic mebBasic=new MebBasic();
		jwtUserInfo.setId(id); 
		jwtUserInfo.setName(nickname);
		jwtUserInfo.setSystemType(type);
		return Jwt.createToken(mebBasic.getId(), jwtUserInfo);
	}
	
	/**
	 * 查询用户基础信息表(User)列表
	 *@param user User对象
	 * @return
	 */
	@RequestMapping(value = "/postion", method = RequestMethod.GET)
	protected ResultBean postion(@RequestParam(value = "longitude") float longitude,@RequestParam(value = "latitude") float latitude,
							   @RequestParam(value = "address") String address,@RequestHeader(value=Busi.TOKEN) String token) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("longitude", longitude);
		map.put("latitude", latitude);
		map.put("address", address);
		mebBasicService.update(FontCache.getLoaclUser().getId(), map);
		return null;
	}
	
	
	/**
	 * 提现
	 *@param user User对象
	 * @return
	 */
	@RequestMapping(value = "/wallet/exit", method = RequestMethod.GET)
	protected ResultBean walletExit(@RequestParam(value = "amount") float amount) { 
		And where =new And("userId", FontCache.getLoaclUser().getId(),Restrictions.EQ);
		MebWallet mebWallet = mebWalletService.get(where); 
		float walletFree=mebWallet.getFree();
		if(walletFree<amount){
			return fail("您可用金额只有:"+walletFree+",大于提现金额。"); 
		}
		return success("提现成功"); 
	}
	
	/**
	 * 是否已经要求过
	 * @return
	 */
	protected Boolean hasInvite(Integer publishId,Integer userId){
		And and =new And("publishId", publishId,Restrictions.EQ);
		and.add("userId", userId, Restrictions.EQ);
		List<Invite> invateList = inviteService.list(and, null);
		if(invateList!=null && !invateList.isEmpty()){
			return true;
		}
		return false;
	}
	
	/**
	 * 提现
	 *@param user User对象
	 * @return
	 */
	@RequestMapping(value = "/wallet/log/{offset}/{pageSize}", method = RequestMethod.GET)
	protected ResultBean walletLog(@PathVariable("offset") Integer offset,@PathVariable("pageSize") Integer pageSize) { 
		And where =new And("userId", FontCache.getLoaclUser().getId(),Restrictions.EQ);
		Order order=new Order();
		order.add("createtime",Order.DESC);
		PagerModel<MebWalletLog> log = mebWalletLogService.page(where, order, offset, pageSize);
		return success(log); 
	}
	
//	/**
//	 * 提现
//	 *@param user User对象
//	 * @return
//	 */
//	@RequestMapping(value = "/referer/{userId}", method = RequestMethod.GET)
//	protected ResultBean updateReferer(@PathVariable("userId") Integer refererId) { 
//		MebBasic referer = mebBasicService.get(refererId);
//		if(null==referer){
//			return fail("找不到推荐人");
//		}
//		else{
//			Integer userId = FontCache.getLoaclUser().getId();
//			MebBasic me = mebBasicService.get(userId);
//			if(me!=null &&  null==me.getReferer()){
//				mebBasicService.update(userId, "referer", refererId);
//			}
//		}
//		return success("更新推荐人成功！"); 
//	}
	
	/**
	 * 查询用户基础信息表(User)列表
	 * @param user User对象
	 * @return
	 */
	@NoNeedAuth
	@RequestMapping(value = "/verifycode", method = RequestMethod.GET)
	protected ResultBean verifycode(@RequestParam(value = "phone") String phone,@RequestParam(value = "yzm") String yzm) {
		boolean result =smsInfoService.verifycode(phone, yzm);
		if(result)
			return success("验证码校验成功");
		return fail("验证码错误！");
	}
	
//	/**
//	 * 解密用户手机号
//	 * @param encryptedData
//	 * @param iv
//	 */
//	@RequestMapping(value = "/decodePhone", method = RequestMethod.GET)
//	public ResultBean decodeUserInfo(@RequestParam(value = "encryptedData") String encryptedData,
//			@RequestParam(value = "iv") String iv) {
//		Integer userId = FontCache.getLoaclUser().getId();
//		MebBasic guestsExtend = mebBasicService.get(userId);
//		JSONObject jsob = WXAppletUserInfo.getUserInfo(encryptedData, guestsExtend.getSessionKey(), iv);
//		if(jsob!=null){
//			String phoneNumber = jsob.getString("phoneNumber");
//			mebBasicService.update(userId, "phone", phoneNumber);
//			return success("获取手机号成功");
//		}
//		return fail("应用错误！");  
//	}
	
	/**
	 * 解密用户手机号
	 * @param encryptedData
	 * @param iv
	 * @throws Exception 
	 */
	@RequestMapping(value = "/decodePhone", method = RequestMethod.POST)
	public ResultBean decodeUserInfo(@RequestBody PhoneDecodeParms t ) throws Exception {
		Integer userId = FontCache.getLoaclUser().getId();
		if(null==t.getCode()){
			return fail("授权code不能为空！");  
		}
		String sessionKey=WXAppletUserInfo.getSessionKeyOrOpenid(t.getCode()).getSession_key();//6gk
		JSONObject jsob = WXAppletUserInfo.getUserInfo(t.getEncryptedData(), sessionKey, t.getIv());
		if(jsob!=null){
			String phoneNumber = jsob.getString("phoneNumber");
			mebBasicService.update(userId, "phone", phoneNumber,"username","dd");
			return success(phoneNumber);
		}
		return fail("应用错误！");  
	}
	
	
	/**
	 * 查询用户信息
	 * @param guestsExtendId 主键id
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/userInfo/{id}", method = RequestMethod.GET)
	private ResultBean getUserInfo(@PathVariable(value = "id")  String id) throws Exception {
		logger.info("查询商户列表");
		MebBasic basicUser = mebBasicService.get(id);
		MebWallet mebWallet = mebWalletService.get(id);
		MebBasicWalletInfo mw=new MebBasicWalletInfo();
		BlockBeanUtils.copyProperties(basicUser, mw);
	    BlockBeanUtils.copyPropertieInclude(mebWallet, mw,
	    		"deposit", "free", "freeze", "stop", "credit", "opinion", "times","failTimes","sucessTimes");
	    mw.setCreatetime(null);
	    return success(mw);
	}
	
	
	public static void main(String[] args) {
//		String jwtString = new MebBasicController().createJwtInfo(1, "12", BusiUserType.USER);
//		JwtValidateResult<JwtUserInfo> jwt = Jwt.validToken(jwtString);
//		System.out.println(jwt.getState().getState());
//		System.out.println(jwtString); 
		
//		{
//			 "type":"2",
//			 "phone"："1343445454545",
//			 "nickname":"ssdf颠倒是非",
//			 "profile":"fdsf",
//			 "files"："图片介绍对象数组（商家注册页面）",
//			 "address"："地址（商家注册页面）",
//			 "longitude"："323",
//			 "latitude"："434",
//			 "remark"："用户介绍（商家注册页面）"
//			}
		MebBasic mebBasic=new MebBasic();
		mebBasic.setType(2);
		mebBasic.setPhone("1343445454545");
		mebBasic.setNickname("ssdf颠倒是非");
		mebBasic.setProfile("dfsdf");
		mebBasic.setAddress("保利心语");
		mebBasic.setLongitude(323.323f);
		mebBasic.setLatitude(323.323f);
		mebBasic.setRemark("sfssfsfs是疯疯傻傻"); 
	}
	

	
	
	/**
	 * 解密用户手机号
	 * @param encryptedData
	 * @param iv
	 */
	@RequestMapping(value = "/writePhone", method = RequestMethod.GET)
	public ResultBean writePhone(@RequestParam(value = "yzm") String yzm,@RequestParam(value = "phone") String phone) {
		Integer userId = FontCache.getLoaclUser().getId();
		boolean result =smsInfoService.verifycode(phone, yzm);
		if(result){
			mebBasicService.update(userId, "phone", phone);
			return success("获取手机号成功");
		}
		return fail("验证码错误！"); 
	}
	
}

/*
 * Powered By [rapid-framework]
 *  copyright © 趋快科技(武汉)有限公司
 * Since 2017 - 2017
 */

package com.block.core.module.user.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.block.core.beanutils.CipherUtil;
import com.block.core.beanutils.annotation.NoNeedAuth;
import com.block.core.domain.Busi;
import com.block.core.domain.SystemCache;
import com.block.core.framework.BaseController;
import com.block.core.framework.dto.ResultBean;
import com.block.core.ibatis.beans.PagerModel;
import com.block.core.ibatis.sql.criteria.And;
import com.block.core.ibatis.sql.criteria.Restrictions;
import com.block.core.ibatis.sql.order.Order;
import com.block.core.module.systemlog.entity.SystemLogType;
import com.block.core.module.systemlog.service.SystemlogService;
import com.block.core.module.user.entity.UserModel;
import com.block.core.module.user.service.UserService;
import com.block.core.redis.RedisSevice;
import com.block.core.redis.baseuser.UserCenter;


/**
 * Controller
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@Controller
@ResponseBody
@RequestMapping("/system/user")//域/模块
public class UserController  extends BaseController{
	
	//日志打印类
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//注入UserService
	@Resource(name="userServiceSystem")
	UserService userService;
	
	@Resource(name="redisSevice")
	private RedisSevice redisSevice;
	
	//注入SystemlogService
	@Resource(name="systemlogServiceSystem")
	SystemlogService systemlogService;
	
	/**
	 * 插入(User)对象
	 * @param user User对象
	 * @return
	 */
	@RequestMapping(value = "/addOrUpdate", method = RequestMethod.POST)
	private ResultBean addOrUpdate(@RequestBody @Valid UserModel user,BindingResult bindResult) {
		if(bindResult.hasFieldErrors("phone")){
			return fail(bindResult.getFieldError("phone").getDefaultMessage());
		}
		if(bindResult.hasFieldErrors("username")){
			return fail(bindResult.getFieldError("username").getDefaultMessage());
		}
		UserCenter _user=SystemCache.getLoaclUser();
		try {
			if(user.getId()==null){
				if(bindResult.hasFieldErrors("password")){
					return fail(bindResult.getFieldError("password").getDefaultMessage());
				}
				 user.setStatus(Busi.YES_STRING);
				 userService.addUser(user, _user);
				 return success("增加对象信息成功");
			}
			else
			{
				user.setUsername(null);
				user.setPassword(null);
				user.setUpdatetime(new Date());
//				UserModel tempUserModel=getUserModelByName(user.getUsername());
//				if(tempUserModel!=null && tempUserModel.getId().equals(user.getId())){
//					fail("注册失败，用户名已存在");
//				}
				userService.update(user);
				logger.info("更新对象成功！");
				return success("更新对象信息成功");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return fail("注册失败，用户名已存在");
		}
	}
	
	/**
	 * 插入(User)对象
	 * @param user User对象
	 * @return
	 */
	@RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
	private ResultBean updateStatus(@RequestBody @Valid UserModel user,BindingResult bindResult) {
		UserCenter _user=SystemCache.getLoaclUser();
		user.setId(Integer.parseInt(_user.getSystemId()));
		user.setUsername(null);
		user.setPassword(null);
		user.setUpdatetime(new Date());
		int flag = userService.update(user);
		logger.info("插入(User)对象成功！");
		return success(flag);
	}
	
	
	/**
	 * 修改用户信息（所有用户）
	 *@param user User对象
	 * @return
	 */
	@RequestMapping(value = "/updatePersonalInfo", method = RequestMethod.POST)
	private ResultBean updatePersonalInfo(@RequestBody @Valid UserModel user,BindingResult bindResult) {
		UserCenter _user=SystemCache.getLoaclUser();
		user.setId(Integer.parseInt(_user.getSystemId()));
		user.setUsername(null);
		user.setPassword(null);
		user.setUpdatetime(new Date());
		int flag = userService.update(user);
		logger.info("插入(User)对象成功！");
		return success(flag);
	}
	
	/**
	 * 用户修改密码
	 * @param userExtendId 主键id
	 * @return
	 */
	@RequestMapping(value = "/changePwd", method = RequestMethod.GET)
	private ResultBean changePwd(@RequestParam(value = "oldPassword") String oldPassword,
			@RequestParam(value = "newPassword") String newPassword) {
		logger.info("修改密码！"); 
		UserModel user = userService.get(SystemCache.getLoaclUser().getSystemId());
		Boolean isSuccess=user.getPassword().equals(CipherUtil.encodePassword(oldPassword, user.getUsername()));
		if(!isSuccess) {
			return fail("原密码错误或手机号错误！");
		}
		userService.update(user.getId(), "password", CipherUtil.encodePassword(newPassword, user.getUsername()));
		return success("修改密码成功！");
	}
	
	/**
	 * 分页查询(User)分页对象
	 * @param offset 第几页
	 * @param pageSize 分页大小
	 * @param user User对象
	 * @return
	 */
	@RequestMapping(value = "/page/{offset}/{pageSize}", method = RequestMethod.GET)
	private ResultBean page(@PathVariable("offset") Integer offset,@PathVariable("pageSize") Integer pageSize ,@Param("user") UserModel user) {
		And and =new And("username", user.getUsername(),Restrictions.LIKE);
	 	and.add("status", user.getStatus(),Restrictions.EQ);
	 	and.add("phone", user.getPhone(),Restrictions.EQ);
	 	Order order=new Order();
	 	order.add("createtime",Order.DESC);
	 	order.add("status");
		PagerModel<UserModel> pageModel = userService.page(and,order,offset,pageSize);
		logger.info("分页查询(User)分页对象成功！");
		return success(pageModel);
	}

	/**
	 * 根据id删除对象
	 * @param ids id的数组
	 * @return 修改记录的条数
	 */
	@RequestMapping(value = "/deletes", method = RequestMethod.GET)
	public ResultBean deleteByIds(@RequestParam(value = "ids") String[] ids){
		UserCenter _user=SystemCache.getLoaclUser();
		int flag = userService.deleteByIds(ids);
		logger.info("批量删除(User)对象成功！");
		systemlogService.log("删除用户", _user.getName()+"删除用户", _user.getSystemId(), SystemLogType.accountType_m);
		return  success(flag);
	}
	
	
	/**
	 * 根据id删除账户信息(Account)对象
	 * @param accountId 主键id
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@NoNeedAuth
	private ResultBean login(@RequestBody UserModel account,HttpSession session) throws Exception {
		if (StringUtils.isBlank(account.getUsername()) || StringUtils.isBlank(account.getPassword())) {
			return fail("账户和密码不能为空");
		}
		account.setPassword(CipherUtil.encodePassword(account.getPassword(), account.getUsername()));
		And and =new And("username", account.getUsername(),Restrictions.EQ);
	 	and.add("password", account.getPassword(),Restrictions.EQ); 
		Integer errorNum = (Integer) session.getAttribute("errorNum");
		UserCenter userCenter =null;
		if (errorNum == null) {
			errorNum = 0;
		}
		else if (errorNum > 5) {
			return fail("您的账户5次登陆失败,不能再登录!");
		}
		//字段规则校验
		UserModel user = userService.get(and);
		if (user==null) {
			logger.debug("登陆失败，账号不存在！");
			errorNum = errorNum + 1;
			session.setAttribute("errorNum", errorNum);
			return fail("登陆失败，账号不存在！");

		} else {
			userCenter = new UserCenter();
			userCenter.setSystemId(""+user.getId());
			userCenter.setName(user.getUsername());
			userCenter.setPhone(user.getPhone());
			if (!Busi.YES_STRING.equals(user.getStatus())) {
			logger.debug("帐号已被禁用，请联系管理员!");
			return fail("帐号已被禁用，请联系管理员!");
			}
		}
		redisSevice.createToken(userCenter, null);
		return success(userCenter.getToken());
	}
	
	
	/**
	 * 退出登录
	 * @param accountId 主键id
	 * @return
	 */
	@RequestMapping(value = "/loginOut", method = RequestMethod.GET)
	private ResultBean loginOut(HttpServletRequest req) throws Exception {
		logger.debug("用户退出登录!");
		redisSevice.deleteToken(SystemCache.getToken());
		return success("退出成功！");
	}
	
}

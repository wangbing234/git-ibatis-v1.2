/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: -MebBasic
 * @date 2017-11-04 15:50:22
 * @version V1.0
 **/
package com.block.module.font.basic.mebbasic.service.impl;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.block.core.common.CoreParamCache;
import com.block.core.domain.Busi;
import com.block.core.ibatis.service.impl.BaseCenterServiceImpl;
import com.block.core.ibatis.sql.criteria.And;
import com.block.core.ibatis.sql.criteria.Restrictions;
import com.block.core.jwt.Jwt;
import com.block.core.redis.UserType;
import com.block.core.redis.baseuser.JwtUserInfo;
import com.block.module.common.enums.BusiUserType;
import com.block.module.common.enums.CommonManager;
import com.block.module.font.basic.mebbasic.dao.MebBasicDao;
import com.block.module.font.basic.mebbasic.entity.MebBasic;
import com.block.module.font.basic.mebbasic.service.MebBasicService;
import com.block.module.font.basic.mebwallet.entity.MebWallet;
import com.block.module.font.basic.mebwallet.service.MebWalletService;
import com.block.module.font.basic.wxpay.entity.OAuthJsToken;
import com.block.module.font.basic.wxpay.util.RandomStringGenerator;
import com.block.module.font.basic.wxpay.util.WXAppletUserInfo;
import com.block.module.font.guest.guestsextend.entity.GuestStatus;
import com.block.module.font.tenant.tenantextend.entity.TenantExtend;
import com.block.module.font.tenant.tenantextend.web.TenantStatus;
import com.block.module.font.tenant.tenantscope.service.TenantScopeService;
import com.block.module.font.user.userextend.entity.UserStatus;
import com.block.module.font.user.userpublish.entity.UserPublish;
import com.block.module.font.user.userpublish.service.UserPublishService;


/**
 * Service实现类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@Service("mebBasicServiceBasic")
public class MebBasicServiceImpl extends BaseCenterServiceImpl<MebBasic,MebBasicDao> implements MebBasicService {
	
	//日志打印类
		private Logger logger = LoggerFactory.getLogger(this.getClass());
		
		@Resource(name="coreParamCache")
		private CoreParamCache coreParamCache;
		//注入MebWalletService
		@Resource(name="mebWalletServiceBasic")
		MebWalletService mebWalletService;
		
		//注入MebWalletService
		@Resource(name="userPublishServiceUser")
		UserPublishService userPublishService;
		
		@Resource(name="tenantScopeServiceTenant")
		TenantScopeService tenantScopeService;
	    
	    /**
	     * 初始化钱包信息
	     * @param po
	     * @param userId
	     */
	    private void addMebWallet(MebBasic po,Integer userId) {
	    	MebWallet mebWallet=new MebWallet();
	    	mebWallet.setUserId(userId);
	    	mebWallet.setCredit(80f);
	    	mebWallet.setDeposit(0f);
	    	mebWallet.setFirstPay(0);
	    	mebWallet.setFree(0f);
	    	mebWallet.setFreeze(0f);
	    	mebWallet.setOpinion(0f);
	    	mebWallet.setTimes(0);
	    	mebWallet.setSucessTimes(0);
	    	mebWallet.setFailTimes(0);
	    	mebWalletService.add(mebWallet);
	    }

		@Override
		public void updateTenant(TenantExtend userExtend) {
			writeImage(userExtend);
			userExtend.setWeixin(userExtend.getScope());
			this.baseDao.update(userExtend);
			
			//更新租户营业范围
			tenantScopeService.updateTenantScope(userExtend);
		}
		
		public void writeImage(MebBasic userExtend) {
			//更新验证照片
			String publicFilePath=coreParamCache.get("verify_file_path").toString()+File.separator+userExtend.getId();
			MultipartFile[] files=new MultipartFile[1];
			files[0]=userExtend.getFile();
			String images=saveFileByPath(publicFilePath, files);
			//更新租户状态
			userExtend.setImages(images);
		}

		/**
		 * 保存图路径
		 * @param path
		 * @param files
		 * @return
		 */
		private String saveFileByPath(String path,MultipartFile[] files) {
			// TODO Auto-generated method stub
			StringBuilder sb=new StringBuilder();
			if(files!=null && files.length>0)
			{
				//删除父目录
				File parentFile = new File(path);
				if(parentFile.exists()) parentFile.delete();
				for (MultipartFile file : files) {
					if(file==null)
						continue;
				   String fileName = RandomStringGenerator.getRandomStringByLength(5)+".png";
				   logger.info("保存图片路径："+path+fileName);  
			        File targetFile = new File(path, fileName);  
			        if(!targetFile.exists()){
			            targetFile.mkdirs();  
			        }  
			        //保存  
			        try {  
			            file.transferTo(targetFile);  
			        } catch (Exception e) {
			            e.printStackTrace();  
			        }  
			        sb.append(",").append(path).append(File.separator).append(fileName);
				}
				if(StringUtils.isNoneBlank(sb.toString())){
					return sb.substring(1);
				}
			}
			return "";
		}
		
		/**
		 * 维护用户信息
		 * @param openid
		 */
		private Map<String, Object> createOrAddUserInfo(MebBasic mebBasic) 
		{
			And and =new And("openid", mebBasic.getOpenid(),Restrictions.EQ);
			MebBasic oldMebBasic = baseDao.get(and);
			Map<String, Object> ret = new HashMap<String, Object>();   
			if(oldMebBasic!=null){
				getTokenInfo(oldMebBasic, ret);    
			}
			//第一次创建信息
			else{
				mebBasic.setCreatetime(new Date());
				mebBasic.setFirst(Busi.YES_STRING);
				mebBasic.setStatus(UserStatus.FORBID);
				int userId = this.baseDao.add(mebBasic);
				mebBasic.setId(userId);
		    	//增加钱包信息
		    	 ret.put("openid", mebBasic.getOpenid());    
		         ret.put("id", userId);
		         ret.put("first", mebBasic.getFirst());
		       //创建用户信息
				JwtUserInfo jwtUserInfo=new JwtUserInfo();
				jwtUserInfo.setId(userId); 
				jwtUserInfo.setOpenid(mebBasic.getOpenid());
				jwtUserInfo.setName(mebBasic.getNickname());
		        String token=Jwt.createToken(userId, jwtUserInfo);
				ret.put("token", token);
			}
			
			 if(oldMebBasic!=null && BusiUserType.USER.equals(oldMebBasic.getType()))
			 {
				UserPublish publish = userPublishService.get(oldMebBasic.getId());
				ret.put("publish",publish);
				if(null!=oldMebBasic.getReferer())
					ret.put("referer", oldMebBasic.getReferer());
			 }
			 ret.put("serviceType",CommonManager.serviceTypeList);
			return ret;
		}

		private void getTokenInfo(MebBasic oldMebBasic, Map<String, Object> ret) {
			//创建用户信息
			JwtUserInfo jwtUserInfo=new JwtUserInfo();
			jwtUserInfo.setId(oldMebBasic.getId()); 
			jwtUserInfo.setName(oldMebBasic.getNickname());
			jwtUserInfo.setOpenid(oldMebBasic.getOpenid());
			jwtUserInfo.setSystemType(oldMebBasic.getType());
			String token=Jwt.createToken(oldMebBasic.getId(), jwtUserInfo);
			 ret.put("openid", oldMebBasic.getOpenid());    
			 ret.put("type", oldMebBasic.getType());
			 ret.put("id", oldMebBasic.getId());
			 ret.put("first", oldMebBasic.getFirst());
			 ret.put("token", token);
			 if(Busi.NO_STRING.equals(oldMebBasic.getFirst())){
				 ret.put("status",oldMebBasic.getStatus());
				 ret.put("phone",oldMebBasic.getPhone());
			 }
		}
		
		
		public Map<String, Object> wxlogin(MebBasic mebBasic) throws Exception {
		     Map<String, Object> ret =null; 
		     OAuthJsToken oauthJsToken= WXAppletUserInfo.getSessionKeyOrOpenid(mebBasic.getCode());    
		     if(oauthJsToken!=null) {
		         mebBasic.setOpenid(oauthJsToken.getOpenid());
		         mebBasic.setSessionKey(oauthJsToken.getSession_key()); 
		         ret=createOrAddUserInfo(mebBasic);
		     }
		     return ret;
		 }

		@Override
		public Map<String, Object> wxloginUpdate(MebBasic mebBasic) throws Exception {
			Map<String, Object> ret = new HashMap<String, Object>(); 
			And and =new And("openid", mebBasic.getOpenid(),Restrictions.EQ);
			MebBasic oldMebBasic = baseDao.get(and);
			mebBasic.setId(oldMebBasic.getId());
			updateUserInfoFirst(mebBasic, oldMebBasic);
			//再次获取数据全信息
			oldMebBasic = baseDao.get(and);
			getTokenInfo(oldMebBasic,ret);
		    return ret;
		}

		/**
		 * 初始化注释
		 * @param mebBasic  传过来的
		 * @param oldMebBasic 数据库中
		 */
		private void updateUserInfoFirst(MebBasic mebBasic, MebBasic oldMebBasic) {
			
			//如果是用户，设置为在线状态
			mebBasic.setFirst(Busi.NO_STRING);
			//首次登陆
			if(BusiUserType.USER.equals(mebBasic.getType())){
				mebBasic.setStatus(UserStatus.OUT_LINE);
				
				//增加用户发布信息
				UserPublish up=new UserPublish();
				up.setId(oldMebBasic.getId());
				up.setCreatetime(new Date());
				userPublishService.addUserPublish(up);
				writeImage(mebBasic);
				baseDao.update(mebBasic);
			}
			//如果是商家，设置为在线状态
			else if(BusiUserType.TENANT.equals(mebBasic.getType())){
				mebBasic.setStatus(TenantStatus.VERIFY);
				TenantExtend userExtend=new TenantExtend();
				BeanUtils.copyProperties(mebBasic, userExtend);
				updateTenant(userExtend);
			}
			//如果是客户，设置为在线状态
			else if(BusiUserType.GUEST.equals(mebBasic.getType())){
				mebBasic.setStatus(GuestStatus.NORMAL);
				baseDao.update(mebBasic);
			}
			//增加前钱包信息
			addMebWallet(mebBasic,mebBasic.getId());
		}

	
}

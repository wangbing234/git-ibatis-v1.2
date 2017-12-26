package com.block.core.module.quartzjob.service.impl;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.Resource;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.block.core.ibatis.service.impl.BaseCenterServiceImpl;
import com.block.core.module.quartzjob.dao.QuartzJobDao;
import com.block.core.module.quartzjob.entity.QuartzJob;
import com.block.core.module.quartzjob.service.QuartzCache;
import com.block.core.module.quartzjob.service.QuartzJobService;

@Service
@Component("aiJobListener")
public class AiJobListener extends BaseCenterServiceImpl<QuartzJob,QuartzJobDao> implements QuartzJobService, JobListener {
	//日志打印类
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Resource(name="quartzCache")
	private QuartzCache quartzCache;

	@Override
	public String getName() {
		return "aiJobListener";
	}
	
	public void testJob(){
		log.info("--------------testJob--------------");
	}

	/**
	 * 即将执行时
	 */
	@Override
	public void jobToBeExecuted(JobExecutionContext context) {
		String jobId = context.getJobDetail().getKey().getName();
		 QuartzJob quartzJob = baseDao.get(jobId);
		if (null == quartzJob.getFirstFireTime()) {
			baseDao.update(jobId, "firstFireTime", new Date());
		}

		baseDao.update(jobId, "lastFireTime", new Date());
		Serializable fireTimes = baseDao.getField(jobId,  "fireTimes");
		baseDao.update(jobId, "fireTimes", 1+Integer.valueOf(fireTimes.toString()));
	}

	/**
	 * 在trigger中被否决执行时
	 */
	@Override
	public void jobExecutionVetoed(JobExecutionContext context) {

	}

	/**
	 * 执行完毕时
	 */
	@Override
	public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
		if (null == jobException) {
			String jobId = context.getJobDetail().getKey().getName();
			Serializable fireTimes = baseDao.getField(jobId,  "succTimes");
			baseDao.update(jobId, "succTimes", 1+Integer.valueOf(fireTimes.toString()));
		} else { // 执行异常

		}
	}
}

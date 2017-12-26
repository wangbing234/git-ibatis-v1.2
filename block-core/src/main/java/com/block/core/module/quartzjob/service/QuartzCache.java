package com.block.core.module.quartzjob.service;

import java.util.HashMap;
import java.util.Map;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.block.core.module.quartzjob.entity.QuartzJob;
import com.block.core.module.quartzjob.service.impl.AiJobListener;


@Component("quartzCache")
public class QuartzCache implements SystemCacheApi {
	//日志打印类
	private Logger log = LoggerFactory.getLogger(this.getClass());

	private Scheduler scheduler;

	private Map<String, QuartzJob> jobMap;

	@Override
	public void init() {
		jobMap = new HashMap<String, QuartzJob>();
	}

	@Override
	public void refresh() {
		jobMap.clear();
	}

	@Override
	public void destroy() {
		jobMap.clear();
		jobMap = null;
		try {
			scheduler.shutdown(true);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scheduler = null;
	}

	public void put(String jobId, QuartzJob quartzJob) {
		if (jobMap.containsKey(jobId)) {
			// 更新任务cronExpression
			TriggerKey triggerKey = new TriggerKey(jobId);
			CronTrigger newTrigger = (CronTrigger) TriggerBuilder.newTrigger().withIdentity(quartzJob.getJobId()).withSchedule(CronScheduleBuilder.cronSchedule(quartzJob.getCronExpression())).build();
			try {
				scheduler.rescheduleJob(triggerKey, newTrigger);
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
		} else {
			this.jobMap.put(jobId, quartzJob);
		}
	}

	public QuartzJob get(String jobId) {
		return this.jobMap.get(jobId);
	}

	public void start() {
		log.info("------启动定时任务------");
		try {
			WebApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
			scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.getListenerManager().addSchedulerListener(new AiSchedulerListener());
			scheduler.getListenerManager().addTriggerListener((AiTriggerListener) ctx.getBean("aiTriggerListener"));
			scheduler.getListenerManager().addJobListener((AiJobListener) ctx.getBean("aiJobListener"));

			jobMap.values().forEach(quartzJob -> {
				try {
					// 将QuartzJob对象转换为job和trigger
					JobDetail job = JobBuilder.newJob(AiMethodInvokingJob.class).withIdentity(quartzJob.getJobId()).usingJobData("targetObject", quartzJob.getBeanId()).usingJobData("targetMethod", quartzJob.getMethodName()).build();
					CronTrigger trigger = (CronTrigger) TriggerBuilder.newTrigger().withIdentity(quartzJob.getJobId()).withSchedule(CronScheduleBuilder.cronSchedule(quartzJob.getCronExpression())).build();
					// 加载任务
					scheduler.scheduleJob(job, trigger);
				} catch (SchedulerException e) {
					e.printStackTrace();
				}
			});

			scheduler.start();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	public void execute(String jobId) {
		JobKey jobKey = new JobKey(jobId);
		try {
			scheduler.triggerJob(jobKey);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	public boolean valid(String jobId) {
		jobMap.get(jobId).setStop(!jobMap.get(jobId).getStop());
		return jobMap.get(jobId).getStop();
	}
	
	
}

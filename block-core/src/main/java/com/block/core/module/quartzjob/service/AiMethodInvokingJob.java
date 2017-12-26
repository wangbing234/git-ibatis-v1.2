package com.block.core.module.quartzjob.service;

import java.lang.reflect.Method;

import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class AiMethodInvokingJob implements Job {
	//日志打印类
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		String beanId = context.getMergedJobDataMap().get("targetObject").toString();
		String methodName = context.getMergedJobDataMap().get("targetMethod").toString();
		Trigger trigger = context.getTrigger();
		if (trigger instanceof CronTrigger) {
			log.info("定时任务[" + ((CronTrigger) trigger).getCronExpression() + "]开始，threadId=" + Thread.currentThread().getId() + "，beanId=" + beanId + "，methodName=" + methodName);
		} else if (trigger instanceof SimpleTrigger) {
			log.info("即时任务开始，threadId=" + Thread.currentThread().getId() + "，beanId=" + beanId + "，methodName=" + methodName);
		}

		try {
			WebApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
			Object target = ctx.getBean(beanId);
			Method method = target.getClass().getMethod(methodName);
			method.invoke(target);
			log.info("任务执行完毕");
		} catch (Exception e) {
			e.printStackTrace();
			throw new JobExecutionException("任务执行失败");
		}
	}

}

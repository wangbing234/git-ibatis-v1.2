package com.block.core.module.quartzjob.service;

import javax.annotation.Resource;

import org.quartz.CronTrigger;
import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.Trigger.CompletedExecutionInstruction;
import org.quartz.TriggerListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("aiTriggerListener")
public class AiTriggerListener implements TriggerListener {
	//日志打印类
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Resource(name="quartzCache")
	private QuartzCache quartzCache;

	public QuartzCache getQuartzCache() {
		return quartzCache;
	}

	public void setQuartzCache(QuartzCache quartzCache) {
		this.quartzCache = quartzCache;
	}

	@Override
	public String getName() {
		return "aiTriggerListener";
	}

	/**
	 * 触发时
	 */
	@Override
	public void triggerFired(Trigger trigger, JobExecutionContext context) {

	}

	/**
	 * 执行“否决”判定，返回是否“否决”
	 */
	@Override
	public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
		// 定时任务且处于暂停状态
		Boolean isStop=quartzCache.get(trigger.getKey().getName()).getStop();
		if (trigger instanceof CronTrigger && isStop) {
			log.info("job " + trigger.getKey().getName() + "被否决");
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 错过触发时机
	 */
	@Override
	public void triggerMisfired(Trigger trigger) {

	}

	/**
	 * 触发完成，晚于job的jobWasExecuted
	 */
	@Override
	public void triggerComplete(Trigger trigger, JobExecutionContext context, CompletedExecutionInstruction triggerInstructionCode) {

	}

}

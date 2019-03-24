package br.com.ilegra.tech_ninja.scheduler;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import br.com.ilegra.tech_ninja.service.DataLoaderJob;

public class QuartzConfig {
	
	private static final Integer INTERVAL = 5;

	private static Scheduler scheduler;
	
	public static void dataProcessSchedulerRunner() throws Exception {
        scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
 
        Trigger trigger =  buildTrigger();
        scheduleJob(trigger);
    }
 
    private static void scheduleJob(Trigger trigger) throws Exception {

    	JobDetail someJobDetail = JobBuilder.newJob(DataLoaderJob.class)
        		.withIdentity("data-loader-job", "group1")
        		.build();
 
        scheduler.scheduleJob(someJobDetail, trigger);
    }
 
    private static Trigger buildTrigger() {
 
        Trigger trigger = TriggerBuilder
        		.newTrigger()
        		.withIdentity("data-loader-trigger", "group1")
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(INTERVAL)
                        .repeatForever())
                .build();
        return trigger;
    }
 
}

package com.bdqn.quartz;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;
import org.quartz.SchedulerException;

public class DatabaseBackupJob implements Job {
	
	public static void main(String[] args) throws Exception{
//      jobDataMap();
      databaseCurrentJob();

}
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		 JobDetail detail = context.getJobDetail();
	        String database = detail.getJobDataMap().getString("database");
	         
	        System.out.printf("给数据库 %s 备份, 耗时10秒 %n" ,database);
	        try {
	            Thread.sleep(10000);
	        } catch (InterruptedException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	}
	
	private static void databaseCurrentJob() throws Exception {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
          
        Trigger trigger = newTrigger().withIdentity("trigger1", "group1")
            .startNow()
            .withSchedule(simpleSchedule()
                .withIntervalInSeconds(2)
                .withRepeatCount(10))
            .build();
  
        //定义一个JobDetail
        JobDetail job = newJob(DatabaseBackupJob.class)
            .withIdentity("backupjob", "databasegroup")
            .usingJobData("database", "how2java")
            .build();
         
        //调度加入这个job
        scheduler.scheduleJob(job, trigger);
  
        //启动
        scheduler.start();
          
        //等待200秒，让前面的任务都执行完了之后，再关闭调度器
        Thread.sleep(200000);
        scheduler.shutdown(true);      
    }
 
    private static void jobDataMap() throws SchedulerException, InterruptedException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
  
        Trigger trigger = newTrigger().withIdentity("trigger1", "group1")
            .startNow()
            .withSchedule(simpleSchedule()
                .withIntervalInSeconds(2)
                .withRepeatCount(10))
            .build();
  
        //定义一个JobDetail
        JobDetail job = newJob(MailJob.class)
            .withIdentity("mailjob1", "mailgroup")
            .usingJobData("email", "admin@10086.com")
            .build();
         
        //用JobDataMap 修改email
        job.getJobDataMap().put("email", "weather67@163.com");
         
        //调度加入这个job
        scheduler.scheduleJob(job, trigger);
  
        //启动
        scheduler.start();
          
        //等待20秒，让前面的任务都执行完了之后，再关闭调度器
        Thread.sleep(20000);
        scheduler.shutdown(true);
    }

}

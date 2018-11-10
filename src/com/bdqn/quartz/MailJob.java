package com.bdqn.quartz;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;


public class MailJob implements Job {
	
	 public static void main(String[] args) throws Exception{
         //创建调度器
         Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

         //定义一个触发器
         Trigger trigger = newTrigger().withIdentity("trigger1", "group1") //定义名称和所属的组
             .startNow()
             .withSchedule(simpleSchedule()
                 .withIntervalInSeconds(2) //每隔2秒执行一次
                 .withRepeatCount(10)) //总共执行11次(第一次执行不基数)
             .build();

         //定义一个JobDetail
         JobDetail job = newJob(MailJob.class) //指定干活的类MailJob
             .withIdentity("mailjob1", "mailgroup") //定义任务名称和分组
             .usingJobData("email", "weather67@163.com") //定义属性
             .build();
          
         //调度加入这个job
         scheduler.scheduleJob(job, trigger);

         //启动
         scheduler.start();
          
         //等待20秒，让前面的任务都执行完了之后，再关闭调度器
         Thread.sleep(20000);
         scheduler.shutdown(true);
 }

	 public void execute(JobExecutionContext context) throws JobExecutionException {
	        JobDetail detail = context.getJobDetail();
	        String email = detail.getJobDataMap().getString("email");
	         
	        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	        String now = sdf.format(new  Date());
	         
	        System.out.printf("给邮件地址 %s 发出了一封定时邮件, 当前时间是: %s%n" ,email, now);
	    }
	
	

}

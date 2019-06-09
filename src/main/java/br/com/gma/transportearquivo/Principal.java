package br.com.gma.transportearquivo;

import java.util.List;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

//https://www.codejava.net/java-se/ftp/java-ftp-list-files-and-directories-example
//https://www.codejava.net/java-se/ftp/java-ftp-file-download-tutorial-and-example
//https://medium.com/@pedholiveira/agendando-tarefas-em-java-com-quartz-b596435be856
public class Principal {

	public static void main(String[] args) {
		 
		System.out.println("Inicio: "+Principal.class.getName());

		 SchedulerFactory shedFact = new StdSchedulerFactory();
         try {
                Scheduler scheduler = shedFact.getScheduler();
                scheduler.start();
                JobDetail job = JobBuilder.newJob(MyJob.class)
                              .withIdentity("tranparqjob", "grupounico")
                              .build();
                Trigger trigger = TriggerBuilder.newTrigger()
                              .withIdentity("tranparqjobTRIGGER","grupounico")
                              .withSchedule(CronScheduleBuilder.cronSchedule("0/59 * * * * ?"))
                              .build();
                scheduler.scheduleJob(job, trigger);
         } catch (SchedulerException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
         }
	}

}

package main.java.NotificationSystem;

import java.text.ParseException;
import java.time.LocalDate;

import org.quartz.CronExpression;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.mysql.cj.x.protobuf.Mysqlx.ClientMessages.Builder;

import main.java.Database.IDBConnectionPoint;

public class BirthdayNotifier {
    
    // the class invoked to send birthday emails to everyone who should receive a notification on todays date

    public BirthdayNotifier(){
        // nothing to setup, all work done in JobEmail
    }


    /**
     * The function in charge of scheduling the emails for the day
     */
    public void scheduleDailyEmails(){
        try {

            JobDetail emailJob = JobBuilder.newJob(JobEmail.class)
            .withIdentity("emailJob", "group").build();

            // build cron date expression for 8am of the date the notifications are to be sent
            String[] bdDateArr = LocalDate.now().toString().split("-");
            String currDay = bdDateArr[2];
            String currMonth = bdDateArr[1];
            String currYear = bdDateArr[0];
            String cronDate = "0 45 7 " + currDay + " " + BirthdayNotifier.convertStrToMonth(currMonth) + " ? " + currYear;
            // test
            // String cronDate = "0/30 * * * * ?";
            Trigger trigger = TriggerBuilder.newTrigger()
            .withIdentity("cronTrigger", "group")
            .withSchedule(CronScheduleBuilder.cronSchedule(new CronExpression(cronDate)))
            .build();

            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(emailJob, trigger);

            Thread.sleep(1000000);
            scheduler.shutdown();

        } catch (Exception e){
            System.out.println("Error in scheduling of email");
            e.printStackTrace();
        }

    }


    /**
     * Helper to return correct month strings given an int (to display months in calendar page)
     * 
     * TO BE CALLED: line before moving onto calendar page (both button and nav)
     * 
     * @param month     the string value of a month in "mm" (01-12)
     * @return          the string value corresponding to the month index
     */
    public static String convertStrToMonth(String month) {
        switch (month) {
            case "01":
                return "JAN";
            case "02":
                return "FEB";
            case "03":
                return "MAR";
            case "04":
                return "APR";
            case "05":
                return "MAY";
            case "06":
                return "JUN";
            case "07":
                return "JUL";
            case "08":
                return "AUG";
            case "09":
                return "SEP";
            case "10":
                return "OCT";
            case "11":
                return "NOV";
            default:
                return "DEC";
        }
    }
}

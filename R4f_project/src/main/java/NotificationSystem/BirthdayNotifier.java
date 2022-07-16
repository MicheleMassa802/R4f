package main.java.NotificationSystem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

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
    
    private static final int START_HOUR = 7;
    private static final int START_MINUTE = 45;
    private static final int START_SECOND = 0;
    private static final int DFLT_GAP = 30;

    // the class invoked to send birthday emails to everyone who should receive a notification on todays date

    public BirthdayNotifier(){
        // nothing to setup, all work done in JobEmail
    }


    /**
     * The function in charge of scheduling the emails for the day
     */
    public void scheduleDailyEmails(){
        Date currentTime = new Date();
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");  
        String[] timeArray = timeFormat.format(currentTime).toString().split(":");
        int hour = Integer.parseInt(timeArray[0]);
        int minute = Integer.parseInt(timeArray[1]);
        int seconds = Integer.parseInt(timeArray[2]);

        if (hour < START_HOUR || ((hour == START_HOUR) && (minute < START_MINUTE))){
            // try to schedule job
            
            try {
                JobDetail emailJob = JobBuilder.newJob(JobEmail.class)
                .withIdentity("emailJob", "group").build();

                // build cron date expression for 8am of the date the notifications are to be sent
                String[] bdDateArr = LocalDate.now().toString().split("-");
                String currDay = bdDateArr[2];
                String currMonth = bdDateArr[1];
                String currYear = bdDateArr[0];
                // program must be open and start running before the default start time so that cron trigger works, runnning program after => error 
                String cronDate = START_SECOND + " " + START_MINUTE + " " + START_HOUR + " " + currDay + " " + BirthdayNotifier.convertStrToMonth(currMonth) + " ? " + currYear;
                Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("cronTrigger", "group")
                .withSchedule(CronScheduleBuilder.cronSchedule(new CronExpression(cronDate)))
                .build();

                Scheduler scheduler = new StdSchedulerFactory().getScheduler();
                scheduler.scheduleJob(emailJob, trigger);  // schedule job with trigger
                scheduler.start();  // start scheduler to run notification system

                // wait long enough to let scheduler run job which starts at start time + give it default gap extra seconds

                // figure out # of seconds to sleep for ((Start time - current Time)[in secs] + default gap secs)
                int secondsToWait = DFLT_GAP + ((START_HOUR - hour) * 3600) + ((START_MINUTE - minute) * 60) + (START_SECOND - seconds);  // default gap + hour intv + min intv + sec intv

                try {
                    // sleep for necessary time
                    System.out.println("Waiting for " + secondsToWait + " seconds...");
                    Thread.sleep(secondsToWait * 1000);
                    
                } catch (Exception e) {}

                // shutdown after wait is over
                scheduler.shutdown(true);
                    
            } catch (Exception e){
                System.out.println("Error in scheduling of email");
                e.printStackTrace();
            }
        } else {
            // else, made it too late to the window
            System.out.println("Start time has already passed, emails for today can no longer be scheduled.");
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

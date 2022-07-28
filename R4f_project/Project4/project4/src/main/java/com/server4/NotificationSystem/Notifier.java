package com.server4.NotificationSystem;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.server4.Controllers.CalendarController;
import com.server4.Controllers.UserController;
import com.server4.Database.ExecuteQuery;
import com.server4.Database.IDBConnectionPoint;

public class Notifier {
    
    public Notifier(){
        // nothing to setup in constructor
    }

    /**
     * Function in charge of scheduling and sending all notification that need to be sent for a day (in the morning @7 am)
     * 
     * @param hourToSchedule    the hour at which the email notifications are to be sent at (in military time 0 - 24)
     * @param minToSchedule     the minute at which the email notifications are to be sent at (0-59)
     */
    public static void ScheduleNotification(int hourToSchedule, int minToSchedule){

        Timer timer = new Timer();

        TimerTask emailTask = new TimerTask() {
            
            @Override
            public void run(){
                
                // constants for DB access
                final String DB_TABLE = "users";

                // for all users who are to get notified, send notification email
                System.out.println("Email job --->>> Date and Time is: " + new Date());

                // get information here for emails to be sent
                // for user id in user table, get each of 
                IDBConnectionPoint userDbConnection = new ExecuteQuery(DB_TABLE);  // no column names since retrieval doesnt need them
                
                // get all userIds, and from that get all columns of users
                ArrayList<String> allUserIds = new ArrayList<>();
                try {
                    allUserIds = userDbConnection.executeRetrieve(null, null, 1, "user_id");
                } catch (SQLException e) {
                    System.out.println("Retrieval of all users failed! Class: JobEmail");
                    e.printStackTrace();
                }

                // info to be obtained and setup for each user
                CalendarController calController;
                UserController userController;
                ArrayList<String> bdIdsToNotify;
                ArrayList<String> userInfo;
                
                // for each user, check their calendars to see if they have a birthday today
                System.out.println("USERS: " + allUserIds.toString());
                for (String userId : allUserIds){
                    System.out.println("USER ID: " + userId);
                    calController = new CalendarController(userId);
                    userController = new UserController();

                    userInfo = userController.getUserFromId(userId);  // username = 1, email = 9, calId = 8
                    
                    // setup cal Id and execute BD check
                    calController.setCalendarId(userInfo.get(8));
                    bdIdsToNotify = calController.executeNotificationCheck();
                    System.out.println("BD IDS: " + bdIdsToNotify.toString());

                    for (String bdId : bdIdsToNotify){
                        System.out.println("Email sending with: " + bdId);
                        SendEmail emailSender = new SendEmail(userInfo.get(1), userInfo.get(9), bdId);
                        emailSender.executeSend();
                    }
                }

                System.out.println("Task Completed");
            }
        };

        // setup date and time to schedule task
        String[] notificationDate = LocalDate.now().toString().split("-");

        Calendar date = Calendar.getInstance();
        date.set(Calendar.YEAR, Integer.parseInt(notificationDate[0]));
        date.set(Calendar.MONTH, (Integer.parseInt(notificationDate[1]) - 1));
        date.set(Calendar.DAY_OF_MONTH, Integer.parseInt(notificationDate[2]));
        date.set(Calendar.HOUR_OF_DAY, hourToSchedule);
        date.set(Calendar.MINUTE, minToSchedule);
        // secs and millisecs set by default to 0
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        System.out.println("Sending emails for date: " + date.getTime());
        timer.schedule(emailTask, date.getTime());
    }
}

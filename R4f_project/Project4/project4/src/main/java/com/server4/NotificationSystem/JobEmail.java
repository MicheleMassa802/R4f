// package com.server4.NotificationSystem;

// import java.sql.SQLException;
// import java.util.ArrayList;
// import java.util.Date;

// import org.quartz.Job;
// import org.quartz.JobExecutionContext;
// import org.quartz.JobExecutionException;


// import com.server4.Controllers.CalendarController;
// import com.server4.Controllers.UserController;
// import com.server4.Database.ExecuteQuery;
// import com.server4.Database.IDBConnectionPoint;

// public class JobEmail  implements Job{
//     // class in charge of checking for users to send notification emails to 

//     // db constants (table name and columns)
//     private final String DB_TABLE = "users";

//     // the class invoked to send notification emails the second someones opens up a R4F session

//     public void execute(JobExecutionContext context) throws JobExecutionException {
//         System.out.println("Email job --->>> Date and Time is: " + new Date());

//         // get information here for emails to be sent
//         // for user id in user table, get each of 
//         IDBConnectionPoint userDbConnection = new ExecuteQuery(DB_TABLE);  // no column names since retrieval doesnt need them
        
//         // get all userIds, and from that get all columns of users
//         ArrayList<String> allUserIds = new ArrayList<>();
//         try {
//             allUserIds = userDbConnection.executeRetrieve(null, null, 1, "user_id");
//         } catch (SQLException e) {
//             System.out.println("Retrieval of all users failed! Class: JobEmail");
//             e.printStackTrace();
//         }
        
//         // info to be obtained and setup for each user
//         CalendarController calController;
//         UserController userController;
//         ArrayList<String> bdIdsToNotify;
//         ArrayList<String> userInfo;
        
//         // for each user, check their calendars to see if they have a birthday today
//         System.out.println("USERS: " + allUserIds.toString());
//         for (String userId : allUserIds){
//             System.out.println("USER ID: " + userId);
//             calController = new CalendarController(userId);
//             userController = new UserController();

//             userInfo = userController.getUserFromId(userId);  // username = 1, email = 9, calId = 8
            
//             // setup cal Id and execute BD check
//             calController.setCalendarId(userInfo.get(8));
//             bdIdsToNotify = calController.executeNotificationCheck();
//             System.out.println("BD IDS: " + bdIdsToNotify.toString());

//             for (String bdId : bdIdsToNotify){
//                 System.out.println("Email sending with: " + bdId);
//                 SendEmail emailSender = new SendEmail(userInfo.get(1), userInfo.get(9), bdId);
//                 emailSender.executeSend();
//             }
//         }
//     }
    
// }

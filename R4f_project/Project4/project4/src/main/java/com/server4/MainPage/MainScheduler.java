package com.server4.MainPage;

import com.server4.NotificationSystem.Notifier;

public class MainScheduler {
    
    public static void setScheduler() {
        
        // these two ints describe the time at which the emails will be sent (7:00 am by default)
        final int HOUR = 7;  // 0 - 23
        final int MINUTE = 0;  // 0 - 59

        // run email scheduler
        Notifier.ScheduleNotification(HOUR, MINUTE);;  // schedule the notification emails to be sent to at the input hour and minutes
    }
}

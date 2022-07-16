package main.java.MainPage;

import main.java.NotificationSystem.BirthdayNotifier;

public class MainScheduler {
    
    public static void main(String[] args) {
        
        // run email scheduler
        BirthdayNotifier bdNotifier = new BirthdayNotifier();
        bdNotifier.scheduleDailyEmails();  // schedule the notification emails to be sent to
    }
}

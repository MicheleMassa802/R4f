package main.java.MainPage;

import java.util.ArrayList;

import main.java.NotificationSystem.BirthdayNotifier;

public class Main {
    public static void main(String[] args) {

        // R4F cmdLine program call
        CommandLineAndy cmdLineManager = new CommandLineAndy();
        cmdLineManager.welcomeLoop();

        // testing
        BirthdayNotifier bdNotifier = new BirthdayNotifier();
        bdNotifier.scheduleDailyEmails();  // schedule the notification emails to be sent to
        // ArrayList<String> list = new ArrayList<>();
        // System.out.println((list.toString() == "[]"));
        
    }
}

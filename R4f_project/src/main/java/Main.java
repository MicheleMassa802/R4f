package main.java;

import main.java.Helpers.SendEmail;

public class Main {
    public static void main(String[] args) {
        // main code here ...
        System.out.println("Email sending testing in progress");
        SendEmail sender = new SendEmail("admin", "massamichele802@gmail.com", "bd69420");
        sender.executeSend();
        System.out.println("End of test");
    }
}

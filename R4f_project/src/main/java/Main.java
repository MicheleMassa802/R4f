package main.java;

import main.java.Helpers.UseCaseMsgGenerator;

import java.sql.SQLException;
import java.util.ArrayList;

import main.java.Database.ExecuteQuery;
import main.java.Helpers.UseCaseMsgGenerator;

public class Main {
    public static void main(String[] args) {
        // main code here ...


        /** random message generation using the msg generator helper (testing of row retrieval) */

        //UseCaseMsgGenerator msgGen = new UseCaseMsgGenerator();
        //System.out.println("Random message generated: " + msgGen.generateMessage());


        /** message insertion into msg table */

        // String messages = "Happy birthday and many happy returns of the day. May all your wishes come true.\nHappy Birthday! May God bless you with everything you desire. Many many happy returns of the day.\nHappy Birthday! My heartfelt prayers will always be with you wherever you are.\nWishing you a very special birthday and a wonderful year ahead! Many many happy returns of the day.\nHappy Birthday! May this day brings about cherishing memories for the years to come!\nHappy birthday and many happy returns of the day.\nIts your day; spend it with the people who make you the happiest. Happy Birthday!\nLife is a journey. Let me be your guide all the way. Happy birthday!\nHappy Birthday! Wishing you buckets of love and uncountable gleeful moments!\nCheers to the paths you have crossed and the journeys to come! Happy Birthday!\nHappy Birthday! Let’s make a toast for a wittier, wiser, merrier you!\nMay all your wishes be granted when you blow out the candles today! Happy Birthday!\nMay your birthday be filled with immense joy and happiness. Happy Birthday to you!\nMay your birthday comes with all those things that your heart desires. A very happy birthday to you.\nHappy birthday. Wishing you a birthday full of happiness, health, and prosperity.\nAccept my heartfelt birthday wish with all my love. Happy birthday to you.\nWishing you a day full of fun and exciting memories! Happy birthday.\nHave a very happy birthday! May you accomplish great things in life.\nWish you a wonderful birthday full of love and warm wishes. Stay blessed!\nHappy birthday, dear! Live this day and every day to the fullest.\nMay God grant you love and happiness forever! Happy birthday!\nHappy birthday. I hope that each of your desires comes true. Live long and prosper!\nMuch love and blessings on your birthday, dear. Be happy, forever.\nWishing you a happy birthday, precious. Stay just the way you are!\nHappy Birthday! Youre special to us every day, so theres no surprise party for today!\nEvery year, I wish for you to be wiser, only to be disappointed later! Happy Birthday!\nIt looks like we dont have enough space on the cake to put the right number of candles! Happy Birthday!\nHappy Birthday! Focus more on the wonderful memories and less on your grey hair!\nThis is a very special day because on this day the bitterness of this world was balanced by the arrival of a sweet person. That person was you. Happy birthday!\nIts a sad thing to see so many people growing up so fast without growing wise. But I dont care as long as its my best friend. Happy birthday!\nIts another birthday of yours when I have to draw a fake smile on my face and wish you good things in life. Thats what I am doing right now. Happy birthday!\nYour height definitely received a boost, but your wisdom didnt! Happy birthday to you!\nHappy Birthday! You are aging just like fine wine! May your future days be full of laughter and adventures!\nYou have got a birthday wish from someone as special as me, what more can you ask for? Happy birthday!\nHappy Birthday! It looks like you dont need to be under adult supervision anymore!\nIf you knew what birthdays really mean, you would stop celebrating it. But as long as we all get to eat cakes and drink wines, nothing matters!\nA simple word of wisdom, The number wrinkles in your face is directly proportional to the number of birthdays you celebrate in life. Happy birthday dear!\nI think I should buy a sugar-free cake for you this time because I dont really think conventional sweet cakes are suitable for your age anymore. Happy birthday to you!\nAnother year older means another year wiser but look at you! Happy Birthday dearest dummy!\nBirthday means gifts and I gift you with greetings and well wishes because you are not worth of expenses. Just Kidding. Happy Birthday.";
        // String[] messageArray = messages.split("\n");


        // ExecuteQuery sql = new ExecuteQuery("msg_gen", "msg");
        
        // for (String msg : messageArray){
        //     System.out.println("Current message: " + msg);
        //     sql.executeInsert(msg);
        // }

        
        /** message replacement into msg table */

        // ExecuteQuery sql = new ExecuteQuery("msg_gen", "");
        // sql.executeUpdate("msg_id", "1", "msg", "Happy Birthday! Hope you get to enjoy your day to the fullest, and I wish you all the best :)");
        

        /** message retrieval column extraction test */
        ExecuteQuery sql = new ExecuteQuery("msg_gen", "");
        
        ArrayList<String> data = new ArrayList<>();
        try {
            // retrieve all existing usernames
            data = sql.executeRetrieve(null, null, 1, "msg_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Id column: " + data.toString());

        
    }
}

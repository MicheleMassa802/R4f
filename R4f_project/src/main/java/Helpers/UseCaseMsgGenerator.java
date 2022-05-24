package main.java.Helpers;

import java.util.ArrayList;
import java.util.Random;

public class UseCaseMsgGenerator {
    // randomly generating 4 congratulations messages from DB table
    private String result;
    private ArrayList<String> messages;  // gotten from the database

    /**
     * Constructor for initializing the random message generator, the user of this object doesnt need to 
     * provide anything, but just a call of the generator method will yield the randomized message.
     */
    public UseCaseMsgGenerator(){

        // pull default messages from the database table and store them inside the messages arraylist

    }

    public String generateMessage(){
        Random rand = new Random();
        int randIndex = rand.nextInt(this.messages.size());
        this.result = this.messages.get(randIndex);
        return this.result;
    }
}

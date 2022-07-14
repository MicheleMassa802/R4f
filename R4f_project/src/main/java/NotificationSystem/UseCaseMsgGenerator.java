package main.java.NotificationSystem;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import main.java.Database.ExecuteQuery;

public class UseCaseMsgGenerator {
    // randomly generating 4 congratulations messages from DB table
    private String result;
    private ArrayList<String> messages;  // gotten from the database
    private final int NUM_OF_ROWS = 41;  // 1 through 41 messages available

    /**
     * Constructor for initializing the random message generator, the user of this object doesnt need to 
     * provide anything, but just a call of the generator method will yield the randomized message.
     */
    public UseCaseMsgGenerator(){
        ExecuteQuery sql = new ExecuteQuery("msg_gen");
        
        // pull all default messages from the database table and store them inside the messages arraylist
        this.messages = new ArrayList<>();
        
        String msg = "";
        int i;
        for (i = 1; i <= NUM_OF_ROWS; i++){
            // get the single column using index 1 from msg table (index 0: msg_id, index 1: msg)
            // compare based on msg_id column to the current i, extracting 2 columns
            try {
                msg = sql.executeRetrieve("msg_id", Integer.toString(i), 2, null).get(1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            this.messages.add(msg);
            System.out.println("Current message being pulled: " + msg);
        }
    }

    public String generateMessage(){
        Random rand = new Random();
        int randIndex = rand.nextInt(this.messages.size());
        this.result = this.messages.get(randIndex);
        return this.result;
    }
}

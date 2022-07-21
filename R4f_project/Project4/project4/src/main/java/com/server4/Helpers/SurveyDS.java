package com.server4.Helpers;

import java.util.HashMap;

public class SurveyDS {
    // the data structure for the survey entity filled by users through the survey page

    // this object is always temporary and not stored in the databases beyond just its
    // information used to fill birthday objects, which are built from the main loop using the
    // birthday controller

    private String calendarId;  // calendar Id used to have bd objects refer to the correct calendar
    private HashMap<String, String> questions;  // question key : answer value
    private final String[] qConstants = {
        "0: What is the user ID of the person inviting you to fill this survey?",
        "1: What is your name?",
        "2: What is your last name?",
        "3: What is your birthday? [dd-mm-yyyy]",
        "4: What is your instagram username?",
        "5: What is your twitter username?",
        "6: What is your discord username?"
    };
    
    /**
     * Constructor for initializing the survey object with the default questions and a given reference calendar
     * @param calendarId reference to the calendar this survey is being filled for (for a bd object in that calendar)
     */
    public SurveyDS(String calendarId){
        this.calendarId = calendarId;

        this.questions = new HashMap<>();
        // fill in questions hashmap keys with empty values
        for (String qString : qConstants){
            this.questions.put(qString, "");
        }
    }

    /**
     * Set the answer to a question key given by qId in this survey object's questions attribute
     * @param qId       the id of a question according to the constants defined above
     * @param answer    the answer being provided to fill the value corresponding to the qId
     */
    public void setQuestionAnswer(int qId, String answer){
        for (String qString : this.questions.keySet()){
            String splitQ[] = qString.split(":");
            if (splitQ[0] == Integer.toString(qId)){
                this.questions.put(qString, answer);
            }
        }
    }

    /**
     * Get the answer to a question in the question hashmap
     * @param qId indicates the question for which we want an answer
     * @return the string answer for this question (the corresponding value to a key)
     */
    public String getQuestionAnswer(int qId){
        String answer = "";
        for (String qString : this.questions.keySet()){
            String splitQ[] = qString.split(":");
            if (splitQ[0] == Integer.toString(qId)){
                answer = this.questions.get(qString);
            }
        }
        return answer;
    }


    public String getCalendarId(){return this.calendarId;}

}

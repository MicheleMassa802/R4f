package com.server4.User;


public class UserEntity {
    // the actual user object

    private final String username;
    private String userId;
    private String password;
    private final String email;
    private int notificationType;
    private String userBdId;
    private String calendarId;


    /**
     * Constructor for initializing the User object through information provided through account linked surveys (SurveyDS object)
     * During the creation use case the SurveyDS results are extracted and provided for this, the BirthdayEntity and CalendarEntity
     * constructors
     * 
     * @param username              name used to refer to user
     * @param password              password used in sign in by user
     * @param email                 email used to refer to user 
     * @param notiType              notification setting for a birthday warning (1, 2, or 3 notifications throughout day)
     */
    public UserEntity(String username, String password, String email, int notiType) {
            // provided info
            this.username = username;
            this.password = password;
            this.email = email;
            this.notificationType = notiType;
            
            // objects and other attributes to be built simultaneously with the account creation provided through use case and setter functions
    }

    /**
     * Overwritting of toString method to display User object information
     */
    @Override
    public String toString() {
        String info = "Username: " + this.username + "\nUser ID: " + this.getUserId() + "\n Email: " + this.email +
            "\nNotification Type: " + this.notificationType;
            
        
        return info + "\nSocial Media Links:\n";
    }

    // getters and setters
    public String getUserId(){return this.userId;}
    public void setUserId(String userId){this.userId = userId;}
    public String getBdId(){return this.userBdId;}
    public void setBdId(String bdId){this.userBdId = bdId;}
    public String getCalendarId(){return this.calendarId;}
    public void setCalendarId(String calendarId){this.calendarId = calendarId;}

    public String getUsername(){return this.username;}
    public String getPswd(){return this.password;}

}

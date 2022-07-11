package main.java.Controllers;

import java.time.LocalDate;
import java.util.ArrayList;

import main.java.Calendar.CalendarEntity;
import main.java.Calendar.CreateCalendarUseCase;
import main.java.Calendar.ManageEventsUseCase;
import main.java.Calendar.NotifyEventUseCase;

public class CalendarController {
    // manages communication to the use cases for the Birthday object

    private final CreateCalendarUseCase calendarCreator;
    private ManageEventsUseCase calendarEventManager;
    private final NotifyEventUseCase calendarNotifier;
    // attributes to be setup upon creation of calendar
    public String userId;
    public String calendarId;


    /**
     * Calendar controller constructor, setting up use cases (that can be immediately setup)
     * @param userId    the id of the user logged in at the moment
     */
    public CalendarController(String userId){
        this.userId = userId;
        this.calendarCreator = new CreateCalendarUseCase(userId);
        this.calendarNotifier = new NotifyEventUseCase();
        this.calendarEventManager = null;
    }

    
    /**
     * Create a new empty calendar for a user who just logged in for the first time
     */
    public void executeCreateCalendar(){
        this.calendarId = this.calendarCreator.runCalendarCreation();
        // upon getting a calendarId, setup the event manager use case
        this.setCalendarEventManager(this.calendarId);
    }


    /**
     * Setup the event manager use case
     * @param calId the id of the calendar to manage events for
     */
    public void setCalendarEventManager(String calId){
        this.calendarEventManager = new ManageEventsUseCase(calId, this.userId);
    }


    /**
     * Create a calendar given its id
     * This method is used to create calendars after a user's firts login and calendar creation
     * @param calId the id of the calendar corresponding to this user
     */
    public CalendarEntity getCalFromId(String calId){
        this.calendarCreator.createCalendarFromId(calId);
        return this.calendarCreator.calendarInstance;

    }


    /**
     * Add or remove a birthday to a calendar object at the correct date
     * This method should only be used after this.calendarEventManager is setup
     * @param date  date whose corresponding value is to be modified yyyy-mm-dd of current year
     * @param bdId  bdId to add/remove from calendar
     */
    public void modifyCalendarEvent(boolean addBd, String date, String bdId){
        
        if (this.calendarEventManager == null){
            System.out.println("Error when modifying calendar events, Calendar event manager has not been setup");
            return;
        }
        // else
        
        if (addBd){
            // if addBd is true, bdId is added to calendar, else its removed from calendar
            this.calendarEventManager.addBirthday(date, bdId);
        } else {
            this.calendarEventManager.removeBirthday(date, bdId);
        }

    }


    /**
     * Generate the list of notifications for the current day
     * @return notification array to the list of notifications for the day
     */
    public ArrayList<String> executeNotificationCheck(){
        return this.calendarNotifier.notifyBdsPresent(LocalDate.now().toString(), this.calendarId);
 
    }



}

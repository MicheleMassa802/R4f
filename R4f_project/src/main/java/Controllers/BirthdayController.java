package main.java.Controllers;

import java.util.ArrayList;

import main.java.Birthday.BirthdayEntity;
import main.java.Birthday.CreateBirthdayUseCase;
import main.java.Helpers.UseCaseAccessSML;

public class BirthdayController {
    // manages communication to the use cases for the User object

    private final UseCaseAccessSML smlCreator;
    private final CreateBirthdayUseCase bdCreator;
    // attributes to be setup upon bd creation
    public String bdId;
    public String smlId;


    /**
     * Birthday controller constructor, setting up the use cases
     */
    public BirthdayController(){
        this.smlCreator = new UseCaseAccessSML();
        this.bdCreator = new CreateBirthdayUseCase();

    }

    /**
     * Setup the sml table to get the sml_id to setup the birthday object creation
     * @param ig    the instagram link
     * @param twt   the twitter link
     * @param dsc   the discord link
     */
    public void executeCreateSML(String ig, String twt, String dsc){
        this.smlId = this.smlCreator.insertLinks(ig, twt, dsc);
    }


    /**
     * Feeds birthday creation information to user case to execute creation steps
     * @param bd        the birthday date in yyyy-mm-dd
     * @param name      name of user
     * @param lastName  last name of user
     */
    public void executeCreateBirthday(String bd, String name, String lastName){
        this.bdId = this.bdCreator.runBdCreation(bd, name, lastName, this.smlId);

    }


    /**
     * Given a bdId, return a birthday entity containning all information needed wrt a birthday
     * @param bdId  the identifier for the birthday object (should be gotten from a calendar's bdIdList)
     * @return      the birthday entity with all its needed info
     */
    public BirthdayEntity getBdFromID(String bdId){
        this.bdCreator.createBirthdayFromID(bdId);
        return this.bdCreator.bdInstance;
    }

}

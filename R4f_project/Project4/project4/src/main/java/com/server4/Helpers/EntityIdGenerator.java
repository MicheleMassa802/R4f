/**
 * This class has now gone into maintenance state, as for the moment being, ids are going to be done through SQL and their auto
 * incremented integer system
 * 
 * Update: This class is no longer of use to the application, however, it will be left here as backup just in case its needed in the 
 * future
 */
package com.server4.Helpers;

public class EntityIdGenerator {
    // class for actual id generator functions where you implement the interface functions

    private int currBirthdayIdNum;
    private int currCalendarIdNum;
    private int currUserIdNum;


    /**
     * Constructor for the entity ID generator object
     * @param bd,cal,user   the current values as gotten from the database to use as the numerical part of a new entity's ID
     */
    public EntityIdGenerator(int bd, int cal, int user){
        this.currBirthdayIdNum = bd;
        this.currCalendarIdNum = cal;
        this.currUserIdNum = user;
    }

    /**
     * Generates a string entity Id based on the entity code fed ('B', 'C', 'U')
     * In the case of a YearDS structure, its id is just: YC<num> (a Y prefixing a calendar ID)
     * 
     * @param entityCode        char to prefix the resulting code
     * @return                  string representing a new valid entity Id
     */
    String generateEntityId(char entityCode){
        int currIdNumValue = getCurrentIdnum(entityCode);
        String result = entityCode + Integer.toString(currIdNumValue);
        return result;
    }

    /**
     * Helper to generateEntityId in charge of fetching the current entity id number to create based on the entity code from the
     * database and increases it for the next creation.
     * 
     * @param entityCode    the index into the database table used to find the current id number for a new entity
     * @return              the number associated to this entity code in the database
     */
    int getCurrentIdnum(char entityCode){
            switch (entityCode) {
                case 'B':
                    int temp1 = this.currBirthdayIdNum;
                    this.currBirthdayIdNum += 1;  // increment to reflect the increase of the new curr num in the database
                    return temp1;
                case 'C':
                    int temp2 = this.currCalendarIdNum;
                    this.currCalendarIdNum += 1;  // increment to reflect the increase of the new curr num in the database
                    return temp2;
                default:
                    int temp3 = this.currUserIdNum;
                    this.currUserIdNum += 1;  // increment to reflect the increase of the new curr num in the database
                    return temp3;
            }
    }
}

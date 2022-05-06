package main.java.Birthday;

import java.time.LocalDate;
import java.util.HashMap;

public class BirthdayEntity {
    // the actual birthday object

    private String birthdayId;
    private final LocalDate birthday;
    private final String name;
    private final String lastname;
    private HashMap<String, String> socialMediaLinks;


    /**
     * Constructor for initializing the Birthday object through the information provided through account linked
     * surveys (SurveyDS object).
     * 
     * @param day,month,year        representing the date of birth of a person
     * @param name                  real name of person
     * @param lastname              real last name of person
     * @param insta,twitter,snap    strings containning social media account names to link to user
     */
    public BirthdayEntity(int day, int month, int year, String name, String lastname,
        String insta, String twitter, String snap){
            this.name = name;
            this.lastname = lastname;
            this.socialMediaLinks = new HashMap<>();
            this.socialMediaLinks.put("Instagram", insta);
            this.socialMediaLinks.put("Twitter", twitter);
            this.socialMediaLinks.put("SnapChat", snap);
            this.birthday = LocalDate.of(year, month, day);
            // id String created alongside this object
    }


    /**
     * Overwritting of toString to display birthday object information
     */
    @Override
    public String toString() {
        String info = "Fullname: " + this.name + " " + this.lastname + "\nBirthday ID: " + this.birthdayId + 
            "\nBirthday: " + this.birthday.toString();
        
        StringBuilder smLinks = new StringBuilder();
    
        int i = 1;
        for (String link : this.socialMediaLinks.keySet()){
            smLinks.append("[Link ").append(i).append("] ").append(link).append("\n");
            i += 1;
        }

        return info + "\nSocial Media Links:\n" + smLinks;
    }


    // getters and setters
    public String getBirthdayId(){return this.birthdayId;}
    public void setBirthdayId(String bdId){this.birthdayId = bdId;}


}

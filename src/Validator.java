

/** an uninstantiable class which defines the Validator class
 * it just holds methods which are used in more than one class
 * Author: Ivan Segade Carou
  */

public class Validator {



         //public static String validateForm(Teacher person, String day, String month, String year){
    public static String validateForm(Teacher person, String[] date){
             String error = "";

        if (person.getName().equals("no specified") ||  !isLetter(person.getName()))
            error ="Enter a valid name";

        if (person.getSurname().equals("no specified") || !isLetter(person.getSurname()))
            error +="Enter a valid surname";

        if (person.getAddress().equals("no specified"))
            error +="\nEnter a address";

        if (person.getTown().equals("no specified") || !isLetter(person.getTown()))
            error +="\nEnter a valid town";


        if (person.getCounty().equals("no specified") || !isLetter(person.getCounty()))
            error +="\nEnter a county";


        if ( person.getPhone().length() == 10 &&  isNumber(person.getPhone())){
                if (!person.getPhone().startsWith("08") && !person.getPhone().startsWith("06"))
                    error += "\naaaaanPlease enter a valid phone number";
            }else
                error += "\nPlease enter a valid phone number";

        if (person.getEmail().equals("no specified"))
            error += "\nPlease, enter a valid email";

         if (!checkDate(date[0], date[1], date[2]))
             error += "\nEnter a valid Date of Birth";

        if (person.getDepartment().length() < 5 || person.getDepartment().equals("no specified"))
        error += "\nEnter a valid department";


        return error;
    } // end validate form


    /** method which vlaidates if a string has only letters
     *
     * @param enter
     * @return
     */
    public static  boolean isLetter(String enter){
        int x = 0;

        while (x<enter.length()){
            if (Character.isDigit(enter.charAt(x)))
                x = enter.length() +1;
            else
                x++;
        } // end while

        if (x>enter.length())
            return false;

        return true;
    } // end is letter


    /** method which checks if the string is just number
     *
     * @param enter
     * @return
     */

    public static  boolean isNumber(String enter){
        int x = 0;

        while (x<enter.length()){
            if (!Character.isDigit(enter.charAt(x)))
                x = enter.length() +1;
            else
                x++;
        } // end while

        if (x>enter.length())
            return false;

        return true;
    } // end is letter


    /** method which checks the date entered is correct
     *
     * @param myDay
     * @param myMonth
     * @param myYear
     * @return
     */

    public static  boolean checkDate(String myDay, String myMonth, String myYear) {
        boolean correct = true;

        if (!myDay.equals("Day") && !myMonth.equals("Month") && !myYear.equals("Year"))
        {
            int month = Integer.parseInt(myMonth);
            int day =Integer.parseInt(myDay);
            int year = Integer.parseInt(myYear);

            switch (month) {
                case 4:
                case 6:
                case 9:
                case 11:
                    if (day == 31)
                        correct = false;

                    break;

                case 2:

                    if ((year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0))) {
                        if (day > 29)
                            correct = false;
                    }else
                    if (day >28)
                        correct = false;

            }// end switch
        } else
            correct = false;

        return correct;
    } //end check date



} // end class
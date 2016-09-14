import javax.swing.*;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.*;

public class CalendarDriver
{

        public static void main(String[] args)
        {

                JFrame calendarMenu = createMenu();


                // All calendar months, weeks, and days from August to May
                // all twelve months are listed for ease in remembering indices
                String[] allMonths = new String[] {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"};


                // first week is the week that contains Monday August 1st, and the last week is the week that contains Friday May 12th
                String[][] allAcademicWeeks = new String[41][7];


                /*
                // array of school year weeks and days (the 31st slot will remain as null for the months with only 30 days)
                String[][] academicYear = new String[10][31];

                // initializes academicYear two-dimensional array
                for(int A = 0 ; A < academicYear.length ; A++)
                {
                        for(int B = 0 ; B < academicYear[A].length ; B++)
                        {
                                academicYear[A][B] = null;
                        }
                }
*/


                /*****
                        Days Array
                *****/
                // will contain all dates between August 1st and May 31st (inclusive) in string form
                String[] allAcademicDays = new String[306];

                // for filling the allAcademicDays array
                int curDayIndex = 0;


                // will turn all calendar dates (outside of June and July) into strings in the form DDMMMM for September and DDMMM for the other months


                for(int A = 1 ; A <= 12 ; A++)
                {
                        if(A != 6 && A != 7)
                        {
                                String curMonthName = allMonths[A-1];
                                String curDate = "";

                                for(int B = 1 ; B <= 30 ; B++)
                                {

                                        // adds a zero to the beginning of the number string so that it has two digits representing the date
                                        if(B < 10)
                                        {
                                                curDate = "0" + Integer.toString(B) + curMonthName;
                                        }
                                        else
                                        {
                                                curDate = Integer.toString(B) + curMonthName;
                                        }

                                        // adds the date to an array of strings
                                        allAcademicDays[curDayIndex] = curDate;
                                        curDayIndex++;

                                }

                                // for months with 31 days
                                if( A == 1 || A == 3 || A == 5 || A == 8 || A == 10 || A == 12 )
                                {
                                        curDate = Integer.toString(31) + curMonthName;
                                        allAcademicDays[curDayIndex] = curDate;
                                        curDayIndex++;
                                }

                        }
                }



                /*****
                        Section for Testing Portions of Code
                *****/

                System.out.println("Printing Days Array:");

                for(int A = 0 ; A < allAcademicDays.length ; A++)
                {
                        System.out.println(allAcademicDays[A]);
                }


        }










        public static JFrame createMenu()
        {
                JFrame menu = new JFrame("Calendar");

                JMenuBar menuBar = new JMenuBar();

                return(menu);


        }







}


/*************************************************************************
                                DO NOT DELETE


        Calendar Dates:

        * 42 weeks from August 1st to May 12th




                                DO NOT DELETE
*************************************************************************/

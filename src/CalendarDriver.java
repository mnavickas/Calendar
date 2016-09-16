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


                // All calendar months, weeks, and days from August to May --> (length of 10)
                String[] allAcademicMonths = new String[] {"Aug", "Sept", "Oct", "Nov", "Dec", "Jan", "Feb", "Mar", "Apr", "May"};

                // first week is the week that contains Monday August 1st, and the last week is the week that contains Friday May 12th
                String[][] allAcademicWeeks = new String[44][7];

                // initializes weeks array
                for(int A = 0 ; A < allAcademicWeeks.length ; A++)
                {
                        for(int B = 0 ; B < allAcademicWeeks[A].length ; B++)
                        {
                                allAcademicWeeks[A][B] = null;
                        }
                }

                // will contain all dates between August 1st and May 31st (inclusive) in string form
                String[] allAcademicDays = new String[306];




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

                // for filling the allAcademicDays array
                int curDayIndex = 0;


                // will turn all calendar dates (outside of June and July) into strings in the form DDMMMM for September and DDMMM for the other months


                for(int A = 0 ; A < allAcademicMonths.length ; A++)
                {

                        String curMonthName = allAcademicMonths[A];
                        String curDate = "";

                        // first for loop limited to 28 days for February 2017
                        for(int B = 1 ; B <= 28 ; B++)
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

                        // to fill the months with at least 30 days
                        if( curMonthName != "Feb" )
                        {
                                curDate = Integer.toString(29) + curMonthName;
                                allAcademicDays[curDayIndex] = curDate;
                                curDayIndex++;

                                curDate = Integer.toString(30) + curMonthName;
                                allAcademicDays[curDayIndex] = curDate;
                                curDayIndex++;
                        }

                        // for months with 31 days
                        if( curMonthName == "Aug" || curMonthName == "Oct" || curMonthName == "Dec" || curMonthName == "Jan" || curMonthName == "Mar" || curMonthName == "May" )
                        {
                                curDate = Integer.toString(31) + curMonthName;
                                allAcademicDays[curDayIndex] = curDate;
                                curDayIndex++;
                        }

                }




                /*****
                        Weeks Array
                *****/

                curDayIndex = 0;
                for(int A = 0 ; A < allAcademicWeeks.length ; A++)
                {
                        for(int B = 0 ; B < allAcademicWeeks[A].length ; B++)
                        {
                                // skips over July 31st, 2016, which lies on a Sunday
                                if(A == 0 && B == 0) {;}

                                else if(curDayIndex < allAcademicDays.length)
                                {
                                        allAcademicWeeks[A][B] = allAcademicDays[curDayIndex];
                                        curDayIndex++;
                                }
                        }
                }






                /*****
                        Section for Testing Portions of Code
                *****/

                System.out.println("Printing Days Array:");

                for(int A = 0 ; A < allAcademicWeeks.length ; A++)
                {
                        System.out.println("\n\nWeek: " + A);
                        for(int B = 0 ; B < allAcademicWeeks[A].length ; B++)
                        {
                                System.out.print(allAcademicWeeks[A][B] + ", ");
                        }
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

        * 44 weeks from August 1st to May 31st
        * Note: July 31st is set to null
        * Calendar work should start with week three (allAcademicWeeks index two)




                                DO NOT DELETE
*************************************************************************/

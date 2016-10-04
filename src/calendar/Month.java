package calendar;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Data type for a month.
 */
public class Month {
    private Calendar cal;

    private static String[] monthNames = { "January", "Feburary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };

    /**
     * Construct a week from its first day.
     * @param year the year number
     * @param month the month number
     */ 
    public Month(int year, int month) {
	cal = new GregorianCalendar(year, month, 1);
    }

    /**
     * Construct a month with an available cal value.
     * @param cal The calendar to use as the start of the month.
     */
    public Month(Calendar cal) {
	this.cal = cal;
    }

    /**
     * Convert the week to a string.
     * @return string rep of week
     */
    public String toString() {
	return monthNames[cal.get(Calendar.MONTH)];
    }

    /**
     * The integer value of the first day of the month.
     * 0 for January, 11 for December, etc.
     * @return the integer value described above
     */
    public int getFirstDayOfMonth() {
	return cal.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * The integer value of the year.
     * @return year number
     */
    public int getYear() {
	return cal.get(Calendar.YEAR);
    }

    /**
     * Get the month number to use.
     * @return month number
     */
    public int getMonthNum() {
	return cal.get(Calendar.MONTH);
    }

    /**
     * get the previous month
     * @return the next month
     */
    public Month getNextMonth() {
	Calendar newCal = (Calendar) cal.clone();
	newCal.add(Calendar.MONTH, 1);
	return new Month(newCal);
    }

    /**
     * get the previous month
     * @return the previous month
     */
    public Month getPreviousMonth() {
	Calendar newCal = (Calendar) cal.clone();
	newCal.add(Calendar.MONTH, -1);
	return new Month(newCal);
    }
}

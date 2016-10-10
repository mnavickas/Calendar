package calendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Day class
 *
 * "Day" represents a single day. It determines where on the calendar to put
 * each day and how to read/write to the database. Each "Day" has a
 * corresponding DayView that handles user interface elements.
 */
public class Day {
    // member variables
    private Calendar cal;

    /**
     * Instantiate a Day.
     * @param cal The java.util.Calendar object to use for that day.
     *            Seconds and hours are ignored.
     */
    public Day(Calendar cal)
    {
	this.cal = cal;
    }

    /**
     * Get the agenda for this day.
     * @return String The agenda
     */
    public String getAgenda() {
	return null;
    }

    /**
     * Set the agenda for this day.
     * @param agenda The agenda to set
     */
    public void setAgenda(String agenda) {
	
    }

    /**
     * Get the day's number in the current month.
     * This is used by the day view to display each numbered day.
     * @return int This returns the day's number in the month.
     */
    public int getDayNumber() {
	return cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Get the string for the date.
     * @return date in string form
     */
    public String toString() {
	// http://stackoverflow.com/questions/7317455/converting-calendar-date-to-string
	return new SimpleDateFormat("dd-MMM-yyyy").format(this.cal.getTime());
    }

    /**
     * Get the date.
     * @return the date of this day
     */
    public java.sql.Date getDate() {
	// http://stackoverflow.com/questions/3574811/how-can-i-get-a-date-from-my-calendar
	return new java.sql.Date(cal.getTime().getTime());
    }


    // used for get day name
    private String[] months = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };


    /*
     * Get the name of the week (Sunday, Monday, Tuesday...)
     * @return The name of the week in string form.
     */
    public String getDayName() {
	return months[this.cal.get(Calendar.DAY_OF_WEEK) - Calendar.SUNDAY];
    }
}

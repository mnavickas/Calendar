package calendar;

import java.util.Calendar;

/**
 * Date type for a week. 
 */
public class Week {
    private Calendar cal;

    private Day[] days;

    /**
     * Construct a week from its first day.
     * @param cal the Calendar date to use.
     */ 
    public Week(Calendar cal) {
	this.cal = (Calendar) cal.clone();


	// initialize days
	days = new Day[7];

	// set the first day to the given calendar
	days[0] = new Day((Calendar) cal.clone());

	// add six more days
	for (int i = 1; i < 7; i++) {
	    cal.add(Calendar.DATE, 1);
	    days[i] = new Day((Calendar) cal.clone());
	}
    }

    /**
     * Get the days in this week.
     * @return A list of 7 days.
     */
    public Day[] getDays() {
	return days;
    }

    /**
     * Convert the week to a string.
     * @return string rep of week
     */
    public String toString() {
	return "Week of " + days[0] + " to " + days[6];
    }

    /**
     * get the previous week
     * @return the week previously
     */
    public Week getPreviousWeek() {
	Calendar newCal = (Calendar) cal.clone();
	newCal.add(Calendar.DAY_OF_YEAR, -7);
	return new Week(newCal);
    }

    /**
     * get the next week
     * @return the next week
     */
    public Week getNextWeek() {
	Calendar newCal = (Calendar) cal.clone();
	newCal.add(Calendar.DAY_OF_YEAR, 7);
	return new Week(newCal);
    }
}

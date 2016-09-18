import java.util.Calendar;

/*
 * Date type for a week. 
 */
public class Week {
    private Day[] days;

    /*
     * Construct a week from its first day.
     * @param cal the Calendar date to use.
     */ 
    public Week(Calendar cal) {
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

    /*
     * Get the days in this week.
     * @return A list of 7 days.
     */
    public Day[] getDays() {
	return days;
    }
}
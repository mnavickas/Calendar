import java.util.Calendar;

public class Week {
    public Day[] days;

    // Calendar cal: first day of week
    // creates a "week" from a calendar point
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
}

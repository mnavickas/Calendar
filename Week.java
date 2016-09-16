import java.util.Calendar;

public class Week {
    public Day[] days;

    // Calendar cal: first day of week
    public Week(Calendar cal) {
	days = new Day[7];
	days[0] = new Day((Calendar) cal.clone());
	for (int i = 1; i < 7; i++) {
	    cal.add(Calendar.DATE, 1);
	    days[i] = new Day((Calendar) cal.clone());
	}
    }
}

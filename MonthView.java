import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

import java.util.Calendar;
import java.util.GregorianCalendar;

// Month view shows a month
public class MonthView extends JPanel {
    // used for header
    String[] dayNames = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
    String[] monthNames = { "January", "Feburary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };

    // contains all days in the month
    DayView[] days;

    public String monthName;

    // temp for testing
    public static void main(String[] args) {
	initialize(2016, Calendar.AUGUST);
    }

    public static void initialize(int year, int month) {
	MonthView view = new MonthView(year, month);
	JFrame frame = new JFrame(view.monthName);
	frame.add(view, BorderLayout.CENTER);
	frame.pack();
	frame.setVisible(true);
    }

    // MonthView is a panel for showing the month
    // int year: current year
    // int month: the month, 0 being Jan, 11 being Dec
    public MonthView(int year, int month) {
	super(new BorderLayout());

	// set up a calendar from the given year, month
	Calendar calendar = new GregorianCalendar(year, month, 1);
	int dayOfMonth = calendar.get(Calendar.DAY_OF_WEEK);

	this.monthName = monthNames[calendar.get(Calendar.MONTH)];
	JLabel label = new JLabel(monthName);
	add(label, BorderLayout.NORTH);

	JPanel panel1 = new JPanel(new BorderLayout());

	// set up the header to use
	JPanel panel2 = new JPanel(new GridLayout(1, 7));
	for (String dayName : dayNames) {
	    panel2.add(new JLabel(dayName));
	}
	panel1.add(panel2, BorderLayout.NORTH);

	// set up the 5x7 grid
	// a day view is added for each (even those not in the month!)
	JPanel panel3 = new JPanel(new GridLayout(5, 7));
	days = new DayView[35];
	for (int i = 0; i < 35; i++) {
	    Calendar cal = new GregorianCalendar(year, month, 2 + i - dayOfMonth);
	    days[i] = new DayView(new Day(cal));

	    // this day is not in the month!
	    if (month != cal.get(Calendar.MONTH)) {
		days[i].setBackground(Color.lightGray);
	    }

	    panel3.add(days[i]);
	}
	panel1.add(panel3, BorderLayout.CENTER);

	add(panel1, BorderLayout.CENTER);
    }
}

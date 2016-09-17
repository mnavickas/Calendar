import java.awt.GridLayout;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

import java.util.Calendar;
import java.util.GregorianCalendar;

// represents a 7 day period
public class WeekView extends JPanel {
    // used for header
    String[] months = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };

    // week object used to display
    Week week;

    // temp for testing
    public static void main(String[] args) {
	JFrame frame = new JFrame("Week View");
	Calendar firstDay = new GregorianCalendar(2016, Calendar.SEPTEMBER, 2);
	Week week = new Week(firstDay);
	WeekView view = new WeekView(week);
	frame.add(view, BorderLayout.CENTER);
	frame.pack();
	frame.setVisible(true);
  frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //http://stackoverflow.com/questions/258099/how-to-close-a-java-swing-application-from-the-code

    }

    public static void initialize(int year, int month, int day) {
	JFrame frame = new JFrame("Day View");
	Calendar firstDay = new GregorianCalendar(year, month, day);
	Week week = new Week(firstDay);
	WeekView view = new WeekView(week);
	frame.add(view, BorderLayout.CENTER);
	frame.pack();
	frame.setVisible(true);
    }

    // WeekView is a panel for showing a 7day period
    // the week objects contains the 7 days to display
    public WeekView(Week week) {
	super(new BorderLayout());

	// setup a header with each month
	JPanel panel1 = new JPanel(new GridLayout(1, 7));
	for (String m : months) {
	    JLabel label = new JLabel(m);
	    panel1.add(label);
	}
	add(panel1, BorderLayout.NORTH);

	// setup a 7 grid pieces
	JPanel panel2 = new JPanel(new GridLayout(1, 7));
	for (Day day : week.days) {
	    DayView view = new DayView(day);
	    panel2.add(view);
	}
	add(panel2, BorderLayout.CENTER);
    }
}

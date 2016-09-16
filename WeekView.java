import java.awt.GridLayout;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class WeekView extends JPanel {
    String[] months = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };

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
    }

    // MonthView is a panel for showing the month
    // int year: current year
    // int month: the month, 0 being Jan, 11 being Dec
    public WeekView(Week week) {
	super(new BorderLayout());

	JPanel panel1 = new JPanel(new GridLayout(1, 7));
	for (String m : months) {
	    JLabel label = new JLabel(m);
	    panel1.add(label);
	}
	add(panel1, BorderLayout.NORTH);

	JPanel panel2 = new JPanel(new GridLayout(1, 7));
	for (Day day : week.days) {
	    DayView view = new DayView(day);
	    panel2.add(view);
	}
	add(panel2, BorderLayout.CENTER);
    }
}

import java.awt.GridLayout;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MonthView extends JPanel {
    String[] months = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };

    DayView[] days; // include days not in month for convenience

    // temp for testing
    public static void main(String[] args) {
	JFrame frame = new JFrame("Month View");
	MonthView view = new MonthView(2016, Calendar.SEPTEMBER);
	frame.add(view, BorderLayout.CENTER);
	frame.pack();
	frame.setVisible(true);
    }

    // MonthView is a panel for showing the month
    // int year: current year
    // int month: the month, 0 being Jan, 11 being Dec
    public MonthView(int year, int month) {
	super(new BorderLayout());

	Calendar calendar = new GregorianCalendar(year, month, 1);
	int dayOfMonth = calendar.get(Calendar.DAY_OF_WEEK);

	JPanel panel1 = new JPanel(new GridLayout(1, 7));
	for (String m : months) {
	    JLabel label = new JLabel(m);
	    panel1.add(label);
	}
	add(panel1, BorderLayout.NORTH);

	JPanel panel2 = new JPanel(new GridLayout(6, 7));
	days = new DayView[35];
	for (int i = 0; i < 35; i++) {
	    days[i] = new DayView(new Day(new GregorianCalendar(year, month, 2 + i - dayOfMonth)));
	    panel2.add(days[i]);
	}
	add(panel2, BorderLayout.CENTER);
    }
}

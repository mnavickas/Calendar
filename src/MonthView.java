package calendar;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.util.Calendar;
import java.util.GregorianCalendar;

/*
 * Month view shows an interface for a month.
 */
public class MonthView extends JPanel {
    // used for headers
    String[] dayNames = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
    String[] monthNames = { "January", "Feburary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };

    // contains all days in the month
    DayView[] days;

    public String monthName;

    /*
     * Initialize the window for a Month View.
     * Used as a helper by StartMenu.
     *
     * @param year Year to use for the month for example 2016.
     * @param month Month number to use for the month for example 10 for
     *              September.
     */
    public static void initialize(int year, int month) {
	DBManager db = new DBManager("testuser");
	MonthView view = new MonthView(year, month, true, db);

	JFrame frame = new JFrame(view.monthName);

	// back button
	JButton button = new JButton("BACK");
	frame.add(button,BorderLayout.NORTH);

	// actual view
	frame.add(view, BorderLayout.CENTER);

	frame.pack();
	frame.setVisible(true);
	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //http://stackoverflow.com/questions/258099/how-to-close-a-java-swing-application-from-the-code
	button.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0)  {
		    StartMenu.initialize();
		    frame.dispose();
		}
	    });
    }

    /*
     * Creates a month view based on information about the month
     * @param year The year of the month, for example 2016.
     * @param month The month number to use, for example 10 for September.
                    0 being Jan, 11 being Dec.
     * @param needsHeader Whether to display a header showing Sunday to
     *                    Saturday.
     */
    // MonthView is a panel for showing the month
    public MonthView(int year, int month, boolean needsHeader, DBManager db) {
	super(new BorderLayout());

	// set up a calendar from the given year, month
	Calendar calendar = new GregorianCalendar(year, month, 1);
	int dayOfMonth = calendar.get(Calendar.DAY_OF_WEEK);

	// add label for month name
	this.monthName = monthNames[calendar.get(Calendar.MONTH)];
	JLabel label = new JLabel(monthName);
	add(label, BorderLayout.NORTH);

	JPanel panel1 = new JPanel(new BorderLayout());

	// set up the header to use
	if (needsHeader) {
	    JPanel panel2 = new JPanel(new GridLayout(1, 7));
	    for (String dayName : dayNames) {
		panel2.add(new JLabel(dayName));
	    }
	    panel1.add(panel2, BorderLayout.NORTH);
	}

	// set up the 5x7 grid
	// a day view is added for each (even those not in the month!)
	JPanel panel3 = new JPanel(new GridLayout(5, 7));
	days = new DayView[35];
	for (int i = 0; i < 35; i++) {
	    Calendar cal = new GregorianCalendar(year, month, 2 + i - dayOfMonth);
	    days[i] = new DayView(new Day(cal, db), false);

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

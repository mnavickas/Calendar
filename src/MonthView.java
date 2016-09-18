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
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Month view shows an interface for a month.
 */
public class MonthView extends JPanel {
    // used for headers
    private String[] dayNames = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };

    // contains all days in the month
    private DayView[] days;

    // used to set window name
    public String monthName;

    /**
     * Initialize the window for a Month View.
     * Used as a helper by StartMenu.
     *
     * @param month The object that stores information on the displayed month. 
     * @param db The database to use.
     */
    public static void initialize(Month month, DBManager db) {
	MonthView view = new MonthView(month, true, db);

	JFrame frame = new JFrame(view.monthName);

	// back button
	JButton button = new JButton("Back to main menu");
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

    /**
     * Helper legacy method
     * @param year Year to use for the month for example 2016.
     * @param month Month number to use for the month for example 10 for
     *              September.
     * @param db The database to use.
     */
    public static void initialize(int year, int month, DBManager db) {
	initialize(new Month(year, month), db);
    }

    /**
     * Creates a month view based on information about the month
     * @param month The month object to use.
     * @param needsHeader Whether to display a header showing Sunday to
     *                    Saturday.
     * @param db Database to load user data from.
     */
    public MonthView(Month month, boolean needsHeader, DBManager db) {
	super(new BorderLayout());

	JPanel parent = this;

	// first day of the month, used to place starting point
	int dayOfMonth = month.getFirstDayOfMonth();

	// add label for month name (used by initialize)
	this.monthName = month.toString();

	JPanel panel1 = new JPanel(new BorderLayout());

	// set up the header to use
	if (needsHeader) {
	    JPanel panel4 = new JPanel(new BorderLayout());

	    JButton prevButton = new JButton("<-");
	    prevButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
			// http://stackoverflow.com/questions/9650874/java-swing-obtain-window-jframe-from-inside-a-jpanel
			JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(parent);
			topFrame.dispose();

			initialize(month.getPreviousMonth(), db);
		    }
		});
	    panel4.add(prevButton, BorderLayout.WEST);

	    JButton nextButton = new JButton("->");
	    nextButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
			JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(parent);
			topFrame.dispose();

			initialize(month.getNextMonth(), db);
		    }
		});
	    panel4.add(nextButton, BorderLayout.EAST);

	    JLabel monthLabel = new JLabel(month.toString(), SwingConstants.CENTER);
	    panel4.add(monthLabel, BorderLayout.CENTER);

	    JPanel panel2 = new JPanel(new GridLayout(1, 7));
	    for (String dayName : dayNames) {
		panel2.add(new JLabel(dayName));
	    }
	    panel4.add(panel2, BorderLayout.SOUTH);

	    panel1.add(panel4, BorderLayout.NORTH);
	}

	// set up the 5x7 grid
	// a day view is added for each (even those not in the month!)
	JPanel panel3 = new JPanel(new GridLayout(5, 7));
	days = new DayView[35];
	for (int i = 0; i < 35; i++) {
	    Calendar cal = new GregorianCalendar(month.getYear(), month.getMonthNum(), 2 + i - dayOfMonth);
	    days[i] = new DayView(new Day(cal, db), false, false);

	    // this day is not in the month!
	    if (month.getMonthNum() != cal.get(Calendar.MONTH)) {
		days[i].setBackground(Color.lightGray);
	    }

	    panel3.add(days[i]);
	}
	panel1.add(panel3, BorderLayout.CENTER);

	add(panel1, BorderLayout.CENTER);
    }
}

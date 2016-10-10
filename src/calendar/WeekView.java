package calendar;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 * Display the days for a 7 day week period.
 */
public class WeekView extends JPanel {


    /**
	 * 
	 */
	private static final long serialVersionUID = 2250432841323024223L;

	/**
     * Initialize the week from parameters. Helper function needed by StartMenu.
     * @param year The year to use for the week.
     * @param month The month to use for the week.
     * @param day The day to use for the week.
     */
    public static void initialize(int year, int month, int day) {
	// initialize the week from params
	Calendar firstDay = new GregorianCalendar(year, month, day);
	Week week = new Week(firstDay);

	initialize(week);
    }

    /**
     * Create a week window from a week param
     *
     * @param week The week to display
     */
    public static void initialize(Week week) {
	JFrame frame = new JFrame(week.toString());

	WeekView view = new WeekView(week, true, false);
	frame.add(view, BorderLayout.CENTER);

	// back button
	JButton button = new JButton("Back to main menu");
	frame.add(button,BorderLayout.NORTH);
	button.addActionListener(new ActionListener()
	    {

		public void actionPerformed(ActionEvent arg0)
		{
		    StartMenu.initialize();
		    frame.dispose();
		}
	    });

	frame.pack();
	frame.setVisible(true);
	frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //http://stackoverflow.com/questions/258099/how-to-close-a-java-swing-application-from-the-code
    }

    /**
     * Construct a week view from a week object.
     * @param week The week to display.
     * @param needsHeader Whether to display the Sunday-Saturday header.
     * @param needsText Whether to display a text area in each day view.
     */
    public WeekView(Week week, boolean needsHeader, boolean needsText) {
	super(new BorderLayout());

	JPanel parent = this;

	if (needsHeader) {
	    JPanel panel4 = new JPanel(new BorderLayout());

	    JButton prevButton = new JButton("<-");
	    prevButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
			// http://stackoverflow.com/questions/9650874/java-swing-obtain-window-jframe-from-inside-a-jpanel
			JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(parent);
			topFrame.dispose();

			initialize(week.getPreviousWeek());
		    }
		});
	    panel4.add(prevButton, BorderLayout.WEST);

	    JButton nextButton = new JButton("->");
	    nextButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
			JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(parent);
			topFrame.dispose();

			initialize(week.getNextWeek());
		    }
		});

	    panel4.add(nextButton, BorderLayout.EAST);

	    JLabel weekLabel = new JLabel(week.toString(), SwingConstants.CENTER);
	    panel4.add(weekLabel, BorderLayout.CENTER);

	    // setup a header with each month
	    JPanel panel1 = new JPanel(new GridLayout(1, 7));
	    for (Day day : week.getDays()) {
		JLabel label = new JLabel(day.getDayName());
		panel1.add(label);
	    }
	    panel4.add(panel1, BorderLayout.SOUTH);

	    add(panel4, BorderLayout.NORTH);
	}

	// setup a 7 grid pieces
	JPanel panel2 = new JPanel(new GridLayout(1, 7));
	for (Day day : week.getDays()) {
	    DayView view = new DayView(day, needsText);
	    panel2.add(view);
	}
	add(panel2, BorderLayout.CENTER);
    }
}

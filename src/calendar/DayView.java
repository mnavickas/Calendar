package calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Event.EventPlanner;

/**
 * DayView
 *
 * DayView creates the interface elements for one day in the calendar.
 */
public class DayView extends JPanel {


    /**
	 * 
	 */
	private static final long serialVersionUID = 6233076767005237805L;

	/**
     * Initialize a DayView window.
     * This is a helper function used by the StartMenu.
     * @param day The day to create the day view for.
     */
    public static void initialize(Day day) {
	JFrame frame = new JFrame(day.toString());

	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //http://stackoverflow.com/questions/258099/how-to-close-a-java-swing-application-from-the-code
	DayView view = new DayView(day, true);
	frame.add(view, BorderLayout.CENTER);
	frame.pack();
	frame.setVisible(true);


    }

    /**
     * Same as above but with different parameters.
     *
     * @param year The year to be shown for example 2016.
     * @param month The month to be shown for example 10 for September.
     * @param day The day to be shown for example 18.
     */
    public static void initialize(int year, int month, int day) {
	initialize(new Day(new GregorianCalendar(year, month, day)));
    }

    /**
     * Create the view to show the current day.
     *
     * @param day The object to use to get info on the day.
     * @param needsText Whether to display the text area of the agenda.
     */ 
    public DayView(final Day day, boolean needsText) {
	super(new BorderLayout());

	setBorder(BorderFactory.createLineBorder(Color.black));
	setPreferredSize(new Dimension(400, 400));
	setCursor(new Cursor(Cursor.HAND_CURSOR));
	addMouseListener(new MouseListener() {
		public void mousePressed(MouseEvent e) {
		}
		public void mouseReleased(MouseEvent e) {
		}
		public void mouseEntered(MouseEvent e) {
		}
		public void mouseExited(MouseEvent e) {
		}
		public void mouseClicked(MouseEvent e) {
		   EventPlanner.create(day.getDate());
		}
	    });

	// label showing day number
	JLabel label = new JLabel("" + day.getDayNumber());
	add(label, BorderLayout.NORTH);


    }
}

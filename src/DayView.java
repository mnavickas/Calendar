import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import javax.swing.JButton;

import java.util.Calendar;
import java.util.GregorianCalendar;

/*
 * DayView
 *
 * DayView creates the interface elements for one day in the calendar.
 */
public class DayView extends JPanel {
    private Day day;

    /*
     * Initialize a DayView window.
     * This is a helper function used by the StartMenu.
     *
     * @param year The year to be shown for example 2016.
     * @param month The month to be shown for example 10 for September.
     * @param day The day to be shown for example 18.
     */
    public static void initialize(int year, int month, int day) {
	JFrame frame = new JFrame("Day View");
	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //http://stackoverflow.com/questions/258099/how-to-close-a-java-swing-application-from-the-code
	DayView view = new DayView(new Day(new GregorianCalendar(year, month, day)), true);
	frame.add(view, BorderLayout.CENTER);
	frame.pack();
	frame.setVisible(true);

	JButton button = new JButton("BACK");
	frame.add(button,BorderLayout.NORTH);
	button.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		    StartMenu.initialize();
		    frame.dispose();
		}
	    });
    }

    /*
     * Create the view to show the current day.
     *
     * @param day The object to use to get info on the day.
     * @param needsText Whether to display the text area of the agenda.
     */ 
    public DayView(Day day, boolean needsText) {
	super(new BorderLayout());

	setBorder(BorderFactory.createLineBorder(Color.black));

	// label showing day number
	JLabel label = new JLabel("" + day.getDayNumber());
	add(label, BorderLayout.PAGE_START);

	// editable agenda
	if (needsText) {
	    JTextArea agendaText = new JTextArea(day.getAgenda());
	    agendaText.setLineWrap(true);
	    agendaText.setWrapStyleWord(true);
	    agendaText.setPreferredSize(new Dimension(100, 100));

	    agendaText.getDocument().addDocumentListener(new DocumentListener() {
		    public void changedUpdate(DocumentEvent documentEvent) {
		    }
		    public void insertUpdate(DocumentEvent documentEvent) {
		    }
		    public void removeUpdate(DocumentEvent documentEvent) {
		    }
		});
	    add(agendaText, BorderLayout.CENTER);
	}
    }
}

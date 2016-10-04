package calendar;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Cursor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import javax.swing.JButton;
import javax.swing.text.Document;
import javax.swing.text.BadLocationException;
import javax.swing.SwingUtilities;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * DayView
 *
 * DayView creates the interface elements for one day in the calendar.
 */
public class DayView extends JPanel {
    private Day day;

    /**
     * Initialize a DayView window.
     * This is a helper function used by the StartMenu.
     * @param day The day to create the day view for.
     * @param isPopup Whether to open in "popup" mode,
     *                that is whether to have a back menu shown.
     */
    public static void initialize(Day day, boolean isPopup) {
	JFrame frame = new JFrame(day.toString());

	if (isPopup) {
	    frame.setLocationRelativeTo(null);
	    frame.setAlwaysOnTop(true);
	}

	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //http://stackoverflow.com/questions/258099/how-to-close-a-java-swing-application-from-the-code
	DayView view = new DayView(day, true, isPopup);
	frame.add(view, BorderLayout.CENTER);
	frame.pack();
	frame.setVisible(true);

	if (!isPopup) {
	    JButton button1 = new JButton("Back to main menu");
	    frame.add(button1,BorderLayout.NORTH);
	    button1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
			StartMenu.initialize();
			frame.dispose();
		    }
		});
	}
    }

    /**
     * Same as above but with different parrameters.
     *
     * @param year The year to be shown for example 2016.
     * @param month The month to be shown for example 10 for September.
     * @param day The day to be shown for example 18.
     * @param isPopup Whether to use popup mode in this day view.
     * @param db The database to use.
     */
    public static void initialize(int year, int month, int day, boolean isPopup, DBManager db) {
	initialize(new Day(new GregorianCalendar(year, month, day), db), isPopup);
    }

    /**
     * Create the view to show the current day.
     *
     * @param day The object to use to get info on the day.
     * @param needsText Whether to display the text area of the agenda.
     * @param isPopup Enable "popup" mode for this day view, this means
     *                That the window will close when the "save" button
     *                is pressed.
     */ 
    public DayView(Day day, boolean needsText, boolean isPopup) {
	super(new BorderLayout());

	JPanel parent = this;

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
		    initialize(day, true);
		}
	    });

	// label showing day number
	JLabel label = new JLabel("" + day.getDayNumber());
	add(label, BorderLayout.NORTH);

	// editable agenda
	if (needsText) {
	    JTextArea agendaText = new JTextArea(day.getAgenda());
	    agendaText.setLineWrap(true);
	    agendaText.setWrapStyleWord(true);
	    agendaText.setPreferredSize(new Dimension(200, 200));

	    // progressive saving is disable because it slows down
	    agendaText.getDocument().addDocumentListener(new DocumentListener() {
		    public void changedUpdate(DocumentEvent documentEvent) {
			// saveText(documentEvent);
		    }
		    public void insertUpdate(DocumentEvent documentEvent) {
			// saveText(documentEvent);
		    }
		    public void removeUpdate(DocumentEvent documentEvent) {
			// saveText(documentEvent);
		    }
		    private void saveText(DocumentEvent event) {
			Document document = event.getDocument();
			int length = document.getLength();
			String text = "";
			try {
			    text = document.getText(0, length);
			} catch (BadLocationException e) {
			    text = "";
			}
			day.setAgenda(text);
		    }
		});
	    add(agendaText, BorderLayout.CENTER);

	    // save button
	    JButton button2;
	    if (isPopup) {
		button2 = new JButton("Save and close");
	    } else {
		button2 = new JButton("Save");
	    }
	    add(button2,BorderLayout.NORTH);
	    button2.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
			Document document = agendaText.getDocument();
			int length = document.getLength();
			String text = "";
			try {
			    text = document.getText(0, length);
			} catch (BadLocationException e) {
			    text = "";
			}
			day.setAgenda(text);

			// close after save if popup
			if (isPopup) {
			    JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(parent);
			    topFrame.dispose();
			}
		    }
		});
	}
    }
}

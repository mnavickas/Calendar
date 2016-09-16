import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.BorderFactory;

import java.util.Calendar;
import java.util.GregorianCalendar;

// represents a single day
public class DayView extends JPanel {
    Day day;

    // temp for testing
    public static void main(String[] args) {
	JFrame frame = new JFrame("Day");
	Day day = new Day(new GregorianCalendar(2016, Calendar.SEPTEMBER, 2));
	day.setAgenda("1. asdf\n2. asdfg\n3.asdfgh");
	DayView view = new DayView(day);
	frame.add(view);
	frame.pack();
	frame.setVisible(true);
    }

    public DayView(Day day) {
	super(new BorderLayout());

	setBorder(BorderFactory.createLineBorder(Color.black));

	// label showing day number
	JLabel label = new JLabel("" + day.getDayNumber());
	add(label, BorderLayout.PAGE_START);

	// editable agenda
        JTextArea agendaText = new JTextArea(day.getAgenda());
	agendaText.setPreferredSize(new Dimension(100, 100));
	add(agendaText, BorderLayout.CENTER);

	// TODO: add handler when agenda text is edited
    }
}

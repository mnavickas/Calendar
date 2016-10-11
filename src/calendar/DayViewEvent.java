package calendar;

import java.awt.Color;
import java.text.ParseException;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import Event.DateFormatter;
import Event.Event;
/**
 * 
 * Tile showing the event.
 *
 */
public class DayViewEvent extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2318788304336791454L;
	
	private Event theEvent;
	/**
	 * Create the tile
	 * @param e Event to display
	 * @param overlapFlag Does this event overlap today?
	 */
	public DayViewEvent(Event e, boolean overlapFlag){
		this.setLayout(null);
		theEvent = e;

		//this.setBackground(Color.BLACK);
		Border doubleBorder = BorderFactory.createCompoundBorder(
				BorderFactory.createRaisedBevelBorder(), 
				BorderFactory.createLoweredBevelBorder()
				);
	
		setBorder(BorderFactory.createCompoundBorder(
													doubleBorder, 
													BorderFactory.createLineBorder(Color.BLACK)
													));
	
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(DateFormatter.getFormat().parse(e.StartDate));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
		}
		
		JLabel name = new JLabel(e.Name);
		name.setForeground(Color.BLACK);
		name.setBounds(10, 5, 400, 30);

		add(name);
		
		JLabel times = new JLabel(e.StartTime+" -> "+e.StopTime);
		times.setForeground(Color.BLACK);
		times.setBounds(30, 35, 400, 30);

		add(times);
		
		if(overlapFlag){
			JLabel flag = new JLabel("This event overlaps another.");
			flag.setForeground(Color.RED);
			flag.setBounds(30, 65, 400, 30);

			add(flag);
		}
		

	}

	/**
	 * @return this Event
	 */
	public Event getEvent() {
		return theEvent;
	}
	

}

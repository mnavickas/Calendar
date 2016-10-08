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

public class DayViewEvent extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2318788304336791454L;
	private int day;
	private int month;
	private int year;

	public DayViewEvent(Event e){
		this.setLayout(null);

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
		day = cal.get(Calendar.DAY_OF_MONTH);
		month = cal.get(Calendar.MONTH);
		year = cal.get(Calendar.YEAR);
		
		JLabel name = new JLabel(e.Name);
		name.setForeground(Color.BLACK);
		name.setBounds(10, 5, 400, 30);

		add(name);
		
		JLabel times = new JLabel(e.StartTime+" -> "+e.StopTime);
		times.setForeground(Color.BLACK);
		times.setBounds(30, 45, 400, 30);

		add(times);
		

	}


	public int getDay() {
		// TODO Auto-generated method stub
		return day;
	}


	public int getMonth() {
		// TODO Auto-generated method stub
		return month;
	}

	public int getYear() {
		// TODO Auto-generated method stub
		return year;
	}
	

}

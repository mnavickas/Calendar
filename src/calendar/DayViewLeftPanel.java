package calendar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Event.EventPlanner;

public class DayViewLeftPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8850861518801177742L;
	
	public static final int FRAME_WIDTH 		= 700;
	public static final int FRAME_HEIGHT 		= 700;
	public DayViewLeftPanel(
							Date date,
							String dateString, 
							JFrame old
							)
	{
		setLayout(null);
		setBackground(Color.lightGray);
		setPreferredSize(new Dimension((int)((1.0/3.0)*FRAME_WIDTH),FRAME_HEIGHT));
		setMinimumSize(new Dimension((int)((1.0/3.0)*FRAME_WIDTH),FRAME_HEIGHT));
		setMaximumSize(new Dimension((int)((1.0/3.0)*FRAME_WIDTH),FRAME_HEIGHT));
		
		JLabel allDayEvents = new JLabel("All-Day Events for:");

		allDayEvents.setBounds(25,5,200,30);
		
		JLabel dateLabel = new JLabel(dateString);

		dateLabel.setBounds(65,35,200,30);
		

		add(allDayEvents);
		add(dateLabel);

		JButton b = new JButton("New Event");
		b.setBounds(0,560,(int)getPreferredSize().getWidth(),40);
		b.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				EventPlanner.create(date,old);
			}
			
		});
		
		
		
		add(b);
	
	}

}

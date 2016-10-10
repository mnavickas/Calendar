package calendar;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import Event.Event;
import Event.EventCache;
import Event.RepeatType;
/**
 * 
 * Frame that will show the event we are viewing.
 *
 */
public class DayViewEventViewer extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 763604350492381552L;
	
	private static final int FRAME_HEIGHT = 600;
	private static final int FRAME_WIDTH = 400;
	/**
	 * Singleton instance of this.
	 */
	private static  DayViewEventViewer mInstance;
	/**
	 * Event who's details we are showing;
	 */
	private final Event eventToShow;
	/**
	 * Parent Frame
	 */
	private static JFrame sFrame;
	
	/**
	 * Create an Event Viewer and pop it to the front.
	 * @param e The event to display
	 * @param frame Parent Frame
	 * @return An Event viewer frame
	 */
	public static DayViewEventViewer create(DayViewEvent e, JFrame frame){
		if(mInstance == null){
			return mInstance = new DayViewEventViewer(e,frame);
		}
		else{
			mInstance.toFront();
			return null;
		}

	}

	/**
	 * Build the frame and add components.
	 * @param e The event to display
	 * @param frame Parent Frame
	 */
	private DayViewEventViewer(DayViewEvent e, JFrame frame)
	{
		sFrame = frame;
		eventToShow = e.getEvent();
		
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				mInstance = null;
			}
		});
		
		setTitle("View Event");
		setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setMinimumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		setResizable(false);
		
		JLabel name = new JLabel(eventToShow.Name);
		name.setBounds(50,10,400,30);
		add(name);
		
		JLabel times = new JLabel(eventToShow.StartTime +" - "+eventToShow.StopTime);
		times.setBounds(50,60,400,30);
		add(times);
		

		JTextArea descriptionText = new JTextArea();
		descriptionText.setLineWrap(true);
		descriptionText.setWrapStyleWord(true);
		descriptionText.setEditable(false);
		descriptionText.setBorder(BorderFactory.createLoweredBevelBorder());
		descriptionText.setText(eventToShow.Description);
		descriptionText.setBounds(50,100,300,200);
		add(descriptionText);
		

		RepeatType r = eventToShow.rType;

		JLabel repeat = new JLabel("Repeat Type: "+r.toPrettyString());
		repeat.setBounds(50,300,300,30);
		add(repeat);
		
		
		if(r == RepeatType.DAY_OF_WEEK)
		{
			String days = "";
			long rDays = eventToShow.rDays;

				if((rDays>>0 & 0x01) == 1){
					days+="Sun. ";
				}
				if((rDays>>1 & 0x01) == 1){
					days+="Mon. ";
				}
				if((rDays>>2 & 0x01) == 1){
					days+="Tues. ";
				}
				if((rDays>>3 & 0x01) == 1){
					days+="Wed. ";
				}
				if((rDays>>4 & 0x01) == 1){
					days+="Thur. ";
				}
				if((rDays>>5 & 0x01) == 1){
					days+="Fri. ";
				}
				if((rDays>>6 & 0x01) == 1){
					days+="Sat. ";
				}
				JLabel repeatDay = new JLabel(days);
				repeatDay.setBounds(50,350,300,30);
				add(repeatDay);
		}
	
		
		/*
		 * Submit Button
		 */
		{
			JButton submit = new JButton("Close");
			submit.setBounds(50, 500, 100, 30);
			submit.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					mInstance.dispose();
					mInstance = null;

				}
			
			});
			add(submit);
			
			JButton delete = new JButton("Remove");
			delete.setBounds(250, 500, 100, 30);
			delete.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					mInstance.dispose();
					mInstance = null;
					EventCache.getInstance().removeEvent(eventToShow);
					if(StartMenu.viewTypeEnum == View.Day){
						if(sFrame != null )
						{
							sFrame.dispose();
						}
						DayViewPopup.initialize(StartMenu.YearHold, StartMenu.monthHold, StartMenu.dayHold);
					}
					
				}
			
			});
			add(delete);
		}
		setVisible(true);
	}
}

package calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.Box;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

import Event.DateFormatter;
import Event.Event;
import Event.EventCache;
import Event.FormatTypes;

public class DayViewPopup extends JFrame{

    public static final int FRAME_WIDTH 		= 700;
    public static final int FRAME_HEIGHT 		= 700;
private static final long serialVersionUID = -3078193866264175025L;
	
	private JPanel leftBar;
	private String dateString;
	private Date date;
	private JScrollPane rightScrollPane;
	private LinkedList<Event> todaysEvents;
	private LinkedList<Event> allDayEvents;
	
	public static void initialize(int y, int m, int d){
		try {
			new DayViewPopup(y,m,d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}


	private DayViewPopup(int y, int m, int d) throws ParseException{
		todaysEvents = new LinkedList<Event>();
		date = setDate(y,m,d);

		dateString = DateFormatter.format(FormatTypes.Date, date);

		todaysEvents = EventCache.getInstance().getEventsForDate(dateString);

		allDayEvents = new LinkedList<Event>();
		//allDayEvents = removeAllDayEvents(todaysEvents);
		sort(todaysEvents);
		
		initThisPanel();
		
		
		JButton button = new JButton("Back to main menu");
		button.setSize(FRAME_WIDTH,50);
		add(button,BorderLayout.NORTH);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)  {
			    StartMenu.initialize();
			    dispose();
			}
		    });
		add(button,BorderLayout.NORTH);
		
		Component spacer = Box.createRigidArea(new Dimension(FRAME_WIDTH,50));
		
		addLeftBar();
		
		addRightScrollBar();
		
		spacer = Box.createRigidArea(new Dimension(FRAME_WIDTH,50));
		add(spacer, BorderLayout.PAGE_END);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setResizable(false);
		setVisible(true);

		
	}
	private Date setDate(int y, int m, int d){
		Calendar firstDay = new GregorianCalendar(y, m, d);	
		return firstDay.getTime();
		
	}
	private void initThisPanel(){
		setLayout( new BorderLayout() );
		setPreferredSize(new Dimension(FRAME_WIDTH,FRAME_HEIGHT+50));
		setMinimumSize(new Dimension(FRAME_WIDTH,FRAME_HEIGHT+50));
		setMaximumSize(new Dimension(FRAME_WIDTH,FRAME_HEIGHT+50));
		setBackground(Color.GRAY);
	}
	private void addLeftBar(){
		leftBar = new DayViewLeftPanel(date,dateString,allDayEvents,this);
		add(leftBar, BorderLayout.LINE_START);
	}
	private void addRightScrollBar(){
		rightScrollPane = new JScrollPane();
		rightScrollPane.setPreferredSize(new Dimension((int)((2.0/3.0)*FRAME_WIDTH),(int)(FRAME_HEIGHT)));
		rightScrollPane.setMinimumSize(new Dimension((int)((2.0/3.0)*FRAME_WIDTH),(int)(FRAME_HEIGHT)));
		rightScrollPane.setMaximumSize(new Dimension((int)((2.0/3.0)*FRAME_WIDTH),(int)(FRAME_HEIGHT)));
		
		JScrollBar vertical = rightScrollPane.getVerticalScrollBar();
		vertical.setUnitIncrement(20);
		InputMap im = vertical.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		im.put(KeyStroke.getKeyStroke("DOWN"), "positiveUnitIncrement");
		im.put(KeyStroke.getKeyStroke("UP"), "negativeUnitIncrement");
		

		//rightScrollPane.add(new JLabel(todaysEvents.get(0).Name));
		rightScrollPane.setViewportView(new DayViewGrid(rightScrollPane,date,todaysEvents));
		
		add(rightScrollPane, BorderLayout.LINE_END);
		
	}
	/**
	 * remove all day events before this point
	 * @param linkedList
	 */
	public void sort(LinkedList<Event> lle){
		Collections.sort(lle, new Comparator<Event>() {
	         @Override
	         public int compare(Event o1, Event o2) {
	             return getTimeStringAsMinutes(o1.StartTime)-getTimeStringAsMinutes(o2.StartTime);
	         }
	     });
	}
	public int getTimeStringAsMinutes(String timeString){
		int timeInMinutes=0;
		
		String[] arr = timeString.split(":");
		int hourValue = Integer.parseInt(arr[0]);
		char[] minutes = arr[1].toCharArray();
		int minutesValue = Integer.parseInt(minutes[0]+"")* 10 +Integer.parseInt(minutes[1]+"");
		boolean isAM = minutes[2] =='a';
		
		if(hourValue == 12 && isAM){
			timeInMinutes = minutesValue;
		}else if(isAM){
			timeInMinutes = hourValue*60+minutesValue;
		}else if(hourValue == 12 && !isAM){
			timeInMinutes = hourValue*60+minutesValue;
		}else if(!isAM){
			timeInMinutes = 12*60+hourValue*60+minutesValue;
		}
		return timeInMinutes;
		
	}
	public LinkedList<Event> removeAllDayEvents(LinkedList<Event> lle){
		
		Iterator<Event> it  = lle.iterator();
		LinkedList<Event> allDayEvents = new LinkedList<Event>();
		
		//fix this.
		while(it.hasNext()){
			Event e = it.next();
			if(e.StartTime.equals("-1") || e.StopTime.equals("-1")){
				it.remove();
				allDayEvents.add(e);
			}
			
		}
		return allDayEvents;
	}
}

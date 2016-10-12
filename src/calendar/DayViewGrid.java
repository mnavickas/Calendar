package calendar;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Event.DateFormatter;
import Event.DateSpan;
import Event.Event;
import Event.FormatTypes;
/**
 * Grid of events on right panel for dayview
 *
 */
public class DayViewGrid extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6262892174085562405L;
	Date date;

	public DayViewGrid(JScrollPane pane, Date d, LinkedList<Event> todaysEvents, EventPicker eventPicker){
		date = d;
		int sizeOfEachGrid = 100;
		int sizeOfList = todaysEvents.size();
		setLayout(new GridLayout(sizeOfList>6?sizeOfList:6,1));
		setMinimumSize(new Dimension(pane.getWidth(),pane.getHeight()));
		setMaximumSize(new Dimension(pane.getWidth(),sizeOfList*sizeOfEachGrid));
		setPreferredSize(new Dimension(pane.getWidth(),sizeOfList*sizeOfEachGrid));

		LinkedList<Tuple> eventSpans = getTodaysSpans(todaysEvents);
		Iterator<Tuple> spansIt = eventSpans.iterator();

		while(spansIt.hasNext()){
			boolean flag = false;
			Tuple theTuple = spansIt.next();

			@SuppressWarnings("unchecked")
			Iterator<Tuple> spansIt2 = ((LinkedList<Tuple>)eventSpans.clone()).iterator();

			while(spansIt2.hasNext()){
				Tuple compareTuple = spansIt2.next();
				if(theTuple.event.unique_id != compareTuple.event.unique_id )
				{
					if(compareTuple.todays.intersects(theTuple.todays)){
						flag = true;
					}
				}
			}
			DayViewEvent e = new DayViewEvent(theTuple.event,flag);
			e.addMouseListener(eventPicker);
			add(e);
		}	
	}

	/**
	 * 
	 * @param todaysEvents LinkedList of events
	 * @return LinkedList of tuples of today's event and the particular datespan
	 */
	private LinkedList<Tuple> getTodaysSpans(LinkedList<Event> todaysEvents){
		LinkedList<Tuple> pairs = new LinkedList<Tuple>();

		String dateString = DateFormatter.format(FormatTypes.Date, date);
		DateSpan today;
		try {
			Date start = DateFormatter.getAdvFormat().parse(dateString+ " 00:00");
			Date stop = DateFormatter.getAdvFormat().parse(dateString+ " 23:59");
			today = new DateSpan(start,stop);

		} catch (ParseException e) {
			today = new DateSpan(new Date(), new Date());
		}

		for(int i = 0; i<todaysEvents.size(); i++){
			Event event = todaysEvents.get(i);
			for(int j = 0; j<event.getTimeIntervalList().size(); j++){
				DateSpan span = event.getTimeIntervalList().get(j);

				if(today.intersects(span)){
					pairs.add(new Tuple(event,span));
					break;
				}
			}
		}
		return pairs;
	}
	private class Tuple{
		public Event event;
		public DateSpan todays;

		public Tuple(Event e, DateSpan todays){
			this.event = e;
			this.todays=todays;
		}
	}
}

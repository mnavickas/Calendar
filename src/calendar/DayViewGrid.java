package calendar;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Event.Event;
/**
 * Grid of events on right panel for dayview
 *
 */
public class DayViewGrid extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6262892174085562405L;

	public DayViewGrid(JScrollPane pane, Date d, LinkedList<Event> todaysEvents, EventPicker eventPicker){
		int sizeOfEachGrid = 100;
		int sizeOfList = todaysEvents.size();
		setLayout(new GridLayout(sizeOfList>6?sizeOfList:6,1));
		setMinimumSize(new Dimension(pane.getWidth(),pane.getHeight()));
		setMaximumSize(new Dimension(pane.getWidth(),sizeOfList*sizeOfEachGrid));
		setPreferredSize(new Dimension(pane.getWidth(),sizeOfList*sizeOfEachGrid));
	
		@SuppressWarnings("unchecked")
		LinkedList<Event> listClone = (LinkedList<Event>) todaysEvents.clone();
		
		Iterator<Event> it = todaysEvents.iterator();
	
		while(it.hasNext()){
			boolean flag = false;
			Event theEvent = it.next();
			for(int i = 0; i < listClone.size(); i++ ){
				Event ev = listClone.get(i);
				if(theEvent.unique_id == ev.unique_id )
				{
					continue;
				}
				
				for(int j = 0; j < theEvent.getTimeIntervalList().size(); j++){
					for(int k = 0; k<ev.getTimeIntervalList().size(); k++){
						if(theEvent.getTimeIntervalList().get(j).intersects(ev.getTimeIntervalList().get(k)))
						{
							flag = true;
						}
					}
				}
				
			}
			DayViewEvent e = new DayViewEvent(theEvent,flag);
			e.addMouseListener(eventPicker);
			add(e);
			
		}

		
	}



}

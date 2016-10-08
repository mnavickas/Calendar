package Event;
import static calendar.Debug.isDebug;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import FileOperations.FileIO;



/**
 * Handling class for scheduled events
 */
public class EventCache {

	private static final String EVENT_FILE = "Events.txt";

	/**
	 * List of all the Events.
	 */
	private LinkedList<Event> eventsList;
	
	/**
	 * Singleton Instance
	 */
	private static EventCache sInstance = null;
	
	/**
	 * Populate the EventCache
	 */
	private EventCache(){
		// Load From file, convert to events.
		JSONArray events;
		try{
			events = FileIO.readToArray(EVENT_FILE);
			eventsList = arrayToList(events);
		}catch(FileNotFoundException e){
			//File doesn't exist yet, create empty list.
			eventsList = new LinkedList<Event>();
		}
		
	}
	
	/**
	 * Convert JSONArray to LinkedList
	 * @param array JSONArray of events
	 */
	private LinkedList<Event> arrayToList(JSONArray array){
		LinkedList<Event> eventList = new LinkedList<Event>();
		
		for(int idx = 0; idx<array.size(); idx++)
		{
			JSONObject event = (JSONObject)array.get(idx);
			Event e = new Event(event);
			e.setAllTimeIntervals();
			eventList.add(e);
		}
		return eventList;
	}
	
	/**
	 * @return Singleton Instance Variable
	 */
	public static EventCache getInstance(){
		if(sInstance == null){
			sInstance = new EventCache();
		}
		return sInstance;
	}
	/**
	 * Add an event to the cached List.
	 * @param event Event to add to cached List
	 */
	public void addEvent(Event event)
	{
		/**
		 * The item should be complete, so do this now.
		 */
		event.setAllTimeIntervals();

		eventsList.add(event);
		FileIO.writeToFile(EVENT_FILE, listToArray(eventsList) );
		
	}
	
	/**
	 * 
	 * @param dateString to get dates for
	 * @return List of events intersecting that date.
	 * @throws ParseException 
	 */
	public LinkedList<Event> getEventsForDate(String dateString) throws ParseException{
		//TODO verify this
		LinkedList<Event> todaysEvents = new LinkedList<Event>();
		
		//start of day, to end of day.
		Date start = DateFormatter.getAdvFormat().parse(dateString+ " 00:00");
		Date stop = DateFormatter.getAdvFormat().parse(dateString+ " 23:59");
		
		DateSpan span = new DateSpan(start,stop);
		if(isDebug()){
			System.out.println("Day we are checking for conflict: "+span.toString());
			System.out.println("total size: "+eventsList.size());
		}

		for(int idx = 0; idx<eventsList.size(); idx++)
		{
			Event theEvent = eventsList.get(idx);
			
			if( hasIntersection(span, theEvent) )
			{
				todaysEvents.add(theEvent);
			}
		}
		return todaysEvents;
	}
	/**
	 * Check if the DateSpan intersects any of the Event's DateSpans
	 * @param span DateSpan to check
	 * @param e Event to check against
	 * @return true if there exists an intersection
	 */
	private boolean hasIntersection(DateSpan span, Event e){

		for(int i = 0; i< e.getTimeIntervalList().size(); i++)
		{
			if(isDebug()){
				System.out.println(e.Name+" time interval: "+e.getTimeIntervalList().get(i).toString());
			}
			
			if(span.intersects(e.getTimeIntervalList().get(i)))
			{
				return true;
			}
		}
		return false;
	}


	
	/**
	 * @param e Event to Remove
	 * @return true if the event was removed
	 */
	public boolean removeEvent(Event e){
		
		boolean retn = false;
		
		Iterator<Event> it = eventsList.iterator();
		while(it.hasNext())
		{
			Event event = it.next();
			
			if( event.unique_id == e.unique_id ){
				it.remove();
				retn = true;
				break;
			}
		}

		FileIO.writeToFile(EVENT_FILE, listToArray(eventsList));

		return retn;
	}

	/**
	 * Convert LinkedList to JSONArray
	 * @param list LinkedList of events
	 * @return LinkedList as a JSONArray
	 */
	@SuppressWarnings("unchecked")
	private JSONArray listToArray(LinkedList<Event> list){
		JSONArray array = new JSONArray();
		
		Iterator<Event> it = list.iterator();
		while(it.hasNext())
		{
			array.add(it.next().toJSONObject());
		}
		
		return array;
	}
	

}

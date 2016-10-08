package Event;

import static calendar.Debug.isDebug;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import org.json.simple.JSONObject;

/**
 * 
 * Event container for handling
 *
 */
public class Event {

	public static final String START_DATE_STRING = "start_date";
	public static final String STOP_DATE_STRING = "end_date";
	public static final String START_STRING = "start";
	public static final String STOP_STRING = "end";
	public static final String NAME_STRING = "name";
	public static final String DESC_STRING = "description";
	public static final String ID_STRING = "id";
	public static final String REPEAT_STRING = "repeat_type";
	public static final String REPEAT_DAYS_STRING = "repeat_days";
	
	
	private LinkedList<DateSpan> timeIntervals;
	
	@SuppressWarnings("unchecked")
	public LinkedList<DateSpan> getTimeIntervalList()
	{
		return (LinkedList<DateSpan>) timeIntervals.clone();
	}
	
	/**
	 * DataField variable
	 */
	public String StartDate,StopDate, StartTime,StopTime,Name,Description;
	/**
	 * DataField variable
	 */
	public long unique_id;
	/**
	 * DataField variable
	 */
	public RepeatType rType;
	/**
	 * DataField variable
	 */
	public int rDays;
	
	private static final Date lastDate;
	
	static{
		Calendar c = (Calendar)Calendar.getInstance().clone();
		c.set( 2017, 5, 1, 0, 0, 0 );
		lastDate = c.getTime();
	}

	/**
	 * Constructor for configuring Event as a datatype.
	 */
	public Event(){}
	
	/**
	 * 'Copy' constructor
	 * @param JSONObject we are converting.
	 */
	public Event(JSONObject theEvent)
	{
		StartDate = (String)theEvent.get(Event.START_DATE_STRING);
		StopDate = (String)theEvent.get(Event.STOP_DATE_STRING);
		Name = (String)theEvent.get(Event.NAME_STRING);
		StartTime = (String)theEvent.get(Event.START_STRING);
		StopTime = (String)theEvent.get(Event.STOP_STRING);
		Description = (String)theEvent.get(Event.DESC_STRING);
		unique_id = (long)theEvent.get(Event.ID_STRING);
		rType = RepeatType.valueOf((String)theEvent.get(Event.REPEAT_STRING));
		rDays = (int)theEvent.get(Event.REPEAT_DAYS_STRING);
	}
	
	/**
	 * 
	 * @return this as a JSONObject
	 */
	@SuppressWarnings("unchecked")
	public JSONObject toJSONObject()
	{
		JSONObject jsonEvent = new JSONObject();
		jsonEvent.put(Event.START_DATE_STRING, StartDate);
		jsonEvent.put(Event.STOP_DATE_STRING, StopDate);
		jsonEvent.put(Event.NAME_STRING, Name);
		jsonEvent.put(Event.DESC_STRING, Description);
		jsonEvent.put(Event.START_STRING, StartTime);
		jsonEvent.put(Event.STOP_STRING, StopTime);
		jsonEvent.put(Event.ID_STRING, unique_id);
		jsonEvent.put(Event.REPEAT_STRING, rType.toString());
		jsonEvent.put(Event.REPEAT_DAYS_STRING, rDays);
		return jsonEvent;
	}
	
	/**
	 * Set all the time intervals this event occupies
	 */
	void setAllTimeIntervals()
	{
		try {
			timeIntervals = getAllTimeIntervals();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(isDebug())
		System.out.println("Time Intervals for "+this.Name+": "+timeIntervals.size());
	}
	
	private LinkedList<DateSpan> getAllTimeIntervals() throws ParseException
	{
		LinkedList<DateSpan> timeIntervals = new LinkedList<DateSpan>();
		switch(rType)
		{
			case BIWEEKLY:
				handleBiWeekly(timeIntervals);
				break;
			case DAY_OF_WEEK:
				handleDayOfWeek(timeIntervals);
				break;
			case MONTHLY:
				handleMonthly(timeIntervals);
				break;
			case WEEKLY:
				handleWeekly(timeIntervals);
				break;
			case NONE:
				handleNone(timeIntervals);
				break;
			default:
				break;
			
		}
		return timeIntervals;
	}
	/**
	 * No repeat events
	 * @param timeIntervals
	 * @throws ParseException
	 */
	private void handleNone(LinkedList<DateSpan> timeIntervals) throws ParseException
	{
		Date startDate = DateFormatter.getAdvFormat().parse(StartDate +" "+StartTime);
		Date stopDate = DateFormatter.getAdvFormat().parse(StopDate +" "+StopTime);	
		timeIntervals.add(new DateSpan(startDate, stopDate));
	}
	/**
	 * Add 7 days
	 * @param timeIntervals
	 * @throws ParseException
	 */
	private void handleWeekly(LinkedList<DateSpan> timeIntervals) throws ParseException
	{
		Date startDate = DateFormatter.getAdvFormat().parse(StartDate +" "+StartTime);
		Date stopDate = DateFormatter.getAdvFormat().parse(StopDate +" "+StopTime);
		
		GregorianCalendar cStart = new GregorianCalendar();
		cStart.setTime(startDate);
		
		GregorianCalendar cStop = new GregorianCalendar();
		cStop.setTime(stopDate);
		
		while(cStart.getTime().before(lastDate))
		{
			timeIntervals.add( new DateSpan(cStart.getTime(),cStop.getTime()));
			cStop.add(Calendar.DAY_OF_MONTH, 7); //should Work
			cStart.add(Calendar.DAY_OF_MONTH, 7); //should work
			
		}
	}
	/**
	 * Check against bitset for enabled bits
	 * @param timeIntervals
	 * @throws ParseException
	 */
	private void handleDayOfWeek(LinkedList<DateSpan> timeIntervals) throws ParseException
	{
		Date startDate = DateFormatter.getAdvFormat().parse(StartDate +" "+StartTime);
		Date stopDate = DateFormatter.getAdvFormat().parse(StopDate +" "+StopTime);

		GregorianCalendar cStart = new GregorianCalendar();
		cStart.setTime(startDate);
		cStart.set(Calendar.DAY_OF_WEEK, 0);
		
		
		GregorianCalendar cStop = new GregorianCalendar();
		cStop.setTime(stopDate);
		cStop.set(Calendar.DAY_OF_WEEK, 0);
		
		
		while(cStart.getTime().before(lastDate))
		{
			for(byte i = 0; i<7; i++){
				if((rDays>>i & 0x01) == 1){
					
					cStart.set(Calendar.DAY_OF_WEEK, i+1);
					cStop.set(Calendar.DAY_OF_WEEK, i+1);
					DateSpan d = new DateSpan(cStart.getTime(),cStop.getTime());
					timeIntervals.add(d);

					
				}
			}
			cStart.set(Calendar.DAY_OF_WEEK, 0);
			cStop.set(Calendar.DAY_OF_WEEK, 0);
			cStart.add(Calendar.DAY_OF_MONTH, 7);
			cStop.add(Calendar.DAY_OF_MONTH, 7);
		}
		
	}
	
	/**
	 * MUST BE LESS THAN THE 28th OF THE MONTH
	 * @param timeIntervals
	 * @throws ParseException
	 */
	private void handleMonthly(LinkedList<DateSpan> timeIntervals) throws ParseException
	{
		Date startDate = DateFormatter.getAdvFormat().parse(StartDate +" "+StartTime);
		Date stopDate = DateFormatter.getAdvFormat().parse(StopDate +" "+StopTime);
		
		GregorianCalendar cStart = new GregorianCalendar();
		cStart.setTime(startDate);
		
		GregorianCalendar cStop = new GregorianCalendar();
		cStop.setTime(stopDate);
		
		
		while(cStart.getTime().before(lastDate))
		{
			timeIntervals.add( new DateSpan(cStart.getTime(),cStop.getTime()));
			
			int count = cStart.getActualMaximum(Calendar.DAY_OF_MONTH);
			cStop.add(Calendar.DAY_OF_MONTH, count);//Should work
			cStart.add(Calendar.DAY_OF_MONTH, count);

			
		}
	}
	
	/**
	 * Add 14 days
	 * @param timeIntervals
	 * @throws ParseException
	 */
	private void handleBiWeekly(LinkedList<DateSpan> timeIntervals) throws ParseException
	{
		Date startDate = DateFormatter.getAdvFormat().parse(StartDate +" "+StartTime);
		Date stopDate = DateFormatter.getAdvFormat().parse(StopDate +" "+StopTime);
		
		GregorianCalendar cStart = new GregorianCalendar();
		cStart.setTime(startDate);
		
		GregorianCalendar cStop = new GregorianCalendar();
		cStop.setTime(stopDate);
		timeIntervals.add( new DateSpan(cStart.getTime(),cStop.getTime()));
		
		while(cStart.getTime().before(lastDate))
		{
		
			timeIntervals.add( new DateSpan(cStart.getTime(),cStop.getTime()));
			cStop.add(Calendar.DAY_OF_MONTH, 14);//Should work
			cStart.add(Calendar.DAY_OF_MONTH, 14);//Should work
			
		}
	}
}

package Event;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
	
	private static SimpleDateFormat dateFormatter = new SimpleDateFormat("MM-dd-yyyy");;
	private static SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("MM-dd-yyyy hh:mm");
	/**
	 * 
	 * @return date Format
	 */
	public static SimpleDateFormat getFormat(){
		return dateFormatter;
	}
	/**
	 * 
	 * @return dateTime Format
	 */
	public static SimpleDateFormat getAdvFormat(){
		return dateTimeFormatter;
	}
	/**
	 * 
	 * @param type Type of format
	 * @param date Date to format
	 * @return date as string.
	 */
	public static String format(FormatTypes type, Date date){
		switch(type)
		{
		case Date:
			return dateFormatter.format(date);
		case DateTime:
			return dateTimeFormatter.format(date);
		default:
			return dateFormatter.format(date);
		
		}
		
	}
}

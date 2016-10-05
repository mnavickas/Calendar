package Event;
/**
 * Different ways an Event can Repeat
 */
public enum RepeatType {
	/**
	 * Once a week 
	 */
	WEEKLY,
	
	/**
	 * Once every two weeks
	 */
	BIWEEKLY,
	
	/**
	 * Day of Month less than 28..
	 */
	MONTHLY,
	
	/**
	 * One Day Events Only
	 */
	DAY_OF_WEEK, 
	
	/**
	 * No repeat
	 */
	NONE;
}

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
	/**
	 * @return this as a user friendly string
	 */
	public String toPrettyString(){
		switch(this)
		{
		case BIWEEKLY:
			return "Biweekly";
		case DAY_OF_WEEK:
			return "Day Of Week";
		case MONTHLY:
			return "Monthy";
		case NONE:
			return "No Repeat";
		case WEEKLY:
			return "Weekly";
		
		}
		return "";
		
	}
}

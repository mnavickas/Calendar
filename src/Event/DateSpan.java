package Event;

import java.util.Date;
/**
 * Data type to house event time spans
 *
 */
public class DateSpan{
	private Date first;
	private Date second;
	
	public DateSpan(Date l, Date r){
		first = l;
		second = r;
	}
	/**
	 * 
	 * @param end DateSpan to compare against
	 * @return true if there is an intersection
	 */
	public boolean intersects(DateSpan end){
		//http://stackoverflow.com/questions/325933/determine-whether-two-date-ranges-overlap
		 return this.first.before(end.second) && end.first.before(this.second);
	}
	
	/**
	 * Convert this DateSpan to a String.
	 */
	@Override
	public String toString(){
		String span = "(";
		span+= first.toString();
		span+= " to ";
		span+= second.toString();
		span+= ")";
		return span;
	}
}

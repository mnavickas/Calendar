package Event;

import java.util.Date;

public class DateSpan{
	public Date first;
	public Date second;
	
	public DateSpan(Date l, Date r){
		first = l;
		second = r;
	}
	
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

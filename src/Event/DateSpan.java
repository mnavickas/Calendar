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
		 return this.first.before(end.second) && this.second.before(end.first);
	}
}

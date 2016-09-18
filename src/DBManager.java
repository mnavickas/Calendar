package calendar;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * DBManager handles reading and storing information in the database.
 */
public class DBManager {
	private final String URL = "jdbc:mysql://mysql.eecs.ku.edu:3306/layer";
	private final String USER = "layer";
	private final String PASS = "loganayer";

	/*
	 * Get the event data from the date.
	 *
	 * @param date The date to get in the database.
	 * @param user The current user.
	 */ 
	public String getEventFromDate(Date date, String user) throws SQLException {
		//Initialize JDBC variables
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		//Initialize the return value
		String retVal = "";
		
		try {
			//Attempt to connect to database
			con = DriverManager.getConnection(URL, USER, PASS);
			
			//Prepare our SQL
			ps = con.prepareStatement("SELECT i.event "
					+ "FROM cal_date_event i "
					+ "WHERE i.date = ? "
					+ "AND i.user_id = ?;");
			//Input our selected date and user
			ps.setDate(1, date);
			ps.setString(2, user);
			
			//Execute the query
			rs = ps.executeQuery();
			
			//Loop through our results
			while (rs.next()) {
				//Add the event to the return value
				retVal += rs.getString("event");
				//Separate by a new line if other events present
				retVal += "\n";
			}
		} catch (SQLException e) {
		    System.out.println("Cannot connect the database!" + e);
		} finally {
			con.close();
		}
		
		//Return our string of events
		return retVal;
	}

	/*
	 * Set the event for a date.
	 *
	 * @param date The date to set.
	 * @param user The user to access in the database.
	 * @param event The event to store.
	 */
	public void setEventToDate(Date date, String user, String event) throws SQLException{
		//Initialize JDBC variables
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			//Attempt to connect to database
			con = DriverManager.getConnection(URL, USER, PASS);
			
			//Prepare our SQL
			ps = con.prepareStatement("INSERT INTO cal_date_event "
					+ "(user_id, date, event) "
					+ "VALUES "
					+ "(?, ?, ?);");
			//Input our selected date, user, and event
			ps.setString(1, user);
			ps.setDate(2, date);
			ps.setString(3, event);
			
			//Execute the query
			ps.execute();
		} catch (SQLException e) {
		    System.out.println("Cannot connect the database!" + e);
		} finally {
			con.close();
		}
	}
	
}

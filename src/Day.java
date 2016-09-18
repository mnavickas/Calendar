package calendar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;

import java.text.SimpleDateFormat;

import java.util.Calendar;

/*
 * Day class
 *
 * "Day" represents a single day. It determines where on the calendar to put
 * each day and how to read/write to the database. Each "Day" has a
 * corresponding DayView that handles user interface elements.
 */
public class Day {
    // member variables
    private Calendar cal;
    private String fileName;
    public String dayContents;

    // for file I/O
    FileReader fr = null;
    FileWriter fw = null;
    BufferedReader br = null;
    BufferedWriter bw = null;

    /*
     * Instantiate a Day.
     * @param cal The java.util.Calendar object to use for that day.
     *            Seconds and hours are ignored.
     */
    public Day(Calendar cal)
    {
	this.cal = cal;

	// date will be in form MMDD
	this.fileName = ""
	    + String.format("%02d", cal.get(Calendar.MONTH))
	    + String.format("%02d", cal.get(Calendar.DAY_OF_MONTH));

	this.dayContents = "";
    }

    /*
     * Get the agenda for this day.
     * @return String The agenda
     */
    public String getAgenda() {
	return this.dayContents;
    }

    /*
     * Set the agenda for this day.
     * @param agenda The agenda to set
     */
    public void setAgenda(String agenda) {
	this.dayContents = agenda;
    }

    /*
     * Edit the current day.
     */
    public void editDay() throws FileNotFoundException {
	// if the file exists
	if(this.fileName != "") {
	    fr = new FileReader(this.fileName);
	    //br = new BufferedReader(fr);
	} else {
	}
    }

    /*
     * Get the day's number in the current month.
     * This is used by the day view to display each numbered day.
     * @return int This returns the day's number in the month.
     */
    public int getDayNumber() {
	return cal.get(Calendar.DAY_OF_MONTH);
    }
}

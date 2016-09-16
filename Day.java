import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;

import java.text.SimpleDateFormat;

import java.util.Calendar;

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

    public Day(Calendar cal)
    {
	this.cal = cal;

	// date will be in form MMDD
	this.fileName = ""
	    + String.format("%02d", cal.get(Calendar.MONTH))
	    + String.format("%02d", cal.get(Calendar.DAY_OF_MONTH));

	this.dayContents = "";
    }

    public String getAgenda() {
	return this.dayContents;
    }

    public void setAgenda(String agenda) {
	this.dayContents = agenda;
    }

    public void editDay() throws FileNotFoundException {
	// if the file exists
	if(this.fileName != "") {
	    fr = new FileReader(this.fileName);
	    //br = new BufferedReader(fr);
	} else {
	}
    }

    public int getDayNumber() {
	return cal.get(Calendar.DAY_OF_MONTH);
    }
}

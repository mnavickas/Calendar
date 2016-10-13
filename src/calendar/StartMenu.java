package calendar;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import enums.Months;

/**
 * StartMenu class
 *
 * This is the driver file for the Calendar app.  The user will use the three menus to select
 * a view type, a month, and a specific date.  When the user hits submit,
 *
 */

public class StartMenu extends JPanel
{


	/**
	 * 
	 */
	private static final long serialVersionUID = -7949455824962153307L;
	/**
	 * static variables to be used in the generation of the menu
	 */
	private  JComboBox viewType;
	private  JComboBox selectMonth;
	private  JComboBox selectDay;
	private  JComboBox selectDay2;
	private  JComboBox selectDay3;



	/**
	 * member variables for the start menu to use
	 */
	private String monthChoice="";
	private int dayChoice;
	private String viewChoice="";




	/**
	 * additional static variables that will be used in displaying the user's choices
	 */

	private static String [] mwd = {"Yearly View","Monthly View","Weekly View","Daily View"};
	private static String [] months = {"August","September","October","November","December","January","February","March","April","May"};

	private static String [] days = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
	private static String [] days2 = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"};
	private static String [] days3 = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28"};



	// JFrame instance that will be set to the start menu
	public static JFrame menu;
	public static void main(String[] args)
	{
		initialize();
	}

	/**
	 * Allows the start menu to re-initialize
	 */
	public static void initialize()
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				menu = new StartMenuFrame("Calendar");

			}
		});
	}

	/**
	 * will retrieve the start menu frame
	 * @return the main menu for user interaction
	 */
	public final JFrame getMainFrame()
	{
		return menu;
	}


	/**
	 * StartMenu constructor,  sets GridBagLayout
	 */
	public StartMenu()
	{

		monthChoice = "August";
		Dimension size = getPreferredSize();
		size.width = 500;
		setPreferredSize(size);

		// menu instructions
		JLabel date = new JLabel("Select a date to start with: ");
		JLabel view = new JLabel("Select a View to start with: ");

		viewType = new JComboBox(mwd); //select month,week, or day view
		selectMonth = new JComboBox(months); //select month,week, or day view


		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.LAST_LINE_END;
		gc.weightx = .5;
		gc.weighty = 0;

		gc.gridx = 0;
		gc.gridy = 0;
		add(view,gc);
		gc.anchor = GridBagConstraints.LINE_END;

		gc.gridx = 0;
		gc.gridy = 1;
		add(date,gc);

		gc.anchor = GridBagConstraints.LAST_LINE_START;
		gc.gridx = 1;
		gc.gridy = 0;
		add(viewType,gc);





		gc.anchor = GridBagConstraints.LINE_START;
		gc.gridx = 1;
		gc.gridy = 1;
		add(selectMonth,gc);


		selectDay = new JComboBox(days); //select day to start with
		selectDay2 = new JComboBox(days2); //select day to start with
		selectDay3 = new JComboBox(days3); //select day to start with

		gc.anchor = GridBagConstraints.CENTER;
		gc.gridx = 1;
		gc.gridy = 1;
		add(selectDay,gc);
		selectDay.setVisible(true);
		add(selectDay2,gc);
		selectDay2.setVisible(false);
		add(selectDay3,gc);
		selectDay3.setVisible(false);



		ActionListener cbActionListener = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				monthChoice= (String) selectMonth.getSelectedItem();
				dayChoice=  selectDay.getSelectedIndex()+1;


				if(monthChoice.equals("August") || monthChoice.equals("October")|| monthChoice.equals("December") || monthChoice.equals("January") || monthChoice.equals("March") || monthChoice.equals("May"))
				{
					selectDay.setVisible(true);
					selectDay2.setVisible(false);
					selectDay3.setVisible(false);
				}


				else if(monthChoice.equals("September") || monthChoice.equals("November") || monthChoice.equals( "April"))
				{
					selectDay.setVisible(false);
					selectDay2.setVisible(true);
					selectDay3.setVisible(false);
				}


				else if(monthChoice.equals("February"))
				{
					selectDay.setVisible(false);
					selectDay2.setVisible(false);
					selectDay3.setVisible(true);
				}
			}
		};
		selectMonth.addActionListener(cbActionListener);
		

		final JButton button = new JButton("Submit");
		gc.anchor = GridBagConstraints.LINE_START;
		gc.gridx = 1;
		gc.gridy = 3;
		add(button,gc);

		button.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent arg0)
			{
				if(monthChoice.equals( "August") || monthChoice.equals("October")|| monthChoice.equals("December") || monthChoice.equals("January") || monthChoice.equals("March") || monthChoice.equals("May"))
				{
					dayChoice = 	selectDay.getSelectedIndex()+1;
				}
				else if(monthChoice.equals("September") || monthChoice.equals("November") || monthChoice.equals( "April"))
				{
					dayChoice = 	selectDay2.getSelectedIndex()+1;
				}
				else if(monthChoice.equals("Febuary"))
				{
					dayChoice = 	selectDay3.getSelectedIndex()+1;
				}
				monthChoice= (String) selectMonth.getSelectedItem();
				viewChoice= (String) viewType.getSelectedItem();
				toMainProgram(viewChoice,monthChoice,dayChoice);
			}
		});

	}




	public static View viewTypeEnum;
	public static int YearHold, monthHold, dayHold;
	/**
	 * ToMainProgram takes the JcomboBox values as strings, converts the inputs into ints and returns the values to the respective viewtype class
	 * @param month represents months in an String form
	 * @param day day represented as int
	 * @param view is a string that determines which viewtype class will initialize
	 */
	public static void toMainProgram(String view, String month, int day)
	{

		int dayCount = day;
		int monthCount = Months.valueOf(month).ordinal();
		int year = (monthCount<7) ? 2017 : 2016;

		YearHold = year;
		monthHold = monthCount;
		dayHold = day;

		if(view=="Yearly View")
		{
			YearView.initialize(year,monthCount,dayCount);
			viewTypeEnum = View.Year;
		}
		if(view=="Monthly View")
		{
			MonthView.initialize(year,monthCount);
			viewTypeEnum = View.Month;
		}
		if(view=="Weekly View")
		{
			WeekView.initialize(year, monthCount, dayCount);
			viewTypeEnum = View.Week;
		}
		if(view=="Daily View")
		{
			DayViewPopup.initialize(year,monthCount, dayCount);
			viewTypeEnum = View.Day;
		}
		menu.dispose();
	}
}

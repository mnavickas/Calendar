package Event;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import enums.Months;

public class EventPlanner extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5294561201437336422L;


	private static final String[] time_strings = new String[2*12*4];

	/**
	 *  Initialize the time String array in chronological order
	 */
	static{
		int count = 0;
		for(int t = 0; t<2; t++)
		{
			for(int i = 0; i< 60; i+=15,count++){
				time_strings[count]="12:"+i;
				if(i == 0)
					time_strings[count]+=0;
				if(t==0)
					time_strings[count]+="am";
				else
					time_strings[count]+="pm";
			}
			for(int i = 1; i<12;i++)
			{
				for(int j = 0; j< 60; j+=15,count++){
					time_strings[count]=i+":"+j;
					if(j == 0)
						time_strings[count]+=0;
					if(t==0)
						time_strings[count]+="am";
					else
						time_strings[count]+="pm";
				}
			}
		}

	}

	private static String [] months = 	{
			"August",
			"September",
			"October",
			"November",
			"December",
			"January",
			"February",
			"March",
			"April",
			"May"
	};



	private JComboBox<String> startTime;
	private JComboBox<String> endTime;
	private JLabel startTimeLabel;
	private JLabel endTimeLabel;

	private JLabel startDateLabel;
	private JLabel endDateLabel;
	private JTextField startDate;

	private JComboBox<String> monthList 		= null;
	private JComboBox<String> dayList			= null;
	private JComboBox<String> yearList			= null;

	private JTextField nameText;
	private JTextArea descriptionText;
	
	private JCheckBox Sunday;
	private JCheckBox Monday;
	private JCheckBox Tuesday;
	private JCheckBox Wednesday;
	private JCheckBox Thursday;
	private JCheckBox Friday;
	private JCheckBox Saturday;
	
	private JRadioButton DoW;
	private JRadioButton Weekly;
	private JRadioButton Monthly;
	private JRadioButton BiWeekly;
	private JRadioButton None;

	private static final int TEXT_BOX_LENGTH = 300;

	private static final int DESCRIPTION_Y = 250;
	private static final int DESCRIPTION_HEIGHT = 200;

	private static final int NAME_Y = 45;
	private static final int NAME_HEIGHT = 30;

	private static final int FRAME_HEIGHT = 750;
	private static final int FRAME_WIDTH = 400;

	private innerSaveClass saveClass;

	private String mDate;

	private static  EventPlanner mInstance;

	public static void create(Date dateOn)
	{

		if(mInstance == null ){
			mInstance = new EventPlanner(dateOn);
		}
		else{
			mInstance.toFront();
		}
	}

	private EventPlanner(Date dateOn)
	{

		saveClass = new innerSaveClass();
		mDate = DateFormatter.format(FormatTypes.Date,dateOn);

		setTitle("New Event for "+mDate);
		setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setMinimumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		setResizable(false);
		
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				mInstance = null;
			}
		});


		/*
		 * Event name field
		 */
		{
			JLabel nameLabel = new JLabel("Event Name:");
			nameLabel.setBounds(50,NAME_Y-40,250,50);
			//nameLabel.setFont(FontManager.getStandardFont());
			add(nameLabel);

			nameText = new JTextField();
			//nameText.setFont(FontManager.getStandardFont());
			nameText.setBorder(BorderFactory.createLoweredBevelBorder());
			nameText.setBounds(50, NAME_Y, TEXT_BOX_LENGTH, NAME_HEIGHT);
			add(nameText);	
		}


		{
			startDateLabel = new JLabel("Start Date:");
			startDateLabel.setBounds(50,80,150,30);
			add(startDateLabel);

			startDate = new JTextField(mDate);
			startDate.setEditable(false);
			startDate.setBounds(50,105,100,30);
			add(startDate);

			endDateLabel = new JLabel("End Date:");
			endDateLabel.setBounds(250,80,150,30);
			add(endDateLabel);

			initMonthBox();
			initDayBox();
			initYearBox();

			startTimeLabel = new JLabel("Start Time:");
			startTimeLabel.setBounds(50,145,150,30);
			add(startTimeLabel);

			startTime = new JComboBox<String>(time_strings);
			startTime.setBounds(50,175,110,30);
			add(startTime);

			endTimeLabel = new JLabel("End Time:");
			endTimeLabel.setBounds(250,145,150,30);
			add(endTimeLabel);

			endTime = new JComboBox<String>(time_strings);
			endTime.setBounds(250,175,110,30);
			add(endTime);

		}
		
		//Repeat Type Fields
		{
			
			Weekly = new JRadioButton("Weekly");
			Weekly.setBounds(0,210,70,50);
			Weekly.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					
						boolean is = DoW.isSelected();
						Sunday.setEnabled(is);
						Monday.setEnabled(is);
						Tuesday.setEnabled(is);
						Wednesday.setEnabled(is);
						Thursday.setEnabled(is);
						Friday.setEnabled(is);
						Saturday.setEnabled(is);
						
				
				}
			});
			BiWeekly = new JRadioButton("BiWeekly");
			BiWeekly.setBounds(80,210,80,50);
			BiWeekly.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					
						boolean is = DoW.isSelected();
						Sunday.setEnabled(is);
						Monday.setEnabled(is);
						Tuesday.setEnabled(is);
						Wednesday.setEnabled(is);
						Thursday.setEnabled(is);
						Friday.setEnabled(is);
						Saturday.setEnabled(is);
						
				
				}
			});
			Monthly = new JRadioButton("Monthly");
			Monthly.setBounds(170, 210,70,50);
			Monthly.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					
						boolean is = DoW.isSelected();
						Sunday.setEnabled(is);
						Monday.setEnabled(is);
						Tuesday.setEnabled(is);
						Wednesday.setEnabled(is);
						Thursday.setEnabled(is);
						Friday.setEnabled(is);
						Saturday.setEnabled(is);
						
				
				}
			});
			
			DoW = new JRadioButton("Day Of Week");
			DoW.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					
						boolean is = DoW.isSelected();
						Sunday.setEnabled(is);
						Monday.setEnabled(is);
						Tuesday.setEnabled(is);
						Wednesday.setEnabled(is);
						Thursday.setEnabled(is);
						Friday.setEnabled(is);
						Saturday.setEnabled(is);
						
				
				}
			});
			DoW.setBounds(270, 210,100,50);
			
			None= new JRadioButton("None");
			None.setBounds(170, 250,100,50);
			None.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					
						boolean is = DoW.isSelected();
						Sunday.setEnabled(is);
						Monday.setEnabled(is);
						Tuesday.setEnabled(is);
						Wednesday.setEnabled(is);
						Thursday.setEnabled(is);
						Friday.setEnabled(is);
						Saturday.setEnabled(is);
						
				
				}
			});
			None.setSelected(true);
			
			ButtonGroup bg = new ButtonGroup();
	
			bg.add(Weekly);
			bg.add(BiWeekly);
			bg.add(Monthly);
			bg.add(DoW);
			bg.add(None);
			
			add(Weekly);
			add(BiWeekly);
			add(Monthly);
			add(DoW);
			add(None);
			
		}
		
		{
			Sunday = new JCheckBox("Sunday");
			Sunday.setBounds(50, 300,100,30);
			Sunday.setEnabled(false);
			add(Sunday);
			Monday = new JCheckBox("Monday");
			Monday.setBounds(50, 330,100,30);
			Monday.setEnabled(false);
			add(Monday);
			Tuesday = new JCheckBox("Tuesday");
			Tuesday.setBounds(50, 360,100,30);
			Tuesday.setEnabled(false);
			add(Tuesday);
			Wednesday = new JCheckBox("Wednesday");
			Wednesday.setBounds(50, 390,100,30);
			Wednesday.setEnabled(false);
			add(Wednesday);
			Thursday = new JCheckBox("Thursday");
			Thursday.setBounds(250, 300,100,30);
			Thursday.setEnabled(false);
			add(Thursday);
			Friday = new JCheckBox("Friday");
			Friday.setBounds(250, 330,100,30);
			Friday.setEnabled(false);
			add(Friday);
			Saturday = new JCheckBox("Saturday");
			Saturday.setBounds(250, 360,100,30);
			Saturday.setEnabled(false);
			add(Saturday);
			
		}

		/*
		 * Event Description Field
		 */
		{
			JLabel descriptionLabel = new JLabel("Brief Event Description:");
			descriptionLabel.setBounds(50,DESCRIPTION_Y+170,250,50);
			add(descriptionLabel);

			descriptionText = new JTextArea();
			descriptionText.setLineWrap(true);
			descriptionText.setWrapStyleWord(true);
			descriptionText.setBorder(BorderFactory.createLoweredBevelBorder());
			descriptionText.setBounds(50, DESCRIPTION_Y+210, TEXT_BOX_LENGTH, DESCRIPTION_HEIGHT);

			descriptionText.addKeyListener(new KeyListener(){

				@Override
				public void keyPressed(KeyEvent arg0) {
					if( arg0.getKeyCode() == KeyEvent.VK_ENTER){
						saveClass.save();
					}
				}

				@Override
				public void keyReleased(KeyEvent arg0) {
					//Intentionally left blank
				}

				@Override
				public void keyTyped(KeyEvent arg0) {
					//Intentionally left blank
				}

			});
			add(descriptionText);
		}


		/*
		 * Submit Button
		 */
		{
			JButton submit = new JButton("Save");
			submit.setBounds(100, 680, 200, 30);
			submit.addActionListener(saveClass);
			add(submit);
		}

		setVisible(true);
	}

	/**
	 * Save the event to JSON and exit this frame
	 * @throws IrregularFormatException
	 */
	private void saveAndExit()
	{
		
		Event event = new Event();

		event.Name = nameText.getText();
		event.Description =  descriptionText.getText();
		event.StartDate = mDate;

		String dateEnd = Months.valueOf(months[monthList.getSelectedIndex()]).get()+1+"-";
		dateEnd+=dayList.getSelectedIndex()+1+"-";
		dateEnd+=yearList.getSelectedIndex()+2016;
		event.StopDate = dateEnd;

		event.StartTime = startTime.getSelectedItem().toString();
		event.StopTime = endTime.getSelectedItem().toString();

		if(DoW.isSelected())
		{
			event.rType = RepeatType.DAY_OF_WEEK;
		}else if (Weekly.isSelected()){
			event.rType = RepeatType.WEEKLY;
		}else if (Monthly.isSelected()){
			event.rType = RepeatType.MONTHLY;
		}else if(BiWeekly.isSelected()){
			event.rType = RepeatType.BIWEEKLY;
		}else{
			event.rType = RepeatType.NONE;
		}
		
		if(event.rType == RepeatType.DAY_OF_WEEK){
			event.rDays = 0;
			event.rDays |= (Sunday.isSelected()?1:0)    <<0;
			event.rDays |= (Monday.isSelected()?1:0)    <<1;
			event.rDays |= (Tuesday.isSelected()?1:0)   <<2;
			event.rDays |= (Wednesday.isSelected()?1:0) <<3;
			event.rDays |= (Thursday.isSelected()?1:0)  <<4;
			event.rDays |= (Friday.isSelected()?1:0)    <<5;
			event.rDays |= (Saturday.isSelected()?1:0)  <<6;
		}else{
			event.rDays = 0;
		}

		Random r = new Random(System.nanoTime());
		event.unique_id = r.nextLong();


		EventCache.getInstance().addEvent(event);
		JOptionPane.showMessageDialog(this, "Event Saved!","Event Planner",JOptionPane.INFORMATION_MESSAGE);
		dispose();
		
	}

	/**
	 * Initialize Month JComboBox
	 */
	private void initMonthBox()
	{
		monthList = new JComboBox<String>(months);

		monthList.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				resetDayBox();
			}
		});
		monthList.setBounds(180,105,100,30);
		add(monthList);
	}
	
	/**
	 * Initialize day JComboBox
	 */
	private void initDayBox()
	{
		Calendar cal = Calendar.getInstance();

		int count = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

		String[] names = new String[count];
		for(int i = 1; i<=count; i++){
			names[i-1] = i+"";
		}

		dayList = new JComboBox<String>(names);


		dayList.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {

			}
		});
		dayList.setBounds(280,105,50,30);
		add(dayList);
	}
	
	/**
	 * Initialize year JComboBox
	 */
	private void initYearBox()
	{
		String[] years = {"2016","2017"};

		yearList = new JComboBox<String>(years);
		yearList.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {

				Months m = Months.valueOf(months[monthList.getSelectedIndex()]);
				if( m == Months.February ){
					resetDayBox();
				}

			}
		});
		yearList.setBounds(330,105,60,30);
		add(yearList);
	}
	
	private void resetDayBox()
	{
		int temp = dayList.getSelectedIndex()+1;

		dayList.removeAllItems();
		Calendar cal = Calendar.getInstance();
		Months m = Months.valueOf(months[monthList.getSelectedIndex()]);
		cal.set(Calendar.MONTH, m.get());
		cal.set(Calendar.YEAR, yearList.getSelectedIndex()+2016);

		int count = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

		temp = (temp<=count)?temp:count;
		temp--;

		for(int i = 1; i<=count; i++){

			dayList.addItem(i+"");
		}
		dayList.setSelectedIndex(temp);
	}
	
	private class innerSaveClass implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			save();	
		}

		private void save()
		{
			if(nameText.getText().equals(""))
			{
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(null, "Please name your event.","Error",JOptionPane.WARNING_MESSAGE);
			}
			else if(startTime.getSelectedIndex()>endTime.getSelectedIndex())//TODO account for multiday
			{
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(null, "Your event has negative time :( ","Time Machine?",JOptionPane.WARNING_MESSAGE);
			}
			else
			{
				saveAndExit();	
			}
			mInstance = null;
		}
	}
}

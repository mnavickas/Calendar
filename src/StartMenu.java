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
import javax.swing.JOptionPane;
import javax.swing.JTextField;

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
         * static variables to be used in the generation of the menu
        */
        public static JComboBox viewType;
        public static JComboBox selectMonth;
        public static JComboBox selectDay;
        public static JComboBox selectDay2;
        public static JComboBox selectDay3;
        public static JComboBox selectDay4;


        /**
         * member variables for the start menu to use
        */
        private String monthChoice="";
        private String dayChoice="";
        private String viewChoice="";
        private String yearChoice="";
        private int choice =0;



        /**
         * additional static variables that will be used in displaying the user's choices
         */

        private static String [] mwd = {"Yearly View","Monthly View","Weekly View","Daily View"};
        private static String [] months = {"August","September","October","November","December","January","Febuary","March","April","May"};

        private static String [] days = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
        private static String [] days2 = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"};
        private static String [] days3 = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28"};
        private StartMenu mainWindowInstance;



        // JFrame instance that will be set to the start menu
        public static JFrame menu;
        public static void main(String[] args)
        {

        	SwingUtilities.invokeLater(new Runnable()
                {
        			public void run(){
        			menu = new StartMenuFrame("Calendar");
        			menu.setSize(850,700);

        			menu.setResizable(true); //http://stackoverflow.com/questions/18031704/jframe-how-to-disable-window-resizing
        			menu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //http://stackoverflow.com/questions/258099/how-to-close-a-java-swing-application-from-the-code

        			menu.setVisible(true);
        		}
                });
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
                                menu.setSize(850,700);

                                menu.setResizable(true); //http://stackoverflow.com/questions/18031704/jframe-how-to-disable-window-resizing

                                menu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //http://stackoverflow.com/questions/258099/how-to-close-a-java-swing-application-from-the-code

                                menu.setVisible(true);
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
         * @param size sets the size of the JFrame
         * @param viewtype is a JComboBox that contains different viewtypes
         * @param selectMonth is a JComboBox that contains different months
         * @param selectDay is a JComboBox that contains different days
         * @param selectDay2 is a JComboBox that holds an array of strings 1-30
         * @param selectDay2 is a JComboBox that holds an array of strings 1-28
         * @param cbActionListener is a JComboBox action listener that displays the correct days of the month when the user selects a month
        */
        public StartMenu()
        {

                //
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
                                dayChoice= (String) selectDay.getSelectedItem();


                                if(monthChoice == "August" || monthChoice=="October" || monthChoice== "December" || monthChoice=="January" || monthChoice=="March" || monthChoice=="May")
                                {
                                        selectDay.setVisible(true);
                                        selectDay2.setVisible(false);
                                        selectDay3.setVisible(false);
                                }


                                else if(monthChoice == "September" || monthChoice== "November" || monthChoice== "April")
                                {
                                        selectDay.setVisible(false);
                                        selectDay2.setVisible(true);
                                        selectDay3.setVisible(false);
                                }


                                else if(monthChoice=="Febuary")
                                {
                                        selectDay.setVisible(false);
                                        selectDay2.setVisible(false);
                                        selectDay3.setVisible(true);
                                }
                        }
                };
                selectMonth.addActionListener(cbActionListener);


		// user input label
		JLabel userLabel = new JLabel("User name to use (16 char limit): ");
		gc.anchor = GridBagConstraints.LINE_END;
                gc.gridx = 0;
                gc.gridy = 2;
                add(userLabel,gc);

		// user input field
		JTextField userField = new JTextField("testuser", 20);

		// from http://stackoverflow.com/questions/4527604/jtextfield-only-shows-as-a-slit-using-gridbaglayout-need-help
		userField.setMinimumSize(userField.getPreferredSize());

		gc.anchor = GridBagConstraints.LINE_START;
                gc.gridx = 1;
                gc.gridy = 2;
		add(userField,gc);


                final JButton button = new JButton("Submit");
                gc.anchor = GridBagConstraints.LINE_START;
                gc.gridx = 1;
                gc.gridy = 3;
                add(button,gc);

                button.addActionListener(new ActionListener()
                {

                        public void actionPerformed(ActionEvent arg0)
                        {

                        	dayChoice= (String) selectDay.getSelectedItem();
                                monthChoice= (String) selectMonth.getSelectedItem();
                        	viewChoice= (String) viewType.getSelectedItem();

				String userText = userField.getText();
				if (userText.length() > 16) {
				    userText = userText.substring(0, 16);
				}

                        	mainWindowInstance = new StartMenu();
                        	disposeframe();
                        	toMainProgram(viewChoice,monthChoice,dayChoice,userText);
                        }
                });

        }




        /**
         * ToMainProgram takes the JcomboBox values as strings, converts the inputs into ints and returns the values to the respective viewtype class
         * @param mc represents months in an integer form; beginning at 0 for January to 11 for December
         * @param dc converts the string of days to the appropriate integer form.
         * @param year sets the value of the year depending on the month of the shcool year.
         * @param view is a string that determines which viewtype class will initialize
         * @param username The username to use for the database.
        */
        
        //To Main Program
        public static void toMainProgram(String view, String month, String day, String username)
        {
                DBManager db = new DBManager(username);

                int mc = 0;
                int dc = Integer.parseInt(day);

                if(month=="January")
                {
                        mc=0;
                }
                if(month=="Febuary")
                {
                        mc=1;
                }
                if(month=="March")
                {
                        mc=2;
                }
                if(month=="April")
                {
                        mc=3;
                }
                if(month=="May")
                {
                        mc=4;
                }
                if(month=="August")
                {
                        mc=7;
                }
                if(month=="September")
                {
                        mc=8;
                }
                if(month=="October")
                {
                        mc=9;
                }
                if(month=="November")
                {
                        mc=10;
                }
                if(month=="December")
                {
                        mc=11;
                }

                int year;
                if(mc<7)
                {
                        year = 2017;
                }
                else
                {
                        year = 2016;
                }
                if(view=="Yearly View")
                {
                        YearView.initialize(db);
                }

                if(view=="Monthly View")
                {
		    MonthView.initialize(year,mc,db);
                }
                if(view=="Weekly View")
                {
		    WeekView.initialize(year, mc, dc, db);
                }
                if(view=="Daily View")
                {
		    DayView.initialize(year, mc, dc, false, db);
                }

        }


        //MonthlyView1.main(months);
        public void disposeframe()
        {
                // Disposing menu frame
                //http://stackoverflow.com/questions/7122349/disposing-jframe-from-another-class
                mainWindowInstance.getMainFrame().dispose();
        }




}

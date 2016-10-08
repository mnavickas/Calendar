package calendar;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



/**
 * YearView class
 *
 * The YearView will display all ten possible instances of MonthView in a single frame.
 * The dates within each month can be clicked by the user, allowing the user to edit the day's details.
*/
public class YearView extends JPanel
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 7000411652144795187L;
		// member variables used by the class
        MonthView[] months;
        JPanel panelNorth;
        JPanel panelCenter;
        JPanel panelGap;
        JLabel yearViewTitle;
        int[] academicMonths;
        String[] academicMonthsStrings;
        String[] days;


        /**
         * Creates an instance of the YearView within a JFrame
         * @param db The database to load from
        */
        public static void initialize(int y, int m, int d)
        {
                JFrame frame = new JFrame("Year View");


                // returns to the main start menu
                YearView view = new YearView();
                JButton button = new JButton("Back to main menu");
                frame.add(button,BorderLayout.NORTH);
                frame.add(view, BorderLayout.CENTER);
                frame.pack();
                frame.setVisible(true);

                // action that occurs when the back button is selected
                button.addActionListener(new ActionListener()
                {

                      public void actionPerformed(ActionEvent arg0)
                      {
                        StartMenuFrame.initialize();
                        frame.dispose();
                      }
                });

        }

        /**
         * Yearview constructor
         * @param db The database to load days from.
        */
        public YearView()
        {

                super( new BorderLayout() );

                this.panelNorth = new JPanel( new GridLayout(2,1) );

                this.panelCenter = new JPanel( new GridLayout(4,5) );
                this.panelGap = new JPanel( new GridLayout(1,1) );


                // creates the view's label and adds it to the frame
                this.yearViewTitle = new JLabel("Academic School Year: 2016/2017");
                panelNorth.add(yearViewTitle);
                panelNorth.add( new JLabel("") );
                add(panelNorth, BorderLayout.NORTH);

                // useful member variables for labeling the month instances
                this.academicMonths = new int[] { Calendar.AUGUST , Calendar.SEPTEMBER , Calendar.OCTOBER , Calendar.NOVEMBER , Calendar.DECEMBER , Calendar.JANUARY , Calendar.FEBRUARY , Calendar.MARCH , Calendar.APRIL , Calendar.MAY };

                this.academicMonthsStrings = new String[] { "August" , "September" , "October" , "November" , "December" , "January" , "February" , "March" , "April" , "May" };

                this.days = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };

                this.months = new MonthView[10];
                int curYear = 2016;



                // interates through array of MonthView instances that will be placed within the JFrame
                for(int A = 0 ; A < 10 ; A++)
                {
                        // for titling the months correctly
                        if(A >= 5)
                        {
                                curYear = 2017;
                        }

                        // creates the month and adds it to the frame
                        this.months[A] = new MonthView( new Month(curYear, academicMonths[A]), false);

                        JPanel panel1 = new JPanel(new GridLayout(1,1));
                        panel1.add( new JLabel(academicMonthsStrings[A] + " " + Integer.toString(curYear) ) );
                        months[A].add(panel1, BorderLayout.NORTH);
                }



                // adds an empty gap between adjacent month panels in the frame
                for(int A = 0 ; A < 10 ; A++)
                {
                        this.panelCenter.add( months[A] );
                        {
                                this.panelCenter.add( new JPanel( new GridLayout(1,1) ) );
                        }
                }
                add(this.panelCenter, BorderLayout.CENTER);


        }




}

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

import java.util.Calendar;
import java.util.GregorianCalendar;





public class YearView extends JPanel
{

        MonthView[] months;
        JPanel panelNorth;
        JPanel panelCenter;
        JPanel panelGap;
        JLabel yearViewTitle;
        int[] academicMonths;
        String[] academicMonthsStrings;
        String[] days;



        public static void initialize()
        {
                JFrame frame = new JFrame("Year View");

                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                YearView view = new YearView();
                JButton button = new JButton("BACK");
                frame.add(button,BorderLayout.NORTH);
                frame.add(view, BorderLayout.CENTER);
                frame.pack();
                frame.setVisible(true);
              button.addActionListener(new ActionListener()
              {

                      public void actionPerformed(ActionEvent arg0)
                      {
                        StartMenu.initialize();
                        frame.dispose();
                      }
              });
        }


        public YearView()
        {
                super( new BorderLayout() );

                this.panelNorth = new JPanel( new GridLayout(2,1) );

                this.panelCenter = new JPanel( new GridLayout(4,5) );
                this.panelGap = new JPanel( new GridLayout(1,1) );

                // odd number --> panelCenter and even number --> panelGap
                // will help with alternating the sizes of adjacent panels
                int whichPanel = 1;


                this.yearViewTitle = new JLabel("Academic School Year: 2016/2017");
                panelNorth.add(yearViewTitle);
                panelNorth.add( new JLabel("") );
                add(panelNorth, BorderLayout.NORTH);


                this.academicMonths = new int[] { Calendar.AUGUST , Calendar.SEPTEMBER , Calendar.OCTOBER , Calendar.NOVEMBER , Calendar.DECEMBER , Calendar.JANUARY , Calendar.FEBRUARY , Calendar.MARCH , Calendar.APRIL , Calendar.MAY };

                this.academicMonthsStrings = new String[] { "August" , "September" , "October" , "November" , "December" , "January" , "February" , "March" , "April" , "May" };

                this.days = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };



                this.months = new MonthView[10];
                int curYear = 2016;

                for(int A = 0 ; A < 10 ; A++)
                {
                        if(A >= 5)
                        {
                                curYear = 2017;
                        }

                        this.months[A] = new MonthView( curYear, academicMonths[A], false );

                        JPanel panel1 = new JPanel(new GridLayout(1,1));
                        panel1.add( new JLabel(academicMonthsStrings[A] + " " + Integer.toString(curYear) ) );
                        months[A].add(panel1, BorderLayout.NORTH);
                }




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

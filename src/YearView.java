import java.awt.GridLayout;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

import java.util.Calendar;
import java.util.GregorianCalendar;





public class YearView extends JPanel
{

        MonthView[] months;


        public static void main(String[] args)
        {
                JFrame frame = new JFrame("Year View");
                YearView view = new YearView();

                frame.add(view, BorderLayout.CENTER);
                frame.pack();
                frame.setVisible(true);

        }


        public YearView()
        {
                super( new BorderLayout() );

                Calendar calendar2016 = new GregorianCalendar(2016, 8, 1);
                Calendar calendar2017 = new GregorianCalendar(2017, 1, 1);

                JPanel panelNorth = new JPanel( new GridLayout(2,1) );
                JPanel panelCenter = new JPanel( new GridLayout(4,5) );
                JPanel panelGap = new JPanel( new GridLayout(1,1) );


                JLabel year = new JLabel("Academic School Year: 2016/2017");
                panelNorth.add(year);
                panelNorth.add( new JLabel("") );
                add(panelNorth, BorderLayout.NORTH);


                int[] academicMonths = new int[] { Calendar.AUGUST , Calendar.SEPTEMBER , Calendar.OCTOBER , Calendar.NOVEMBER , Calendar.DECEMBER , Calendar.JANUARY , Calendar.FEBRUARY , Calendar.MARCH , Calendar.APRIL , Calendar.MAY };

                String[] academicMonthsStrings = { "August" , "September" , "October" , "November" , "December" , "January" , "February" , "March" , "April" , "May" };

                String[] days = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };



                months = new MonthView[10];
                int curYear = 2016;

                for(int A = 0 ; A < 10 ; A++)
                {
                        if(A >= 5)
                        {
                                curYear = 2017;
                        }

                        months[A] = new MonthView( curYear, academicMonths[A] );


                        JPanel panel1 = new JPanel(new GridLayout(1,1));
                        panel1.add( new JLabel(academicMonthsStrings[A] + " " + Integer.toString(curYear) ) );
                        months[A].add(panel1, BorderLayout.NORTH);


                        panelCenter.add( months[A] );

                        panelCenter.add( new JPanel( new GridLayout(1,1) ) );
                }
                add(panelCenter, BorderLayout.CENTER);

        }




}

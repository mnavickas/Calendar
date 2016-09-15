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
public class startMenu extends JPanel
{
	public static JFrame menu;
	public static void main(String[] args)
	{
		
		SwingUtilities.invokeLater(new Runnable(){
				public void run(){
				menu = new startMenuFrame("Calendar");
				menu.setSize(500,400);
				menu.setResizable(false); //http://stackoverflow.com/questions/18031704/jframe-how-to-disable-window-resizing
				menu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //http://stackoverflow.com/questions/258099/how-to-close-a-java-swing-application-from-the-code
				menu.setVisible(true);
			}
	});
}
	public final JFrame getMainFrame(){
		return menu;
	}
	private JComboBox viewType;
	private JComboBox selectMonth;
	private JComboBox selectDay;
	private String monthChoice="";
	private String dayChoice="";
	private String viewChoice="";
	private int choice =0;
	private static String [] mwd = {"Monthly View","Weekly View","Daily View"};
	private static String [] months = {"August","September","October","November","December","January","Febuary","March","April","May"};
	private static String [] days = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
	private startMenu mainWindowInstance;

	
	public startMenu(){
	Dimension size = getPreferredSize();
	size.width = 500;
	setPreferredSize(size);
	JLabel date = new JLabel("Select a date to start with: ");
	JLabel view = new JLabel("Select a View to start with: ");
	viewType = new JComboBox(mwd); //select month,week, or day view
	selectMonth = new JComboBox(months); //select month,week, or day view
	selectDay = new JComboBox(days); //select day to start with
	
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

	
	ActionListener cbActionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	monthChoice= (String) selectMonth.getSelectedItem();
           	dayChoice= (String) selectDay.getSelectedItem();
        	if(monthChoice == "September" && dayChoice == "29")
        	{
        		toMainProgram();


        	}
        	else if(monthChoice =="September")
        	{

        	}



        }
    };
	selectMonth.addActionListener(cbActionListener);
	

	gc.anchor = GridBagConstraints.CENTER;
	gc.gridx = 1;
	gc.gridy = 1;
	add(selectDay,gc);

	final JButton button = new JButton("Submit");
	gc.anchor = GridBagConstraints.LINE_START;
	gc.gridx = 1;
	gc.gridy = 2;
	add(button,gc);
    button.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent arg0) {
        	dayChoice= (String) selectDay.getSelectedItem();
        	monthChoice= (String) selectMonth.getSelectedItem();
        	viewChoice= (String) viewType.getSelectedItem();
        	if(monthChoice=="May" || dayChoice=="1" || viewChoice=="Monthly View")
        	{
        		mainWindowInstance = new startMenu();
        		disposeframe();
        		toMainProgram();      	
        		}
        }
    });
}
//To Main Program
	public static void toMainProgram(){
		
		MonthlyView1.main(months);
}
public void disposeframe(){ 
	// Disposing menu frame
	//http://stackoverflow.com/questions/7122349/disposing-jframe-from-another-class
	mainWindowInstance.getMainFrame().dispose();
}
}


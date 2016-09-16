import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;

public class startMenuFrame extends JFrame{
	public static Container c;
	private startMenu menuDetails;
	
	public startMenuFrame(String title){
		super(title);
		
		setLayout(new BorderLayout());
		menuDetails = new startMenu();

		//JButton button = new JButton("Submit");
		Container c = getContentPane();

		//c.add(button,BorderLayout.SOUTH);
		c.add(menuDetails,BorderLayout.CENTER);
	}
	
}

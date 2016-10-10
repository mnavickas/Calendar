package calendar;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;

/**
 * Frame used by StartMenu.
 * Tutorial used to get familiar with JavaSwing https://www.youtube.com/watch?v=svM0SBFqp4s
 */
public class StartMenuFrame extends JFrame
{

        private static final long serialVersionUID = 5462223600l;
        private StartMenu menuDetails;

        /**
	 * Container needed for later references in StartMenu.
	 */
        public static Container c;

        /**
	 * Create a window with a title
	 * @param title The title to use for the window.
	 */ 
        public StartMenuFrame(String title)
        {
        	super(title);
        	menuDetails = new StartMenu();
    
        	setLayout(new BorderLayout());
        	setSize(850,700);
        	setResizable(true); //http://stackoverflow.com/questions/18031704/jframe-how-to-disable-window-resizing
			setVisible(true);
        	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	add(menuDetails,BorderLayout.CENTER);

        }

}

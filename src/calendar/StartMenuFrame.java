package calendar;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;

/**
 * Frame used by StartMenu.
 * @param c is a container that holds content into the JFrame
 * @param StartMenu menuDetails creates a new startmenu object when the program starts
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

        	setLayout(new BorderLayout());
        	menuDetails = new StartMenu();

        	//JButton button = new JButton("Submit");
        	Container c = getContentPane();

        	//c.add(button,BorderLayout.SOUTH);
        	c.add(menuDetails,BorderLayout.CENTER);

        }

}

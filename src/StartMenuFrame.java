package calendar;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;

/**
 * Frame used by StartMenu.
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

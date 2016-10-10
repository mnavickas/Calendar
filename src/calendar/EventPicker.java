package calendar;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
/**
 * MouseListener for clicking a date to view an event
 */
public class EventPicker implements MouseListener {

	/**
	 * Parent frame.
	 */
	private JFrame frame;
	public EventPicker(JFrame frame){
		this.frame = frame;
		
	}
	/**
	 * Create an event viewer when we click on this event.
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getClickCount() == 2){
			DayViewEvent e = (DayViewEvent)arg0.getSource();
			DayViewEventViewer.create(e,frame);
			
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		//Intentionally blank
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		//Intentionally blank
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		//Intentionally blank
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		//Intentionally blank
	}

}

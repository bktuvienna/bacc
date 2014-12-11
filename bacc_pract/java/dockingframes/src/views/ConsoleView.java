package views;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.jfree.data.xy.XYSeriesCollection;

import bibliothek.gui.dock.common.DefaultSingleCDockable;
import bibliothek.gui.dock.common.action.CAction;

public class ConsoleView extends DefaultSingleCDockable{
	public ConsoleView(String id, String title, CAction[] actions) {
		super(id, title, actions);
		this.createContent();
	}
	
	public void createContent(){
		JPanel panel = new JPanel();
		panel.setVisible(true);
		panel.add(new JTextArea());
		this.add(panel);
	}
}

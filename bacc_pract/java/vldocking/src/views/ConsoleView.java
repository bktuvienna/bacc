package views;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JPanel;

import com.vldocking.swing.docking.DockKey;
import com.vldocking.swing.docking.Dockable;

public class ConsoleView extends JPanel implements Dockable{
	
	DockKey key = new DockKey("console");
	
	public ConsoleView(){
		setUpView();
	}
	
	private void setUpView(){
		//this.setLayout(new BorderLayout());
		this.setName("Console");
		this.setVisible(true);
	}
	
	@Override
	public Component getComponent() {
		return this;
	}

	@Override
	public DockKey getDockKey() {
		return this.key;
	}

}

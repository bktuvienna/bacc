package views;

import java.awt.Component;

import javax.swing.JPanel;

import com.vldocking.swing.docking.DockKey;
import com.vldocking.swing.docking.Dockable;

public class ValuetableView extends JPanel implements Dockable{
	
	DockKey key = new DockKey("valuetable");
	
	@Override
	public Component getComponent() {
		return this;
	}

	@Override
	public DockKey getDockKey() {
		return key;
	}

}

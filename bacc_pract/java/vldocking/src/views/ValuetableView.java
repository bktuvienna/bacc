package views;

import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JTable;

import model.Data;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.vldocking.swing.docking.DockKey;
import com.vldocking.swing.docking.Dockable;

public class ValuetableView extends JPanel implements Dockable{
	
	private DockKey key = new DockKey("valuetable");
	
	public ValuetableView(){
		super();
		this.setSize(340,768);
		setUpView();
		
	}
	
	private void setUpView(){
		XYSeriesCollection dataset = Data.getDataset();
		XYSeries values = dataset.getSeries(0);
		Object[][] data = new Object[400][2];
		
		for(int i=0;i<values.getItemCount();i++){
			Number x = values.getX(i);
			Number y = values.getY(i);
			data[i][0]=x;
			data[i][1]=y;			
		}
		
		Object[] colNames = {"X","Y"};		
		JTable table = new JTable(data,colNames);
		this.add(table);
	}
	
	@Override
	public Component getComponent() {
		return this;
	}

	@Override
	public DockKey getDockKey() {
		return key;
	}

}

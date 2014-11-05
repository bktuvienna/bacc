package panels;

import java.awt.Component;
import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class ValueTablePanel extends JPanel{
	
	private JPanel tpanel;
	
	public ValueTablePanel(XYSeriesCollection dataset){
		super();
		
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
		JPanel tablePanel = new JPanel();
		tablePanel.add(table);
		tpanel = tablePanel;
	}
	
	public Component returnTablePanel(){
		return tpanel;
	}

}

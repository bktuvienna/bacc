package panels;

import java.awt.Component;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class OutputPanel extends JPanel{
	
	private Object[] values;
	private JPanel tpanel;
	
	public OutputPanel(XYSeriesCollection dataset){
		super();
		values = dataset.getSeries().toArray();
		Vector model = new Vector(); 
		Vector row = new Vector();
		for(int i=0;i<values.length;i++){
			row.clear();
			row.add(values[i]);			
			model.add(row);
		}
		System.out.println(model);
		Vector colNames = new Vector();
		colNames.add("X");
		colNames.add("Y");
		JTable table = new JTable(model,colNames);
		JPanel tablePanel = new JPanel();
		tablePanel.add(table);
		tpanel = tablePanel;
	}
	
	public Component returnTablePanel(){
		return tpanel;
	}

}

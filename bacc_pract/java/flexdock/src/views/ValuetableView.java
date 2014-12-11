package views;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTable;

import model.Data;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class ValuetableView  extends JPanel{
	public ValuetableView(){
		super();
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
		table.setVisible(true);
		this.add(table);
	}
}

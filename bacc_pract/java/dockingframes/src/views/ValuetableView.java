package views;

import javax.swing.JPanel;
import javax.swing.JTable;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import bibliothek.gui.dock.common.DefaultSingleCDockable;
import bibliothek.gui.dock.common.action.CAction;

public class ValuetableView extends DefaultSingleCDockable{
	public ValuetableView(String id, String title, CAction[] actions, XYSeriesCollection dataset) {
		super(id, title, actions);
		this.createContent(dataset);
	}
	
	private void createContent(XYSeriesCollection dataset){
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
        tablePanel.setVisible(true);
        this.add(tablePanel);
	}
}

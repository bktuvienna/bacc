package views;

import javax.swing.JComponent;
import javax.swing.JPanel;

import model.Data;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;

public class ValuechartView  extends JPanel{
	public ValuechartView(){
		super();
		setUpView();
	}
	
	private void setUpView(){
		final JFreeChart chart = ChartFactory.createXYBarChart("","X",false,"Y",Data.getDataset(),PlotOrientation.HORIZONTAL,true,true,false);
				
		XYPlot plot = chart.getXYPlot();
		XYBarRenderer renderer = (XYBarRenderer)plot.getRenderer();
		renderer.setShadowVisible(false);
		renderer.setMargin(0.96);
		
		final ChartPanel panel = new ChartPanel(chart);
		panel.setMinimumDrawHeight(10);
	    panel.setMaximumDrawHeight(2000);
	    panel.setMinimumDrawWidth(10);
	    panel.setMaximumDrawWidth(2000);
	    panel.setSize(this.getSize());
	    this.add(panel);
	}
}

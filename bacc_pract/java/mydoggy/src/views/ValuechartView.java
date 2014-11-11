package views;

import java.awt.Component;



import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

//http://www.java2s.com/Code/Java/Chart/JFreeChartXYSeriesDemo3.htm
public class ValuechartView extends ApplicationFrame{
	
	private Component vpanel;
	
	//dataset comes from ScatterplotPanel
	public ValuechartView(String title,XYSeriesCollection dataset) {
		super(title);
		final JFreeChart chart = ChartFactory.createXYBarChart("","X",false,"Y",dataset,PlotOrientation.HORIZONTAL,true,true,false);
				
		XYPlot plot = chart.getXYPlot();
		XYBarRenderer renderer = (XYBarRenderer)plot.getRenderer();
		renderer.setShadowVisible(false);
		renderer.setMargin(0.96);
		
		final ChartPanel panel = new ChartPanel(chart);
		panel.setMinimumDrawHeight(10);
	    panel.setMaximumDrawHeight(2000);
	    panel.setMinimumDrawWidth(10);
	    panel.setMaximumDrawWidth(2000);
		vpanel = panel;
	}
	
	public Component returnBarChartPanel(){
		return vpanel;
	}
}

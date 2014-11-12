package views;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYSeriesCollection;

import bibliothek.gui.dock.common.DefaultSingleCDockable;
import bibliothek.gui.dock.common.action.CAction;

public class ValuechartView extends DefaultSingleCDockable{
	public ValuechartView(String id, String title, CAction[] actions, XYSeriesCollection dataset) {
		super(id, title, actions);
		this.createContent(dataset);
	}
	
	private void createContent(XYSeriesCollection dataset){
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
        panel.setVisible(true);
        this.add(panel);
	}
}

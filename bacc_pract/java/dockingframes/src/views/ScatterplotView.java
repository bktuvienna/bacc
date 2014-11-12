package views;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYSeriesCollection;

import bibliothek.gui.dock.common.SingleCDockable;
import bibliothek.gui.dock.common.DefaultSingleCDockable;
import bibliothek.gui.dock.common.action.CAction;

public class ScatterplotView extends DefaultSingleCDockable{

	public ScatterplotView(String id, String title, CAction[] actions, XYSeriesCollection dataset) {
		super(id, title, actions);
		this.createContent(dataset);
	}
	
	private void createContent(XYSeriesCollection dataset){
		JFreeChart chart = ChartFactory.createScatterPlot("", "X", "Y", dataset);
		XYPlot plot = (XYPlot)chart.getPlot();
		Shape dotShape = new Ellipse2D.Double(0,0,5,5);
		XYItemRenderer renderer = plot.getRenderer();
		renderer.setSeriesShape(0, dotShape);
		
		final ChartPanel panel = new ChartPanel(chart,true);
        panel.setMinimumDrawHeight(10);
        panel.setMaximumDrawHeight(2000);
        panel.setMinimumDrawWidth(10);
        panel.setMaximumDrawWidth(2000);
        panel.setVisible(true);
        this.add(panel);
	}

	
}

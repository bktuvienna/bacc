package panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.FastScatterPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;


//http://www.java2s.com/Code/Java/Chart/JFreeChartFastScatterPlotDemo.htm
//http://stackoverflow.com/questions/6665354/changing-the-shapes-of-points-in-scatter-plot
public class ScatterplotPanel extends ApplicationFrame {
	
	private Component scpanel;
	private XYSeriesCollection dataset;
	
	public ScatterplotPanel(String title) {
		super(title);
		JFreeChart chart = ChartFactory.createScatterPlot("", "X", "Y", createData());
		XYPlot plot = (XYPlot)chart.getPlot();
		Shape dotShape = new Ellipse2D.Double(0,0,5,5);
		XYItemRenderer renderer = plot.getRenderer();
		renderer.setSeriesShape(0, dotShape);
		
		final ChartPanel panel = new ChartPanel(chart,true);
        panel.setMinimumDrawHeight(10);
        panel.setMaximumDrawHeight(2000);
        panel.setMinimumDrawWidth(10);
        panel.setMaximumDrawWidth(2000);
        scpanel = panel;
	}
	
	//creates a 20x20 dataset of type XYDataset
	private XYDataset createData(){
		XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
	    XYSeries series = new XYSeries("Scatterplot Values");
	    Random rand = new Random();
		for(int i=0;i<20;i++){
			for(int k=0;k<20;k++){			
				double valuex = rand.nextGaussian();
				double valuey = rand.nextGaussian();
				series.add(valuex,valuey);
			}
		}
		xySeriesCollection.addSeries(series);
		dataset = xySeriesCollection;
		return dataset;
	}
	
	//returns the used dataset for the scatterplot
	public XYSeriesCollection dataset(){
		return dataset;	
	}
	
	//returns the panel where the Scatterplot is drawn
	public Component returnScatterplotPanel(){
		return scpanel;
	}

}

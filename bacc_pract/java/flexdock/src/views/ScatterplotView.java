package views;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import model.Data;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;

public class ScatterplotView  extends JPanel{
	public ScatterplotView(){
		super();
		setUpView();
	}
	
	private void setUpView(){
		JFreeChart chart = ChartFactory.createScatterPlot("", "X", "Y", Data.getDataset());
		XYPlot plot = (XYPlot)chart.getPlot();
		Shape dotShape = new Ellipse2D.Double(0,0,5,5);
		XYItemRenderer renderer = plot.getRenderer();
		renderer.setSeriesShape(0, dotShape);
		
		final ChartPanel panel = new ChartPanel(chart,true);
        panel.setMinimumDrawHeight(10);
        panel.setMaximumDrawHeight(2000);
        panel.setMinimumDrawWidth(10);
        panel.setMaximumDrawWidth(2000);
        panel.setSize(this.getSize());
        this.add(panel);
	}
}

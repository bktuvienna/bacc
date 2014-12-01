/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nbrcp.demo.core.views;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.nbrcp.demo.core.model.Data;

/**
 *
 * @author bkowatsch
 */
public class Scatterplot extends JPanel{
	
    public Scatterplot(){
		super();               
		this.setSize(600,600);
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

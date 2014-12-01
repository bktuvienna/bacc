/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nbrcp.demo.core.views;

import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.nbrcp.demo.core.model.Data;

/**
 *
 * @author bkowatsch
 */
public class Barchart extends JPanel{
	
	public Barchart(){
		super();
		this.setSize(340,768);
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

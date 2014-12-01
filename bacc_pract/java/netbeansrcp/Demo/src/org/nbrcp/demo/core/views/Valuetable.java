/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nbrcp.demo.core.views;

import javax.swing.JPanel;
import javax.swing.JTable;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.nbrcp.demo.core.model.Data;

/**
 *
 * @author bkowatsch
 */
public class Valuetable extends JPanel{
		
	public Valuetable(){
		super();
		this.setSize(340,768);
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
		this.add(table);
	}
}

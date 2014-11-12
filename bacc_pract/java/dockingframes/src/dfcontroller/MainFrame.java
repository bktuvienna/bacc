package dfcontroller;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;

import views.ScatterplotView;
import views.ValuechartView;
import views.ValuetableView;
import model.Data;
import bibliothek.gui.dock.common.CControl;
import bibliothek.gui.dock.common.CLocation;
import bibliothek.gui.dock.common.SingleCDockable;

public class MainFrame {
	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		CControl controller = new CControl(frame);
		
		frame.setLayout(new GridLayout(1,1));
		frame.add(controller.getContentArea());
		
		SingleCDockable scatterplot = new ScatterplotView("scatterplot","Scatterplot",null,Data.getDataset());
		SingleCDockable valuechart = new ValuechartView("valuechart","Valuechart",null,Data.getDataset());
		SingleCDockable valuetable = new ValuetableView("valuetable","Valuetable",null,Data.getDataset());
		
		controller.addDockable(valuechart);
		controller.addDockable(scatterplot);
		controller.addDockable(valuetable);
			
		valuetable.setLocation(CLocation.base().normalEast(0.2));
		scatterplot.setLocation(CLocation.base().normalWest(0.8));
		valuechart.setLocation(CLocation.base().normalWest(0.2));
		
		
		valuetable.setVisible(true);
		scatterplot.setVisible(true);
		valuechart.setVisible(true);
		
		frame.setBounds(20,20,1024,768);
		frame.setVisible(true);
	}
}

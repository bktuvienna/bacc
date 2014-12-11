package controller;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.flexdock.docking.DockingConstants;
import org.flexdock.docking.DockingManager;
import org.flexdock.docking.DockingPort;
import org.flexdock.docking.defaults.DefaultDockingPort;

import views.ConsoleView;
import views.ScatterplotView;
import views.ValuechartView;
import views.ValuetableView;

//with help from http://www.javalobby.org/java/forums/t52990.html
public class fdcontroller {
	public static void main(String[] args){
		JFrame mainframe=new JFrame("Flexdock Demo");
		
		mainframe.getContentPane().setLayout(new BorderLayout());
		
		DefaultDockingPort globalPort = new DefaultDockingPort();
		globalPort.setPreferredSize(new Dimension(100,100));
		
		JComponent scatterplot = new ScatterplotView();
		DockingManager.registerDockable(scatterplot);
		DockingManager.dock(scatterplot, (DockingPort)globalPort);
		
		JComponent valuechart = new ValuechartView();
		DockingManager.registerDockable(valuechart);
		DockingManager.dock(valuechart, scatterplot, DockingConstants.WEST_REGION, 0.2f);
		
		JComponent valuetable = new ValuetableView();
		DockingManager.registerDockable(valuetable);
		DockingManager.dock(valuetable, scatterplot, DockingConstants.EAST_REGION, 0.2f);
		
		JComponent console = new ConsoleView();
		DockingManager.registerDockable(console);
		DockingManager.dock(console, scatterplot, DockingConstants.SOUTH_REGION, 0.2f);
			
		mainframe.getContentPane().add(globalPort, BorderLayout.CENTER);
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainframe.setSize(1024, 768);
		mainframe.setVisible(true);
	}
}

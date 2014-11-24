package controller;

import javax.swing.JFrame;

import views.ConsoleView;
import views.ScatterplotView;
import views.ValuechartView;
import views.ValuetableView;

import com.vldocking.swing.docking.DockingDesktop;

public class vlController extends JFrame{
	
	DockingDesktop desk = new DockingDesktop();
	ConsoleView cview = new ConsoleView();
	ScatterplotView sview = new ScatterplotView();
	ValuechartView vview = new ValuechartView();
	ValuetableView vtview = new ValuetableView();
	
	public vlController(){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1024,768);
		this.setVisible(true);
		this.getContentPane().add(desk);
		desk.addDockable(sview);
		//desk.addDockable(vview);
		//desk.addDockable(vtview);
		//desk.addDockable(cview);
	}
		
	public static void main(String[] args){
		vlController frame = new vlController();
	}

	
}

package controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import views.ConsoleView;
import views.ScatterplotView;
import views.ValuechartView;
import views.ValuetableView;

import com.vldocking.swing.docking.DockingConstants;
import com.vldocking.swing.docking.DockingDesktop;
//adapted code from https://code.google.com/p/vldocking/w/list
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
		desk.split(sview,cview,DockingConstants.SPLIT_BOTTOM);
		desk.split(sview,vtview,DockingConstants.SPLIT_RIGHT);
		desk.split(sview,vview,DockingConstants.SPLIT_LEFT);
		
		desk.setDockableWidth(sview, 0.75);
		desk.setDockableWidth(vview, 0.25);
		desk.setDockableWidth(vtview, 0.25);
		desk.setDockableHeight(cview,0.20);
		
		JButton floatVview = new JButton("Make Valuechart floating!");
		floatVview.setPreferredSize(new Dimension(300,30));
		cview.add(floatVview);
		floatVview.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					desk.setFloating(vview, true);
				} catch(IllegalArgumentException e1){
					JOptionPane.showMessageDialog(desk, "Please attach the Valuechart view!");
				}
			}			
		});
	}
		
	public static void main(String[] args){
		long before = System.currentTimeMillis();
		vlController frame = new vlController();
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				frame.setVisible(true);
			}
		});
		long after = System.currentTimeMillis();
		long performance = after-before;
		System.out.println("Performance: "+performance+" ms");
	}	
}

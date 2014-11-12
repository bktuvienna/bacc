package dfcontroller;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;

import views.ScatterplotView;
import views.ValuechartView;
import views.ValuetableView;
import model.Data;
import bibliothek.gui.dock.common.CControl;
import bibliothek.gui.dock.common.CGrid;
import bibliothek.gui.dock.common.CLocation;
import bibliothek.gui.dock.common.CWorkingArea;
import bibliothek.gui.dock.common.DefaultMultipleCDockable;
import bibliothek.gui.dock.common.SingleCDockable;

public class MainFrame {
	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		CControl controller = new CControl(frame);
		
		frame.setLayout(new GridLayout(1,1));
		frame.add(controller.getContentArea());
		
		//for adding multiple tabs of scatterplot CWorking Area is needed
		final CWorkingArea work = controller.createWorkingArea("work");
				
		SingleCDockable scatterplot = new ScatterplotView("scatterplot","Scatterplot",null,Data.getDataset());
		SingleCDockable valuechart = new ValuechartView("valuechart","Valuechart",null,Data.getDataset());
		SingleCDockable valuetable = new ValuetableView("valuetable","Valuetable",null,Data.getDataset());
		
		CGrid grid = new CGrid(controller);
		grid.add(0,0,0.25,1,valuechart);
		grid.add(0.5,0,0.5,1,work);
		grid.add(1,0,0.25,1,valuetable);
		controller.getContentArea().deploy(grid);
		
		work.add(scatterplot);
		work.show(scatterplot);
		DefaultMultipleCDockable editor = new DefaultMultipleCDockable(null);
		editor.setTitleText("Editor");
		editor.setCloseable(true);
		work.show(editor);
		editor.toFront();
		
		frame.setBounds(20,20,1024,768);
		frame.setVisible(true);
	}
}

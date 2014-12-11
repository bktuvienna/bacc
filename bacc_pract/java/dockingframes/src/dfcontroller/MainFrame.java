package dfcontroller;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import views.ConsoleView;
import views.ScatterplotView;
import views.ValuechartView;
import views.ValuetableView;
import model.Data;
import bibliothek.extension.gui.dock.theme.BubbleTheme;
import bibliothek.extension.gui.dock.theme.EclipseTheme;
import bibliothek.extension.gui.dock.theme.FlatTheme;
import bibliothek.extension.gui.dock.theme.SmoothTheme;
import bibliothek.extension.gui.dock.theme.bubble.BubbleColorScheme;
import bibliothek.extension.gui.dock.theme.bubble.BubbleColorScheme.Distribution;
import bibliothek.gui.DockController;
import bibliothek.gui.DockTheme;
import bibliothek.gui.dock.common.CControl;
import bibliothek.gui.dock.common.CGrid;
import bibliothek.gui.dock.common.CLocation;
import bibliothek.gui.dock.common.CWorkingArea;
import bibliothek.gui.dock.common.DefaultMultipleCDockable;
import bibliothek.gui.dock.common.SingleCDockable;
import bibliothek.gui.dock.themes.ColorScheme;
import bibliothek.gui.dock.themes.NoStackTheme;

//with help from https://code.google.com/p/docking-frames/source/browse/code/trunk/docking-frames-base/docking-frames-demo-tutorial/src/main/java/tutorial/core/basics/ThemesExample.java?r=373
public class MainFrame implements ActionListener, ItemListener{
	
	CControl controller;
	JFrame frame;
	
	public JMenuBar createThemesMenu(){
		JMenuBar menuBar = new JMenuBar();
		JMenu themes = new JMenu("Themes");
		menuBar.add(themes);
		JMenuItem smooth = new JMenuItem("Smooth");
		JMenuItem flat = new JMenuItem("Flat");
		JMenuItem eclipse = new JMenuItem("Eclipse");
		JMenuItem bubble = new JMenuItem("Bubble");
		smooth.addActionListener(this);
		flat.addActionListener(this);
		eclipse.addActionListener(this);
		bubble.addActionListener(this);
		themes.add(smooth);
		themes.add(flat);
		themes.add(bubble);
		themes.add(eclipse);
		return menuBar;
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		JMenuItem source = (JMenuItem)(e.getSource());
		if(source.getText().equals("Smooth")){
			controller.getController().setTheme(new NoStackTheme(new SmoothTheme()));
		}
		if(source.getText().equals("Flat")){
			controller.getController().setTheme(new NoStackTheme(new FlatTheme()));
		}
		if(source.getText().equals("Bubble")){
			controller.getController().setTheme(new NoStackTheme(new BubbleTheme()));
			ColorScheme colors = new BubbleColorScheme(Distribution.BRG);
			controller.getController().getProperties().set(BubbleTheme.BUBBLE_COLOR_SCHEME, colors);
		}
		if(source.getText().equals("Eclipse")){
			controller.getController().setTheme(new NoStackTheme(new EclipseTheme()));
		}
		
	}
	
	public void createWindow(){
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setJMenuBar(createThemesMenu());
		
		controller = new CControl(frame);
		
		frame.setLayout(new GridLayout(1,1));
		frame.add(controller.getContentArea());
		
		//for adding multiple tabs of scatterplot CWorking Area is needed
		//final CWorkingArea work = controller.createWorkingArea("work");
				
		SingleCDockable scatterplot = new ScatterplotView("scatterplot","Scatterplot",null,Data.getDataset());
		SingleCDockable valuechart = new ValuechartView("valuechart","Valuechart",null,Data.getDataset());
		SingleCDockable valuetable = new ValuetableView("valuetable","Valuetable",null,Data.getDataset());
		SingleCDockable console = new ConsoleView("console","Console",null);
		
		CGrid grid = new CGrid(controller);
		grid.add(0,0,0.25,1,valuechart);
		grid.add(1,0,0.5,0.75,scatterplot);
		grid.add(2,0,0.25,1,valuetable);
		grid.add(1,1,0.5,0.25,console);
		controller.getContentArea().deploy(grid);
		
		//work.add(scatterplot);
		//work.show(scatterplot);
		//DefaultMultipleCDockable editor = new DefaultMultipleCDockable(null);
		//editor.setTitleText("Editor");
		//editor.setCloseable(true);
		//work.show(editor);
		//editor.toFront();
		
		frame.setBounds(20,20,1024,768);
		frame.setVisible(true);
	}
	
	public static void main(String[] args){
		MainFrame f = new MainFrame();
		f.createWindow();
	}
}

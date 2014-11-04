package controller;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.border.Border;

import org.noos.xing.mydoggy.ContentManager;
import org.noos.xing.mydoggy.ContentManagerUI;
import org.noos.xing.mydoggy.ToolWindow;
import org.noos.xing.mydoggy.ToolWindowAnchor;
import org.noos.xing.mydoggy.ToolWindowTab;
import org.noos.xing.mydoggy.ToolWindowType;
import org.noos.xing.mydoggy.plaf.MyDoggyToolWindow;
import org.noos.xing.mydoggy.plaf.MyDoggyToolWindowManager;
import org.noos.xing.mydoggy.plaf.ui.content.MyDoggyMultiSplitContentManagerUI;
import org.noos.xing.mydoggy.plaf.ui.content.MyDoggyTabbedContentManagerUI;

import panels.OutputPanel;
import panels.ScatterplotPanel;
import panels.ValuesPanel;

//coded with help from http://mydoggy.sourceforge.net/docs/tutorialset.html
public class MainFrame {
	
	public static void main(String[] args) {
		
		JFrame mainWindow = new JFrame("MyDoggy");
		MyDoggyToolWindowManager twm = new MyDoggyToolWindowManager();
		
		JMenuBar menuBar = new JMenuBar();
		JMenu features = new JMenu("Features");
		menuBar.add(features);
		
		mainWindow.setJMenuBar(menuBar);
		mainWindow.getContentPane().add(twm);
		mainWindow.setSize(new Dimension(1024,768));
		mainWindow.setVisible(true);
		
		runContentManager(twm);
	}
	
	public static void runContentManager(MyDoggyToolWindowManager twm){
		ContentManager cm = twm.getContentManager();
		cm.setContentManagerUI(new MyDoggyMultiSplitContentManagerUI());
		
		ScatterplotPanel scpanel = new ScatterplotPanel("Scatterplot");
		cm.addContent("Scatterplot","Scatterplot",null,scpanel.returnScatterplotPanel());
		
		JPanel testpanel2 = new JPanel();
		cm.addContent("Console","Console",null,testpanel2);		
		
		Icon icon = new ImageIcon();
		ValuesPanel vpanel = new ValuesPanel("",scpanel.dataset());
		ToolWindow valuewindow = twm.registerToolWindow(
			    "Values",        // Tool Window identifier
			    "Values",    // Tool Window Title
			    icon,           // Tool Window Icon
			    vpanel.returnBarChartPanel(),      // Tool Window Component
			    ToolWindowAnchor.LEFT // Tool Window Anchor
			);
		valuewindow.setAvailable(true);
		
		OutputPanel opanel = new OutputPanel(scpanel.dataset());
		ToolWindow valuetable = twm.registerToolWindow(
			    "Valuetable",        // Tool Window identifier
			    "Valuetable",    // Tool Window Title
			    icon,           // Tool Window Icon
			    opanel.returnTablePanel(),      // Tool Window Component
			    ToolWindowAnchor.RIGHT // Tool Window Anchor
			);
		valuetable.setAvailable(true);
	}
	

}

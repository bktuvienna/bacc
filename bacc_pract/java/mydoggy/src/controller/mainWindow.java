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

public class mainWindow {	
	public static void main(String[] args) {
		
		JFrame mainWindow = new JFrame("MyDoggy");
		MyDoggyToolWindowManager twm = new MyDoggyToolWindowManager();
		
		JMenuBar menuBar = new JMenuBar();
		JMenu features = new JMenu("Features");
		menuBar.add(features);
		
		mainWindow.setJMenuBar(menuBar);
		mainWindow.getContentPane().add(twm);
		mainWindow.setSize(new Dimension(800,800));
		mainWindow.setVisible(true);
		
		runContentManager(twm);
		setUpToolWindows(twm);
	}
	
	public static void runContentManager(MyDoggyToolWindowManager twm){
		ContentManager cm = twm.getContentManager();
		cm.setContentManagerUI(new MyDoggyMultiSplitContentManagerUI());
		
		JPanel testpanel1 = new JPanel();
		cm.addContent("test1","test1",null,testpanel1);
		
		JPanel testpanel2 = new JPanel();
		cm.addContent("test2","test2",null,testpanel2);		
	}
	
	public static void setUpToolWindows(MyDoggyToolWindowManager twm){
		
		Icon icon = new ImageIcon();
		ToolWindow toolwindow = twm.registerToolWindow(
			    "Debug",        // Tool Window identifier
			    "Debugging",    // Tool Window Title
			    icon,           // Tool Window Icon
			    new JPanel(),      // Tool Window Component
			    ToolWindowAnchor.LEFT // Tool Window Anchor
			);
		
		toolwindow.setType(ToolWindowType.DOCKED);
		toolwindow.setAvailable(true);
		
	}
	

}

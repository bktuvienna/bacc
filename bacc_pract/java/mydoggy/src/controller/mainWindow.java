package controller;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import org.noos.xing.mydoggy.ContentManager;
import org.noos.xing.mydoggy.ContentManagerUI;
import org.noos.xing.mydoggy.ToolWindow;
import org.noos.xing.mydoggy.ToolWindowAnchor;
import org.noos.xing.mydoggy.plaf.MyDoggyToolWindow;
import org.noos.xing.mydoggy.plaf.MyDoggyToolWindowManager;
import org.noos.xing.mydoggy.plaf.ui.content.MyDoggyTabbedContentManagerUI;

public class mainWindow {
	
	
	//public static MyDoggyToolWindowManager init(){
		//return twm;
	//}
	
	public static void main(String[] args) {
		
		JFrame mainWindow = new JFrame("MyDoggy");
		MyDoggyToolWindowManager twm = new MyDoggyToolWindowManager();
		mainWindow.getContentPane().add(twm,BorderLayout.CENTER);
		mainWindow.setSize(new Dimension(800,800));
		mainWindow.setVisible(true);
		
		ContentManager cm = twm.getContentManager();
		ContentManagerUI cmui = new MyDoggyTabbedContentManagerUI();
		cm.setContentManagerUI(cmui);
		JPanel testpanel1 = new JPanel();
		testpanel1.setPreferredSize(new Dimension(200,200));
		cm.addContent("test1","test1",null,testpanel1);
		
		JPanel testpanel2 = new JPanel();
		testpanel2.setPreferredSize(new Dimension(200,200));
		cm.addContent("test2","test2",null,testpanel2);
		
		
		/*
		//ContentManager cm = twm.getContentManager();
		Icon icon = new ImageIcon();
		Component component = new Button();
		//ContentManager.addContent("Tools",
		//						  "Tools",
		//						  null,
		//						  component,
		//						  "ToolWindows"
		//	);
		
		ToolWindow toolwindow = twm.registerToolWindow(
			    "Debug",        // Tool Window identifier
			    "Debugging",    // Tool Window Title
			    icon,           // Tool Window Icon
			    component,      // Tool Window Component
			    ToolWindowAnchor.LEFT // Tool Window Anchor
			);*/

	}

}

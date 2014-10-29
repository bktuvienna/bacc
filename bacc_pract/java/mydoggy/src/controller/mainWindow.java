package controller;

import java.awt.Component;

import javax.swing.Icon;

import org.noos.xing.mydoggy.ToolWindowAnchor;
import org.noos.xing.mydoggy.plaf.MyDoggyToolWindow;
import org.noos.xing.mydoggy.plaf.MyDoggyToolWindowManager;

public class mainWindow {

	public static void main(String[] args) {
		MyDoggyToolWindowManager twm=new MyDoggyToolWindowManager();
		Icon icon;
		Component component;
		MyDoggyToolWindow toolwindow = twm.registerMyDoggyToolWindow(
			    "Debug",        // Tool Window identifier
			    "Debugging",    // Tool Window Title
			    icon,           // Tool Window Icon
			    component,      // Tool Window Component
			    ToolWindowAnchor.LEFT // Tool Window Anchor
			);

	}

}

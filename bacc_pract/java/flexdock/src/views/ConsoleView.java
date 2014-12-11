package views;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class ConsoleView extends JPanel{
	public ConsoleView(){
		super();
		setUpView();
	}
	
	private void setUpView(){
		this.setLayout(new BorderLayout());
		this.setName("Console");
		this.setVisible(true);
	}
}

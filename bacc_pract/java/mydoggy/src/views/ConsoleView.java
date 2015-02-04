package views;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class ConsoleView extends JPanel{
	public ConsoleView(){
		setUpView();
	}
	
	private void setUpView(){
		this.setLayout(new BorderLayout());
		this.setName("Console");
		this.setVisible(true);
	}
}

package swingy.views.components;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import swingy.model.ISwingyModel;
import swingy.views.SwingyGUIMainMenuView;

public class HerosSelectionComponent extends JPanel implements ISwingyModel {
	private static final long		serialVersionUID = 1L;
	private JLabel					comment = new JLabel("*select your favorite hero and click START GAME.");
	private JButton					selectButton = new JButton("Select Hero");
	
	private SwingyGUIMainMenuView	view;
	
	public HerosSelectionComponent(SwingyGUIMainMenuView view) {
		super();
		this.view = view;
		this.setLayout(new BorderLayout());
		selectButton.setForeground(Color.gray);
		selectButton.setEnabled(false);
	}
	
	public void paintModel() {
		this.add(this.comment, BorderLayout.NORTH);
		this.add(this.selectButton, BorderLayout.SOUTH);
		this.view.addtoBottom(this, BorderLayout.NORTH);
		
		this.view.update();
	}
	
	public void remove() {
		this.view.removeToBottom(this);
		this.view.update();
	}
	
	public JButton getButton() {
		return (this.selectButton);
	}
	
	public void setButtonEnabled(boolean enable) {
		if (enable == true)
			selectButton.setForeground(Color.black);
		else
			selectButton.setForeground(Color.gray);
		selectButton.setEnabled(enable);
		selectButton.repaint();
	}
	
	protected void paintComponent(java.awt.Graphics g) {
		
	}
}

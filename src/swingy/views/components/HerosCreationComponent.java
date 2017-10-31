package swingy.views.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import swingy.model.ISwingyModel;
import swingy.views.SwingyGUIMainMenuView;

public class HerosCreationComponent extends JPanel implements ISwingyModel {
	private static final long serialVersionUID = 1L;
	
	private static final String unselectedButtonText = "Create a new Hero";
	private static final String selectedButtonText = "Annuler";
	
	private JLabel	comment = new JLabel("*click add new hero for create a new hero.");
	private JButton	selectButton = new JButton(unselectedButtonText);
	private boolean isSelected = false;
	
	private SwingyGUIMainMenuView view;
	
	public HerosCreationComponent(SwingyGUIMainMenuView view) {
		super();
		this.view = view;
		this.setLayout(new BorderLayout());
		selectButton.setSelected(false);
		selectButton.setEnabled(true);
		selectButton.setForeground(Color.black);
	}
	
	public JButton getButton() {
		return (this.selectButton);
	}
	
	public void paintModel() {
		this.add(this.comment, BorderLayout.NORTH);
		this.add(this.selectButton, BorderLayout.SOUTH);
		this.view.addtoBottom(this, BorderLayout.SOUTH);
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
		if (this.isSelected && this.comment.isVisible()) {
			this.comment.setVisible(false);
		} else if (!this.isSelected && !this.comment.isVisible()) {
			this.comment.setVisible(true);
		}
	}
	
	public void addEventButtonMouseListener(MouseListener l) {
		selectButton.addMouseListener(l);
	}
	
	public boolean isSelected() {
		return (isSelected);
	}
	
	public void setSelected(boolean s) {
		
		if (s) {
			selectButton.setText(selectedButtonText);
		} else {
			selectButton.setText(unselectedButtonText);
		}
		
		this.isSelected = s;
	}
}

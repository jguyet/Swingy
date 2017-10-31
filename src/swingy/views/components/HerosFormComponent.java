package swingy.views.components;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import swingy.views.SwingyGUIMainMenuView;

public class HerosFormComponent extends JPanel {

	private static final long 	serialVersionUID = 1L;
	private final String[] 		classes = {"Magician", "Princess", "Warrior", "Crazy"};
	
	private JPanel 				top = new JPanel();
	
	//NAME
	private JLabel				lblName = new JLabel("Hero Name");
	private TextField			inputName = new TextField();
	//CLASS
	private JLabel				lblClass = new JLabel("Class");
	private JComboBox<String>	classSelect = new JComboBox<String>(classes);
	
	private JPanel				bottom = new JPanel();
	private JButton				valide = new JButton("Valider");
	
	private SwingyGUIMainMenuView view;
	
	public HerosFormComponent(SwingyGUIMainMenuView view) {
		super();
		this.view = view;
		this.setLayout(new BorderLayout());
		GridLayout experimentLayout = new GridLayout(0,2);
		top.setLayout(experimentLayout);
		//line 1
		top.add(lblName);
		top.add(lblClass);	
		//line 2
		top.add(inputName);
		top.add(classSelect);
		
		this.add(top, BorderLayout.NORTH);
		
		bottom.setLayout(new BorderLayout());
		bottom.add(valide, BorderLayout.SOUTH);
		this.add(bottom, BorderLayout.SOUTH);
	}
	
	public void paintModel() {
		this.inputName.setText("");
		this.classSelect.setSelectedIndex(0);
		
		this.view.addtoBottom(this, BorderLayout.NORTH);
		this.view.update();
		this.view.getWindow().setSize(1000, 370);
		this.setVisible(false);
		this.setVisible(true);
	}
	
	public void remove() {
		this.view.removeToBottom(this);
		this.view.getWindow().setSize(1000, 350);
		this.view.update();
	}
	
	public JButton getButton() {
		return (this.valide);
	}
	
	public JComboBox<String> getComboBox() {
		return (this.classSelect);
	}
	
	public String getName() {
		return (this.inputName.getText());
	}
}

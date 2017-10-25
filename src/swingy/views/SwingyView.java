package swingy.views;

import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import swingy.model.ISwingyModel;

public class SwingyView extends JPanel{

	/**
	 * VERSION
	 */
	private static final long serialVersionUID = 1L;
	
	private Window win;
	private ArrayList<ISwingyModel> models = new ArrayList<ISwingyModel>();
	
	public SwingyView(Window win) {
		this.win = win;
		BorderLayout b = new BorderLayout();
		this.setLayout(b);
	}
	
	public void init() {
		this.setVisible(false);
		this.setVisible(true);
	}
	
	public Window getWindow() {
		return (this.win);
	}
	
	public void update() {
		
		this.repaint();
		
	}
	
	public void addModel(ISwingyModel model) {
		models.add(model);
	}
	
	public void removeModel(ISwingyModel model) {
		models.remove(model);
	}
	
	/**
	 * child method painting
	 */
	protected void paintComponent(java.awt.Graphics g)
	{
		super.paintComponent(g);
		
		g.clearRect(0, 0, this.win.getWidth(), this.win.getHeight());
		
		for (ISwingyModel model : models) {
			model.paint(g);
		}
		
	}

}

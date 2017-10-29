package swingy.views;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import swingy.model.ISwingyModel;
import swingy.views.events.ResponseListener;

public class SwingyGUIMainMenuView extends JPanel implements IView{

	/**
	 * VERSION
	 */
	private static final long serialVersionUID = 1L;
	
	private Window win;
	private ArrayList<ISwingyModel> models = new ArrayList<ISwingyModel>();
	
	public SwingyGUIMainMenuView(Window win) {
		this.win = win;
		BorderLayout b = new BorderLayout();
		this.setLayout(b);
	}
	
	@Override
	public void init() {
		this.win.add(this);
		this.setVisible(false);
		this.setVisible(true);
	}
	
	@Override
	public void destroy() {
		this.win.remove(this);
		this.win.setVisible(false);
		this.win.setVisible(true);
	}
	
	public Window getWindow() {
		return (this.win);
	}

	@Override
	public void update() {
		this.repaint();
	}

	@Override
	public void addModel(ISwingyModel model) {
		models.add(model);
	}

	@Override
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
		
		ArrayList<ISwingyModel> tmp = new ArrayList<ISwingyModel>(models);
		
		for (ISwingyModel model : tmp) {
			model.paint(g);
		}
		
	}

	@Override
	public void waitResponse(ResponseListener rep) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void println(String txt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void print(String txt) {
		// TODO Auto-generated method stub
		
	}

}

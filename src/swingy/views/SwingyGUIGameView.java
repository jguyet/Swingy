package swingy.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import swingy.model.ISwingyModel;
import swingy.utils.Utils;
import swingy.views.events.ResponseListener;

public class SwingyGUIGameView extends JPanel implements IView{

	/**
	 * VERSION
	 */
	private static final long serialVersionUID = 1L;
	
	private Window win;
	private ArrayList<ISwingyModel> models = new ArrayList<ISwingyModel>();
	
	private ISwingyModel banner = null;
	
	public SwingyGUIGameView(Window win) {
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
	
	public void addBanner(ISwingyModel model) {
		this.banner = model;
	}
	
	public Window getWindow() {
		return (this.win);
	}
	
	/**
	 * child method painting
	 */
	protected void paintComponent(java.awt.Graphics g)
	{
		super.paintComponent(g);
		
		g.clearRect(0, 0, this.win.getWidth(), this.win.getHeight());
		
		g.setColor(Color.BLACK);//Utils.HexToRGB("#886A08"));
		g.fillRect(0, 0, this.win.getWidth(), this.win.getHeight());
		
		ArrayList<ISwingyModel> tmp = new ArrayList<ISwingyModel>(models);
		
		for (ISwingyModel model : tmp) {
			model.paint(g);
		}
		
		if (this.banner != null) {
			this.banner.paint(g);
		}
	}
	
	@Override
	public void addKeyListener(KeyListener l) {
		this.win.addKeyListener(l);
	}

	@Override
	public void addMouseListener(MouseListener m) {
		this.win.addMouseListener(m);
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
	
	@Override
	public void removeKeyListener(KeyListener l) {
		this.win.removeKeyListener(l);
	}

	@Override
	public void removeMouseListener(MouseListener m) {
		this.win.removeMouseListener(m);
	}

}

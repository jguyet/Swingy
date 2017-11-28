package swingy.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import swingy.model.ISwingyModel;
import swingy.views.events.ResponseListener;

public class SwingyGUIMainMenuView extends JPanel implements IView{

	/**
	 * VERSION
	 */
	private static final long serialVersionUID = 1L;
	private static final int		MENU_WIDTH = 1000;
	private static final int		MENU_HEIGTH = 350;
	
	private Window					win;
	
	private JPanel					top = new JPanel();
	private ArrayList<Component>	componentsTop = new ArrayList<Component>();
	
	private JPanel					bottom = new JPanel();
	private ArrayList<Component>	componentsBottom = new ArrayList<Component>();
	
	public SwingyGUIMainMenuView(Window win) {
		this.win = win;
		BorderLayout b = new BorderLayout();
		this.setLayout(b);
		this.top.setLayout(new BorderLayout(0, 15));
		this.bottom.setLayout(new BorderLayout());
	}
	
	public void addtoTop(Component comp, Object constraints) {
		removeToTop(comp);
		this.top.add(comp, constraints);
		this.componentsTop.add(comp);
	}
	
	public void removeToTop(Component comp) {
		if (this.componentsTop.contains(comp)) {
			this.top.remove(comp);
			this.componentsTop.remove(comp);
		}
	}
	
	public void addtoBottom(Component comp, Object constraints) {
		removeToBottom(comp);
		this.bottom.add(comp, constraints);
		this.componentsBottom.add(comp);
	}
	
	public void removeToBottom(Component comp) {
		if (this.componentsBottom.contains(comp)) {
			this.bottom.remove(comp);
			this.componentsBottom.remove(comp);
		}
	}
	
	@Override
	public void init() {
		this.win.add(this);
		this.win.setSize(MENU_WIDTH, MENU_HEIGTH);
		
		this.add(top, BorderLayout.NORTH);
		this.add(bottom, BorderLayout.SOUTH);
		
		this.setVisible(false);
		this.setVisible(true);
		//###############################################################################
		//TOP
		//###############################################################################
		/*this.top.add(this.swingyTitle, BorderLayout.NORTH);
		this.heroTable.prepare();
		this.top.add(this.heroTable, BorderLayout.SOUTH);
		this.add(this.top, BorderLayout.NORTH);
		//###############################################################################
		//BOTTOM
		//###############################################################################
		
		this.block_selection.prepare();
		this.block_creation.prepare();
		
		this.bottom.add(this.block_selection, BorderLayout.NORTH);
		this.bottom.add(this.block_creation, BorderLayout.SOUTH);
		this.add(this.bottom, BorderLayout.SOUTH);*/
	}
	
	/*public void removeHeroTable() {
		this.top.remove(this.heroTable);
	}
	
	public void repaintHeroTable() {
		this.remove(this.top);
		this.heroTable.prepare();
		this.top.add(this.heroTable, BorderLayout.SOUTH);
		this.add(this.top, BorderLayout.NORTH);
		this.setVisible(false);
		this.setVisible(true);
	}
	
	public void addcreationForm() {
		this.remove(this.bottom);
		this.bottom.remove(this.block_selection);
		this.creationform = new CreationForm();
		this.bottom.add(this.creationform, BorderLayout.NORTH);
		this.add(this.bottom, BorderLayout.SOUTH);
	}
	
	public void removeCreationForm() {
		this.remove(this.bottom);
		this.bottom.remove(this.creationform);
		this.bottom.add(this.block_selection, BorderLayout.NORTH);
		this.add(this.bottom, BorderLayout.SOUTH);
	}
	
	public void paintBlockCreation() {
		
		if (this.block_creation.isSelected() && this.win.getHeight() != 370) {
			this.win.setSize(1000, 370);
			this.block_creation.comment.setVisible(false);
			this.block_creation.getButton().setText("Annuler");
			addcreationForm();
			this.block_selection.setVisible(false);
		} else if (!this.block_creation.isSelected() && this.win.getHeight() == 370) {
			this.win.setSize(1000, 350);
			this.block_creation.comment.setVisible(true);
			this.block_creation.getButton().setText("Create a new Hero");
			removeCreationForm();
			this.block_selection.setVisible(true);
		}
	}*/
	
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
		
	}

	@Override
	public void removeModel(ISwingyModel model) {
		
	}
	
	/**
	 * child method painting
	 */
	protected void paintComponent(java.awt.Graphics g)
	{
		super.paintComponent(g);
		
		//###########################################
		// MISE EN TRANSPARENCE DU BACKGROUND
		this.setOpaque(false);
		this.setBackground(new Color(0,0,0,0));
		//###########################################
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

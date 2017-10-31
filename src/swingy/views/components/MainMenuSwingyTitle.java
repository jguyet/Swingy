package swingy.views.components;

import javax.swing.JPanel;

import swingy.ressources.Sprite;

public class MainMenuSwingyTitle extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int TITLE_WIDTH = 1000;
	private final int TITLE_HEIGHT = 40;
	private final int TITLE_START_PAINT_WIDTH = 430;
	private final int TITLE_START_PAINT_HEIGHT = 0;

	protected void paintComponent(java.awt.Graphics g)
	{
		super.paintComponent(g);
		this.setSize(TITLE_WIDTH, TITLE_HEIGHT);
		Sprite.TITLE.paint(g, TITLE_START_PAINT_WIDTH, TITLE_START_PAINT_HEIGHT);
	}
}

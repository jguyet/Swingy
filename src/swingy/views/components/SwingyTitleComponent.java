package swingy.views.components;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import swingy.model.ISwingyModel;
import swingy.ressources.Sprite;
import swingy.views.SwingyGUIMainMenuView;

public class SwingyTitleComponent extends JPanel implements ISwingyModel{

	private static final long		serialVersionUID = 1L;
	
	private static final int		TITLE_WIDTH = 1000;
	private static final int		TITLE_HEIGHT = 40;
	private static final int		TITLE_START_PAINT_WIDTH = 430;
	private static final int		TITLE_START_PAINT_HEIGHT = 0;
	
	private SwingyGUIMainMenuView	view;
	
	public SwingyTitleComponent(SwingyGUIMainMenuView view) {
		this.view = view;
	}
	
	public void paintModel() {
		this.view.addtoTop(this, BorderLayout.NORTH);
		this.view.update();
	}

	protected void paintComponent(java.awt.Graphics g)
	{
		super.paintComponent(g);
		this.setSize(TITLE_WIDTH, TITLE_HEIGHT);
		Sprite.TITLE.paint(g, TITLE_START_PAINT_WIDTH, TITLE_START_PAINT_HEIGHT);
	}
}

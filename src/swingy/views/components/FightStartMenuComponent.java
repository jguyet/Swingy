package swingy.views.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JPanel;

import swingy.entity.Entity;
import swingy.ressources.Sprite;
import swingy.utils.Utils;
import swingy.utils.Vector2;
import swingy.views.SwingyGUIGameView;

public class FightStartMenuComponent extends JPanel{
	/**
	 * GRAPHIC VERSION
	 */
	private static final long serialVersionUID = 1L;

	private SwingyGUIGameView	view;
	
	private Entity p1;
	private Entity p2;
	
	private ArrayList<Vector2> bandesToRight = new ArrayList<Vector2>();
	private ArrayList<Vector2> bandesToLeft = new ArrayList<Vector2>();
	
	public FightStartMenuComponent(SwingyGUIGameView view, Entity p1, Entity p2) {
		super();
		this.view = view;
		this.p1 = p1;
		this.p2 = p2;
		this.setLayout(new BorderLayout());
		
		//transparence
		this.setOpaque(false);
		this.paintModel();
		
		for (int y = 0; y < this.view.getHeight(); y += 110) {
			bandesToRight.add(new Vector2(-(this.view.getWidth()), y));
			bandesToLeft.add(new Vector2(this.view.getWidth(), y + 55));
		}
		
	}
	
	public void paintModel() {
		this.view.add(this);
		this.setVisible(false);
		this.setVisible(true);
	}
	
	public void removeModel() {
		this.view.remove(this);
	}
	
	protected void paintComponent(java.awt.Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
	    
		g2.setColor(Color.WHITE);
		
		for (Vector2 v : bandesToRight) {
			g2.fillRect(v.x, v.y, this.view.getWidth(), 55);
			if (v.x == 0)
				continue ;
			if (v.x > 0)
				v.x = 0;
			else
				v.x += 20;
		}
		
		for (Vector2 v : bandesToLeft) {
			g2.fillRect(v.x, v.y, this.view.getWidth(), 55);
			if (v.x == 0)
				continue ;
			if (v.x < 20)
				v.x = 0;
			else
				v.x -= 20;
		}
		
	    g2.setColor(Utils.HexToRGB("#D8D8D8"));
	    g2.fillRect((this.view.getWidth() / 2) - 10, 0, 20, (this.view.getHeight() / 2) - 80);
	    g2.fillRect((this.view.getWidth() / 2) - 10, (this.view.getHeight() / 2), 20, (this.view.getHeight() / 2));
	    	
	    g2.setColor(Color.BLACK);
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		        RenderingHints.VALUE_ANTIALIAS_ON);
		Font font = new Font("Serif", Font.PLAIN, 50);
		g2.setFont(font);
		g2.drawString("FIGHT", (this.view.getWidth() / 2) - 70, (this.view.getHeight() / 2) - 25);
		
		paintSpriteLeft(g2, p1.getSprite());
		paintSpriteRight(g2, p2.getSprite());
		
		Sprite.ESCAPE.paint(g2, 100, 300);
	}
	
	private void paintSpriteLeft(Graphics2D g2, Sprite s) {
		int h = s.getHeight();
		int w = s.getWidth();
		int pid = s.posid;
		
		//east 8
		//west 12
		s.posid = 8;
		
		if (s.columnH == 1 && s.columnW == 1) {
			s.posid = 0;
		}
		
		s.setHeight(200);
		s.setWidth(200);
		s.paint(g2, 100, 100);
		
		s.setHeight(h);
		s.setWidth(w);
		s.posid = pid;
	}
	
	private void paintSpriteRight(Graphics2D g2, Sprite s) {
		int h = s.getHeight();
		int w = s.getWidth();
		int pid = s.posid;
		
		//east 8
		//west 12
		s.posid = 12;
		
		if (s.columnH == 1 && s.columnW == 1) {
			s.posid = 0;
		}
		
		s.setHeight(200);
		s.setWidth(200);
		s.paint(g2, (this.view.getWidth() - 200) - 100, 100);
		
		s.setHeight(h);
		s.setWidth(w);
		s.posid = pid;
	}
}

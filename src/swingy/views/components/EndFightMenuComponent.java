package swingy.views.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JPanel;

import swingy.entity.Entity;
import swingy.model.ISwingyModel;
import swingy.ressources.Sprite;
import swingy.views.SwingyGUIGameView;

public class EndFightMenuComponent extends JPanel implements ISwingyModel {

	/**
	 * GRAPHIC VERSION
	 */
	private static final long serialVersionUID = 1L;

	private SwingyGUIGameView	view;
	
	private Entity winner;
	private Entity looser;
	
	private boolean hasWin;
	
	public EndFightMenuComponent(SwingyGUIGameView view, Entity winner, Entity looser, boolean hasWin) {
		super();
		this.view = view;
		this.winner = winner;
		this.looser = looser;
		this.hasWin = hasWin;
		this.setLayout(new BorderLayout());
		
		//transparence
		this.setOpaque(false);
		this.paintModel();
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
	    
		//g2.fillRect(0, 0, this.view.getWidth(), this.view.getHeight());
		
		g2.setColor(Color.WHITE);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Font font = new Font("Serif", Font.PLAIN, 50);
		g2.setFont(font);
		
		if (this.hasWin) {
			
			g2.drawString("You Win", (this.view.getWidth() / 2) - 100, (this.view.getHeight() / 2));
		} else {
			
		}
		
		paintSpriteLeft(g2, winner.getSprite());
		paintSpriteRight(g2, looser.getSprite());
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

package swingy.views.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JPanel;

import swingy.entity.Entity;
import swingy.entity.artefacs.Artefact;
import swingy.model.ISwingyModel;
import swingy.ressources.Sprite;
import swingy.views.SwingyGUIGameView;

public class EndFightMenuComponent extends JPanel implements ISwingyModel {

	/**
	 * GRAPHIC VERSION
	 */
	private static final long serialVersionUID = 1L;

	private SwingyGUIGameView	view;
	private Entity				winner;
	private Entity				looser;
	private boolean				hasWin;
	private ArrayList<Artefact>	drops;
	
	public EndFightMenuComponent(SwingyGUIGameView view, Entity winner, Entity looser, boolean hasWin, ArrayList<Artefact> drops) {
		super();
		this.view = view;
		this.winner = winner;
		this.looser = looser;
		this.hasWin = hasWin;
		this.drops = drops;
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
		
		g2.setColor(Color.WHITE);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		if (this.hasWin) {
			
			g2.setFont(new Font("Serif", Font.PLAIN, 50));
			g2.setColor(Color.WHITE);
			g2.drawString("You Win", (this.view.getWidth() / 2) - 100, (this.view.getHeight() / 2));
			
			int px = (this.view.getWidth() / 2) - 180;
			int py = (this.view.getHeight() / 2) + 50;
			
			paintPanelDrop(g2, px, py);
		} else {
			
			g2.setFont(new Font("Serif", Font.PLAIN, 50));
			g2.setColor(Color.WHITE);
			g2.drawString("You Loose", (this.view.getWidth() / 2) - 100, (this.view.getHeight() / 2));
			
			int px = (this.view.getWidth() / 2) - 180;
			int py = (this.view.getHeight() / 2) + 50;
			
			paintPanelDrop(g2, px, py);
		}
	}
	
	private void paintPanelDrop(Graphics2D g2, int px, int py) {
		paintSpriteLeft(g2, winner.getSprite(), px, py);
		paintSpriteRight(g2, looser.getSprite(), px, py);
		
		g2.setColor(Color.DARK_GRAY);
		g2.fillRect(px, py, 370, 150);
		
		g2.setFont(new Font("Serif", Font.PLAIN, 30));
		g2.setColor(Color.BLACK);
		g2.drawString("Drops :", px + 10, py + 50);
		
		g2.setColor(Color.GRAY);
		for (int i = 0; i < 6; i++) {
			g2.fillRect(px + 10 + (i * 60), py + 70, 50, 50);
		}
		
		g2.setFont(new Font("Serif", Font.PLAIN, 15));
		g2.setColor(Color.WHITE);
		
		int i = 0;
		for (Artefact a : drops) {
			
			Sprite.ITEMS.posid = a.getPictureId();
			Sprite.ITEMS.paint(g2, px + 10 + (i * 60), py + 70);
			g2.drawString(a.getName(), px + 10 + (i * 60), py + 70 + 70);
			
			i++;
		}
	}
	
	private void paintSpriteLeft(Graphics2D g2, Sprite s, int x, int y) {
		int h = s.getHeight();
		int w = s.getWidth();
		int pid = s.posid;
		
		//east 8
		//west 12
		s.posid = 8;
		
		if (s.columnH == 1 && s.columnW == 1) {
			s.posid = 0;
		}
		
		s.setHeight(150);
		s.setWidth(150);
		s.paint(g2, x - 180, y);
		
		s.setHeight(h);
		s.setWidth(w);
		s.posid = pid;
	}
	
	private void paintSpriteRight(Graphics2D g2, Sprite s, int x, int y) {
		int h = s.getHeight();
		int w = s.getWidth();
		int pid = s.posid;
		
		//east 8
		//west 12
		s.posid = 12;
		
		if (s.columnH == 1 && s.columnW == 1) {
			s.posid = 0;
		}
		
		s.setHeight(150);
		s.setWidth(150);
		s.paint(g2, x + 400, y);
		
		s.setHeight(h);
		s.setWidth(w);
		s.posid = pid;
	}
}

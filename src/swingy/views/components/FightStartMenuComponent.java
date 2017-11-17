package swingy.views.components;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JPanel;

import swingy.entity.Entity;
import swingy.enums.EStatElement;
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
	    
		g2.setColor(Color.GRAY);
		
		g2.setComposite(AlphaComposite.SrcOver.derive(0.8f));
		
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
		g2.setComposite(AlphaComposite.SrcOver.derive(1f));
		
	    g2.setColor(Utils.HexToRGB("#D8D8D8"));
	    g2.fillRect((this.view.getWidth() / 2) - 10, 0, 20, (this.view.getHeight() / 2) - 80);
	    g2.fillRect((this.view.getWidth() / 2) - 10, (this.view.getHeight() / 2), 20, (this.view.getHeight() / 2));
	    	
	    g2.setColor(Color.BLACK);
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		        RenderingHints.VALUE_ANTIALIAS_ON);
		Font font = new Font("Serif", Font.PLAIN, 50);
		g2.setFont(font);
		g2.drawString("FIGHT", (this.view.getWidth() / 2) - 70, (this.view.getHeight() / 2) - 25);
		
		font = new Font("Serif", Font.PLAIN, 100);
		g2.setFont(font);
		g2.drawString("VS", (this.view.getWidth() / 2) - 70, (this.view.getHeight() / 2) / 2);
		
		font = new Font("Serif", Font.PLAIN, 20);
		g2.setFont(font);
		g2.drawString("  " + p1.getName(), 0, 275);
		g2.drawString("Level    : " + p1.getLevel(), 0, 300);
		g2.drawString("HitPoint : " + p1.getStat(EStatElement.HitPoint), 0, 325);
		g2.drawString("Attack   : " + p1.getStat(EStatElement.Attack), 0, 350);
		g2.drawString("Defense  : " + p1.getStat(EStatElement.Defense), 0, 375);
		
		g2.drawString("  " + p2.getName(), (this.view.getWidth() / 2) + 10, 275);
		g2.drawString("Level    : " + p2.getLevel(), (this.view.getWidth() / 2) + 10, 300);
		g2.drawString("HitPoint : " + p2.getStat(EStatElement.HitPoint), (this.view.getWidth() / 2) + 10, 325);
		g2.drawString("Attack   : " + p2.getStat(EStatElement.Attack), (this.view.getWidth() / 2) + 10, 350);
		g2.drawString("Defense  : " + p2.getStat(EStatElement.Defense), (this.view.getWidth() / 2) + 10, 375);
		
		paintSpriteLeft(g2, p1.getSprite());
		paintSpriteRight(g2, p2.getSprite());
		
		//fleeing output
		font = new Font("Serif", Font.PLAIN, 50);
		g2.setFont(font);
		Sprite.ESCAPE.paint(g2, 0, (this.view.getHeight() - (Sprite.ESCAPE.getHeight() + 50)));
		g2.setColor(Color.BLACK);
		g2.drawString("FOR ESCAPE TAP KEY [DELETE]", 0, this.view.getHeight());
		
		
		g2.drawString("FOR FIGHT TAP KEY [ENTER]", (this.view.getWidth() / 2) + 10, this.view.getHeight());
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

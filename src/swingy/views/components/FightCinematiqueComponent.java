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
import swingy.model.ISwingyModel;
import swingy.ressources.Sprite;
import swingy.utils.Utils;
import swingy.utils.Vector2;
import swingy.views.SwingyGUIGameView;

public class FightCinematiqueComponent extends JPanel implements ISwingyModel {

	/**
	 * GRAPHIC VERSION
	 */
	private static final long serialVersionUID = 1L;

	private SwingyGUIGameView	view;
	
	private Entity p1;
	private Entity p2;
	
	private ArrayList<Vector2> bandesToRight = new ArrayList<Vector2>();
	private ArrayList<Vector2> bandesToLeft = new ArrayList<Vector2>();
	
	public FightCinematiqueComponent(SwingyGUIGameView view, Entity p1, Entity p2) {
		super();
		this.view = view;
		this.p1 = p1;
		this.p2 = p2;
		this.setLayout(new BorderLayout());
		
		//transparence
		this.setOpaque(false);
		this.paintModel();
		
		final FightCinematiqueComponent tmp = this;
		final ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
		ses.schedule(new Runnable() {

            @Override
            public void run() {
                tmp.removeModel();
            }
        }, 3, TimeUnit.SECONDS);
		
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
	    
		g2.setColor(Color.BLACK);
		
		for (Vector2 v : bandesToRight) {
			g2.fillRect(v.x, v.y, this.view.getWidth(), 55);
			v.x += 10;
		}
		
		for (Vector2 v : bandesToLeft) {
			g2.fillRect(v.x, v.y, this.view.getWidth(), 55);
			v.x -= 10;
		}
		
	    g2.setColor(Utils.HexToRGB("#D8D8D8"));
	    g2.fillRect(0, ((this.view.getHeight() / 2) - 125), this.view.getWidth(), 55);
	    g2.fillRect(0, ((this.view.getHeight() / 2) - 15), this.view.getWidth(), 55);
	    //g2.setColor(Utils.HexToRGB("#A4A4A4"));
	    //g2.fillRect(0, ((this.view.getWidth() / 2) - 100), this.view.getWidth(), 1);
	    	
	    g2.setColor(Color.WHITE);
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		        RenderingHints.VALUE_ANTIALIAS_ON);
		Font font = new Font("Serif", Font.PLAIN, 50);
		g2.setFont(font);
		g2.drawString("FIGHT", (this.view.getWidth() / 2) - 100, (this.view.getHeight() / 2) - 25);
		
		paintSpriteLeft(g2, p1.getSprite());
		paintSpriteRight(g2, p2.getSprite());
	}
	
	private void paintSpriteLeft(Graphics2D g2, Sprite s) {
		int h = s.getHeight();
		int w = s.getWidth();
		int pid = s.posid;
		
		//east 8
		//west 12
		s.posid = 8;
		
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
		
		s.setHeight(200);
		s.setWidth(200);
		s.paint(g2, (this.view.getWidth() - 200) - 100, 100);
		
		s.setHeight(h);
		s.setWidth(w);
		s.posid = pid;
	}
}

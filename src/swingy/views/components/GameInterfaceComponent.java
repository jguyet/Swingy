package swingy.views.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

import swingy.entity.Entity;
import swingy.enums.EStatElement;
import swingy.model.ISwingyModel;
import swingy.utils.Utils;
import swingy.views.SwingyGUIGameView;

public class GameInterfaceComponent extends JPanel implements ISwingyModel {
	
	/**
	 * GRAPHIC VERSION
	 */
	private static final long serialVersionUID = 1L;

	private SwingyGUIGameView	view;
	
	private Entity				character;
	
	public GameInterfaceComponent(SwingyGUIGameView view) {
		super();
		this.view = view;
		this.setLayout(new BorderLayout());
		
		//transparence
		this.setOpaque(false);
	}
	
	public void paintModel() {
		this.view.add(this);
	}
	
	public void setCharacter(Entity e) {
		this.character = e;
	}
	
	public Entity getCharacter() {
		return this.character;
	}
	
	
	protected void paintComponent(java.awt.Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
	    
	    g2.setColor(Utils.HexToRGB("#D8D8D8"));
	    g2.fillRect(0, 0, this.view.getWidth(), 22);
	    g2.setColor(Utils.HexToRGB("#A4A4A4"));
	    g2.fillRect(0, 22, this.view.getWidth(), 1);
	    
	    if (character != null) {
	    	
	    	String name = character.getName();
	    	
	    	if (name.length() > 12) {
	    		name = name.substring(0, 12) + "...";
	    	}
	    	
		    g2.setColor(Color.BLACK);
		    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			        RenderingHints.VALUE_ANTIALIAS_ON);
			Font font = new Font("Serif", Font.PLAIN, 15);
			g2.setFont(font);
			g2.drawString("Name : ", 0, 15);
			g2.drawString(name, 50, 15);
			
			g2.drawString("Level :", this.view.getWidth() - 200, 15);
			g2.drawString(character.getLevel() + "", this.view.getWidth() - 150, 15);
			
			g2.drawString("Exp :", this.view.getWidth() - 120, 15);
			g2.drawString(character.getExp() + "", this.view.getWidth() - 80, 15);
			
			
			g2.drawString("Life : ", 150, 15);
			
			for (int i = 0; i < character.stats.getStat(EStatElement.HitPoint); i++) {
				
				g2.setColor(Utils.HexToRGB("#424242"));
				Ellipse2D.Double circle = new Ellipse2D.Double((200 + (i * 20)) - 1, 6 - 1, 12, 12);
				g2.fill(circle);
				
				g2.setColor(Utils.HexToRGB("#FF0000"));
				Ellipse2D.Double circle2 = new Ellipse2D.Double(200 + (i * 20), 6, 10, 10);
				g2.fill(circle2);
			}
	    }
	}

}

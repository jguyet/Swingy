package swingy.entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import swingy.App;
import swingy.entity.artefacs.Armor;
import swingy.entity.artefacs.Helm;
import swingy.entity.artefacs.Weapon;
import swingy.entity.statistics.Statistics;
import swingy.enums.EStatElement;
import swingy.ressources.Sprite;
import swingy.utils.Vector2;

public class Drag extends Entity {
	private static Sprite		sprite = Sprite.DRAG;
	private static Statistics	baseStats = new Statistics(
															EStatElement.Attack, 5,
															EStatElement.Defense, 0,
															EStatElement.HitPoint, 30
															);
	private static ArrayList<Class<?>> drops = new ArrayList<Class<?>>();
	
	static {
		drops.add(Armor.class);
		drops.add(Helm.class);
		drops.add(Weapon.class);
	}
	
	public Drag(String name, Vector2 position) {
		super(name, baseStats, position);
		this.transform.direction = 3;
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		
		int px = App.worldMap.getStartWidth() + (this.transform.position.x * App.SCALE) - (sprite.getWidth() / 2);
		int py = App.worldMap.getStartHeight() + (this.transform.position.y * App.SCALE) - (sprite.getHeight() / 2);
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		        RenderingHints.VALUE_ANTIALIAS_ON);
		Font font = new Font(Font.MONOSPACED, Font.PLAIN, 12);
		g.setFont(font);
		g.setColor(Color.ORANGE);
		g.drawString(this.getName(), px + 2, py - 15);
		g.setColor(Color.WHITE);
		g.drawString("lvl " + this.getLevel(), px, py);
		
		sprite.paint(g, px, py);
	}
	
	@Override
	public String classe() {
		return "Rabit";
	}
	
	@Override
	public Sprite getSprite() {
		return Drag.sprite;
	}

	@Override
	public ArrayList<Class<?>> getDrops() {
		return Drag.drops;
	}
}

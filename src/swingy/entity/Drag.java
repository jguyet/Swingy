package swingy.entity;

import java.awt.Graphics;
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
		
		int px = App.worldMap.getStartWidth() + (this.transform.position.x * App.SCALE) - (sprite.getWidth() / 2);
		int py = App.worldMap.getStartHeight() + (this.transform.position.y * App.SCALE) - (sprite.getHeight() / 2);
		
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

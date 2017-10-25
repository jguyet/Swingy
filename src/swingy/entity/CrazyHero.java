package swingy.entity;

import java.awt.Graphics;

import swingy.entity.statistics.Statistics;
import swingy.enums.EStatElement;
import swingy.math.Vector2;
import swingy.ressources.Sprite;

public class CrazyHero extends Entity {
	
	private static Statistics	stats;
	private static Sprite		sprite = Sprite.CRAZY;
	
	static {
		stats = new Statistics();
		stats.addStat(EStatElement.Attack, 5);
		stats.addStat(EStatElement.Defense, 2);
		stats.addStat(EStatElement.HitPoint, 3);
	}
	
	/**
	 * Crazy Hero<br>
	 * 5 Attack<br>
	 * 2 Defense<br>
	 * 3 Hit Points<br>
	 * @param name
	 * @param position
	 */
	public CrazyHero(String name, Vector2 position) {
		super(name, stats, position);
	}

	@Override
	public void paint(Graphics g) {
		sprite.paint(g, this.transform.position.x, this.transform.position.x);
	}

}

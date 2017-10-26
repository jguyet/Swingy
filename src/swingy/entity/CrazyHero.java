package swingy.entity;

import java.awt.Graphics;

import swingy.entity.statistics.Statistics;
import swingy.enums.EStatElement;
import swingy.math.Time;
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
	
	private int[]		animation;
	private int			animid = 0;
	private Vector2		lastpos = new Vector2();

	@Override
	public void paint(Graphics g) {
		
		switch (this.transform.direction) {
		case 1://east
			animation = new int[3];
			animation[0] = 9;
			animation[1] = 10;
			animation[2] = 11;
			break ;
		case 3://south
			animation = new int[3];
			animation[0] = 0;
			animation[1] = 1;
			animation[2] = 2;
			break ;
		case 5://west
			animation = new int[3];
			animation[0] = 4;
			animation[1] = 5;
			animation[2] = 6;
			break ;
		case 7://north
			animation = new int[3];
			animation[0] = 12;
			animation[1] = 13;
			animation[2] = 14;
			break ;
		}
		System.out.println(this.transform.direction);
		
		if (animid >= 3)
			animid = 0;
		
		sprite.posid = animation[animid];
		
		sprite.paint(g, this.transform.position.x, this.transform.position.y);
		if (!(lastpos.x == this.transform.position.x && lastpos.y == this.transform.position.y))
			animid++;
		lastpos = new Vector2(this.transform.position.x, this.transform.position.y);
	}

}

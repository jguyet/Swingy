package swingy.entity;

import java.awt.Graphics;

import swingy.entity.statistics.Statistics;
import swingy.math.Vector2;
import swingy.ressources.Sprite;

public class Rabit extends Entity {
	private static Statistics	stats;
	private static Sprite		sprite = Sprite.WARRIOR;
	
	public Rabit(String name, Vector2 position) {
		super(name, stats, position);
	}
	
	private int[]		animation;
	private int			animid = 0;
	private Vector2		lastpos = new Vector2();

	@Override
	public void paint(Graphics g) {
		
		switch (this.transform.direction) {
		case 1://east
			animation = new int[4];
			animation[0] = 8;
			animation[1] = 9;
			animation[2] = 10;
			animation[3] = 11;
			break ;
		case 3://south
			animation = new int[4];
			animation[0] = 0;
			animation[1] = 1;
			animation[2] = 2;
			animation[3] = 3;
			break ;
		case 5://west
			animation = new int[4];
			animation[0] = 12;
			animation[1] = 13;
			animation[2] = 14;
			animation[3] = 15;
			break ;
		case 7://north
			animation = new int[4];
			animation[0] = 4;
			animation[1] = 5;
			animation[2] = 6;
			animation[3] = 7;
			break ;
		}
		
		if (animid >= 4)
			animid = 0;
		
		sprite.posid = animation[animid];
		
		int px = this.transform.position.x + (sprite.getWidth() / 2) - (Sprite.grounds.getWidth() / 2);
		int py = this.transform.position.y + (sprite.getHeight() - Sprite.grounds.getHeight()) + (Sprite.grounds.getHeight() / 2);
		
		sprite.paint(g, px, py);
		if (!(lastpos.x == this.transform.position.x && lastpos.y == this.transform.position.y))
			animid++;
		lastpos = new Vector2(this.transform.position.x, this.transform.position.y);
	}
}

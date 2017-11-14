package swingy.entity;

import java.awt.Graphics;

import swingy.App;
import swingy.entity.statistics.Statistics;
import swingy.enums.EStatElement;
import swingy.ressources.Sprite;
import swingy.utils.Vector2;

public class Magician extends Entity {

	private static Sprite		sprite = Sprite.MAGE;
	private static Statistics	baseStats = new Statistics(
															EStatElement.Attack, 2,
															EStatElement.Defense, 6,
															EStatElement.HitPoint, 4
															);
	
	public Magician(String name, Vector2 position) {
		super(name, baseStats, position);
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
		
		if (animid >= 3)
			animid = 0;
		
		sprite.posid = animation[animid];
		
		int px = App.worldMap.getStartWidth() + (this.transform.position.x * App.SCALE) - (sprite.getWidth() / 2);
		int py = App.worldMap.getStartHeight() + (this.transform.position.y * App.SCALE) - (sprite.getHeight() / 2);
		
		sprite.paint(g, px, py);
		if (!(lastpos.x == this.transform.position.x && lastpos.y == this.transform.position.y))
			animid++;
		lastpos = new Vector2(this.transform.position.x, this.transform.position.y);
	}
	
	@Override
	public String classe() {
		return "Magician";
	}
	
	@Override
	public Sprite getSprite() {
		return this.sprite;
	}

}

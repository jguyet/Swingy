package swingy.entity.monsters;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import swingy.App;
import swingy.entity.Entity;
import swingy.entity.artefacs.Armor;
import swingy.entity.artefacs.Artefact;
import swingy.entity.artefacs.Helm;
import swingy.entity.artefacs.Weapon;
import swingy.entity.statistics.Statistics;
import swingy.enums.EStatElement;
import swingy.ressources.Sprite;
import swingy.utils.Vector2;

public class Rabit extends Entity {
	private static Sprite		sprite = Sprite.RABIT;
	private static ArrayList<Artefact> drops = new ArrayList<Artefact>();
	
	static {
		drops.add(new Armor("RabitAmor", 2, new Statistics(EStatElement.Defense, 2), false, 394));
		drops.add(new Helm("RabitHelm", 2, new Statistics(EStatElement.HitPoint, 4), false, 402));
		drops.add(new Weapon("RabitSword", 3, new Statistics(EStatElement.Attack, 5), false, 197));
	}
	
	public Rabit(String name, Statistics stats, Vector2 position) {
		super(name, stats, position);
		this.transform.direction = 3;
	}
	
	private int[]		animation;
	private int			animid = 0;
	private Vector2		lastpos = new Vector2();

	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		
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
		
		int px = App.worldMap.getStartWidth() + (this.transform.position.x * App.SCALE) - (sprite.getWidth() / 2);
		int py = App.worldMap.getStartHeight() + (this.transform.position.y * App.SCALE) - (sprite.getHeight() / 2);
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		        RenderingHints.VALUE_ANTIALIAS_ON);
		Font font = new Font(Font.MONOSPACED, Font.PLAIN, 12);
		g.setFont(font);
		g.setColor(Color.YELLOW);
		g.drawString(this.getName(), px + 2, py - 15);
		g.setColor(Color.WHITE);
		g.drawString("lvl " + this.getLevel(), px, py);
		
		sprite.paint(g, px, py);
		if (!(lastpos.x == this.transform.position.x && lastpos.y == this.transform.position.y))
			animid++;
		lastpos = new Vector2(this.transform.position.x, this.transform.position.y);
	}
	
	@Override
	public String classe() {
		return "Rabit";
	}
	
	@Override
	public Sprite getSprite() {
		return Rabit.sprite;
	}

	@Override
	public ArrayList<Artefact> getDrops() {
		return Rabit.drops;
	}
}

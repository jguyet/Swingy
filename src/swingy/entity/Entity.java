package swingy.entity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import swingy.entity.statistics.Statistics;
import swingy.entity.transform.Transform;
import swingy.model.ISwingyModel;
import swingy.ressources.Sprite;
import swingy.utils.Vector2;
import swingy.world.WorldMap;
import swingy.world.WorldMap.Case;

public abstract class Entity implements ISwingyModel {
	
	/**
	 * PUBLIC VARS
	 */
	public Transform	transform;
	public Statistics	stats;
	public byte			direction = 1;
	
	/**
	 * PRIVATE VARS
	 */
	@NotNull
	@Size(max=50,min=1)
	private String		name;
	@Min(value=1)
	private int			level = 1;
	@Min(value=0)
	private long		exp = 0;
	
	/**
	 * Entity constructor
	 * @param name
	 * @param statistics
	 * @param position
	 */
	public Entity(String name, Statistics statistics, Vector2 position) {
		this.name = name;
		this.stats = statistics;
		this.transform = new Transform(position);
	}
	
	public void initRandomPositionToMap(WorldMap map) {
		
		Case c = map.getRandomWalkableCase();
		
		c.addEntity(this);
		
		this.transform.position.x = c.x;
		this.transform.position.y = c.y;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public long getExp() {
		return exp;
	}

	public void setExp(long exp) {
		addExp(exp);
	}
	
	public boolean addExp(long exp) {
		boolean	levelUp		= false;
		
		long lastLevelXp = (long) (((this.level - 1)*1000)+(Math.sqrt(this.level - 2)*450));
		
		while (((this.exp + exp) - lastLevelXp) > (this.level*1000)+(Math.sqrt(this.level - 1)*450)) {
			this.level++;
			levelUp = true;
		}
		this.exp = this.exp + exp;
		return (levelUp);
	}
	
	public String toString() {
		return (this.getName() + " " + this.classe() + " " + this.level + " " + this.exp + "\n");
	}
	
	public abstract String classe();
	
	public abstract Sprite getSprite();
}

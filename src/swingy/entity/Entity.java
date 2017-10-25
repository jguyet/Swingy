package swingy.entity;

import swingy.entity.statistics.Experience;
import swingy.entity.statistics.Statistics;
import swingy.entity.transform.Transform;
import swingy.math.Vector2;
import swingy.model.ISwingyModel;

public abstract class Entity implements ISwingyModel {
	
	/**
	 * PUBLIC VARS
	 */
	public Transform	transform;
	public Statistics	stats;
	
	/**
	 * PRIVATE VARS
	 */
	private String		name;
	private int			level;
	private long		exp;
	
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
		int		newlevel	= Experience.getLevelByExp(this.exp + exp);
		
		if (newlevel > this.level) {
			levelUp = true;
			this.level = newlevel;
		}
		this.exp = this.exp + exp;
		return (levelUp);
	}
}

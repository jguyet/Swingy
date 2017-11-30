package swingy.entity;

import java.util.ArrayList;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import swingy.App;
import swingy.entity.artefacs.Armor;
import swingy.entity.artefacs.Artefact;
import swingy.entity.artefacs.Helm;
import swingy.entity.artefacs.Weapon;
import swingy.entity.statistics.Statistics;
import swingy.entity.transform.Transform;
import swingy.enums.EStatElement;
import swingy.model.ISwingyModel;
import swingy.ressources.Sprite;
import swingy.utils.Utils;
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
	public ArrayList<Artefact> inventory = new ArrayList<Artefact>();
	public int			fightHitPoint = 0;
	
	private Weapon		weapon;
	private Helm		helm;
	private Armor		armor;
	
	/**
	 * PRIVATE VARS
	 */
	@NotNull
	@Size(max=50,min=3)
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
	
	public int getStat(EStatElement e) {
		int baseStat = this.stats.getStat(e);
		
		if (this.weapon != null)
			baseStat += this.weapon.stats.getStat(e);
		if (this.helm != null)
			baseStat += this.helm.stats.getStat(e);
		if (this.armor != null)
			baseStat += this.armor.stats.getStat(e);
		return baseStat;
	}
	
	public Weapon getWeapon() {
		return (this.weapon);
	}
	
	public Armor getArmor() {
		return (this.armor);
	}
	
	public Helm getHelm() {
		return (this.helm);
	}
	
	public void equipeWeapon(Weapon w) {
		if (this.weapon != null) {
			this.weapon.equiped = false;
		}
		this.weapon = w;
		w.equiped = true;
	}
	
	public void equipeArmor(Armor a) {
		if (this.armor != null) {
			this.armor.equiped = false;
		}
		this.armor = a;
		a.equiped = true;
	}
	
	public void equipeHelm(Helm h) {
		if (this.helm != null) {
			this.helm.equiped = false;
		}
		this.helm = h;
		h.equiped = true;
	}
	
	public void equipe(Artefact a) {
		if (a instanceof Weapon) {
			this.equipeWeapon((Weapon)a);
		} else if (a instanceof Helm) {
			this.equipeHelm((Helm)a);
		} else if (a instanceof Armor) {
			this.equipeArmor((Armor)a);
		}
	}
	
	public long getExpLevel(int level) {
		
		return ((long)(((level)*1000) + (Math.sqrt((level) - 1)*450)));
	}
	
	public boolean addExp(long exp) {
		boolean	levelUp		= false;
		
		
		while ((this.exp + exp) > getExpLevel(this.level + 1)) {
			this.level++;
			levelUp = true;
		}
		this.exp = this.exp + exp;
		return (levelUp);
	}
	
	public String toString() {
		
		StringBuilder str = new StringBuilder();
		
		str.append(this.name).append(" ")
		.append(this.classe()).append(" ")
		.append(this.level).append(" ")
		.append(this.exp);
		
		for (Artefact a : this.inventory) {
			str.append(" ").append(a.toString());
		}
		
		str.append("\n");
		
		return (str.toString());
	}
	
	public void moveRandom(WorldMap world) {
		Vector2 v = new Vector2(this.transform.position.x, this.transform.position.y);
		
		int dir = 1;
		if (Utils.getRandomValue(1, 2) == 1)
			dir = -1;
		boolean xx = false;
		if (dir == 1) {
			if (Utils.getRandomValue(1, 2) == 1)
				xx = true;
		}
		if (xx)
			v.x += dir;
		else
			v.y += dir;
		if (world.getCaseByPosition(v) != null && world.getCaseByPosition(v).isWalkable() && !world.getCaseByPosition(v).hasEntity()) {
			world.getCaseByPosition(this.transform.position).removeEntity();
			world.getCaseByPosition(v).addEntity(this);
			this.transform.translate(v);
		}
	}
	
	public abstract String classe();
	
	public abstract Sprite getSprite();
	
	public abstract ArrayList<Artefact> getDrops();
}

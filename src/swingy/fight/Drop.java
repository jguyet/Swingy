package swingy.fight;

import java.util.Map.Entry;

import swingy.entity.artefacs.Armor;
import swingy.entity.artefacs.Artefact;
import swingy.entity.artefacs.Helm;
import swingy.entity.artefacs.Weapon;
import swingy.entity.statistics.Statistics;
import swingy.enums.EArtefact;
import swingy.enums.EStatElement;
import swingy.utils.Utils;

public class Drop {
	
	private EArtefact	type;
	private String		name;
	private int			level;
	private Statistics	min;
	private Statistics	max;
	private int			pictureId;

	public Drop(EArtefact type, String name, int level, Statistics min, Statistics max, int pictureId) {
		
		this.min = min;
		this.max = max;
		this.pictureId = pictureId;
	}
	
	public Artefact generateArtefact() {
	
		Statistics n = new Statistics();
		Artefact a = null;
		
		switch (type) {
			case WEAPON:
				a = new Weapon(name, level, n, false, this.pictureId);
				break ;
			case HELM:
				a = new Helm(name, level, n, false, this.pictureId);
				break ;
			case ARMOR:
				a = new Armor(name, level, n, false, this.pictureId);
				break ;
		}
		
		for (Entry<EStatElement, Integer> entry : min.getStats().entrySet()) {
			
			int minimal = entry.getValue();
			int maximal = max.getStat(entry.getKey());
			
			int rand = Utils.getRandomValue(minimal, maximal);
			
			n.addStat(entry.getKey(), rand);
		}
		a.stats = n;
		return (a);
	}

	public EArtefact getType() {
		return type;
	}

	public void setType(EArtefact type) {
		this.type = type;
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

	public Statistics getMin() {
		return min;
	}

	public void setMin(Statistics min) {
		this.min = min;
	}

	public Statistics getMax() {
		return max;
	}

	public void setMax(Statistics max) {
		this.max = max;
	}

	public int getPictureId() {
		return pictureId;
	}

	public void setPictureId(int pictureId) {
		this.pictureId = pictureId;
	}
}

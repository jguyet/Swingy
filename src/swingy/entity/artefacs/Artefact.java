package swingy.entity.artefacs;

import java.util.Map.Entry;

import swingy.entity.statistics.Statistics;
import swingy.enums.EArtefact;
import swingy.enums.EStatElement;
import swingy.ressources.Sprite;

public abstract class Artefact {

	public Statistics	stats;
	public boolean		equiped = false;
	
	private String		name;
	private int			level;
	private int			pictureId;
	
	public Artefact(String name, Statistics stats, int level, int pictureId) {
		this.name = name;
		this.stats = stats;
		this.level = level;
		this.pictureId = pictureId;
	}
	
	public Artefact(String name, Statistics stats, int level, boolean equiped, int pictureId) {
		this.name = name;
		this.stats = stats;
		this.level = level;
		this.equiped = equiped;
		this.pictureId = pictureId;
	}
	
	public Artefact(String name, String stats, int level, boolean equiped, int pictureId) {
		this.name = name;
		this.stats = parseStats(stats);
		this.level = level;
		this.equiped = equiped;
		this.pictureId = pictureId;
	}
	
	public String getName() {
		return (this.name);
	}
	
	public int getLevel() {
		return (this.level);
	}
	
	public int getPictureId() {
		return (this.pictureId);
	}
	
	public String toString() {
		
		StringBuilder str = new StringBuilder();
		
		str.append(this.getType()).append(",");
		str.append(this.name).append(",");
		str.append(this.level).append(",");
		
		boolean first = true;
		
		for (Entry<EStatElement, Integer> entry : stats.getStats().entrySet()) {
			if (!first)
				str.append(";");
			str.append(entry.getKey()).append("@").append(entry.getValue());
			first = false;
		}
		
		str.append(",");
		str.append(this.equiped).append(",");
		str.append(this.pictureId);
		return (str.toString());
	}
	
	private Statistics parseStats(String stats) {
		String[] split = stats.split(";");
		
		Statistics s = new Statistics();
		
		for (String entry : split) {
			
			String sElement = entry.split("@")[0];
			int val = Integer.parseInt(entry.split("@")[1]);
			
			EStatElement e = EStatElement.valueOf(sElement);
			
			if (e == null)
				continue ;
			s.addStat(e, val);
		}
		return (s);
	}
	
	public abstract Sprite getSprite();
	
	public abstract EArtefact getType();
}

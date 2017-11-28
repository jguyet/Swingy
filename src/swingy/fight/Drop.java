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

	public Drop(EArtefact type, String name, int level, Statistics min, Statistics max) {
		
		this.min = min;
		this.max = max;
	}
	
	public Artefact generateArtefact() {
	
		Statistics n = new Statistics();
		Artefact a = null;
		
		switch (type) {
			case WEAPON:
				a = new Weapon(name, level, n, false);
				break ;
			case HELM:
				a = new Helm(name, level, n, false);
				break ;
			case ARMOR:
				a = new Armor(name, level, n, false);
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
}

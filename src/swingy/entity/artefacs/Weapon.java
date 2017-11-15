package swingy.entity.artefacs;

import swingy.entity.statistics.Statistics;
import swingy.enums.EStatElement;

public class Weapon extends Artefact{

	private static Statistics stats = new Statistics();
	
	static {
		stats.addStat(EStatElement.Attack, 2);
	}
	
	public Weapon() {
		super(stats);
	}

}

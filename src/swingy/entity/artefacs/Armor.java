package swingy.entity.artefacs;

import swingy.entity.statistics.Statistics;
import swingy.enums.EStatElement;

public class Armor extends Artefact{

	private static Statistics stats = new Statistics();
	
	static {
		stats.addStat(EStatElement.Increase_Defense, 2);
	}
	
	public Armor() {
		super(stats);
	}
	
}

package swingy.entity.artefacs;

import swingy.entity.statistics.Statistics;
import swingy.enums.EStatElement;

public class Helm extends Artefact {

	private static Statistics stats = new Statistics();
	
	static {
		stats.addStat(EStatElement.HitPoint, 2);
	}
	
	public Helm() {
		super(stats);
	}
}

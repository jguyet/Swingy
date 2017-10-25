package swingy.entity.artefacs;

import swingy.entity.statistics.Statistics;

public abstract class Artefact {

	public Statistics stats;
	
	public Artefact(Statistics stats) {
		this.stats = stats;
	}
}

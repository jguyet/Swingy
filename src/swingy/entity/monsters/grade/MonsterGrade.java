package swingy.entity.monsters.grade;

import swingy.entity.statistics.Statistics;

public class MonsterGrade {

	public Statistics stats;
	public int level;
	
	public MonsterGrade(int level, Statistics stats) {
		this.level = level;
		this.stats = stats;
	}
}

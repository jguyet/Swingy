package swingy.entity.statistics;

import java.util.HashMap;
import java.util.Map;

import swingy.enums.EStatElement;

public class Statistics {

	private static final int NULL_VALUE = 0;
	
	private Map<EStatElement, Integer> stats = new HashMap<EStatElement, Integer>();
	
	public Statistics(Object ... args) {
		
		for (int i = 0; i < args.length; i += 2) {
			EStatElement e = (EStatElement)args[i];
			Integer val = (Integer)args[i + 1];
			
			addStat(e, val);
		}
	}
	
	public Map<EStatElement, Integer> getStats() {
		return (this.stats);
	}
	
	public int getStat(EStatElement e) {
		if (stats.containsKey(e)) {
			return (stats.get(e));
		}
		return (NULL_VALUE);
	}
	
	public void addStat(EStatElement e, int value) {
		if (stats.containsKey(e)) {
			stats.put(e, stats.get(e) + value);
			return ;
		}
		setStat(e, value);
	}
	
	public void setStat(EStatElement e, int value) {
		stats.put(e, value);
	}
}

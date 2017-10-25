package swingy.entity.statistics;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Experience {
	
	public static final Map<Integer, Long> TAB = new TreeMap<Integer, Long>();
	
	static {
		TAB.put(1,          0L);//level 1
		TAB.put(2,       1400L);//level 2
		TAB.put(3,       2800L);//level 3
		TAB.put(4,       5600L);//level 4
		TAB.put(5,      11200L);//level 5
		TAB.put(6,      22400L);//level 6
		TAB.put(7,      44800L);//level 7
		TAB.put(8,      89600L);//level 8
		TAB.put(9,     179200L);//level 9
		TAB.put(10,    358400L);//level 10
		TAB.put(11,    716800L);//level 11
		TAB.put(12,   1433600L);//level 12
		TAB.put(13,   2867200L);//level 13
		TAB.put(14,   5734400L);//level 14
		TAB.put(15,  11468800L);//level 15
		TAB.put(16,  22937600L);//level 16
		TAB.put(17,  45875200L);//level 17
		TAB.put(18,  91750400L);//level 18
		TAB.put(19, 183500800L);//level 19
		TAB.put(20, 367001600L);//level 20
	}
	
	public static final int getLevelByExp(long exp) {
		
		int level = 1;
		
		for (Entry<Integer, Long> e : TAB.entrySet()) {
			if (e.getValue() < exp && level < e.getKey())
				level = e.getKey();
		}
		return (level);
	}
}

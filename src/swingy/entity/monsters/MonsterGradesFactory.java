package swingy.entity.monsters;

import java.util.ArrayList;

import swingy.entity.Drag;
import swingy.entity.Entity;
import swingy.entity.Rabit;
import swingy.entity.Spider;
import swingy.entity.statistics.Statistics;
import swingy.enums.EStatElement;
import swingy.utils.Utils;
import swingy.utils.Vector2;

public class MonsterGradesFactory {

	/**
	 * RABIT GRADES
	 */
	
	private static ArrayList<MonsterGrade> rabitGrades = new ArrayList<MonsterGrade>();
	
	static {
		//Grade 1
		rabitGrades.add(0, new MonsterGrade(1, new Statistics(
				EStatElement.Attack, 2,
				EStatElement.Defense, 0,
				EStatElement.HitPoint, 4
				)));
		//Grade 2
		rabitGrades.add(1 ,new MonsterGrade(2, new Statistics(
				EStatElement.Attack, 3,
				EStatElement.Defense, 0,
				EStatElement.HitPoint, 5
				)));
		//Grade 3
		rabitGrades.add(2, new MonsterGrade(3, new Statistics(
				EStatElement.Attack, 4,
				EStatElement.Defense, 1,
				EStatElement.HitPoint, 5
				)));
		//Grade 4
		rabitGrades.add(3, new MonsterGrade(4, new Statistics(
				EStatElement.Attack, 5,
				EStatElement.Defense, 1,
				EStatElement.HitPoint, 6
				)));
		//Grade 5
		rabitGrades.add(4, new MonsterGrade(5, new Statistics(
				EStatElement.Attack, 5,
				EStatElement.Defense, 1,
				EStatElement.HitPoint, 6
				)));
	}
	
	public static Rabit getRandomRabit() {
		MonsterGrade g = rabitGrades.get(Utils.getRandomValue(0, rabitGrades.size() - 1));
		
		Rabit r = new Rabit("Rabit", g.stats, new Vector2(0,0));
		
		r.setLevel(g.level);
		return (r);
	}
	
	/**
	 * DRAG GRADES
	 */
	
	private static ArrayList<MonsterGrade> dragGrades = new ArrayList<MonsterGrade>();
	
	static {
		//Grade 1
		dragGrades.add(0, new MonsterGrade(10, new Statistics(
				EStatElement.Attack, 10,
				EStatElement.Defense, 1,
				EStatElement.HitPoint, 15
				)));
		//Grade 2
		dragGrades.add(1 ,new MonsterGrade(20, new Statistics(
				EStatElement.Attack, 20,
				EStatElement.Defense, 3,
				EStatElement.HitPoint, 30
				)));
		//Grade 3
		dragGrades.add(2, new MonsterGrade(30, new Statistics(
				EStatElement.Attack, 30,
				EStatElement.Defense, 5,
				EStatElement.HitPoint, 45
				)));
		//Grade 4
		dragGrades.add(3, new MonsterGrade(40, new Statistics(
				EStatElement.Attack, 40,
				EStatElement.Defense, 7,
				EStatElement.HitPoint, 60
				)));
		//Grade 5
		dragGrades.add(4, new MonsterGrade(50, new Statistics(
				EStatElement.Attack, 50,
				EStatElement.Defense, 10,
				EStatElement.HitPoint, 80
				)));
	}
	
	public static Drag getRandomDrag() {
		MonsterGrade g = dragGrades.get(Utils.getRandomValue(0, dragGrades.size() - 1));
		
		Drag r = new Drag("Drag", g.stats, new Vector2(0,0));
		
		r.setLevel(g.level);
		return (r);
	}
	
	/**
	 * SPIDER GRADES
	 */
	
	private static ArrayList<MonsterGrade> spiderGrades = new ArrayList<MonsterGrade>();
	
	static {
		//Grade 1
		spiderGrades.add(0, new MonsterGrade(6, new Statistics(
				EStatElement.Attack, 6,
				EStatElement.Defense, 2,
				EStatElement.HitPoint, 8
				)));
		//Grade 2
		spiderGrades.add(1 ,new MonsterGrade(8, new Statistics(
				EStatElement.Attack, 8,
				EStatElement.Defense, 3,
				EStatElement.HitPoint, 10
				)));
		//Grade 3
		spiderGrades.add(2, new MonsterGrade(10, new Statistics(
				EStatElement.Attack, 10,
				EStatElement.Defense, 4,
				EStatElement.HitPoint, 12
				)));
		//Grade 4
		spiderGrades.add(3, new MonsterGrade(12, new Statistics(
				EStatElement.Attack, 12,
				EStatElement.Defense, 5,
				EStatElement.HitPoint, 14
				)));
		//Grade 5
		spiderGrades.add(4, new MonsterGrade(14, new Statistics(
				EStatElement.Attack, 14,
				EStatElement.Defense, 6,
				EStatElement.HitPoint, 16
				)));
	}
	
	public static Spider getRandomSpider() {
		MonsterGrade g = spiderGrades.get(Utils.getRandomValue(0, spiderGrades.size() - 1));
		
		Spider r = new Spider("Spider", g.stats, new Vector2(0,0));
		
		r.setLevel(g.level);
		return (r);
	}
	
	public static Entity getRandomEntityGradeByWorldMapSize(int size) {
		MonsterGrade rabit = rabitGrades.get(0);
		MonsterGrade drag = dragGrades.get(0);
		MonsterGrade spider = spiderGrades.get(0);
		
		ArrayList<Entity> lst = new ArrayList<Entity>();
		
		for (int i = 0; i < (size / rabit.level) && i < 100; i++) {
			lst.add(getRandomRabit());
		}
		
		for (int i = 0; i < (size / drag.level) && i < 100; i++) {
			lst.add(getRandomDrag());
		}
		
		for (int i = 0; i < (size / spider.level) && i < 100; i++) {
			lst.add(getRandomSpider());
		}
		
		return (lst.get(Utils.getRandomValue(1, lst.size() - 1)));
	}
	
}

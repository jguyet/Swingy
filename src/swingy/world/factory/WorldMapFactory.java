package swingy.world.factory;

import swingy.entity.Rabit;
import swingy.math.Utils;
import swingy.math.Vector2;
import swingy.world.WorldMap;

public class WorldMapFactory {

	public static WorldMap generateWorldMap(int characterLevel) {
		
		int			size	= (characterLevel - 1 ) * 5 + 10- (characterLevel % 2);
		WorldMap	map		= new WorldMap(size);
		
		return (map);
	}
	
	public static void loadRandomMonsters(WorldMap map) {
		int number = map.getWidth() * 3;
		
		for (int i = 0; i < number; i++) {
			
			Rabit r = new Rabit("Rabit", new Vector2(0,0));
			
			r.initRandomPositionToMap(map);
			map.addMonster(r);
		}
	}
}

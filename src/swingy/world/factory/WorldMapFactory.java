package swingy.world.factory;

import swingy.world.WorldMap;

public class WorldMapFactory {

	public static WorldMap generateWorldMap(int characterLevel) {
		
		int			size	= (characterLevel - 1 ) * 5 + 10- (characterLevel % 2);
		WorldMap	map		= new WorldMap(size);
		
		return (map);
	}
}

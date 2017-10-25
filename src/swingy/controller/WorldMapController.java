package swingy.controller;

import swingy.world.WorldMap;

public class WorldMapController implements ISwingyController{

	private WorldMap world;
	
	public WorldMapController(WorldMap world) {
		this.world = world;
	}
	
	public WorldMap getWorld() {
		return world;
	}

	public void setWorld(WorldMap world) {
		this.world = world;
	}
	
	@Override
	public void control() {
		
	}

}

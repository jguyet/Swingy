package swingy.controller;

import java.util.ArrayList;

import swingy.App;
import swingy.entity.Entity;
import swingy.fight.Fight;
import swingy.utils.Utils;
import swingy.views.factory.ViewFactory;
import swingy.world.WorldMap;
import swingy.world.WorldMap.Case;

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
		final ArrayList<Entity> list = new ArrayList<Entity>(this.world.getMonsters());
		
		for (Entity e : list) {
			if (App.Character.transform.position.equals(e.transform.position)) {
				
				ViewFactory.newFightStartMenuComponent(App.modelInterface.getinstance(), App.gameview, App.Character, e);
				
				Fight fight = new Fight(App.Character, e);
				
				Entity winner = fight.startFight();
				
				if (winner == App.Character) {
				
					App.Character.addExp(100L);
					
					//ViewFactory.newFightCinematique(App.modelInterface.getinstance(), App.gameview, App.Character, e);
					Case c = world.getCaseByPosition(e.transform.position);
					if (c != null) {
						c.removeEntity();
					}
					world.removeMonster(e);
				} else {
					//TODO loose menu
					Utils.writeHeros(App.Characters);
					App.loopController.stop();
					
					App.gameview.println("Respawn to center map");
				}
			}
		}
	}

}

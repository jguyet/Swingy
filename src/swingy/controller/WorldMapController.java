package swingy.controller;

import java.util.ArrayList;

import swingy.App;
import swingy.entity.Entity;
import swingy.enums.EModule;
import swingy.fight.Fight;
import swingy.utils.Utils;
import swingy.views.components.EndFightMenuComponent;
import swingy.views.components.FightStartMenuComponent;
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
	
	private boolean waitResponseStartFight(Entity character, Entity monster) {
		StartFightController controller = new StartFightController(character, monster);
		//WAIT RESPONSE
		controller.control();
		
		return (controller.hasSelectedGoFight());
	}
	
	private void waitResponseEndFightPanel(Entity winner, Entity looser, boolean hasWin) {
		
		EndFightController endFightController = new EndFightController(winner, looser, hasWin);
		endFightController.control();
	}
	
	private void goFight(Entity character, Entity monster) {
		
		boolean gofight = waitResponseStartFight(character, monster);
		
		if (gofight == false && Utils.getRandomValue(1, 100) < 30) {
			//TODO ANIMATION
			gofight = true;
		}
		
		if (gofight) {
			
			Fight fight = new Fight(App.Character, monster);
			
			Entity winner = fight.startFight();
			
			if (winner == App.Character) {
			
				character.addExp(100L);
				waitResponseEndFightPanel(character, monster, true);
				
				Case c = world.getCaseByPosition(monster.transform.position);
				if (c != null)
					c.removeEntity();
				world.removeMonster(monster);
			} else {
				waitResponseEndFightPanel(monster, character, false);
				
				Utils.writeHeros(App.Characters);
				App.loopController.stop();
				App.gameview.println("Respawn to center of the map");
			}
		} else {
			for (int i = 0; i < 3; i++) {
				monster.moveRandom(world);
			}
		}
	}
	
	private void checkMapCollision(Entity character) {
		
		final ArrayList<Entity> list = new ArrayList<Entity>(this.world.getMonsters());
		
		for (Entity e : list) {
			if (character.transform.position.equals(e.transform.position)) {
				this.goFight(character, e);
				break ;
			}
		}
	}
	
	@Override
	public void control() {
		
		Entity character = App.Character;
		
		checkMapCollision(character);
		
		if (App.modelInterface.getinstance() == EModule.GUI) {
			for (Entity e : this.world.getMonsters()) {
				
				if (Utils.getRandomValue(1, 400) == 1) {
					e.moveRandom(world);
				}
			}
		}
	}

}

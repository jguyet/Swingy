package swingy.controller;

import java.util.ArrayList;

import swingy.App;
import swingy.entity.Entity;
import swingy.entity.artefacs.Artefact;
import swingy.entity.factory.EntityFactory;
import swingy.enums.EModule;
import swingy.enums.GameConsoleLabel;
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
	
	private void waitResponseEndFightPanel(Entity winner, Entity looser, boolean hasWin, ArrayList<Artefact> drops) {
		
		EndFightController endFightController = new EndFightController(winner, looser, hasWin, drops);
		endFightController.control();
	}
	
	private void goFight(Entity character, Entity monster) {
		boolean gofight = waitResponseStartFight(character, monster);
		
		App.characterController.wait = true;
		//50 % of chance escaping fight
		if (gofight == false && Utils.getRandomValue(1, 100) < 50) {
			//TODO ANIMATION
			gofight = true;
		}
		
		if (gofight) {
			Fight fight = new Fight(character, monster);
			
			Entity winner = fight.startFight();
			
			if (winner == character) {
			
				ArrayList<Artefact> drops = fight.endfight(character, monster);
				character.inventory.addAll(drops);
				
				character.addExp(100L);
				
				waitResponseEndFightPanel(character, monster, true, drops);
				Case c = world.getCaseByPosition(monster.transform.position);
				if (c != null)
					c.removeEntity();
				world.removeMonster(monster);
				EntityFactory.saveCharacters();
				
			} else {
				waitResponseEndFightPanel(monster, character, false, new ArrayList<Artefact>());
				
				EntityFactory.saveCharacters();
				App.gameview.println("Respawn to center of the map");
				App.loopController.stop();
				
				return ;
			}
		} else {
			//escapefight
			character.transform.setToLastPosition();
			App.gameview.println("Fight escape with success");
			App.gameview.println("\033[32m" + GameConsoleLabel.INDICE_CHARACTER_POSITION.lbl() + character.transform.position.x + " y :" + character.transform.position.y + "\033[00m");
		}
		if (App.characterController != null)
			App.characterController.wait = false;
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

package swingy.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import swingy.App;
import swingy.entity.Entity;
import swingy.entity.artefacs.Armor;
import swingy.entity.artefacs.Artefact;
import swingy.entity.artefacs.Helm;
import swingy.entity.artefacs.Weapon;
import swingy.entity.factory.EntityFactory;
import swingy.enums.EModule;
import swingy.enums.EStatElement;
import swingy.enums.GameConsoleLabel;
import swingy.utils.Vector2;
import swingy.views.events.ResponseListener;

public class CharacterController implements ISwingyController, KeyListener, ResponseListener {

	public boolean wait = false;
	
	private Entity					entity;
	private Map<Integer, KeyEvent>	keysDown = new HashMap<Integer, KeyEvent>();
	
	public CharacterController(Entity e) {
		this.entity = e;
		App.gameview.addKeyListener(this);
		App.gameview.println("Character at position x: " + entity.transform.position.x + " y :" + entity.transform.position.y);
		//printMap();
	}
	
	public void destroy() {
		App.gameview.removeKeyListener(this);
	}
	
	//##################################################################
	// CONTROLER METHOD
	//##################################################################
	
	@Override
	public void control() {
		
		if (wait)
			return ;
		//control entity movements and modif
		
		//wait console response
		App.gameview.print(GameConsoleLabel.CHOISE_YOUR_DIRECTION.lbl());
		App.gameview.waitResponse(this);
		moveCharacter();
		
		if (App.worldMap.getCaseByPosition(this.entity.transform.position) == null) {
			this.entity.addExp(1000);
			EntityFactory.saveCharacters();
			App.loopController.stop();
		}
	}
	
	private void printMap() {
		if (App.modelInterface.getinstance() == EModule.CONSOLE) {
			for (int y = 0; y < App.worldMap.getHeight(); y++) {
				for (int x = 0; x < App.worldMap.getWidth(); x++) {
					if (entity.transform.position.x == x && entity.transform.position.y == y)
						App.gameview.print("o");
					else if (App.worldMap.getCaseByPosition(new Vector2(x, y)).hasEntity())
						App.gameview.print("?");
					else if (!App.worldMap.getCaseByPosition(new Vector2(x, y)).isWalkable())
						App.gameview.print("x");
					else
						App.gameview.print(".");
				}
				App.gameview.println("");
			}
		}
	}
	
	private void moveCharacter() {
		
		if (keysDown.containsKey(KeyEvent.VK_ESCAPE)) {
			
			this.onResponse("BACKTOMENU");
			return ;
		}
		
		if (keysDown.containsKey(KeyEvent.VK_R)) {
			EntityFactory.saveCharacters();
			App.loopController.stop();
			return ;
		}
		
		Vector2 tmp = new Vector2(entity.transform.position.x, entity.transform.position.y);
		
		if (keysDown.containsKey(KeyEvent.VK_RIGHT)
				|| keysDown.containsKey(KeyEvent.VK_D)) {
			tmp.x += 1;
		}
		if (keysDown.containsKey(KeyEvent.VK_LEFT)
				|| keysDown.containsKey(KeyEvent.VK_A)) {
			tmp.x -= 1;
		}
		if (keysDown.containsKey(KeyEvent.VK_UP)
				|| keysDown.containsKey(KeyEvent.VK_W)) {
			tmp.y -= 1;
		}
		if (keysDown.containsKey(KeyEvent.VK_DOWN)
				|| keysDown.containsKey(KeyEvent.VK_S)) {
			tmp.y += 1;
		}
		if (App.worldMap.getCaseByPosition(tmp) == null || App.worldMap.getCaseByPosition(tmp).isWalkable()) {
			Vector2 movement = new Vector2(entity.transform.position.x - tmp.x, entity.transform.position.y - tmp.y);
			if (!(movement.x == 0 && movement.y == 0)) {
				
				if (App.worldMap.getCaseByPosition(entity.transform.position) != null)
					App.worldMap.getCaseByPosition(entity.transform.position).removeEntity();
				if (App.worldMap.getCaseByPosition(tmp) != null)
					App.worldMap.getCaseByPosition(tmp).addEntity(entity);
				
				entity.transform.translate(tmp);
				App.worldMap.setStartWidth(App.worldMap.getStartWidth() + (movement.x * App.SCALE));
				App.worldMap.setStartHeight(App.worldMap.getStartHeight() + (movement.y * App.SCALE));
				App.gameview.println("\033[32m" + GameConsoleLabel.INDICE_CHARACTER_POSITION.lbl() + entity.transform.position.x + " y :" + entity.transform.position.y + "\033[00m");
			}
		} else {
			App.gameview.println("\033[33m" + GameConsoleLabel.CANCEL_CHARACTER_MOVEMENT.lbl() +"\033[00m");
		}
		keysDown.clear();
		//printMap();
	}
	
	//##################################################################
	// KEY LISTENER
	//##################################################################
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keysDown.put(e.getKeyCode(), e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keysDown.remove(e.getKeyCode());
	}

	@Override
	public void onResponse(String response) {
		
		switch (response.split(" ")[0].toUpperCase()) {
			case "NORTH" :
			case "UP":
				keysDown.put(KeyEvent.VK_UP, null);
				break ;
			case "EAST" :
			case "RIGHT":
				keysDown.put(KeyEvent.VK_RIGHT, null);
				break ;
			case "SOUTH" :
			case "DOWN":
				keysDown.put(KeyEvent.VK_DOWN, null);
				break ;
			case "WEST" :
			case "LEFT":
				keysDown.put(KeyEvent.VK_LEFT, null);
				break ;
			case "DEBUGMAP":
				this.printMap();
				break ;
			case "INVENTORY":
				App.gameview.println("Inventory information :");
				
				for (Artefact a: App.Character.inventory) {
					
					String equiped = "";
					
					if (a.equiped)
						equiped = " (Equiped)";
					
					App.gameview.println("(" + App.Character.inventory.indexOf(a) + ") " + a.getName() + " lvl(" + a.getLevel() + ")" + equiped);
				}
				break ;
			case "EQUIPE":
				
				try {
					int id = Integer.parseInt(response.split(" ")[1]);
					Artefact a = App.Character.inventory.get(id);
					
					switch (a.getType()) {
					case WEAPON:
						App.Character.equipeWeapon((Weapon)a);
						break ;
					case HELM:
						App.Character.equipeHelm((Helm)a);
						break ;
					case ARMOR:
						App.Character.equipeArmor((Armor)a);
						break ;
					}
					
					App.gameview.println(a.getType().getString() + " equiped.");
					EntityFactory.saveCharacters();
					
				} catch (Exception e) {
					App.gameview.println("Syntax error id not fund");
				}
				
				break ;
			case "STATS":
				App.gameview.println("Statistics information :");
				App.gameview.println("Name : " + App.Character.getName());
				App.gameview.println("Level : " + App.Character.getLevel());
				App.gameview.println("Exp : " + App.Character.getExpLevel(App.Character.getLevel()) + "/[" + App.Character.getExp() + "]/" + App.Character.getExpLevel(App.Character.getLevel() + 1));
				App.gameview.println("HitPoint : " + App.Character.getStat(EStatElement.HitPoint));
				App.gameview.println("Attack : " + App.Character.getStat(EStatElement.Attack));
				App.gameview.println("Defense : " + App.Character.getStat(EStatElement.Defense));
				break ;
			case "%XP%":
				long current = App.Character.getExp() - App.Character.getExpLevel(App.Character.getLevel());
				long fn = App.Character.getExpLevel(App.Character.getLevel() + 1) - App.Character.getExpLevel(App.Character.getLevel());
				long percent = 100 * current / fn;
				App.gameview.println(percent + "%");
				break ;
			case "BACKTOMENU":
				App.gameview.println("=====================================");
				EntityFactory.saveCharacters();
				if (App.window != null) {
					App.window.setVisible(false);
				}
				App.toMainMenu = true;
				App.loopController.stop();
				
				break ;
			case "HELP":
				App.gameview.println("=====================================");
				App.gameview.println("STATS       print character statistics");
				App.gameview.println("BACKTOMENU  return to main menu");
				App.gameview.println("INVENTORY   print inventory");
				App.gameview.println("EQUIPE <id> equipe artefact id");
				App.gameview.println("QUIT        quit the game");
				App.gameview.println("=====================================");
				break ;
			case "QUIT":
				App.gameview.println("=====================================");
				System.exit(0);
				break ;
		}
	}

	//##################################################################

}

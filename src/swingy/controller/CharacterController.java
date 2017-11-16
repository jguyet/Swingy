package swingy.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import swingy.App;
import swingy.entity.Entity;
import swingy.enums.EModule;
import swingy.enums.EStatElement;
import swingy.enums.GameConsoleLabel;
import swingy.utils.Utils;
import swingy.utils.Vector2;
import swingy.views.events.ResponseListener;

public class CharacterController implements ISwingyController, KeyListener, ResponseListener {

	private Entity					entity;
	private Map<Integer, KeyEvent>	keysDown = new HashMap<Integer, KeyEvent>();
	
	public CharacterController(Entity e) {
		this.entity = e;
		App.gameview.addKeyListener(this);
		App.gameview.println("Character at position x: " + entity.transform.position.x + " y :" + entity.transform.position.y);
		//printMap();
	}
	
	//##################################################################
	// CONTROLER METHOD
	//##################################################################
	
	@Override
	public void control() {
		//control entity movements and modif
		
		//wait console response
		App.gameview.print(GameConsoleLabel.CHOISE_YOUR_DIRECTION.lbl());
		App.gameview.waitResponse(this);
		mouseCharacter();
		
		if (App.worldMap.getCaseByPosition(this.entity.transform.position) == null) {
			this.entity.addExp(1000);
			Utils.writeHeros(App.Characters);
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
	
	private void mouseCharacter() {
		
		if (keysDown.containsKey(KeyEvent.VK_ESCAPE)) {
			
			this.onResponse("BACKTOMENU");
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
		
		switch (response.toUpperCase()) {
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
			case "STATS":
				App.gameview.println("Statistics information :");
				App.gameview.println("Name : " + App.Character.getName());
				App.gameview.println("Level : " + App.Character.getLevel());
				App.gameview.println("Exp : " + App.Character.getExp());
				App.gameview.println("HitPoint : " + App.Character.getStat(EStatElement.HitPoint));
				App.gameview.println("Attack : " + App.Character.getStat(EStatElement.Attack));
				App.gameview.println("Defense : " + App.Character.getStat(EStatElement.Defense));
				break ;
			case "BACKTOMENU":
				App.gameview.println("=====================================");
				Utils.writeHeros(App.Characters);
				if (App.window != null) {
					App.window.setVisible(false);
				}
				App.toMainMenu = true;
				App.loopController.stop();
				
				break ;
			case "HELP":
				App.gameview.println("=====================================");
				App.gameview.println("STATS      print character statistics");
				App.gameview.println("BACKTOMENU return to main menu");
				App.gameview.println("QUIT       quit the game");
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

package swingy.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import swingy.App;
import swingy.entity.Entity;
import swingy.enums.EModule;
import swingy.math.Vector2;
import swingy.views.SwingyConsoleView;
import swingy.views.events.ResponseListener;

public class CharacterController implements ISwingyController, KeyListener, ResponseListener {

	private Entity					entity;
	private Map<Integer, KeyEvent>	keysDown = new HashMap<Integer, KeyEvent>();
	
	public CharacterController(Entity e) {
		this.entity = e;
		App.gameview.addKeyListener(this);
	}
	
	//##################################################################
	// CONTROLER METHOD
	//##################################################################
	
	@Override
	public void control() {
		//control entity movements and modif
		
		//wait console response
		App.gameview.print("Choise direction (North/East/South/West) : ");
		App.gameview.waitResponse(this);
		mouseCharacter();
		App.gameview.println("Character at position x: " + entity.transform.position.x + " y :" + entity.transform.position.y);
		
		if (App.worldMap.getCaseByPosition(this.entity.transform.position) == null) {
			System.out.println("LA");
			this.entity.addExp(1000);
			App.loopController.stop();
		}
		
	}
	
	private void mouseCharacter() {
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
		if (App.worldMap.getCaseByPosition(tmp) == null || App.worldMap.getCaseByPosition(tmp).isWalkable())
			if (!(tmp.x == 0 && tmp.y == 0))
				entity.transform.translate(tmp);
		keysDown.clear();
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
				keysDown.put(KeyEvent.VK_UP, null);
				break ;
			case "EAST" :
				keysDown.put(KeyEvent.VK_RIGHT, null);
				break ;
			case "SOUTH" :
				keysDown.put(KeyEvent.VK_DOWN, null);
				break ;
			case "WEST" :
				keysDown.put(KeyEvent.VK_LEFT, null);
				break ;
		}
	}

	//##################################################################

}

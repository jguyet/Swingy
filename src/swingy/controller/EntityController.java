package swingy.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import swingy.entity.Entity;

public class EntityController implements ISwingyController, KeyListener {

	private Entity					entity;
	private Map<Integer, KeyEvent>	keysDown = new HashMap<Integer, KeyEvent>();
	
	public EntityController(Entity e) {
		this.entity = e;
	}
	
	//##################################################################
	// CONTROLER METHOD
	//##################################################################
	
	@Override
	public void control() {
		//control entity movements and modif
		
		if (keysDown.containsKey(KeyEvent.VK_RIGHT)) {
			
		}
		
		if (keysDown.containsKey(KeyEvent.VK_LEFT)) {
			
		}
		
		if (keysDown.containsKey(KeyEvent.VK_UP)) {
			
		}
		
		if (keysDown.containsKey(KeyEvent.VK_DOWN)) {
			
		}
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
		if (keysDown.containsKey(e.getKeyCode()))
			keysDown.remove(e.getKeyCode());
	}
	//##################################################################

}

package swingy.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import swingy.App;
import swingy.entity.Entity;
import swingy.math.Vector2;

public class CharacterController implements ISwingyController, KeyListener {

	private Entity					entity;
	private Map<Integer, KeyEvent>	keysDown = new HashMap<Integer, KeyEvent>();
	
	public CharacterController(Entity e) {
		this.entity = e;
		App.window.addKeyListener(this);
	}
	
	//##################################################################
	// CONTROLER METHOD
	//##################################################################
	
	@Override
	public void control() {
		//control entity movements and modif
		
		Vector2 tmp = new Vector2(entity.transform.position.x, entity.transform.position.y);
		
		if (keysDown.containsKey(KeyEvent.VK_RIGHT)
				|| keysDown.containsKey(KeyEvent.VK_D)) {
			tmp.x++;
		}
		if (keysDown.containsKey(KeyEvent.VK_LEFT)
				|| keysDown.containsKey(KeyEvent.VK_A)) {
			tmp.x--;
		}
		if (keysDown.containsKey(KeyEvent.VK_UP)
				|| keysDown.containsKey(KeyEvent.VK_W)) {
			tmp.y--;
		}
		if (keysDown.containsKey(KeyEvent.VK_DOWN)
				|| keysDown.containsKey(KeyEvent.VK_S)) {
			tmp.y++;
		}
		
		if (!(tmp.x == 0 && tmp.y == 0))
			entity.transform.translate(tmp);
	}
	
	//##################################################################
	// KEY LISTENER
	//##################################################################
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//System.out.println("PRESSS " + e.getKeyCode());
		keysDown.put(e.getKeyCode(), e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//System.out.println("RELEASE " + e.getKeyCode());
		keysDown.remove(e.getKeyCode());
	}

	//##################################################################

}

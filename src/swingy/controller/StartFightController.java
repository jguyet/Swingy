package swingy.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import swingy.App;
import swingy.entity.Entity;
import swingy.enums.EModule;
import swingy.enums.GameConsoleLabel;
import swingy.views.components.FightStartMenuComponent;
import swingy.views.events.ResponseListener;
import swingy.views.factory.ViewFactory;

public class StartFightController implements ISwingyController, MouseListener, KeyListener, ResponseListener{

	private FightStartMenuComponent view;
	private boolean wait = true;
	private boolean goFight = false;
	
	@SuppressWarnings("unused")
	private Entity	p1;
	private Entity	p2;
	
	public StartFightController(Entity p1, Entity p2) {
		this.p1 = p1;
		this.p2 = p2;
		this.view = ViewFactory.newFightStartMenuComponent(App.modelInterface.getinstance(), App.gameview, p1, p2);
		
		if (App.modelInterface.getinstance() == EModule.GUI) {
			
			App.window.addMouseListener(this);
			App.window.addKeyListener(this);
		}
	}
	
	@Override
	public void control() {
		
		if (App.modelInterface.getinstance() == EModule.GUI) {
			while (wait) {
				try { Thread.sleep(100); } catch(Exception e) {}
			}
			App.window.removeMouseListener(this);
			App.window.removeKeyListener(this);
			view.removeModel();
		}
		else {
			Response rep = new Response();
			
			App.gameview.println("Un ennemi " + this.p2.getName() + " apparait !");
			App.gameview.println("(1) attack");
			App.gameview.println("(2) escape");
			
			while (!rep.isValide()) {
				App.gameview.print(GameConsoleLabel.WAIT_NUMBER.lbl());
				App.gameview.waitResponse(rep);
			}
			
			if (rep.id == 1) {
				this.goFight = true;
			}
		}
	}
	
	private class Response implements ResponseListener {

		public int id = -1;
		public String rep;
		
		@Override
		public void onResponse(String response) {
			rep = response;
		}
		
		public boolean isValide() {
			try {
				id = Integer.parseInt(rep);
			} catch (NumberFormatException e) {
				return false;
			}
			if (id > 2 || id < 1)
				return false;
			return true;
		}
	}
	
	public boolean hasSelectedGoFight() {
		return this.goFight;
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_ENTER:
				goFight = true;
				wait = false;
				break ;
			case KeyEvent.VK_DELETE:
				goFight = false;
				wait = false;
				break ;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void onResponse(String response) {
		// TODO Auto-generated method stub
		
	}

}

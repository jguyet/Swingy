package swingy.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import swingy.App;
import swingy.entity.Entity;
import swingy.enums.EModule;
import swingy.views.components.EndFightMenuComponent;
import swingy.views.factory.ViewFactory;

public class EndFightController implements ISwingyController, KeyListener {

	
	private EndFightMenuComponent	view;
	private boolean					wait = true;
	private boolean					hasWin;
	
	public EndFightController(Entity winner, Entity looser, boolean hasWin) {
		this.hasWin = hasWin;
		this.view = ViewFactory.newEndFightMenuComponent(App.modelInterface.getinstance(), App.gameview, winner, looser, hasWin);
		
		if (App.modelInterface.getinstance() == EModule.GUI) {
			App.window.addKeyListener(this);
		}
	}
	
	@Override
	public void control() {
		
		if (App.modelInterface.getinstance() == EModule.GUI) {
			while (wait) {
				try{ Thread.sleep(100); } catch (Exception e) {}
			}
			this.view.removeModel();
			App.window.removeKeyListener(this);
		}
		
		if (hasWin) {
			App.gameview.println("You Win !");
		} else {
			App.gameview.println("You loose !@@");
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyPressed(KeyEvent e) {
		//on key pressed remove this
		this.wait = false;
	}
	@Override
	public void keyReleased(KeyEvent e) {}

}

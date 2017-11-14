package swingy.controller;

import swingy.App;
import swingy.views.components.GameInterfaceComponent;
import swingy.views.factory.ViewFactory;

public class GameController implements ISwingyController {

	private GameInterfaceComponent model;
	
	public GameController() {
		
		this.model = ViewFactory.newGameGuiInterface(App.modelInterface.getinstance(), App.gameview);
		if (this.model != null) {
			this.model.paintModel();
		}
	}
	
	@Override
	public void control() {
		if (this.model != null) {
			if (this.model.getCharacter() != App.Character)
				this.model.setCharacter(App.Character);
			this.model.repaint();
		}
	}

}

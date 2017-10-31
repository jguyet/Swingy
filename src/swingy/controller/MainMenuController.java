package swingy.controller;

import swingy.App;
import swingy.enums.EModule;
import swingy.views.events.ResponseListener;

public class MainMenuController implements ISwingyController {
	
	public MainMenuController() {
		//App.mainmenuview.addKeyListener(this);
	}
	
	@Override
	public void control() {
		
		if (App.modelInterface.getinstance() == EModule.CONSOLE) {
		
			App.mainmenuview.println("Create your hero :");
			
			NameGetter namegetter = new NameGetter();
			
			while (!namegetter.isValide()) {
				App.mainmenuview.print("Hero name : ");
				App.mainmenuview.waitResponse(namegetter);
			}
		} else {
			
		}
	}
	
	private class NameGetter implements ResponseListener {

		public String name;
		
		@Override
		public void onResponse(String response) {
			name = response;
		}
		
		public boolean isValide() {
			if (name == null)
				return false;
			if (name.length() > 10)
				return false;
			if (name.length() < 2)
				return false;
			return true;
		}
	}
}

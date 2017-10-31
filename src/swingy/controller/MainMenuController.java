package swingy.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import swingy.App;
import swingy.entity.CrazyHero;
import swingy.entity.Entity;
import swingy.entity.Magician;
import swingy.entity.Princess;
import swingy.entity.Warrior;
import swingy.enums.EModule;
import swingy.enums.EStatElement;
import swingy.math.Utils;
import swingy.math.Vector2;
import swingy.views.SwingyGUIMainMenuView;
import swingy.views.components.HerosCreationComponent;
import swingy.views.components.HerosFormComponent;
import swingy.views.components.HerosSelectionComponent;
import swingy.views.components.HerosTableComponent;
import swingy.views.components.SwingyTitleComponent;
import swingy.views.events.ResponseListener;

public class MainMenuController implements ISwingyController, ActionListener, ListSelectionListener {
	
	private ArrayList<Entity>		characters = new ArrayList<Entity>();
	private Entity					selected_Character = null;
	
	private HerosFormComponent		heroFormPanel;
	private HerosTableComponent		heroTable;
	private SwingyTitleComponent	swingyTitlePanel;
	private HerosCreationComponent	miniMenuCreateHero;
	private HerosSelectionComponent	miniMenuSelectionHero;
	
	private boolean 				createConsoleHero = false;
	private boolean					selectConsoleHero = false;
	
	public MainMenuController() {
		
		if (App.modelInterface.getinstance() == EModule.GUI) {
			
			heroFormPanel = new HerosFormComponent((SwingyGUIMainMenuView)App.mainmenuview);
			heroTable = new HerosTableComponent((SwingyGUIMainMenuView)App.mainmenuview);
			swingyTitlePanel = new SwingyTitleComponent((SwingyGUIMainMenuView)App.mainmenuview);
			miniMenuCreateHero = new HerosCreationComponent((SwingyGUIMainMenuView)App.mainmenuview);
			miniMenuSelectionHero = new HerosSelectionComponent((SwingyGUIMainMenuView)App.mainmenuview);
			
			miniMenuCreateHero.getButton().addActionListener(this);
			miniMenuSelectionHero.getButton().addActionListener(this);
			heroFormPanel.getButton().addActionListener(this);
			
			this.heroTable = new HerosTableComponent((SwingyGUIMainMenuView)App.mainmenuview);
			
			this.swingyTitlePanel.paintModel();
			this.heroTable.paintModel();
			this.miniMenuCreateHero.paintModel();
			this.miniMenuSelectionHero.paintModel();
		}
	}
	
	@Override
	public void control() {
		
		if (App.Characters.size() != this.characters.size()) {
			this.characters.clear();			
			this.characters.addAll(App.Characters);
			
			if (App.modelInterface.getinstance() == EModule.GUI) {
				this.heroTable.remove();
				this.heroTable = new HerosTableComponent((SwingyGUIMainMenuView)App.mainmenuview);
				
				for (Entity e : this.characters) {
					this.heroTable.addnewHero(e.getName(), e.classe(), e.getLevel(), e.getExp(),
							e.stats.getStat(EStatElement.Attack),
							e.stats.getStat(EStatElement.Defense), "...", "...");
				}
				this.heroTable.paintModel();
				this.heroTable.addListSelectionListener(this);
			}
		}
		
		if (App.modelInterface.getinstance() == EModule.CONSOLE) {
		
			if (createConsoleHero) {
				createConsoleNewHero();
			} else if (selectConsoleHero) {
				selectConsoleNewHero();
			} else {
				consoleMenu();
			}
		
		}
	}
	
	private void consoleMenu() {
		App.mainmenuview.println("=======MAIN=MENU=SWINGY=======");
		App.mainmenuview.println("Swingy console menu :");
		App.mainmenuview.println("(1) create new Hero");
		App.mainmenuview.println("(2) select exist Hero");
		
		MenuGetter menugetter = new MenuGetter();
		
		while (!menugetter.isValide()) {
			App.mainmenuview.print("Select number : ");
			App.mainmenuview.waitResponse(menugetter);
		}
		
		if (menugetter.id == 1) {
			this.createConsoleHero = true;
		} else if (menugetter.id == 2) {
			this.selectConsoleHero = true;
		}
	}
	
	private class MenuGetter implements ResponseListener {

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
	
	private void createConsoleNewHero() {
		App.mainmenuview.println("========HERO=CREATION=========");
		App.mainmenuview.println("Create your hero :");
		
		NameGetter namegetter = new NameGetter();
		
		while (!namegetter.isValide()) {
			App.mainmenuview.print("Hero name : ");
			App.mainmenuview.waitResponse(namegetter);
		}
		
		App.mainmenuview.println("Select class of your hero :");
		
		App.mainmenuview.println("(1) Magician");
		App.mainmenuview.println("(2) Princess");
		App.mainmenuview.println("(3) Warrior");
		App.mainmenuview.println("(4) Crazy");
		
		ClassGetter classgetter = new ClassGetter();
		
		while (!classgetter.isValide()) {
			App.mainmenuview.print("Select number : ");
			App.mainmenuview.waitResponse(classgetter);
		}
		
		switch (classgetter.id) {
			case 1:
				App.Characters.add(new Magician(namegetter.name, new Vector2(0,0)));
				break ;
			case 2:
				App.Characters.add(new Princess(namegetter.name, new Vector2(0,0)));
				break ;
			case 3:
				App.Characters.add(new Warrior(namegetter.name, new Vector2(0,0)));
				break ;
			case 4:
				App.Characters.add(new CrazyHero(namegetter.name, new Vector2(0,0)));
				break ;
		}
		Utils.writeHeros(App.Characters);
		this.createConsoleHero = false;
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
	
	private class ClassGetter implements ResponseListener {

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
			if (id > 4 || id < 1)
				return false;
			return true;
		}
	}
	
	private void selectConsoleNewHero() {
		App.mainmenuview.println("========HERO=SELECTION========");
		App.mainmenuview.println("Select your hero :");
		int id = 0;
		
		for (Entity e : App.Characters) {
			App.mainmenuview.println("(" + id + ") " + e.getName() + " " + e.classe());
			id++;
		}
		
		CharacterGetter charactergetter = new CharacterGetter(id);
		
		App.mainmenuview.print("Select number : ");
		App.mainmenuview.waitResponse(charactergetter);
		
		if (charactergetter.isValide()) {
			App.Character = App.Characters.get(charactergetter.id);
			App.loopController.stop();
		} else {
			App.mainmenuview.println("Error return to main menu.");
		}
		
		this.selectConsoleHero = false;
	}
	
	private class CharacterGetter implements ResponseListener {

		public int id = -1;
		public String rep;
		private int max;
		
		public CharacterGetter(int max) {
			this.max = max;
		}
		
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
			if (id > max || id < 0)
				return false;
			return true;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!(e.getSource() instanceof JButton))
			return ;
		
		JButton button = (JButton)e.getSource();
		if (button.getText().equalsIgnoreCase(this.miniMenuSelectionHero.getButton().getText())) {
			if (this.selected_Character != null) {
				System.out.println(this.selected_Character.getName());
				App.Character = this.selected_Character;
				App.loopController.stop();
				App.mainmenuview.destroy();
			}
		} else if (button.getText().equalsIgnoreCase(this.miniMenuCreateHero.getButton().getText())) {
			if (!this.miniMenuCreateHero.isSelected()) {
				openHeroPanelCreation();
			} else {
				closeHeroPanelCreation();
			}
		} else if (button.getText().equalsIgnoreCase(this.heroFormPanel.getButton().getText()) && this.heroFormPanel.getName().length() > 0) {
			switch (((String)this.heroFormPanel.getComboBox().getSelectedItem())) {
				case "Magician":
					App.Characters.add(new Magician(this.heroFormPanel.getName(), new Vector2(0,0)));
					break ;
				case "Princess":
					App.Characters.add(new Princess(this.heroFormPanel.getName(), new Vector2(0,0)));
					break ;
				case "Warrior":
					App.Characters.add(new Warrior(this.heroFormPanel.getName(), new Vector2(0,0)));
					break ;
				case "Crazy":
					App.Characters.add(new CrazyHero(this.heroFormPanel.getName(), new Vector2(0,0)));
					break ;
			}
			Utils.writeHeros(App.Characters);
			closeHeroPanelCreation();
		}
	}
	
	private void openHeroPanelCreation() {
		this.miniMenuCreateHero.setSelected(true);
		this.miniMenuSelectionHero.remove();
		this.heroFormPanel.paintModel();
	}
	
	private void closeHeroPanelCreation() {
		this.miniMenuCreateHero.setSelected(false);
		this.miniMenuSelectionHero.paintModel();
		this.heroFormPanel.remove();
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		this.miniMenuSelectionHero.setButtonEnabled(true);
		
    	int sel = this.heroTable.getTable().getSelectedRow();
        
        selected_Character = characters.get(sel);
	}
}

package swingy.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import swingy.App;
import swingy.entity.Entity;
import swingy.enums.EModule;
import swingy.enums.EStatElement;
import swingy.views.SwingyGUIMainMenuView;
import swingy.views.SwingyGUIMainMenuView.MainMenuSelectionHero;
import swingy.views.components.MainMenuHerosTable;
import swingy.views.events.ResponseListener;

public class MainMenuController implements ISwingyController, MouseListener {
	
	private SwingyGUIMainMenuView mainMenu;
	private MainMenuHerosTable		heroTable;
	private ArrayList<Entity>		characters = new ArrayList<Entity>();
	private Entity					selected_Character = null;
	
	public MainMenuController(SwingyGUIMainMenuView menu) {
		this.mainMenu = menu;
		
		mainMenu.getSelectionHeroPanel().addEventButtonMouseListener(this);
		mainMenu.getCreationHeroPanel().addEventButtonMouseListener(this);
		
		
		this.heroTable = new MainMenuHerosTable();
		
		mainMenu.removeHeroTable();
		mainMenu.addHerosTable(heroTable);
		mainMenu.repaintHeroTable();
		loadTableSelectEvent();
	}
	
	@Override
	public void control() {
		
		if (App.Characters.size() != this.characters.size()) {
			this.characters.clear();			
			this.characters.addAll(App.Characters);
			
			this.heroTable = new MainMenuHerosTable();
			
			for (Entity e : this.characters) {
				this.heroTable.addnewHero(e.getName(), e.getClass().getName(), e.getLevel(), e.getExp(),
						e.stats.getStat(EStatElement.Attack),
						e.stats.getStat(EStatElement.Defense), "...", "...");
			}
			
			mainMenu.removeHeroTable();
			mainMenu.addHerosTable(heroTable);
			mainMenu.repaintHeroTable();
			loadTableSelectEvent();
		}
		
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
	
	private void loadTableSelectEvent() {
		final MainMenuSelectionHero tmp = mainMenu.getSelectionHeroPanel();
		final MainMenuHerosTable tablee = this.heroTable;
		this.heroTable.addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent e) {
		    	tmp.setButtonEnabled(true);
		    	int sel = tablee.getTable().getSelectedRow();
		        
		        //System.out.println(sel);
		        //Object[] o = tablee.getRow(sel);
		        
		        selected_Character = characters.get(sel);
		        //System.out.println(o[0]);
		    }
		});
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

	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {
		if (!(e.getComponent() instanceof JButton))
			return ;
		JButton button = (JButton)e.getComponent();
		if (button.getText().equalsIgnoreCase(this.mainMenu.getSelectionHeroPanel().getButton().getText())) {
			System.out.println("SelectionHeroPanel");
			if (this.selected_Character != null) {
				System.out.println(this.selected_Character.getName());
				App.Character = this.selected_Character;
				App.loopController.stop();
				App.mainmenuview.destroy();
			}
		} else if (button.getText().equalsIgnoreCase(this.mainMenu.getCreationHeroPanel().getButton().getText())) {
			System.out.println("CreationHeroPanel");
			this.mainMenu.getCreationHeroPanel().setSelected(!this.mainMenu.getCreationHeroPanel().isSelected());
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
}

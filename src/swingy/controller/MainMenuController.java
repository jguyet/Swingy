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
	
	private HerosFormComponent		heroFormPanel = new HerosFormComponent((SwingyGUIMainMenuView)App.mainmenuview);
	private HerosTableComponent		heroTable = new HerosTableComponent((SwingyGUIMainMenuView)App.mainmenuview);
	private SwingyTitleComponent			swingyTitlePanel = new SwingyTitleComponent((SwingyGUIMainMenuView)App.mainmenuview);
	private HerosCreationComponent	miniMenuCreateHero = new HerosCreationComponent((SwingyGUIMainMenuView)App.mainmenuview);
	private HerosSelectionComponent	miniMenuSelectionHero = new HerosSelectionComponent((SwingyGUIMainMenuView)App.mainmenuview);
	
	public MainMenuController() {
		
		miniMenuCreateHero.getButton().addActionListener(this);
		miniMenuSelectionHero.getButton().addActionListener(this);
		heroFormPanel.getButton().addActionListener(this);
		
		
		this.heroTable = new HerosTableComponent((SwingyGUIMainMenuView)App.mainmenuview);
		
		this.swingyTitlePanel.paintModel();
		this.heroTable.paintModel();
		this.miniMenuCreateHero.paintModel();
		this.miniMenuSelectionHero.paintModel();
	}
	
	@Override
	public void control() {
		
		if (App.Characters.size() != this.characters.size()) {
			this.characters.clear();			
			this.characters.addAll(App.Characters);
			this.heroTable.remove();
			this.heroTable = new HerosTableComponent((SwingyGUIMainMenuView)App.mainmenuview);
			
			for (Entity e : this.characters) {
				this.heroTable.addnewHero(e.getName(), e.getClass().getName(), e.getLevel(), e.getExp(),
						e.stats.getStat(EStatElement.Attack),
						e.stats.getStat(EStatElement.Defense), "...", "...");
			}
			this.heroTable.paintModel();
			this.heroTable.addListSelectionListener(this);
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
		/*final MainMenuSelectionHero tmp = mainMenu.getSelectionHeroPanel();
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
		});*/
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
	public void actionPerformed(ActionEvent e) {
		if (!(e.getSource() instanceof JButton))
			return ;
		
		JButton button = (JButton)e.getSource();
		if (button.getText().equalsIgnoreCase(this.miniMenuSelectionHero.getButton().getText())) {
			System.out.println("SelectionHeroPanel");
			if (this.selected_Character != null) {
				System.out.println(this.selected_Character.getName());
				App.Character = this.selected_Character;
				App.loopController.stop();
				App.mainmenuview.destroy();
			}
		} else if (button.getText().equalsIgnoreCase(this.miniMenuCreateHero.getButton().getText())) {
			System.out.println("CreationHeroPanel");
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

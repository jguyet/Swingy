package swingy.entity.factory;

import swingy.App;
import swingy.entity.Entity;
import swingy.entity.Magician;
import swingy.entity.Princess;
import swingy.entity.Warrior;
import swingy.enums.EPlayableCharacter;
import swingy.utils.Utils;
import swingy.utils.Vector2;

public class EntityFactory {

	public static void saveCharacters() {
		Utils.writeHeros(App.Characters);
	}
	
	public static void loadCharacters() {
		System.out.println("\033[33m======== Chargement heroes.txt ========\033[00m");
		App.Characters = Utils.loadHeros();
		System.out.println("\033[33m=======================================\033[00m");
	}
	
	public static Entity createPlayableCharacterByClassName(String className, String name) {
		EPlayableCharacter ep = EPlayableCharacter.getPlayableCharacter(className);
		
		if (ep == null)
			return null;
		
		switch (ep)
		{
			case CRAZY:
				return new Princess(name, new Vector2());
			case PRINCESS:
				return new Princess(name, new Vector2());
			case MAGICIAN:
				return new Magician(name, new Vector2());
			case WARRIOR:
				return new Warrior(name, new Vector2());
		}
		return null;
	}
}

package swingy.enums;

public enum GameConsoleLabel {

	/**
	 * CharacterController labels
	 */
	CHOISE_YOUR_DIRECTION ("Choise direction (North/East/South/West) : "),
	INDICE_CHARACTER_POSITION ("You has moved to position x: "),
	CANCEL_CHARACTER_MOVEMENT ("Cancel movement caused by an obstacle"),
	
	/**
	 * MainMenuController labels
	 */
	// main menu 
	MAIN_MENU ("=======MAIN=MENU=SWINGY======="),
	MAIN_MENU_QUESTION ("Swingy console menu :"),
	MAIN_MENU_FIRST_POSSIBILITY ("(1) create new Hero"),
	MAIN_MENU_SECOND_POSSIBILITY ("(2) select exist Hero"),
	
	
	WAIT_NUMBER ("Select number : "),
	
	// hero selection menu
	MAIN_MENU_HERO_SELECTION ("========HERO=SELECTION========"),
	MAIN_MENU_HERO_SELECTION_LABEL ("Select your hero :"),
	ERROR_MAIN_MENU_HERO_SELECTION_NUMBER ("Error of character id, return to main menu."),
	
	// hero creation menu
	MAIN_MENU_HERO_CREATION ("========HERO=CREATION========="),
	MAIN_MENU_HERO_CREATION_LABEL ("Create your hero :"),
	MAIN_MENU_HERO_CREATION_WAIT_NAME ("Hero name : "),
	MAIN_MENU_HERO_CREATION_WAIT_CLASS_SELECTION ("Select class of your hero :"),
	;
	
	private String lbl;
	
	public String lbl() {
		return (lbl);
	}
	
	GameConsoleLabel(String element) {
		this.lbl = element;
	}
}

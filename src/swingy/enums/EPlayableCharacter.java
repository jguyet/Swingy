package swingy.enums;

public enum EPlayableCharacter {

	CRAZY ("Crazy"),
	MAGICIAN ("Magician"),
	PRINCESS ("Princess"),
	WARRIOR ("Warrior");
	
	private String character;
	
	public String getName() {
		return (character);
	}
	
	EPlayableCharacter(String artefact) {
		this.character = artefact;
	}
	
	public static EPlayableCharacter getPlayableCharacter(String name) {
		for (EPlayableCharacter m : EPlayableCharacter.values()) {
			if (name.equalsIgnoreCase(m.getName()))
				return (m);
		}
		return null;
	}
}

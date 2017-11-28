package swingy.enums;

public enum EArtefact {

	ARMOR ("Armor"),
	HELM ("Helm"),
	WEAPON ("Weapon");
	
	private String artefact;
	
	public String getString() {
		return (artefact);
	}
	
	EArtefact(String artefact) {
		this.artefact = artefact;
	}
	
	public static EArtefact getArtefact(String name) {
		for (EArtefact m : EArtefact.values()) {
			if (name.equalsIgnoreCase(m.getString()))
				return (m);
		}
		return null;
	}
}

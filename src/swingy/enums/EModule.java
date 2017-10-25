package swingy.enums;

public enum EModule {
	CONSOLE ("console"),
	GUI ("gui");
	
	private String mode;
	
	public String getMode() {
		return (mode);
	}
	
	EModule(String mode) {
		this.mode = mode;
	}
	
	public static EModule getModule(String name) {
		for (EModule m : EModule.values()) {
			if (name.equalsIgnoreCase(m.getMode()))
				return (m);
		}
		return null;
	}
}

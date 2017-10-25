package swingy.enums;

public enum EStatElement {

	Attack (10),
	Defense (11),
	HitPoint (12),
	Increase_Attack (13),
	Increase_Defense (14),
	Increase_HitPoint (15);
	
	private int element;
	
	public int getElement() {
		return (element);
	}
	
	EStatElement(int element) {
		this.element = element;
	}
}

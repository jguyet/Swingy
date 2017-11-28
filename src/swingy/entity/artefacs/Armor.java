package swingy.entity.artefacs;

import swingy.entity.statistics.Statistics;
import swingy.enums.EArtefact;
import swingy.enums.EStatElement;
import swingy.ressources.Sprite;

public class Armor extends Artefact{

	private static Sprite	  sprite = Sprite.ARMOR;
	
	public Armor(String name, int level, Statistics stats, boolean equiped) {
		super(name, stats, level, equiped);
	}
	
	public Armor(String name, int level, String stats, boolean equiped) {
		super(name, stats, level, equiped);
	}

	@Override
	public Sprite getSprite() {
		return Armor.sprite;
	}

	@Override
	public EArtefact getType() {
		return EArtefact.ARMOR;
	}
	
}

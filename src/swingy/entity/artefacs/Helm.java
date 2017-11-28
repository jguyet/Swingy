package swingy.entity.artefacs;

import swingy.entity.statistics.Statistics;
import swingy.enums.EArtefact;
import swingy.enums.EStatElement;
import swingy.ressources.Sprite;

public class Helm extends Artefact {

	private static Sprite	  sprite = Sprite.HELM;
	
	public Helm(String name, int level, Statistics stats, boolean equiped) {
		super(name, stats, level, equiped);
	}
	
	public Helm(String name, int level, String stats, boolean equiped) {
		super(name, stats, level, equiped);
	}
	
	@Override
	public Sprite getSprite() {
		return Helm.sprite;
	}
	
	@Override
	public EArtefact getType() {
		return EArtefact.HELM;
	}
}

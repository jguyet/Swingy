package swingy.entity.artefacs.factory;

import swingy.entity.artefacs.Armor;
import swingy.entity.artefacs.Artefact;
import swingy.entity.artefacs.Helm;
import swingy.entity.artefacs.Weapon;
import swingy.enums.EArtefact;

public class ArtefactFactory {

	public static Artefact createArtefactByPattern(String pattern) {
		
		try
		{
			String[] split = pattern.split(",");
			String className = split[0];
			String name = split[1];
			int level = Integer.parseInt(split[2]);
			String stats = split[3];
			boolean equiped = split[4].equalsIgnoreCase("true") ? true : false;
			int pictureId = Integer.parseInt(split[5]);
			
			EArtefact ea = EArtefact.getArtefact(className);
			
			if (ea == null)
				return null;
			
			switch (ea)
			{
				case HELM:
					return new Helm(name, level, stats, equiped, pictureId);
				case ARMOR:
					return new Armor(name, level, stats, equiped, pictureId);
				case WEAPON:
					return new Weapon(name, level, stats, equiped, pictureId);
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}
}

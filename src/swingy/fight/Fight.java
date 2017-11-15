package swingy.fight;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

import swingy.App;
import swingy.entity.Entity;
import swingy.entity.artefacs.Artefact;
import swingy.enums.EStatElement;
import swingy.utils.Utils;

public class Fight {

	private Entity fighter1;
	private Entity fighter2;
	
	private Entity winner;
	private Entity looser;
	
	
	public Fight(Entity fighter1, Entity fighter2) {
		this.fighter1 = fighter1;
		this.fighter2 = fighter2;
		
		this.fighter1.fightHitPoint = fighter1.getStat(EStatElement.HitPoint);
		this.fighter2.fightHitPoint = fighter2.getStat(EStatElement.HitPoint);
		prepareFight();
	}
	
	private void prepareFight() {
		App.gameview.println("\033[31m===========Fight===========\033[00m");
		App.gameview.println("\033[00m" + this.fighter1.getName() + " Level: " + this.fighter1.getLevel() + "\033[00m");
		App.gameview.println("\033[00mVS\033[00m");
		App.gameview.println("\033[00m" + this.fighter2.getName() + " Level: " + this.fighter2.getLevel() + "\033[00m");
		App.gameview.println("\033[00m===========================\033[00m");
	}
	
	public Entity startFight() {
		
		int turn = 1;
		
		this.winner = fighter1;
		this.looser = fighter2;
		
		while (this.fighter1.fightHitPoint > 0 && this.fighter2.fightHitPoint > 0) {
			App.gameview.println("\033[00mTurn number " + turn + " =================\033[00m");
			Entity starter = fighter2;
			Entity ender = fighter1;
			if (fighter1.getLevel() > fighter2.getLevel()) {
				starter = fighter1;
				ender = fighter2;
			}
			if (attack(starter, ender)) {
				this.winner = starter;
				this.looser = ender;
				break ;
			}
			if (attack(ender, starter)) {
				this.winner = ender;
				this.looser = starter;
				break ;
			}
			
			turn++;
		}
		if (this.winner == this.fighter1) {
			App.gameview.println("\033[00mYou has win fight\033[00m");
		} else {
			App.gameview.println("\033[00mYou has loose fight\033[00m");
		}
		App.gameview.println("\033[00m===========================\033[00m");
		this.winner.inventory.addAll(endfight(this.winner, this.looser));
		return (winner);
	}
	
	public boolean attack(Entity e, Entity target) {
		
		int mindamage = e.getStat(EStatElement.Attack) / 2;
		int maxdamage = e.getStat(EStatElement.Attack);
		
		int damage = Utils.getRandomValue(mindamage, maxdamage);
		
		damage -= target.getStat(EStatElement.Defense);
		
		if (damage < 0)
			damage = 0;
		target.fightHitPoint -= damage;
		if (target.fightHitPoint < 0)
			target.fightHitPoint = 0;
		App.gameview.println("\033[00m" + e.getName() + " Attack \033[31m" + damage + "\033[00m damage to " + target.getName() + "\033[00m");
		App.gameview.println("\033[00m" + target.getName() + " has " + target.fightHitPoint + " hitpoint \033[00m");
		if (target.fightHitPoint <= 0) {
			App.gameview.println("\033[31m" + target.getName() + " is dead \033[00m");
			return true;
		}
		return false;
	}
	
	public ArrayList<Artefact> endfight(Entity winner, Entity looser) {
		ArrayList<Class<?>> possibleDrops = looser.getDrops();
		ArrayList<Artefact> drops = new ArrayList<Artefact>();
		
		for (Class<?> drop : possibleDrops) {
			
			int chancedrop = Utils.getRandomValue(1, 3);
			
			if (chancedrop == 1) {
				try {
				Constructor<?> constructor = drop.getConstructors()[0];
				
				constructor.setAccessible(true);
				Artefact a = (Artefact)constructor.newInstance();
				drops.add(a);
				
				System.out.println("\033[31m" + a.getClass().getName() + " dropped \033[00m");
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return (drops);
	}
}

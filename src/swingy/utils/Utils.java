package swingy.utils;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import swingy.entity.Entity;
import swingy.entity.artefacs.Armor;
import swingy.entity.artefacs.Artefact;
import swingy.entity.artefacs.Helm;
import swingy.entity.artefacs.Weapon;
import swingy.entity.artefacs.factory.ArtefactFactory;
import swingy.entity.factory.EntityFactory;
import swingy.validation.ValidatorEntity;

public class Utils {

	public static int getRandomValue(int i1, int i2)
	{
		if (i2 < i1)
			return 0;
		Random rand = new Random();
		return (rand.nextInt((i2 - i1) + 1)) + i1;
	}
	
	public static ArrayList<String> readHeros() {
		ArrayList<String> characters = new ArrayList<String>();
		try {
			FileReader		fr = new FileReader("heroes.txt");
			BufferedReader	br = new BufferedReader(fr);
			String line = "";
			while ((line = br.readLine()) != null) {
				characters.add(line);
			}
			br.close();
			fr.close();
		} catch(FileNotFoundException e) {
			return (characters);
		} catch (IOException e) {
			return (characters);
		}
		return (characters);
	}
	
	public class CustomComparator implements Comparator<Entity> {
		@Override
		public int compare(Entity object1, Entity object2) {
	        return object1.getLevel() > object2.getLevel() ? 1 : 0;
	    }
	}
	
	public static ArrayList<Entity> loadHeros() {
		ArrayList<String> list = readHeros();
		ArrayList<Entity> entList = new ArrayList<Entity>();
		
		for (String c : list) {
			
			String[] pattern = c.split(" ");
			
			if (pattern.length < 4) {
				continue ;
			}
			
			Entity e = null;
			int level = -1;
			long exp = -1;
			
			try { level = Integer.parseInt(pattern[2]); } catch (NumberFormatException a) {
				System.out.println("\033[31m{Syntax level error on character line \"" + pattern[0] + "\"}\033[00m");
			}
			try { exp = Long.parseLong(pattern[3]); } catch (NumberFormatException a) {
				System.out.println("\033[31m{Syntax exp error on character line \"" + pattern[0] + "\"}\033[00m");
			}
			
			e = EntityFactory.createPlayableCharacterByClassName(pattern[1], pattern[0]);

			if (e != null) {
				//LEVEL
				e.setLevel(level);
				e.setExp(exp);
				
				//ARTEFACTS
				for (int i = 4; i < pattern.length; i++) {
					Artefact a = ArtefactFactory.createArtefactByPattern(pattern[i]);
					
					if (a == null) {
						System.out.println("\033[31m{Syntax artefact error on character line \"" + e.getName() + "\"}\033[00m");
						continue ;
					}
					e.inventory.add(a);
					if (a.equiped) {
						e.equipe(a);
					}
				}
				entList.add(e);
			}
		}
		entList = ValidatorEntity.valideEntityList(entList);	
		//Sort list level > level
		Collections.sort(entList, new Comparator<Entity>() {
			@Override
			public int compare(Entity object1, Entity object2) {
		        return object2.getLevel() - object1.getLevel();
		    }
		});
		
		return (entList);
	}
	
	public static void writeHeros(ArrayList<Entity> characters) {
		try {
			FileWriter fw = new FileWriter("heroes.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			
			for (Entity e : characters) {
				bw.write(e.toString());
			}
			bw.flush();
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Color HexToRGB(String colorStr) {
		return new Color(
	            Integer.valueOf( colorStr.substring( 1, 3 ), 16 ),
	            Integer.valueOf( colorStr.substring( 3, 5 ), 16 ),
	            Integer.valueOf( colorStr.substring( 5, 7 ), 16 ) );
	}
}

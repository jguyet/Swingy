package swingy.utils;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import swingy.entity.CrazyHero;
import swingy.entity.Entity;
import swingy.entity.Magician;
import swingy.entity.Princess;
import swingy.entity.Warrior;
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
	
	public static ArrayList<Entity> loadHeros() {
		ArrayList<String> list = readHeros();
		ArrayList<Entity> entList = new ArrayList<Entity>();
		
		for (String c : list) {
			
			String[] pattern = c.split(" ");
			
			if (pattern.length != 4)
				continue ;
			
			Entity e = null;
			
			int level = -1;
			long exp = -1;
			
			try { level = Integer.parseInt(pattern[2]); } catch (NumberFormatException a) { a.printStackTrace(); }
			try { exp = Long.parseLong(pattern[3]); } catch (NumberFormatException a) { a.printStackTrace(); }
			
			switch (pattern[1]) {
			case "Magician":
				e = new Magician(pattern[0], new Vector2());
				break ;
			case "Princess":
				e = new Princess(pattern[0], new Vector2());
				break ;
			case "Warrior":
				e = new Warrior(pattern[0], new Vector2());
				break ;
			case "CrazyHero":
				e = new CrazyHero(pattern[0], new Vector2());
				break ;
			}
			if (e != null) {
				e.setLevel(level);
				e.setExp(exp);
				
				if (ValidatorEntity.validateEntity(e))
					entList.add(e);
			}
		}
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

package swingy.ressources;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import swingy.utils.Vector2;

public class Sprite{
	
	public static Sprite TITLE;
	
	/**
	 * Map grounds
	 */
	public static Sprite grounds;
	
	/**
	 * Characters
	 */
	public static Sprite CRAZY;
	public static Sprite MAGE;
	public static Sprite PRINCESS;
	public static Sprite WARRIOR;
	
	/**
	 * Monsters
	 */
	public static Sprite RABIT;
	public static Sprite DRAG;
	public static Sprite SPIDER;
	public static Sprite SIRENE;
	
	/**
	 * Button
	 */
	public static Sprite ESCAPE;
	
	/**
	 * Artefacts
	 */
	public static Sprite ARMOR;
	public static Sprite HELM;
	public static Sprite WEAPON;
	
	public static Sprite ITEMS;
	
	/**
	 * Static method for load all res
	 */
	public static void LOAD() {
		TITLE = new Sprite("res/sprites/swingy.png", 1, 1, 0);
		
		grounds = new Sprite("res/sprites/ground/ground.png", 16, 12, 1);
		grounds.setHeight(32);
		grounds.setWidth(32);
		
		CRAZY = new Sprite("res/sprites/characters/1.png", 3, 4, 3);
		CRAZY.setHeight(38);
		CRAZY.setWidth(38);
		
		MAGE = new Sprite("res/sprites/mage.png", 4, 4, 0);
		MAGE.setHeight(38);
		MAGE.setWidth(38);
		
		PRINCESS = new Sprite("res/sprites/princess.png", 4, 4, 0);
		PRINCESS.setHeight(38);
		PRINCESS.setWidth(38);
		
		RABIT = new Sprite("res/sprites/wabit.png", 4, 4, 0);
		RABIT.setHeight(38);
		RABIT.setWidth(38);
		
		WARRIOR = new Sprite("res/sprites/warrior.png", 4, 4, 0);
		WARRIOR.setHeight(38);
		WARRIOR.setWidth(38);
		
		DRAG = new Sprite("res/sprites/drag2.png", 1, 1, 0);
		DRAG.setHeight(38);
		DRAG.setWidth(38);
		
		ESCAPE = new Sprite("res/sprites/escape.png", 5, 2, 0);
		ESCAPE.setHeight(200);
		ESCAPE.setWidth(200);
		
		ARMOR = new Sprite("res/sprites/artefacts/armor.png", 1, 1, 0);
		ARMOR.setHeight(50);
		ARMOR.setWidth(50);
		
		HELM = new Sprite("res/sprites/artefacts/helm.png", 1, 1, 0);
		HELM.setHeight(50);
		HELM.setWidth(50);
		
		WEAPON = new Sprite("res/sprites/artefacts/weapon.png", 1, 1, 0);
		WEAPON.setHeight(50);
		WEAPON.setWidth(50);
		
		SPIDER = new Sprite("res/sprites/spider.png", 7, 4, 0);
		SPIDER.setHeight(38);
		SPIDER.setWidth(38);
		
		SIRENE = new Sprite("res/sprites/sirene.png", 4, 4, 0);
		SIRENE.setHeight(38);
		SIRENE.setWidth(38);
		
		ITEMS = new Sprite("res/sprites/items.png", 14, 30, 0);
		ITEMS.setHeight(50);
		ITEMS.setWidth(50);
		
	}
	
	public Image	img;
	
	public int		posid;
	public int		columnW;
	public int		columnH;
	
	
	private Vector2	d2;
	private Vector2	s1;
	private Vector2	s2;
	private int imgwidth;
	private int imgheight;
	private int width;
	private int height;
	
	public Sprite(String url) {
		loadImage(url);
	}
	
	public Sprite(String url, int columnW, int columnH, int posid) {
		loadImage(url);
		this.columnW = columnW;
		this.columnH = columnH;
		this.posid = posid;
	}
	
	public Sprite copy() throws CloneNotSupportedException {
		return ((Sprite)this.clone());
	}
	
	private void loadImage(String url) {
		try {
		    File pathToFile = new File(url);
		    img = ImageIO.read(pathToFile);
		} catch (IOException ex) {
		    ex.printStackTrace();
		}
		imgwidth = img.getWidth(null);
		imgheight = img.getHeight(null);
		width = imgwidth;
		height = imgheight;
		d2 = new Vector2(imgwidth,imgheight);
		
		s1 = new Vector2(0,0);
		s2 = new Vector2(imgwidth,imgheight);
	}

	public Vector2 getD2() {
		return d2;
	}

	public Vector2 getS1() {
		return s1;
	}

	public Vector2 getS2() {
		return s2;
	}

	public int getImgWidth() {
		return imgwidth;
	}

	public int getImgHeight() {
		return imgheight;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public void update() {
		d2 = new Vector2(width,height);
		
		int pictureW = imgwidth / columnW;
		int pictureH = imgheight / columnH;
		
		int tx = (this.posid % ((int)columnW));
		int ty = ((this.posid / ((int)columnH)));
		
		int minx = (tx * pictureW);
		int miny = (ty * pictureH);
		
		int maxx = (tx * pictureW) + pictureW;
		int maxy = (ty * pictureH) + pictureH;
		
		s1 = new Vector2(minx,miny);
		s2 = new Vector2(maxx,maxy);
	}

	public void paint(Graphics g, int posx, int posy) {
		Graphics2D g2 = (Graphics2D) g;
		
		update();
		g2.drawImage(img, posx, posy, posx + d2.x, posy + d2.y, s1.x, s1.y, s2.x, s2.y, null);
	}
}

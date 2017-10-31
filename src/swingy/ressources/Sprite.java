package swingy.ressources;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import swingy.App;
import swingy.math.Vector2;

public class Sprite{
	
	public static Sprite TITLE;
	public static Sprite grounds;
	public static Sprite CRAZY;
	public static Sprite MAGE;
	public static Sprite PRINCESS;
	public static Sprite RABIT;
	public static Sprite WARRIOR;
	
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

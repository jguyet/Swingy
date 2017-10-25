package swingy.world;

import java.awt.Graphics;
import java.awt.Graphics2D;

import swingy.App;
import swingy.math.Utils;
import swingy.model.ISwingyModel;
import swingy.ressources.Sprite;

public class WorldMap implements ISwingyModel {

	private int width;
	private int height;
	
	private Case[][] ground;
	
	/**
	 * Constructor of MAP size X size
	 * @param size
	 */
	public WorldMap(int size) {
		this.width = size;
		this.height = size;
		intitalize();
	}
	
	private void intitalize() {
		this.ground = new Case[this.height][this.width];
		
		for (int y = 0; y < this.height; y++) {
			for (int x = 0; x < this.width; x++) {
				int rand = Utils.getRandomValue(1, 10);
				int groundId = 0;
				if (rand == 1)
					groundId = 130;
				else
					groundId = Utils.getRandomValue(0, 1);
				
				this.ground[y][x] = new Case(groundId, true);
			}
		}
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

	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		int scale = 30;
		int startx = (App.window.getWidth() / 2) - ((this.width * scale) / 2);
		int starty = (App.window.getHeight() / 2) - ((this.height * scale) / 2);
		Sprite grounds = Sprite.grounds;
		
		for (int y = 0; y < this.height; y++) {
			for (int x = 0; x < this.width; x++) {
				int px = startx + (x * scale);
				int py = starty + (y * scale);
				
				grounds.posid = this.ground[y][x].groundId;
				grounds.paint(g2, px, py);
			}
		}
	}
	
	public class Case {
		
		private int		groundId;
		private boolean	walkable;
		
		public Case(int groundId, boolean walkable) {
			this.groundId = groundId;
			this.walkable = walkable;
		}
		
		public int getGroundId() {
			return (this.groundId);
		}
		
		public boolean isWalkable() {
			return (this.walkable);
		}
	}
}

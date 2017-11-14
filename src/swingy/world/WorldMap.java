package swingy.world;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import swingy.App;
import swingy.entity.Entity;
import swingy.entity.Rabit;
import swingy.model.ISwingyModel;
import swingy.ressources.Sprite;
import swingy.utils.Utils;
import swingy.utils.Vector2;

public class WorldMap implements ISwingyModel {

	private int width;
	private int height;
	
	private Case[][] ground;
	private ArrayList<Case>		cases = new ArrayList<Case>();
	private ArrayList<Entity>	monsters = new ArrayList<Entity>();
	private Entity				character = null;
	
	private int					startWidth;
	private int					startHeight;
	
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
		this.startWidth = (App.window.getWidth() / 2) - ((this.width * App.SCALE) / 2);
		this.startHeight = (App.window.getHeight() / 2) - ((this.height * App.SCALE) / 2);
		this.ground = new Case[this.height][this.width];
		
		for (int y = 0; y < this.height; y++) {
			for (int x = 0; x < this.width; x++) {
				int rand = Utils.getRandomValue(1, 10);
				int groundId = 0;
				boolean walkable = true;
				if (rand == 1) {
					groundId = 130;
					walkable = false;
				} else
					groundId = Utils.getRandomValue(0, 1);
				
				this.ground[y][x] = new Case(x, y, groundId, walkable);
				cases.add(this.ground[y][x]);
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
	
	public void addMonster(Entity e) {
		this.monsters.add(e);
	}
	
	public void removeMonster(Entity e) {
		this.monsters.remove(e);
	}
	
	public ArrayList<Entity> getMonsters() {
		return (this.monsters);
	}
	
	public void addCharacter(Entity character) {
		this.character = character;
		
		this.character.transform.position.x = this.width / 2;
		this.character.transform.position.y = this.height / 2;
	}
	
	public void removeCharacter() {
		this.character = null;
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Sprite grounds = Sprite.grounds;
		
		for (int y = 0; y < this.height; y++) {
			for (int x = 0; x < this.width; x++) {
				int px = getStartWidth() + (x * App.SCALE);
				int py = getStartHeight() + (y * App.SCALE);
				
				px = px - (grounds.getWidth() / 2);
				py = py - (grounds.getHeight() / 2);
				
				grounds.posid = this.ground[y][x].groundId;
				grounds.paint(g2, px, py);
			}
		}
		
		for (Entity e : this.monsters) {
			e.paint(g2);
		}
		if (character != null)
			character.paint(g2);
	}
	
	public int getStartWidth() {
		return (this.startWidth);
	}
	
	public void setStartWidth(int w) {
		this.startWidth = w;
	}
	
	public int getStartHeight() {
		return (this.startHeight);
	}
	
	public void setStartHeight(int h) {
		this.startHeight = h;
	}
	
	public Case getRandomWalkableCase() {
		
		Case finalcase = null;
		
		while (finalcase == null) {
			int id = Utils.getRandomValue(0, this.cases.size() - 1);
			Case c = this.cases.get(id);
			
			if (c.isWalkable() == true) {
				finalcase = c;
			}
		}
		return (finalcase);
	}
	
	public Case getCaseByPosition(Vector2 position) {
		
		int x = position.x;
		int y = position.y;
		
		if (x >= this.width || x < 0 || y < 0 || y >= this.height) {
			return null;
		}
		return (this.ground[y][x]);
	}
	
	public void spawnMonsters() {
		int number = this.width;
		
		for (int i = 0; i < number; i++) {
			
			Rabit r = new Rabit("Rabit", new Vector2(0,0));
			
			r.initRandomPositionToMap(this);
			this.addMonster(r);
		}
	}
	
	public class Case {
		
		public int		x;
		public int		y;
		
		private int		groundId;
		private boolean	walkable;
		private Entity	entity = null;
		
		public Case(int x, int y, int groundId, boolean walkable) {
			this.x = x;
			this.y = y;
			this.groundId = groundId;
			this.walkable = walkable;
		}
		
		public int getGroundId() {
			return (this.groundId);
		}
		
		public boolean hasEntity() {
			return (entity != null);
		}
		
		public boolean isWalkable() {
			return (this.walkable);
		}
		
		public void addEntity(Entity e) {
			this.entity = e;
		}
		
		public void removeEntity() {
			this.entity = null;
		}
	}
}

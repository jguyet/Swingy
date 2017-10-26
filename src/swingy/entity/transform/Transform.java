package swingy.entity.transform;

import swingy.entity.Entity;
import swingy.math.Vector2;

public class Transform {

	public Vector2	position;
	
	/*
	 *   678
	 *   501
	 *   432
	 */
	public byte		direction = 1;
	
	
	private Entity entity;
	
	public Transform(Entity e, Vector2 position) {
		this.entity = e;
		this.position = position;
	}
	
	public void translate(Vector2 newposition) {
		if (newposition.x > position.x)
			direction = 1;
		if (newposition.x < position.x)
			direction = 5;
		if (newposition.y > position.y)
			direction = 3;
		if (newposition.y < position.y)
			direction = 7;
		this.position = newposition;
	}
}

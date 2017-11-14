package swingy.entity.transform;

import swingy.utils.Vector2;

public class Transform {

	public Vector2	position;
	
	/*
	 *   678
	 *   501
	 *   432
	 */
	public byte		direction = 1;
	
	public Transform(Vector2 position) {
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

package games.survival.gameobjects.gameplay;

import java.util.ArrayList;

import org.newdawn.slick.Image;

import games.survival.World;
import games.survival.gameobjects.GameObject;
import games.survival.utils.Vector2;

public class MoveableGameObject extends GameObject {

	protected Vector2 old_location;

	public MoveableGameObject(World world, Image spr) {
		super(world, spr);
		old_location = new Vector2(0,0);
	}

	protected void updateCollisionData()
	{
		old_location.x = location.x;
		old_location.y = location.y;
	}

	protected void undoLocation()
	{
		location.x = old_location.x;
		location.y = old_location.y;
	}



	public ArrayList<GameObject> overlapPoint(Vector2 point)
	{
		ArrayList<GameObject> res = new ArrayList<GameObject>();
		for(GameObject i : world.getObjectList())
		{
			if(i.isCollidingWithPoint(point))
			{
				res.add(i);
			}
		}
		return res;
	}
}

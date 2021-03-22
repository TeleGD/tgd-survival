package games.survival.gameobjects.mapping;

import org.newdawn.slick.Image;

import games.survival.World;
import games.survival.gameobjects.GameObject;
import games.survival.utils.Vector2;

public class MapObject extends GameObject{

	public MapObject(World world, Image spr, Vector2 loc)
	{
		this(world, spr,loc,true);
	}

	public MapObject(World world, Image spr, Vector2 loc, boolean collisions)
	{
		super(world, spr);
		if(collisions == false)
			boundingBox = null;
		location = loc;
		sprite = spr;
	}
}

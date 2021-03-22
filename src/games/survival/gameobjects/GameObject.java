package games.survival.gameobjects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import games.survival.World;
import games.survival.utils.Vector2;

public abstract class GameObject {

	protected World world;
	protected Image sprite;
	public Vector2 location;
	public Vector2 boundingBox;
	protected GameObject collisionOn;

	public GameObject(World world)
	{
		this.world = world;
		sprite = null;
		boundingBox = null;
		location = new Vector2(0,0);
	}

	public GameObject(World world, Image spr)
	{
		this.world = world;
		sprite = spr;
		if(spr != null)
			boundingBox = new Vector2(spr.getWidth(), spr.getHeight());
		location = new Vector2(0,0);
	}

	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) {

	}

	public void render(GameContainer container, StateBasedGame game, Graphics g, boolean useCamera) {
		if(sprite != null)
		{
			if(useCamera)
				g.drawImage(sprite, location.x - world.camera.location.x - sprite.getWidth()/2, location.y - world.camera.location.y- sprite.getHeight()/2);
			else
				g.drawImage(sprite, location.x - sprite.getWidth()/2, location.y- sprite.getHeight()/2);
		}
	}
	public void move(Vector2 direction)
	{
		location.x += direction.x;
		location.y += direction.y;
	}

	public boolean isCollidingWithPoint(Vector2 loc)
	{
		if(boundingBox != null)
		{
			if(loc.x+ boundingBox.x/2f > location.x)
				if(loc.x - boundingBox.x/2f < location.x)
					if(loc.y + boundingBox.y/2f > location.y)
						if(loc.y - boundingBox.y/2f < location.y)
							return true;
		//A NORMALISER
		}
		return false;
	}

	protected boolean isCollidingWithSomething()
	{
		for(GameObject i : world.getObjectList())
		{
			if(isCollidingWith(i) != null)
			{
				collisionOn = i;
				return true;
			}
		}
		collisionOn = null;
		return false;
	}

	public void hurt(float damage)
	{

	}

	public void destroy(GameObject obj) {
		world.deleteObjects.add(obj);
	}

	public Vector2 isCollidingWith(GameObject other)
	{
		if(boundingBox != null && other.boundingBox != null)
		{
			if(other.equals(this))
				return null;
			if(other.location.x + other.boundingBox.x/2f + boundingBox.x/2f > location.x)
				if(other.location.x - other.boundingBox.x/2f - boundingBox.x/2f < location.x)
					if(other.location.y + other.boundingBox.y/2f + boundingBox.y/2f > location.y)
						if(other.location.y - other.boundingBox.y/2f - boundingBox.y/2f < location.y)
							return new Vector2(other.location.x - location.x, other.location.y - location.y);
		//A NORMALISER
		}
		return null;
	}
}

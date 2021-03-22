package games.survival.gameobjects.items;

import org.newdawn.slick.Image;

import games.survival.World;
import games.survival.utils.Vector2;

public class Water extends Items {

	public float amount;

	public Water(World world, Vector2 location, String name,float amount,Image sprite)
	{
		super(world, location,sprite);
		this.name = name;
		this.amount = amount;
	}

	//the action that made the item
	@Override
	public void action()
	{
		world.player.thirst.canAdd(amount);
		world.player.removeItem(this);
	}

}

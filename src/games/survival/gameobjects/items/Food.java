package games.survival.gameobjects.items;

import org.newdawn.slick.Image;

import games.survival.World;
import games.survival.utils.Vector2;

public class Food extends Items {

	public float amount;

	public Food (World world, Vector2 location, String name,float amount,Image sprite)
	{
		super(world, location,sprite);
		this.name = name;
		this.amount = amount;
	}

	//the action that made the item
	@Override
	public void action()
	{
		world.player.hunger.canAdd(amount);
		world.player.removeItem(this);
	}




}

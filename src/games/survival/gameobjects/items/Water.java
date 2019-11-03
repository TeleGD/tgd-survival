package games.survival.gameobjects.items;

import org.newdawn.slick.Image;

import games.survival.utils.Vector2;
import games.survival.World;

public class Water extends Items {

	public float amount;

	public Water(Vector2 location, String name,float amount,Image sprite)
	{
		super(location,sprite);
		this.name = name;
		this.amount = amount;
	}

	//the action that made the item
	@Override
	public void action()
	{
		World.activePlayer.thirst.canAdd(amount);
		World.activePlayer.removeItem(this);
	}

}

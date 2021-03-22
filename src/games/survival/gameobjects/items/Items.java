package games.survival.gameobjects.items;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import games.survival.World;
import games.survival.gameobjects.GameObject;
import games.survival.utils.Vector2;

public abstract class Items extends GameObject {

	protected String name;
	private boolean onGround;

	public Items(World world, Vector2 location,Image sprite) {
		super(world, sprite);
		//this.sprite = sprite;
		this.onGround = true;
		this.location = location;
	}

	public abstract void action();

	public boolean getOnGround() {
		return this.onGround;
	}

	public void pick() {
		if(onGround){
		this.onGround=false;
		world.player.addItem(this);
		destroy(this);
		}
		// objet rammassé par joueur --> inventaire
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) {
		if(onGround && isCollidingWithSomething())
		{
			if(this.collisionOn.equals(world.player))
			{
				//System.out.println("entre");
				pick();
				collisionOn = null;
			}
		}
	}


}

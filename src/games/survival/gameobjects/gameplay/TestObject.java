package games.survival.gameobjects.gameplay;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import app.AppLoader;

import games.survival.World;
import games.survival.gameobjects.GameObject;
import games.survival.gameobjects.items.Items;
import games.survival.input.CustomInput;
import games.survival.utils.Vector2;

public class TestObject extends Character{

	protected Vector2 nextPostionUI;

	protected float attackRange = 50;
	protected float attackDamage = 1;
	public ArrayList<Items> itemList;
	public Compteur hunger;
	public Compteur thirst;
	protected Items activatedWeapons;

	public TestObject(World world, Image spr) {
		super(world, spr, 5);
		itemList = new ArrayList<Items> ();
		this.nextPostionUI = new Vector2(140,690);
		health = new Compteur(world, 100,100, new Vector2(200,650), new Vector2(10,11),AppLoader.loadPicture("/images/survival/ui/barre.png"), AppLoader.loadPicture("/images/survival/ui/healthbar.png"));
		world.addUiGameObject(health);
		hunger = new Compteur(world, 30,100, new Vector2(500,650), new Vector2(10,11),AppLoader.loadPicture("/images/survival/ui/barre.png"), AppLoader.loadPicture("/images/survival/ui/hungrybar.png"));
		world.addUiGameObject(hunger);
		thirst = new Compteur(world, 80,100, new Vector2(800,650), new Vector2(10,11),AppLoader.loadPicture("/images/survival/ui/barre.png"), AppLoader.loadPicture("/images/survival/ui/waterbar.png"));
		world.addUiGameObject(thirst);

		location = new Vector2(0, 0);
		sprite = spr;
	}

	public void addItem(Items item) {
		System.out.println("fghjr");
		itemList.add(item);
		this.nextPostionUI.add(new Vector2(70,0));
		world.addUiGameObject(item);
	}

	public void removeItem(Items Item) {
		itemList.remove(Item);

	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) {
		Vector2 moveDirection = new Vector2(0,0);
		if(CustomInput.up)
			moveDirection.y -= 1;
		if(CustomInput.left)
			moveDirection.x -= 1;
		if(CustomInput.down)
			moveDirection.y += 1;
		if(CustomInput.right)
			moveDirection.x += 1;
		if(CustomInput.one)
			if (itemList.size()>0){
			itemList.get(0).action();
			}
		if(CustomInput.two)
			if (itemList.size()>1){
				itemList.get(1).action();
				}
		if(CustomInput.three)
			if (itemList.size()>2){
				itemList.get(2).action();
				}


		walk(moveDirection);

		if(CustomInput.space)
		{
			Vector2 attackPoint = new Vector2(location.x, location.y);
			switch(direction)
			{
			case 0:
				attackPoint.add(new Vector2(0,-attackRange));
				break;
			case 1:
				attackPoint.add(new Vector2(-attackRange,0));
				break;
			case 2:
				attackPoint.add(new Vector2(0,attackRange));
				break;
			case 3:
				attackPoint.add(new Vector2(attackRange,0));
				break;
			}

			for(GameObject i : overlapPoint(attackPoint))
			{
				if(i.equals(this) == false)
					i.hurt(attackDamage);
			}
		}

		if(isCollidingWithSomething())
		{
			if((collisionOn instanceof Items)==false){
				undoLocation();
			}

		}

		world.camera.follow(location, 5, arg2);

		updateCollisionData();
	}
}

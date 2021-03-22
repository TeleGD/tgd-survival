package games.survival.gameobjects.gameplay;

import java.util.ArrayList;

import games.survival.World;
import games.survival.gameobjects.GameObject;
import games.survival.gameobjects.items.Items;

public class Player extends GameObject {
	public ArrayList<Items> itemList = new ArrayList<Items>();
	public float location;
	public 	Compteur life;
	public 	Compteur hunger;
	public 	Compteur thirst;
	public Items activatedWeapons;


	public Player(World world, ArrayList<Items> itemList,float location) {
		super(world);
		this.activatedWeapons = null;
		this.location = location;
		/*this.life = new Compteur("life",100);
		this.hunger = new Compteur("hunger",100);
		this.thirst = new Compteur("thirst",100);*/
		}

	public void addItem(Items  Item) {
		itemList.add(Item);
	}

	public void removeItem(Items Item) {
		itemList.remove(Item);
	}
}

package games.survival.gameobjects.gameplay;

import java.util.ArrayList;

import games.survival.gameobjects.GameObject;
import games.survival.gameobjects.items.Items;

public class Player extends GameObject {
	public static ArrayList<Items> itemList = new ArrayList<Items>();
	public float location;
	public 	Compteur life;
	public 	Compteur hunger;
	public 	Compteur thirst;
	public Items activatedWeapons;


	public Player(ArrayList<Items> itemList,float location) {
		this.activatedWeapons = null;
		this.location = location;
		/*this.life = new Compteur("life",100);
		this.hunger = new Compteur("hunger",100);
		this.thirst = new Compteur("thirst",100);*/
		}

	public static void addItem(Items  Item) {
		itemList.add(Item);
	}

	public void removeItem(Items Item) {
		itemList.remove(Item);
	}
}

package games.survival;

import java.io.File;
import java.util.LinkedList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import games.survival.CityWorld;
import games.survival.gameobjects.GameObject;
import games.survival.gameobjects.gameplay.Compteur;
import games.survival.gameobjects.gameplay.Infected;
import games.survival.gameobjects.gameplay.TestObject;
import games.survival.gameobjects.items.Food;
import games.survival.gameobjects.items.Water;
import games.survival.gameobjects.mapping.MapObject;
import games.survival.gameobjects.mapping.RepeatBackground;
import games.survival.utils.Vector2;

public class CityWorld extends World{

	public final static int ID = 1000;
	public final static String GAME_FOLDER_NAME="survival";
	public final static String DIRECTORY_IMAGES="images"+File.separator+GAME_FOLDER_NAME+File.separator;
	public final static String GAME_NAME = "Survival";
	public static int longueur=1280;
	public static int hauteur=720;

	public void mapGeneration(int seed) throws SlickException
	{
		Image[] batiments = new Image[2];
		batiments[0] = new Image(CityWorld.DIRECTORY_IMAGES + "deccors/batiment1.png");
		batiments[1] = new Image(CityWorld.DIRECTORY_IMAGES + "deccors/batiment2.png");

		Image road0 = new Image(CityWorld.DIRECTORY_IMAGES + "deccors/road0.png");
		Image road1 = new Image(CityWorld.DIRECTORY_IMAGES + "deccors/road1.png");

		addBackground(new RepeatBackground(new Image(CityWorld.DIRECTORY_IMAGES + "backgrounds/concrete0.jpg"), new Vector2(0,0)));

		addGameObject(new MapObject(new Image(CityWorld.DIRECTORY_IMAGES + "deccors/road2.png"), new Vector2(-256,75), false));

		for(int i=0; i<25; i++)
		{
			addGameObject(new MapObject(road0, new Vector2(i*256,75), false));
			addGameObject(new MapObject(road1, new Vector2(-256,75+256+i*256), false));

			addGameObject(new MapObject(road0, new Vector2(-i*256-256-256,75), false));
			addGameObject(new MapObject(road1, new Vector2(-256,75-256-i*256), false));
		}


		for(int i=0; i<25; i++)
		{
			addGameObject(new MapObject(batiments[(int)(Math.random()+0.5f)], new Vector2(-512,-25*170+i*170)));
			addGameObject(new MapObject(batiments[(int)(Math.random()+0.5f)], new Vector2(0,-25*170+i*170)));

			addGameObject(new MapObject(batiments[(int)(Math.random()+0.5f)], new Vector2(i*240,-200)));
			addGameObject(new MapObject(batiments[(int)(Math.random()+0.5f)], new Vector2(-i*240-512,-200)));
			addGameObject(new MapObject(batiments[(int)(Math.random()+0.5f)], new Vector2(i*240,300)));
			addGameObject(new MapObject(batiments[(int)(Math.random()+0.5f)], new Vector2(-i*240-512,300)));

			addGameObject(new MapObject(batiments[(int)(Math.random()+0.5f)], new Vector2(-512,300+300+i*170)));
			addGameObject(new MapObject(batiments[(int)(Math.random()+0.5f)], new Vector2(0,300+300+i*170)));
		}

		addGameObject(new MapObject(new Image(CityWorld.DIRECTORY_IMAGES + "/deccors/panneaustop.png"), new Vector2(-140,-100), false));
	}


	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		World.activeWorld = this;

		objects = new LinkedList<GameObject>();
		deleteObjects = new LinkedList<GameObject>();
		uiobjects = new LinkedList<GameObject>();
		backgrounds = new LinkedList<GameObject>();

		mapGeneration(0);

		World.activePlayer = new TestObject(new Image(CityWorld.DIRECTORY_IMAGES + "chara.png"));
		addGameObject(World.activePlayer);

		Image infect = new Image(CityWorld.DIRECTORY_IMAGES + "infected.png");

		for(int i=3; i<25; i+=2)
		{
			addGameObject(new Infected(infect, new Vector2(i*256,0),1f,2.5f));
			addGameObject(new Infected(infect, new Vector2(-i*256,0),1f,2.5f));

			double test = Math.random();
			if (test <= 0.5) {
				addGameObject(new Water(new Vector2(-i*256+128,0),"e",20,new Image (CityWorld.DIRECTORY_IMAGES + "/items/water.png")));
			}
			if (test > 0.5) {
				addGameObject(new Food(new Vector2(-i*256+128,0),"a",5,new Image (CityWorld.DIRECTORY_IMAGES + "/items/food.png")));
			}
			addGameObject(new Infected(infect, new Vector2(-256,i*256),1f,2.5f));
			addGameObject(new Infected(infect, new Vector2(-256,-i*256),1f,2.5f));
		}


		addUiGameObject(new Compteur(30,100, new Vector2(500,650), new Vector2(10,11),new Image(CityWorld.DIRECTORY_IMAGES + "ui/barre.png"), new Image(CityWorld.DIRECTORY_IMAGES + "ui/hungrybar.png")));
		addUiGameObject(new Compteur(80,100, new Vector2(800,650), new Vector2(10,11),new Image(CityWorld.DIRECTORY_IMAGES + "ui/barre.png"), new Image(CityWorld.DIRECTORY_IMAGES + "ui/waterbar.png")));


		addUiGameObject(new MapObject(new Image(CityWorld.DIRECTORY_IMAGES + "ui/inventory.png"), new Vector2(500,690)));


	}



	@Override
	public int getID() {
		return ID;
	}
}

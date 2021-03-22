package games.survival;

import java.util.LinkedList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import app.AppLoader;

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

	private int ID;
	private int state;

	public CityWorld(int ID) {
		this.ID = ID;
		this.state = 0;
	}

	@Override
	public int getID() {
		return this.ID;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée une unique fois au chargement du programme */
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée à l'apparition de la page */
		if (this.state == 0) {
			this.play(container, game);
		} else if (this.state == 2) {
			this.resume(container, game);
		}
	}

	@Override
	public void leave(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée à la disparition de la page */
		if (this.state == 1) {
			this.pause(container, game);
		} else if (this.state == 3) {
			this.stop(container, game);
			this.state = 0; // TODO: remove
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		/* Méthode exécutée environ 60 fois par seconde */
		Input input = container.getInput();
		if (input.isKeyDown(Input.KEY_ESCAPE)) {
			this.setState(1);
			game.enterState(2, new FadeOutTransition(), new FadeInTransition());
		}
		super.update(container, game, delta);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		/* Méthode exécutée environ 60 fois par seconde */
		super.render(container, game, context);
	}

	public void mapGeneration(int seed) {
		Image[] batiments = new Image[2];
		batiments[0] = AppLoader.loadPicture("/images/survival/deccors/batiment1.png");
		batiments[1] = AppLoader.loadPicture("/images/survival/deccors/batiment2.png");

		Image road0 = AppLoader.loadPicture("/images/survival/deccors/road0.png");
		Image road1 = AppLoader.loadPicture("/images/survival/deccors/road1.png");

		addBackground(new RepeatBackground(this, AppLoader.loadPicture("/images/survival/backgrounds/concrete0.jpg"), new Vector2(0,0)));

		addGameObject(new MapObject(this, AppLoader.loadPicture("/images/survival/deccors/road2.png"), new Vector2(-256,75), false));

		for(int i=0; i<25; i++)
		{
			addGameObject(new MapObject(this, road0, new Vector2(i*256,75), false));
			addGameObject(new MapObject(this, road1, new Vector2(-256,75+256+i*256), false));

			addGameObject(new MapObject(this, road0, new Vector2(-i*256-256-256,75), false));
			addGameObject(new MapObject(this, road1, new Vector2(-256,75-256-i*256), false));
		}


		for(int i=0; i<25; i++)
		{
			addGameObject(new MapObject(this, batiments[(int)(Math.random()+0.5f)], new Vector2(-512,-25*170+i*170)));
			addGameObject(new MapObject(this, batiments[(int)(Math.random()+0.5f)], new Vector2(0,-25*170+i*170)));

			addGameObject(new MapObject(this, batiments[(int)(Math.random()+0.5f)], new Vector2(i*240,-200)));
			addGameObject(new MapObject(this, batiments[(int)(Math.random()+0.5f)], new Vector2(-i*240-512,-200)));
			addGameObject(new MapObject(this, batiments[(int)(Math.random()+0.5f)], new Vector2(i*240,300)));
			addGameObject(new MapObject(this, batiments[(int)(Math.random()+0.5f)], new Vector2(-i*240-512,300)));

			addGameObject(new MapObject(this, batiments[(int)(Math.random()+0.5f)], new Vector2(-512,300+300+i*170)));
			addGameObject(new MapObject(this, batiments[(int)(Math.random()+0.5f)], new Vector2(0,300+300+i*170)));
		}

		addGameObject(new MapObject(this, AppLoader.loadPicture("/images/survival//deccors/panneaustop.png"), new Vector2(-140,-100), false));
	}

	public void play(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée une unique fois au début du jeu */
		objects = new LinkedList<GameObject>();
		deleteObjects = new LinkedList<GameObject>();
		uiobjects = new LinkedList<GameObject>();
		backgrounds = new LinkedList<GameObject>();

		mapGeneration(0);

		this.camera = new Camera();
		this.player = new TestObject(this, AppLoader.loadPicture("/images/survival/chara.png"));
		addGameObject(this.player);

		Image infect = AppLoader.loadPicture("/images/survival/infected.png");

		for(int i=3; i<25; i+=2)
		{
			addGameObject(new Infected(this, infect, new Vector2(i*256,0),1f,2.5f));
			addGameObject(new Infected(this, infect, new Vector2(-i*256,0),1f,2.5f));

			double test = Math.random();
			if (test <= 0.5) {
				addGameObject(new Water(this, new Vector2(-i*256+128,0),"e",20,AppLoader.loadPicture("/images/survival/items/water.png")));
			}
			if (test > 0.5) {
				addGameObject(new Food(this, new Vector2(-i*256+128,0),"a",5,AppLoader.loadPicture("/images/survival/items/food.png")));
			}
			addGameObject(new Infected(this, infect, new Vector2(-256,i*256),1f,2.5f));
			addGameObject(new Infected(this, infect, new Vector2(-256,-i*256),1f,2.5f));
		}


		addUiGameObject(new Compteur(this, 30,100, new Vector2(500,650), new Vector2(10,11),AppLoader.loadPicture("/images/survival/ui/barre.png"), AppLoader.loadPicture("/images/survival/ui/hungrybar.png")));
		addUiGameObject(new Compteur(this, 80,100, new Vector2(800,650), new Vector2(10,11),AppLoader.loadPicture("/images/survival/ui/barre.png"), AppLoader.loadPicture("/images/survival/ui/waterbar.png")));


		addUiGameObject(new MapObject(this, AppLoader.loadPicture("/images/survival/ui/inventory.png"), new Vector2(500,690)));
	}

	public void pause(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée lors de la mise en pause du jeu */
	}

	public void resume(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée lors de la reprise du jeu */
	}

	public void stop(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée une unique fois à la fin du jeu */
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getState() {
		return this.state;
	}

}

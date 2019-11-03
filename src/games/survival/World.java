package games.survival;

import java.util.LinkedList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import games.survival.gameobjects.GameObject;
import games.survival.gameobjects.gameplay.TestObject;
import games.survival.input.CustomInput;

public abstract class World extends BasicGameState {

	public static World activeWorld;
	public static TestObject activePlayer;

	public static LinkedList<GameObject> objects;
	public static LinkedList<GameObject> deleteObjects ;
	protected LinkedList<GameObject> uiobjects;
	protected LinkedList<GameObject> backgrounds;

	public World()
	{

	}

	public void addGameObject(GameObject obj)
	{
		if(objects.contains(obj) == false)
			objects.add(obj);
	}

	public void addBackground(GameObject obj)
	{
		backgrounds.add(obj);
	}

	public void addUiGameObject(GameObject obj)
	{
		if(uiobjects.contains(obj) == false)
			uiobjects.add(obj);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		for(GameObject i : objects)
			i.update(arg0, arg1, arg2);
		for(GameObject i : uiobjects)
			i.update(arg0, arg1, arg2);

		for(GameObject i : deleteObjects)
		{
			objects.remove(i);
			uiobjects.remove(i);
		}
		deleteObjects = new LinkedList<GameObject>();

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
	{
		for(GameObject i : backgrounds)
			i.render(container, game, g, true);

		for(GameObject i : objects)
			i.render(container, game, g, true);

		for(GameObject i : uiobjects)
			i.render(container, game, g, false);
	}

	@Override
	public void keyPressed(int key, char c)
	{
		CustomInput.keyPressed(key, c);
	}

	@Override
	public void keyReleased(int key, char c)
	{
		CustomInput.keyReleased(key, c);
	}

	public LinkedList<GameObject> getObjectList()
	{
		return objects;
	}
}

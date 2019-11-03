package games.survival.gameobjects.mapping;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import games.survival.Camera;
import games.survival.CityWorld;
import games.survival.utils.Vector2;

public class RepeatBackground extends MapObject{

	public RepeatBackground(Image spr, Vector2 loc) {
		super(spr, loc);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g, boolean useCamera) throws SlickException
	{
		float start_x = location.x + (int)((Camera.location.x - CityWorld.longueur/2) / sprite.getWidth()) * sprite.getWidth();
		float start_y = location.y + (int)((Camera.location.y - CityWorld.hauteur/2) / sprite.getHeight()) * sprite.getHeight();


		if(sprite != null)
		{
			if(useCamera)
				for(int xx=-sprite.getWidth(); xx<CityWorld.longueur+ sprite.getWidth(); xx+=sprite.getWidth())
					for(int yy=-sprite.getHeight(); yy<CityWorld.hauteur+ sprite.getHeight(); yy+=sprite.getHeight())
						g.drawImage(sprite, start_x + xx - Camera.location.x, start_y + yy - Camera.location.y);
			else
				g.drawImage(sprite, location.x - sprite.getWidth()/2, location.y- sprite.getHeight()/2);
		}
	}
}

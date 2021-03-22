package games.survival.gameobjects.mapping;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import games.survival.World;
import games.survival.utils.Vector2;

public class RepeatBackground extends MapObject{

	public RepeatBackground(World world, Image spr, Vector2 loc) {
		super(world, spr, loc);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g, boolean useCamera) {
		float start_x = location.x + (int)((world.camera.location.x - World.longueur/2) / sprite.getWidth()) * sprite.getWidth();
		float start_y = location.y + (int)((world.camera.location.y - World.hauteur/2) / sprite.getHeight()) * sprite.getHeight();


		if(sprite != null)
		{
			if(useCamera)
				for(int xx=-sprite.getWidth(); xx<World.longueur+ sprite.getWidth(); xx+=sprite.getWidth())
					for(int yy=-sprite.getHeight(); yy<World.hauteur+ sprite.getHeight(); yy+=sprite.getHeight())
						g.drawImage(sprite, start_x + xx - world.camera.location.x, start_y + yy - world.camera.location.y);
			else
				g.drawImage(sprite, location.x - sprite.getWidth()/2, location.y- sprite.getHeight()/2);
		}
	}
}

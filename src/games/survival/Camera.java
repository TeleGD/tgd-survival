package games.survival;

import games.survival.utils.Utils;
import games.survival.utils.Vector2;

public class Camera {

	protected Vector2 screenCenter = new Vector2(1280/2, 720/2);

	public Vector2 location = new Vector2(0,0);

	public void follow(Vector2 loc, float force, int arg2)
	{
		this.location.x = Utils.lerp(this.location.x, loc.x - screenCenter.x, arg2 / 1000f*force);
		this.location.y = Utils.lerp(this.location.y, loc.y - screenCenter.y, arg2 / 1000f*force);
	}
}

package games.survival;

import games.survival.utils.Utils;
import games.survival.utils.Vector2;

public class Camera {

	protected static Vector2 screenCenter = new Vector2(1280/2, 720/2);

	public static Vector2 location = new Vector2(0,0);

	public static void follow(Vector2 loc, float force, int arg2)
	{
		Camera.location.x = Utils.lerp(Camera.location.x, loc.x - screenCenter.x, arg2 / 1000f*force);
		Camera.location.y = Utils.lerp(Camera.location.y, loc.y - screenCenter.y, arg2 / 1000f*force);
	}
}

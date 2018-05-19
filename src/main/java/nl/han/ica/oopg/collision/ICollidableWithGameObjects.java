package nl.han.ica.oopg.collision;

import nl.han.ica.oopg.objects.GameObject;
import java.util.List;

/**
 * Make your GameObject implement this interface if you want your GameObject to
 * be collidable with other GameObjects.
 */
public interface ICollidableWithGameObjects {

	/**
	 * This method will be triggered when a GameObject has collided with other
	 * GameObjects.
	 * 
	 * @param collidedGameObjects The GameObjects with which a collision should be detected
	 */
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects);
}

package nl.han.ica.OOPDProcessingEngineHAN.Collision;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
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
	 * @param collidedGameObjects
	 */
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects);
}

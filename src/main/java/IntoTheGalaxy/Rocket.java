package IntoTheGalaxy;

import java.util.List;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;

/**
 * An abstract class to create rockets. Is removed from the world when outside of the screen. 
 * Can collide with other GameObjects, but which ones has to be implemented in the subclass.
 */
public abstract class Rocket extends GameObject implements ICollidableWithGameObjects {
	IntoTheGalaxy world;
	
	public Rocket(IntoTheGalaxy world, float x, float y, float width, float height) {
		super(x, y, width, height);
		this.world = world;
	}
	
	/**
	 * Overrides GameObject.update(), to remove rockets outside of the screen.
	 */
	@Override
	public void update() {
		if (y < 0 || y > world.getHeight()) {
			world.deleteGameObject(this);
		}
	}
	
	/**
	 * Method from ICollidableWithGameObjects, is implemented in the subclass.
	 */
	public abstract void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects);
}

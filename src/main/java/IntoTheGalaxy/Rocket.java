package IntoTheGalaxy;

import java.util.List;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;

public abstract class Rocket extends GameObject implements ICollidableWithGameObjects {
	IntoTheGalaxy world;
	
	public Rocket(IntoTheGalaxy world, float x, float y, float width, float height) {
		super(x, y, width, height);
		this.world = world;
	}

	@Override
	public void update() {
		if (y < 0 || y > world.getHeight()) {
			world.deleteGameObject(this);
		}
	}
	
	public abstract void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects);
}

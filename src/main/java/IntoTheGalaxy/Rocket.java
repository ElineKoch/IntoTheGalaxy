package IntoTheGalaxy;

import java.util.List;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.sound.Sound;
import processing.core.PGraphics;

public abstract class Rocket extends GameObject implements ICollidableWithGameObjects {
	public Rocket(float x, float y, float width, float height) {
		super(x, y, width, height);
	}

	@Override
	public void update() {
		
	}
	
	public abstract void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects);
}

package IntoTheGalaxy;

import java.util.*;
import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

/**
 * A Rocket that moves down the screen and can hit Fighter.
 */
public class AlienRocket extends Rocket {

	public AlienRocket(IntoTheGalaxy world, float x, float y, float width, float height) {
		super(world, x, y, width, height);
		setDirection(180);
		setSpeed(4);
	}
	
	/**
	 * Overrides GameObject.draw() to draw Rocket
	 */
	@Override
	public void draw(PGraphics g) {
		g.fill(0xFF00FF00);
		g.rect(getX() - getWidth() / 2, getY(), getWidth(), getHeight() * 3 / 4);
		g.fill(0xFF0000FF);
		g.rect(getX() - getWidth() / 2, getY() + getHeight() * 3 / 4, getWidth(), getHeight() / 4);
	}
	
	/**
	 * Can collide with Alien, disappears from world on collision.
	 */
	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject go : collidedGameObjects) {
			if (go instanceof Fighter) {
				world.deleteGameObject(this);
			}
		}
	}
}
package IntoTheGalaxy;

import java.util.List;
import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

/**
 * A Rocket that moves up the screen and can hit Alien
 */
public class FighterRocket extends Rocket {

	public FighterRocket(IntoTheGalaxy world, float x, float y, float width, float height) {
		super(world, x, y, width, height);
		setDirection(0);
		setSpeed(10);
	}
	
	/**
	 * Overrides GameObject.draw() to draw Rocket
	 */
	@Override
	public void draw(PGraphics g) {
		g.fill(0xFFFF0000);
		g.rect(getX() - getWidth() / 2, getY(), getWidth(), getHeight() / 4);
		g.fill(0xFFFFFFFF);
		g.rect(getX() - getWidth() / 2, getY() + getHeight() / 4, getWidth(), getHeight() * 3 / 4);
	}
	
	/**
	 * Can collide with Alien, disappears from world on collision.
	 */
	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject go : collidedGameObjects) {
			if (go instanceof Alien) {
				world.deleteGameObject(this);
			}
		}
	}
}

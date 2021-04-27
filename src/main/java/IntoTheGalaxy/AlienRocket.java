package IntoTheGalaxy;

import java.util.List;

import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

public class AlienRocket extends Rocket {

	public AlienRocket(IntoTheGalaxy world, float x, float y, float width, float height) {
		super(world, x, y, width, height);
		setDirection(180);
		setSpeed(4);
	}

	@Override
	public void draw(PGraphics g) {
		g.fill(0xFF00FF00);
		g.rect(getX() - getWidth() / 2, getY(), getWidth(), getHeight() * 3 / 4);
		g.fill(0xFF0000FF);
		g.rect(getX() - getWidth() / 2, getY() + getHeight() * 3 / 4, getWidth(), getHeight() / 4);
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject go : collidedGameObjects) {
			if (go instanceof Fighter) {
				world.deleteGameObject(this);
			}
		}
	}
}
package IntoTheGalaxy;

import java.util.List;

import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

public class FighterRocket extends Rocket {
	
	public FighterRocket(float x, float y, float width, float height) {
		super(x, y, width, height);
		setDirection(0);
		setSpeed(5);
	}

	@Override
	public void draw(PGraphics g) {
		g.fill(0xFFFF0000);
		g.rect(getX() - getWidth() / 2, getY(), getWidth(), getHeight() / 4);
		g.fill(0xFFFFFFFF);
		g.rect(getX() - getWidth() / 2, getY() + getHeight() / 4, getWidth(), getHeight() * 3 / 4);
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		// TODO Auto-generated method stub
		
	}
}

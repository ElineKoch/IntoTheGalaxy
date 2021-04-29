package IntoTheGalaxy;

import java.util.*;
import nl.han.ica.oopg.sound.Sound;
import nl.han.ica.oopg.collision.*;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

public class Fighter extends SpriteObject implements ICollidableWithGameObjects {
	private IntoTheGalaxy world;
	private Sound fighterShootSound, fighterExplosionSound;
	private int shootDelay;
	private int hitDelay;
	private int numLives = 3;
	private float startX, startY;

	public Fighter(IntoTheGalaxy world, float xPos, float yPos, Sound shootSound, Sound explosionSound) {
		super(new Sprite(IntoTheGalaxy.MEDIA_URL.concat("Fighter.png")));
		this.world = world;
		fighterShootSound = shootSound;
		fighterExplosionSound = explosionSound;
		resetShootDelay();
		startHitDelay();
		setX(xPos);
		setY(yPos);
		startX = x;
		startY = y;
	}

	public void resetShootDelay() {
		shootDelay = 0;
	}

	public void startHitDelay() {
		hitDelay = 120;
	}

	@Override
	public void update() {
		if (getX() <= 0) {
			setxSpeed(0);
			setX(0);
		}
		if (getX() >= world.width * 5 / 6 - getWidth()) {
			setxSpeed(0);
			setX(world.width * 5 / 6 - getWidth());
		}
		if (getY() <= 0) {
			setySpeed(0);
			setY(0);
		}
		if (getY() >= world.height - getHeight()) {
			setySpeed(0);
			setY(world.height - getHeight());
		}

		if (shootDelay > 0) {
			shootDelay--;
		}

		if (shootDelay > 0) {
			shootDelay--;
		}

		if (hitDelay > 0) {
			hitDelay--;
		}
	}

	@Override
	public void keyPressed(int keyCode, char key) {
		final int speed = 4;
		if (key == 'w') {
			setDirectionSpeed(0, speed);
		}
		if (key == 'a') {
			setDirectionSpeed(270, speed);
		}
		if (key == 's') {
			setDirectionSpeed(180, speed);
		}
		if (key == 'd') {
			setDirectionSpeed(90, speed);
		}

		if (key == 'e' && shootDelay == 0) {
			FighterRocket rocketF = new FighterRocket(world, getX() + getWidth() / 2, getY(), 4, 16);
			world.addGameObject(rocketF, rocketF.getX(), rocketF.getY());
			shootDelay += 48;
			fighterShootSound.cue(0);
			fighterShootSound.play();
		}
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject go : collidedGameObjects) {
			if ((go instanceof Alien || go instanceof AlienRocket) && hitDelay == 0) {
				decreaseLives();
				setX(startX);
				setY(startY);
				setDirectionSpeed(0, 0);
				System.out.println(numLives);
				fighterExplosionSound.cue(0);
				fighterExplosionSound.play();
				if (numLives == 0) {
					world.deleteGameObject(this);
				}
				startHitDelay();
			}
		}
	}

	public void decreaseLives() {
		numLives--;
	}
	
	public int getNumLives() {
		return numLives;
	}
	
	
}
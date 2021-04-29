package IntoTheGalaxy;

import java.util.*;
import nl.han.ica.oopg.collision.*;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import nl.han.ica.oopg.sound.Sound;

public abstract class Alien extends SpriteObject implements ICollidableWithGameObjects {
	protected IntoTheGalaxy world;
	protected float speed = 1.5f;
	protected float startX;
	protected float startY;
	protected Sound alienShootSound, alienExplosionSound;
	protected Random rand;
	protected int numLives = 1;

	public Alien(IntoTheGalaxy world, float xPos, float yPos, Sound shootSound, Sound explosionSound) {
		super(new Sprite(IntoTheGalaxy.MEDIA_URL.concat("Butterfly.png")));
		this.world = world;
		setDirectionSpeed(90, speed);
		setX(xPos * 50);
		setY(yPos * 50);
		setStartX(getX());
		setStartY(getY());
		rand = new Random();

		alienShootSound = shootSound;
		alienExplosionSound = explosionSound;
	}

	public void setStartX(float x) {
		startX = x;
	}

	public float getStartX() {
		return startX;
	}

	public void setStartY(float y) {
		startY = y;
	}

	public float getStartY() {
		return startY;
	}

	@Override
	public void update() {
		if (x < getStartX() - getWidth() * 2) {
			setxSpeed(speed);
		}
		if (x > startX + getWidth() * 2) {
			setxSpeed(-speed);
		}
		shoot();
		makeDive();
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject go : collidedGameObjects) {
			if (go instanceof FighterRocket) {
				alienExplosionSound.cue(0);
				alienExplosionSound.play();
				decreaseLives();
				if (numLives == 0) {
					world.deleteGameObject(this);
					world.alienList.remove(this);
					world.increaseScore(50);
				}
			}
		}
	}

	public void setLives(int lives) {
		numLives = lives;
	}

	public void decreaseLives() {
		numLives--;
	}

	public void shoot() {
		if ((world.getTime() * 100 + 1000) % (rand.nextInt(40000) + 1) == 500) {
			AlienRocket rocketA = new AlienRocket(world, getX() + getWidth() / 2, getY(), 4, 16);
			world.addGameObject(rocketA, rocketA.getX(), rocketA.getY());
			alienShootSound.cue(0);
			alienShootSound.play();
		}
	}

	public void makeDive() {
		if ((world.getTime() * 100 + 1000) % (rand.nextInt(100000) + 1) == 500) {
			doDiveAction();
		}
		if (getY() > world.getHeight() - getHeight()) {
			returnToStart();
		}
	};

	public void returnToStart() {
		if (getY() > world.getHeight() - getHeight()) {
			setySpeed(0);
			setX(startX);
			setY(startY);
		}
	}

	public abstract void doDiveAction();
}
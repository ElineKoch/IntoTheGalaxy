package IntoTheGalaxy;

import java.util.*;
import nl.han.ica.oopg.sound.Sound;
import nl.han.ica.oopg.collision.*;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

/**
 * A spaceship that can be controlled with the WASD keys and shoot Alien with the E key, 
 * can be hit by Alien and AlienRocket and loses a life, deleted from world if 3 lives are gone.
 */
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
	
	/**
	 * Set the value of the shootDelay attribute to 0.
	 */
	public void resetShootDelay() {
		shootDelay = 0;
	}
	
	/**
	 * Set the value of the shootDelay attribute to 0.
	 */
	public void startHitDelay() {
		hitDelay = 120;
	}
	
	/**
	 * Overrides GameObject.update(), keeps fighter within the screen, reduces delays over time.
	 */
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

		if (hitDelay > 0) {
			hitDelay--;
		}
	}
	
	/**
	 * Control the fighter by using the WASD and E keys. 
	 * The E key creates a FighterRocket object that can hit Alien.
	 */
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
			shoot();
		}
	}
	
	private void shoot() {
		FighterRocket rocketF = new FighterRocket(world, getX() + getWidth() / 2, getY(), 4, 16);
		world.addGameObject(rocketF, rocketF.getX(), rocketF.getY());
		shootDelay += 24;
		fighterShootSound.cue(0);
		fighterShootSound.play();
	}
	
	/**
	 * Implements the ICollidableWithGameObjects interface, can collide with Alien and AlienRocket. 
	 * If fighter gets hit, it goes to the starting position and loses a life 
	 * until it disappears from the world at 3 hits.
	 */
	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject go : collidedGameObjects) {
			if ((go instanceof Alien || go instanceof AlienRocket) && hitDelay == 0) {
				decreaseLives();
				setX(startX);
				setY(startY);
				setDirectionSpeed(0, 0);
				fighterExplosionSound.cue(0);
				fighterExplosionSound.play();
				if (numLives == 0) {
					world.deleteGameObject(this);
				}
				startHitDelay();
			}
		}
	}
	
	/**
	 * Decreases the value of the numLives attribute by 1.
	 */
	public void decreaseLives() {
		numLives--;
	}
	
	/**
	 * Returns the value of the numLives attribute.
	 */
	public int getNumLives() {
		return numLives;
	}
}
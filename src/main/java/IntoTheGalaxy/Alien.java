package IntoTheGalaxy;

import java.util.*;
import nl.han.ica.oopg.collision.*;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import nl.han.ica.oopg.sound.Sound;

/**
 * Abstract class to create an Alien. Aliens move side to side at the top of the screen 
 * and can shoot and dive down to the bottom of the screen 
 * at times decided partly by a random number generator.
 */
public abstract class Alien extends SpriteObject implements ICollidableWithGameObjects {
	protected IntoTheGalaxy world;
	protected float speed = 1.5f;
	protected float startX;
	protected float startY;
	protected Sound alienShootSound, alienExplosionSound;
	protected Random rand;
	protected int numLives = 1;

	public Alien(IntoTheGalaxy world, float xPos, float yPos, Sound shootSound, Sound explosionSound, Sprite sprite) {
		super(sprite);
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
	
	/**
	 * Set the value of the startX attribute. 
	 */
	public void setStartX(float x) {
		startX = x;
	}
	
	/**
	 * Returns the value of the startX attribute. 
	 */
	public float getStartX() {
		return startX;
	}
	
	/**
	 * Set the value of the startY attribute. 
	 */
	public void setStartY(float y) {
		startY = y;
	}
	
	/**
	 * Returns the value of the startY attribute. 
	 */
	public float getStartY() {
		return startY;
	}
	
	/**
	 * Sets the value of the numLives attribute. 
	 */
	public void setLives(int lives) {
		numLives = lives;
	}
	
	/**
	 * Sets the value of the numLives attribute. 
	 */
	public void decreaseLives() {
		numLives--;
	}
	
	/**
	 * Overrides GameObject.update(), causes the alien to shoot and make dives, 
	 * the dive action itself is implemented in the subclasses.
	 */
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
	
	/**
	 * Implements the ICollidableWithGameObjects interface. Collides with FighterRocket, loses a life,
	 * if lives are 0, deletes alien from the world and increases score.
	 */
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
					world.score.increaseScore(50);
				}
			}
		}
	}

	/**
	 * Creates an AlienRocket object that can hit fighters at semi random times.
	 */
	public void shoot() {
		if ((world.getTime() * 100 + 1000) % (rand.nextInt(40000) + 1) == 500) {
			AlienRocket rocketA = new AlienRocket(world, getX() + getWidth() / 2, getY(), 4, 16);
			world.addGameObject(rocketA, rocketA.getX(), rocketA.getY());
			alienShootSound.cue(0);
			alienShootSound.play();
		}
	}
	
	/**
	 * Initiates the dive action at semi random times.
	 */
	public void makeDive() {
		if ((world.getTime() * 100 + 1000) % (rand.nextInt(100000) + 1) == 500) {
			doDiveAction();
		}
		if (getY() > world.getHeight() - getHeight()) {
			returnToStart();
		}
	};
	
	/**
	 *Defines what the dive looks like. Has to be implemented in the subclass.
	 */
	public abstract void doDiveAction();
	
	/**
	 * If the alien is below the bottom of the screen, because of a dive, 
	 * it return to the starting position and the ySpeed is set to 0.
	 */
	public void returnToStart() {
		if (getY() > world.getHeight() - getHeight()) {
			setySpeed(0);
			setX(startX);
			setY(startY);
		}
	}
}
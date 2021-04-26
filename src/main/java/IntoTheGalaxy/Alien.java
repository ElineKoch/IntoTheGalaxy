package IntoTheGalaxy;

import java.util.*;
import TutorialWorld.tiles.FloorTile;
import nl.han.ica.oopg.collision.*;
import nl.han.ica.oopg.exceptions.TileNotFoundException;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import processing.core.PVector;
import nl.han.ica.oopg.sound.Sound;

public abstract class Alien extends SpriteObject implements ICollidableWithGameObjects {
    protected IntoTheGalaxy world;
    protected float speed = 1.5f;
    protected float startX;
    protected float startY;
    protected boolean isDiving;
    protected Sound alienShootSound, alienExplosionSound;
    protected Random rand;
    protected int numLives = 1;
    
    public Alien(IntoTheGalaxy world, Sound explosionSound, int xPos, int yPos) {
        // Met `.concat()` plak je 2 strings aan elkaar.
        // De methode returned een nieuwe String terug. 
        super(new Sprite(IntoTheGalaxy.MEDIA_URL.concat("Butterfly.png")));
        this.world = world;
        setDirectionSpeed(90, speed);
        this.alienExplosionSound = explosionSound;
        setX(xPos * 50);
        setY(yPos * 50);
        setStartX(getX());
        setStartY(getY());
        isDiving = false;
        rand = new Random();
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
  			setDirectionSpeed(90, speed);
  		}   
  		if (x > startX + getWidth() * 2) {
  			setDirectionSpeed(270, speed);
  		}
  	}

		@Override
		public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
			for (GameObject go: collidedGameObjects) {
				if (go instanceof Fighter) {
					alienExplosionSound.cue(0);
	        alienExplosionSound.play();
	        decreaseLives();
	        if (numLives == 0) {
	        	world.deleteGameObject(this);
	        }
				}
			}
		}
		//add rocket to collisions
		public void setLives(int lives) {
			numLives = lives;
		}
		
		public void decreaseLives() {
			numLives--; 
		}
		
		public void makeDive() {
			if (world.time % (rand.nextInt(30000) + 600) == 0) {
				doDiveAction();
			}
		};
		
		public abstract void doDiveAction();
}
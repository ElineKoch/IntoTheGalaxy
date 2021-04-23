package TutorialWorld;

import java.util.*;
import TutorialWorld.tiles.FloorTile;
import nl.han.ica.oopg.collision.*;
import nl.han.ica.oopg.exceptions.TileNotFoundException;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import processing.core.PVector;
import nl.han.ica.oopg.sound.Sound;

public class Disc extends SpriteObject implements ICollidableWithTiles, ICollidableWithGameObjects {
    private TutorialWorld world;
    private float speed = 2f;
    private Sound explosionSound;
    
    public Disc(TutorialWorld world, Sound explosionSound) {
        // Met `.concat()` plak je 2 strings aan elkaar.
        // De methode returned een nieuwe String terug. 
        super(new Sprite(TutorialWorld.MEDIA_URL.concat("disc.png")));
        this.world = world;
        this.explosionSound = explosionSound;
        setDirectionSpeed(90, speed);
    }

    @Override
  	public void update() {
  		if (x < 0) {
  			setDirectionSpeed(90, speed);
  		}   
  		if (x > world.getWidth() - this.getWidth()) {
  			setDirectionSpeed(270, speed);
  		}
  	}
    
    @Override
    public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
        PVector vector;
        for (CollidedTile ct : collidedTiles) {
            if (ct.getTile() instanceof FloorTile) {
                try {					
                    vector = world.getTileMap().getTilePixelLocation(ct.getTile());
                    setY(vector.y - getHeight());
                } catch (TileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
      
    }

		@Override
		public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
			for (GameObject go: collidedGameObjects) {
				if (go instanceof Player) {
					explosionSound.cue(0);
	        explosionSound.play();
					world.deleteGameObject(this);
				}
			}
		}
}
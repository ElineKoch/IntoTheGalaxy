package IntoTheGalaxy;

import nl.han.ica.oopg.sound.Sound;
import java.util.*;
import TutorialWorld.tiles.FloorTile;
import nl.han.ica.oopg.collision.*;
import nl.han.ica.oopg.exceptions.TileNotFoundException;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import processing.core.PConstants;
import processing.core.PVector;

public class Fighter extends SpriteObject {
    private IntoTheGalaxy world;
    private int shootDelay;
    private Sound fighterShootSound, fighterExplosionSound;
    
    public Fighter(IntoTheGalaxy world) {
        // Met `.concat()` plak je 2 strings aan elkaar.
        // De methode returned een nieuwe String terug. 
    		super(new Sprite(IntoTheGalaxy.MEDIA_URL.concat("Fighter.png")));
        this.world = world;
        resetShootDelay();
    }
    
  	public void resetShootDelay() {
  		shootDelay = 0;
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
    }
    
    @Override
    public void keyPressed(int keyCode, char key) {
    	final int speed = 4;
    	
      if (keyCode == PConstants.LEFT) {
        setDirectionSpeed(270, speed);
      }
      if (keyCode == PConstants.UP) {
        setDirectionSpeed(0, speed);
      }
      if (keyCode == PConstants.RIGHT) {
        setDirectionSpeed(90, speed);
      }
      if (keyCode == PConstants.DOWN) {
        setDirectionSpeed(180, speed);
      }
      
      if (key == 'q' && shootDelay == 0) {
      	 FighterRocket rocketF = new FighterRocket(getX() + getWidth() / 2, getY(), 4, 16);
         world.addGameObject(rocketF, rocketF.getX(), rocketF.getY());
         shootDelay += 30;
      }
   }
    
//    @Override
//    public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
//        PVector vector;
//        for (CollidedTile ct : collidedTiles) {
//            if (ct.getTile() instanceof FloorTile) {
//                try {					
//                    vector = world.getTileMap().getTilePixelLocation(ct.getTile());
//                    setY(vector.y - getHeight());
//                } catch (TileNotFoundException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//      
//    }
}
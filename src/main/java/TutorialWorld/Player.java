package TutorialWorld;

import nl.han.ica.oopg.sound.Sound;
import java.util.*;
import TutorialWorld.tiles.FloorTile;
import nl.han.ica.oopg.collision.*;
import nl.han.ica.oopg.exceptions.TileNotFoundException;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import processing.core.PConstants;
import processing.core.PVector;

public class Player extends SpriteObject implements ICollidableWithTiles {
    private TutorialWorld world;
    private Sound jump;
    
    public Player(TutorialWorld world, Sound jump) {
        // Met `.concat()` plak je 2 strings aan elkaar.
        // De methode returned een nieuwe String terug. 
        super(new Sprite(TutorialWorld.MEDIA_URL.concat("player.png")));
        this.world = world;
        setGravity(0.1f);
        this.jump = jump;
    }

    @Override
    public void update() {
        
        
    }
    
    @Override
    public void keyPressed(int keyCode, char key) {
    	final int speed = 5;
    	if (keyCode == PConstants.LEFT) {
        setDirectionSpeed(270, speed);
      }
    	if (keyCode == PConstants.RIGHT) {
        setDirectionSpeed(90, speed);
      }
    	if (keyCode == PConstants.UP) {
        setDirectionSpeed(0, speed);
        jump.cue(0);
        jump.play();
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
}
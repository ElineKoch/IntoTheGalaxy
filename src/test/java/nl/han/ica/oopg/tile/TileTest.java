package nl.han.ica.oopg.tile;

import nl.han.ica.oopg.objects.Sprite;
import org.junit.Before;
import org.junit.Test;
import processing.core.PImage;

import static org.junit.Assert.assertEquals;

public class TileTest {

    Tile tile;
    Sprite testSprite = new Sprite(new PImage(50, 50));

    @Before
    public void setup() {
        tile = new Tile(testSprite);
    }

    @Test
    public void getSpriteWhichHasBeenGivenInTheConstructor() {
        assertEquals(tile.getSprite(), testSprite);
    }

    @Test
    public void setSpriteSizeTo100() {
        int testSize = 100;
        tile.setSpriteSize(testSize);
        assertEquals(tile.getSprite().getHeight(), testSize);
        assertEquals(tile.getSprite().getWidth(), testSize);
    }
}

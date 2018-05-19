package nl.han.ica.oopg.objects;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import processing.core.PImage;

public class SpriteTest {

	PImage img;
	Sprite sprite;
	
	@Before
	public void setup()
	{
		img = new PImage(20, 20);
		sprite = new Sprite(img);
	}
	
	@Test
	public void getWidthShouldReturnACorrectValue()
	{
		int value = sprite.getWidth();
		
		assertEquals(img.width, value);
	}
	
	@Test
	public void getHeightShouldReturnACorrectValue()
	{
		int value = sprite.getHeight();
		
		assertEquals(img.height, value);
	}
	
	@Test
	public void getSpriteCenterXShouldReturnACorrectValue()
	{
		float value = sprite.getSpriteCenterX();
		
		assertEquals(10, value, 0);
	}
	
	@Test
	public void getSpriteCenterYShouldReturnACorrectValue()
	{
		float value = sprite.getSpriteCenterY();
		
		assertEquals(10, value, 0);
	}
	
	@Test
	public void setSpriteShouldSetThePImageCorrectly()
	{
		PImage pimg = new PImage(50, 50);
		
		sprite.setSprite(pimg);
		
		assertEquals(pimg, sprite.getPImage());
	}

    @Test
    public void createSpriteByFilenameTest()
    {
        sprite = new Sprite("src/test/java/nl/han/ica/oopg/objects/box.png");
        assertNotNull(sprite.getPImage());
    }

    @Test
    public void createSpriteByFilenameAndIsGifTest()
    {
        sprite = new Sprite("src/test/java/nl/han/ica/oopg/objects/WaterTile.gif");
        assertNotNull(sprite.getPImage());
    }

    @Test
    public void setSpriteByFilenameTest()
    {
        sprite.setSprite("src/test/java/nl/han/ica/oopg/objects/box.png");
        assertNotNull(sprite.getPImage());
    }

    @Test
    public void resizeTest()
    {
        assertEquals(sprite.getHeight(), 20);
        assertEquals(sprite.getWidth(), 20);
        sprite.resize(10,10);
        assertEquals(sprite.getHeight(), 10);
        assertEquals(sprite.getWidth(), 10);
    }
}

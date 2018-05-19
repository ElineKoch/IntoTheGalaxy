package nl.han.ica.oopg.objects;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import processing.core.PImage;

public class AnimatedSpriteObjectTest {

	AnimatedSpriteObject obj;
	
	@Before
	public void setup()
	{
		PImage img = new PImage(25, 25);
		Sprite sprite = new Sprite(img);
		obj = new FakeAnimatedSpriteObject(sprite, 5);
	}
	
	@Test
	public void nextFrameShouldIncreaseTheFrameIndexToTwo()
	{
		obj.setCurrentFrameIndex(1);
		obj.nextFrame();
		
		assertEquals(2, obj.getCurrentFrameIndex());
	}
	
	@Test
	public void nextFrameShouldIncreaseTheFrameIndexToZero()
	{
		obj.setCurrentFrameIndex(4);
		obj.nextFrame();
		
		assertEquals(0, obj.getCurrentFrameIndex());
	}
	
	@Test
	public void getTotalFramesShouldReturnFive()
	{
		assertEquals(5, obj.getTotalFrames());
	}
	
	private class FakeAnimatedSpriteObject extends AnimatedSpriteObject {

		public FakeAnimatedSpriteObject(Sprite sprite, int totalFrames) {
			super(sprite, totalFrames);
		}

		@Override
		public void update() {

		}		
	}
}

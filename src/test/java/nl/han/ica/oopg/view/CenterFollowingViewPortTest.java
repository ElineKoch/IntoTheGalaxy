package nl.han.ica.oopg.view;

import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.pgraphicsstub.PGraphicsCreatorMock;
import nl.han.ica.oopg.pgraphicsstub.PGraphicsMock;

import org.junit.Before;
import org.junit.Test;

import processing.core.PGraphics;
import processing.core.PImage;
import static org.junit.Assert.assertEquals;

public class CenterFollowingViewPortTest {
	
    CenterFollowingViewport centerFollowingViewPort;
    FakeGameObject fakeGameObject;

    @Before
    public void setup()
    {
        fakeGameObject = new FakeGameObject(0, 0, 10, 10);
        centerFollowingViewPort = new CenterFollowingViewport(fakeGameObject, 25, 25);
    }

    @Test
    public void setXOffsetShouldSetCorrectly()
    {
    	centerFollowingViewPort.setXOffset(5);
    	
    	assertEquals(5, centerFollowingViewPort.getXOffset(), 0);
    }
    
    @Test
    public void setYOffsetShouldSetCorrectly()
    {
    	centerFollowingViewPort.setYOffset(5);
    	
    	assertEquals(5, centerFollowingViewPort.getYOffset(), 0);
    }
    
    @Test
    public void setOffsetShouldSetCorrectly()
    {
    	centerFollowingViewPort.setOffset(10, 20);
    	
    	assertEquals(10, centerFollowingViewPort.getXOffset(), 0);
    	assertEquals(20, centerFollowingViewPort.getYOffset(), 0);
    }
    
    @Test
    public void createImageShouldZoomInCorrectly()
    {
    	PGraphicsCreatorMock pGraphicsCreator = new PGraphicsCreatorMock();
    	PGraphicsMock g = pGraphicsCreator.createPGraphics(50, 50);
    	
    	PImage img = centerFollowingViewPort.createImage(g);
    	
    	assertEquals(25, img.width, 0);
    	assertEquals(25, img.height, 0);
    }
    
    @Test
    public void createImageShouldMoveViewportFurther()
    {
    	PGraphicsCreatorMock pGraphicsCreator = new PGraphicsCreatorMock();
    	PGraphicsMock g = pGraphicsCreator.createPGraphics(50, 50);
    	
    	PImage img = centerFollowingViewPort.createImage(g);
    
    	assertEquals(-7, centerFollowingViewPort.getX(), 0);
    	assertEquals(-7, centerFollowingViewPort.getY(), 0);

    	fakeGameObject.setX(10);
    	fakeGameObject.setY(10);
    	
    	img = centerFollowingViewPort.createImage(g);
        
    	assertEquals(3, centerFollowingViewPort.getX(), 0);
    	assertEquals(3, centerFollowingViewPort.getY(), 0);
    }
    
    @Test
    public void createImageShouldMoveViewportBack()
    {
    	PGraphicsCreatorMock pGraphicsCreator = new PGraphicsCreatorMock();
    	PGraphicsMock g = pGraphicsCreator.createPGraphics(50, 50);
    	
    	PImage img = centerFollowingViewPort.createImage(g);
    
    	assertEquals(-7, centerFollowingViewPort.getX(), 0);
    	assertEquals(-7, centerFollowingViewPort.getY(), 0);

    	fakeGameObject.setX(-10);
    	fakeGameObject.setY(-10);
    	
    	img = centerFollowingViewPort.createImage(g);
        
    	assertEquals(-17, centerFollowingViewPort.getX(), 0);
    	assertEquals(-17, centerFollowingViewPort.getY(), 0);
    }
    
    private class FakeGameObject extends GameObject
    {
        public FakeGameObject(float x, float y, float height, float width) {
            super(x, y, height, width);
        }

        @Override
        public void update() {

        }

        @Override
        public void draw(PGraphics g) {

        }
    }
}

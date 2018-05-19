package nl.han.ica.oopg.view;

import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.pgraphicsstub.PGraphicsCreatorMock;
import nl.han.ica.oopg.pgraphicsstub.PGraphicsMock;

import org.junit.Before;
import org.junit.Test;

import processing.core.PGraphics;
import processing.core.PImage;
import static org.junit.Assert.assertEquals;

public class EdgeFollowingViewPortTest {
	
    EdgeFollowingViewport edgeFollowingViewPort;
    FakeGameObject fakeGameObject;

    @Before
    public void setup()
    {
        fakeGameObject = new FakeGameObject(0, 0, 10, 10);
        edgeFollowingViewPort = new EdgeFollowingViewport(fakeGameObject, 25, 25, 0, 0);
    }

    @Test
    public void createImageShouldZoomCorrectly()
    {
    	PGraphicsCreatorMock pGraphicsCreator = new PGraphicsCreatorMock();
    	PGraphicsMock g = pGraphicsCreator.createPGraphics(50, 50);
    	
    	PImage img = edgeFollowingViewPort.createImage(g);
    	
    	assertEquals(25, img.width, 0);
    	assertEquals(25, img.height, 0);
    }
    
    @Test
    public void createImageShouldMoveViewportFurther()
    {
    	PGraphicsCreatorMock pGraphicsCreator = new PGraphicsCreatorMock();
    	PGraphicsMock g = pGraphicsCreator.createPGraphics(50, 50);
    	
    	PImage img = edgeFollowingViewPort.createImage(g);
    	
    	assertEquals(-7, edgeFollowingViewPort.getX(), 0);
    	assertEquals(-7, edgeFollowingViewPort.getY(), 0);
    	
    	fakeGameObject.setX(10);
    	fakeGameObject.setY(10);
    	
    	img = edgeFollowingViewPort.createImage(g);
    	
    	assertEquals(-5, edgeFollowingViewPort.getX(), 0);
    	assertEquals(-5, edgeFollowingViewPort.getY(), 0);
    }
    
    @Test
    public void createImageShouldMoveViewportBack()
    {
    	PGraphicsCreatorMock pGraphicsCreator = new PGraphicsCreatorMock();
    	PGraphicsMock g = pGraphicsCreator.createPGraphics(50, 50);
    	
    	PImage img = edgeFollowingViewPort.createImage(g);
    	
    	assertEquals(-7, edgeFollowingViewPort.getX(), 0);
    	assertEquals(-7, edgeFollowingViewPort.getY(), 0);
    	
    	fakeGameObject.setX(-10);
    	fakeGameObject.setY(-10);
    	
    	img = edgeFollowingViewPort.createImage(g);
    	
    	assertEquals(-10, edgeFollowingViewPort.getX(), 0);
    	assertEquals(-10, edgeFollowingViewPort.getY(), 0);
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

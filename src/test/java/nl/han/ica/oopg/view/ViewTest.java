package nl.han.ica.oopg.view;

import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.pgraphicsstub.PGraphicsCreatorMock;
import nl.han.ica.oopg.pgraphicsstub.PGraphicsMock;

import org.junit.Before;
import org.junit.Test;

import processing.core.PGraphics;
import processing.core.PImage;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ViewTest {
    private View view;

    @Before
    public void setup()
    {
        view = new View(200,200);
        view.setPGraphicsCreator(new PGraphicsCreatorMock());
    }

    @Test
    public void setSizeShouldSetCorrectly()
    {
        assertEquals(view.getWorldHeight(), 200);
        assertEquals(view.getWorldWidth(), 200);
        view.setWorldSize(100,100);
        assertEquals(view.getWorldHeight(), 100);
        assertEquals(view.getWorldWidth(), 100);
    }

    @Test
    public void setBackgroundRGB()
    {
        view.setBackground(255,255,255);
    }

    @Test
    public void setBackgroundImage()
    {
        view.setBackground(new PImage(10,10));
    }

    @Test
    public void setViewPort()
    {
        Viewport viewPort = new Viewport(0,0,0,0);
        view.setViewport(viewPort);
        
        assertEquals(view.getViewport(), viewPort);
    }

    @Test
    public void constructorOverloadWithViewport()
    {
        Viewport viewPort = new Viewport(0,0,0,0);
        view = new View(viewPort,0,0);
        
        assertEquals(view.getViewport(), viewPort);
    }
    
    @Test
    public void viewportShouldbeDrawn()
    {
    	PGraphicsCreatorMock pGraphicsCreator = new PGraphicsCreatorMock();
    	PGraphicsMock g = pGraphicsCreator.createPGraphics(50, 50);
    	
    	view.draw(g, null, new ArrayList<GameObject>(), new ArrayList<Dashboard>());
    	
    	assertEquals(1, g.getGameObjects().size()); // This object is the Viewport.
    }
    
    @Test
    public void objectsShouldbeDrawn()
    {
    	PGraphicsCreatorMock pGraphicsCreator = new PGraphicsCreatorMock();
    	PGraphicsMock g = pGraphicsCreator.createPGraphics(50, 50);
    	
    	ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
    	gameObjects.add(new FakeGameObject(0, 0, 5, 5));
    	
    	ArrayList<Dashboard>dashboards = new ArrayList<Dashboard>();
    	dashboards.add(new FakeDashboard(0, 0, 5, 5));
  
    	view.draw(g, null, gameObjects, dashboards);
        
        assertEquals(2, g.getGameObjects().size()); // Amount of dashboards + the viewport on the view.
    }
    
    private class FakeGameObject extends GameObject {
		public FakeGameObject(int x, int y, int width, int height) {
			super(x, y, height, width);	
		}

		@Override
		public void update() {
			move();
		}

		@Override
		public void draw(PGraphics g) {
			
		}
    }
    
    private class FakeDashboard extends Dashboard {

		public FakeDashboard(float x, float y, float width, float height) {
			super(x, y, width, height);
			
			setPGraphicsCreator(new PGraphicsCreatorMock());
		}
    }
}

package nl.han.ica.oopg.view;

import nl.han.ica.oopg.pgraphicsstub.PGraphicsCreatorMock;
import nl.han.ica.oopg.pgraphicsstub.PGraphicsMock;

import org.junit.Before;
import org.junit.Test;

import processing.core.PImage;
import static org.junit.Assert.assertEquals;

public class ViewPortTest {
    Viewport viewPort;

    @Before
    public void setup()
    {
        viewPort = new Viewport(0,0,0,0);
    }

    @Test
    public void gGetSetX()
    {
        assertEquals(viewPort.getX().intValue(),0);
        viewPort.setX(10);
        assertEquals(viewPort.getX().intValue(),10);
    }

    @Test
    public void getSetY()
    {
        assertEquals(viewPort.getY().intValue(),0);
        viewPort.setY(10);
        assertEquals(viewPort.getY().intValue(),10);
    }

    @Test
    public void getSetZoomWidth()
    {
        assertEquals(viewPort.getZoomWidth().intValue(),0);
        viewPort.setZoomWidth(10);
        assertEquals(viewPort.getZoomWidth().intValue(),10);
    }

    @Test
    public void getSetZoomHeight()
    {
        assertEquals(viewPort.getZoomHeight().intValue(),0);
        viewPort.setZoomHeight(10);
        assertEquals(viewPort.getZoomHeight().intValue(),10);
    }

    @Test
    public void setZoomSize()
    {
        viewPort.setZoomSize(10,10);
        assertEquals(viewPort.getZoomWidth().intValue(),10);
        assertEquals(viewPort.getZoomHeight().intValue(),10);
    }
    
    @Test
    public void vieportShouldZoomCorrectly()
    {
    	PGraphicsCreatorMock pGraphicsCreator = new PGraphicsCreatorMock();
    	PGraphicsMock g = pGraphicsCreator.createPGraphics(50, 50);
    	
    	viewPort.setX(5);
    	viewPort.setY(5);
    	viewPort.setZoomWidth(5);
    	viewPort.setZoomHeight(5);
    	
    	PImage img = viewPort.createImage(g);
    	
    	assertEquals(5, img.width);
    	assertEquals(5, img.height);
    }
}

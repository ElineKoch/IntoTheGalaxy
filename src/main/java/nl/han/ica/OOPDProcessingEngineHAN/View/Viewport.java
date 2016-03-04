package nl.han.ica.OOPDProcessingEngineHAN.View;

import processing.core.PGraphics;
import processing.core.PImage;

/**
 * The viewport controls what is displayed of the drawn world.
 */
public class Viewport {

    /**
     * The x index of the topLeft corner from where the viewPort will cut its image out of the world.
     */
    protected Integer x;
    
    /**
     * The y index of the topLeft corner from where the viewPort will cut its image out of the world.
     */
    protected Integer y;
    
    /**
     * The width in pixels which the viewPort will cut out of the world.
     */
    protected Integer zoomWidth;
    
    /**
     * The height in pixels which the viewPort will cut out of the world.
     */
    protected Integer zoomHeight;

    /**
     * Create a new Viewport object.
     * @param x The x index of the topLeft corner from where the viewPort will cut its image out of the world.
     * @param y The y index of the topLeft corner from where the viewPort will cut its image out of the world.
     * @param zoomWidth The width in pixels which the viewPort will cut out of the world.
     * @param zoomHeight The height in pixels which the viewPort will cut out of the world.
     */
    public Viewport(int x, int y, int zoomWidth, int zoomHeight) {
    	
        this.x = x;
        this.y = y;
        this.zoomWidth = zoomWidth;
        this.zoomHeight = zoomHeight;
    }

    /**
     * The method which is called to cut out an image of the drawn world.
     *
     * @param worldGraphics The canvas which represents the world from which the viewPort will cut its image out of.
     * @return The cut out image of the world.
     */
    public PImage createImage(PGraphics worldGraphics) {
        PImage viewPortImage = worldGraphics.get(x, y, zoomWidth, zoomHeight);
        return viewPortImage;
    }

    /**
     * Gets the x location of the Viewport.
     * @return The x index of the topLeft corner from where the viewPort will cut its image out of the world.
     */
    public Integer getX() {
        return x;
    }

    /**
     * Sets the x location of the Viewport.
     * @param x The x index of the topLeft corner from where the viewPort will cut its image out of the world.
     */
    public void setX(Integer x) {
        this.x = x;
    }

    /**
     * Gets the y location of the Viewport.
     * @return The y index of the topLeft corner from where the viewPort will cut its image out of the world.
     */
    public Integer getY() {
        return y;
    }

    /**
     * Sets the y location of the Viewport.
     * @param y The y index of the topLeft corner from where the viewPort will cut its image out of the world.
     */
    public void setY(Integer y) {
        this.y = y;
    }

    /**
     * Sets the zoom size of the Viewport.
     * @param zoomWidth The width in pixels which the viewPort will cut out of the world.
     * @param zoomHeight The height in pixels which the viewPort will cut out of the world.
     */
    public void setZoomSize(int zoomWidth, int zoomHeight) {
        setZoomWidth(zoomWidth);
        setZoomHeight(zoomHeight);
    }

    /**
     * Gets the zoom size of the Viewport.
     * @return The width in pixels which the viewPort will cut out of the world.
     */
    public Integer getZoomWidth() {
        return zoomWidth;
    }

    /**
     * Gets the zoom width of the Viewport.
     * @param width The width in pixels which the viewPort will cut out of the world.
     */
    public void setZoomWidth(int width) {
        this.zoomWidth = width;
    }

    /**
     * Gets the zoom height of the Viewport.
     * @return The height in pixels which the viewPort will cut out of the world.
     */
    public Integer getZoomHeight() {
        return zoomHeight;
    }

    /**
     * Sets the zoom height of the Viewport.
     * @param zoomHeight The height in pixels which the viewPort will cut out of the world.
     */
    public void setZoomHeight(int zoomHeight) {
        this.zoomHeight = zoomHeight;
    }
}

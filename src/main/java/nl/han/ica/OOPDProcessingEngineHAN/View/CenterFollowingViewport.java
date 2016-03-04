package nl.han.ica.OOPDProcessingEngineHAN.View;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;
import processing.core.PImage;

/**
 * This class allows following a GameObject in the Viewport.
 * The Viewport will move if the followed GameObject if the maximum tolerance in a certain direction from its center is reached.
 */
public class CenterFollowingViewport extends FollowingViewport {

    private Integer centerLocationX;
    private Integer centerLocationY;

    /**
     * The distance in pixels on the x axis where the center of the followed object is from the center of the Viewport.
     */
    protected double xOffset;
    
    /**
     * The distance in pixels on the y axis where the center of the followed object is from the center of the Viewport.
     */
    protected double yOffset;

    /**
     * Create a new CenterFollowingViewport
     * @param followedObject The GameObject the Viewport is following.
     * @param zoomWidth The width in pixels which the viewPort will cut out of the world.
     * @param zoomHeight The height in pixels which the viewPort will cut out of the world.
    */
    public CenterFollowingViewport(GameObject followedObject, int zoomWidth, int zoomHeight) {
        super(followedObject, zoomWidth, zoomHeight);

        setCenterLocationX((int)followedObject.getCenterX());
        setCenterLocationY((int)followedObject.getCenterY());
    }

    /**
     * Create a new CenterFollowingViewport
     * @param followedObject The GameObject the Viewport is following.
     * @param zoomWidth The width in pixels which the viewPort will cut out of the world.
     * @param zoomHeight The height in pixels which the viewPort will cut out of the world.
     * @param xOffset The distance in pixels on the x axis where the center of the followed object is from the center of the Viewport
     * @param yOffset The distance in pixels on the y axis where the center of the followed object is from the center of the Viewport
     */
    public CenterFollowingViewport(GameObject followedObject, int zoomWidth, int zoomHeight, double xOffset, double yOffset) {
        this(followedObject, zoomWidth, zoomHeight);
        setOffset(xOffset, yOffset);
    }

    /**
     * Sets the center x location.
     * @param x
     */
    private void setCenterLocationX(int x){
        centerLocationX = x;
        this.x = (int)((x - (zoomWidth / 2)) - xOffset);
    }

    /**
     * Sets the center y location.
     * @param y
     */
    private void setCenterLocationY(int y){
        centerLocationY = y;
        this.y = (int)((y - (zoomHeight / 2)) - yOffset);
    }

    /**
     * The method which is called to cut out an image of the drawn world.
     * @param worldGraphics The canvas which represents the world from which the viewPort will cut its image out of.
     * @return The cut out image of the world.
     */
    @Override
    public PImage createImage(PGraphics worldGraphics){
        setCenterLocationX((int)(centerLocationX + checkFollowedObjectXLocation()));
        setCenterLocationY((int)(centerLocationY + checkFollowedObjectYLocation()));

        return super.createImage(worldGraphics);
    }

    /**
     * Gets the center x location of the following object with corrected tolerance.
     * @return double
     */
    private double checkFollowedObjectXLocation(){
    	
        int newMaxX = centerLocationX + rightTolerance;
        int newMinX = centerLocationX - leftTolerance;

        if (followedObject.getCenterX() >= newMaxX)
            return (double)followedObject.getCenterX() - newMaxX;
        else if (followedObject.getCenterX() <= newMinX)
            return (double)followedObject.getCenterX() - newMinX;
            
        return 0D;
    }

    /**
     * Gets the center y location of the following object with corrected tolerance.
     * @return double
     */
    private double checkFollowedObjectYLocation(){
        int newMaxY = centerLocationY + topTolerance;
        int newMinY = centerLocationY - bottomTolerance;

        if (followedObject.getCenterY() >= newMaxY)
            return (double)followedObject.getCenterY() - newMaxY;
        else if (followedObject.getCenterY() <= newMinY)
            return (double)followedObject.getCenterY() - newMinY;
        
        return 0D;
    }

    /**
     * Sets the offset.
     * @param xOffset The distance in pixels on the x axis where the center of the followed object is from the center of the Viewport
     * @param yOffset The distance in pixels on the y axis where the center of the followed object is from the center of the Viewport
     */
    public void setOffset(double xOffset, double yOffset) {
        setXOffset(xOffset);
        setYOffset(yOffset);
    }

    /**
     * Gets the x offset.
     * @return The distance in pixels on the x axis where the center of the followed object is from the center of the Viewport
     */
    public double getXOffset() {
        return xOffset;
    }

    /**
     * Sets the x offset.
     * @param xOffset The distance in pixels on the x axis where the center of the followed object is from the center of the Viewport
     */
    public void setXOffset(double xOffset) {
        this.xOffset = xOffset;
    }

    /**
     * Gets the y offset.
     * @return The distance in pixels on the y axis where the center of the followed object is from the center of the Viewport
     */
    public double getYOffset() {
        return yOffset;
    }

    /**
     * Sets the y offset.
     * @param yOffset The distance in pixels on the y axis where the center of the followed object is from the center of the Viewport
     */
    public void setYOffset(double yOffset) {
        this.yOffset = yOffset;
    }
}

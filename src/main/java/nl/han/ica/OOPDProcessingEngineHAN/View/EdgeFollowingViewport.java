package nl.han.ica.OOPDProcessingEngineHAN.View;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;
import processing.core.PImage;

/**
 * This class allows following an GameObject in the Viewport.
 * The viewport will move if the followed GameObject reaches the side of the viewport minus the given tolerance.
 */
public class EdgeFollowingViewport extends FollowingViewport {

    /**
     * Create a new EdgeFollowingViewport.
     * @param followedObject The GameObject the Viewport is following.
     * @param zoomWidth The width in pixels which the viewPort will cut out of the world.
     * @param zoomHeight The height in pixels which the viewPort will cut out of the world.
     * @param xOffset The distance in pixels on the x axis where the center of the followed object is from the center of the Viewport
     * @param yOffset The distance in pixels on the y axis where the center of the followed object is from the center of the Viewport
     */
    public EdgeFollowingViewport(GameObject followedObject, int zoomWidth, int zoomHeight, double xOffset, double yOffset) {
        super(followedObject, zoomWidth, zoomHeight);

        x = (int)(followedObject.getCenterX() - ((zoomWidth / 2) + xOffset));
        y = (int)(followedObject.getCenterY() - ((zoomHeight / 2) + yOffset));
    }

    /**
     * Create a new EdgeFollowingViewport.
     * @param followedObject The GameObject the Viewport is following.
     * @param zoomWidth The width in pixels which the viewPort will cut out of the world.
     * @param zoomHeight The height in pixels which the viewPort will cut out of the world.
     */
    public EdgeFollowingViewport(GameObject followedObject, int zoomWidth, int zoomHeight) {
        this(followedObject, zoomWidth, zoomHeight, 0, 0);
    }

    /**
     * The method which is called to cut out an image of the drawn world.
     * @param worldGraphics The canvas which represents the world from which the viewPort will cut its image out of.
     * @return The cut out image of the world.
     */
    @Override
    public PImage createImage(PGraphics worldGraphics){
	
        x = (int)(x + checkFollowedObjectXLocation(zoomWidth));
        y = (int)(y + checkFollowedObjectYLocation(zoomHeight));

        return super.createImage(worldGraphics);
    }

    /**
     * Gets the x location of the following object with corrected tolerance.
     * @param zoomWidth
     * @return double
     */
    private double checkFollowedObjectXLocation(int zoomWidth) {
    	
        int newMaxX = x + zoomWidth - rightTolerance;
        int newMinX = x + leftTolerance;
        int followedObjectMaxX = (int)(followedObject.getX() + followedObject.getWidth());

        if (followedObjectMaxX >= newMaxX)
            return followedObjectMaxX - newMaxX;
        else if (followedObject.getX() <= newMinX)
            return followedObject.getX() - newMinX;
            
        return 0D;
    }

    /**
     * Gets the y location of the following object with corrected tolerance.
     * @param zoomHeight
     * @return double
     */
    private double checkFollowedObjectYLocation(int zoomHeight) {
    	
        int newMaxY = y + zoomHeight - bottomTolerance;
        int newMinY = y + topTolerance;
        int followedObjectMaxY = (int)(followedObject.getY() + followedObject.getHeight());

        if (followedObjectMaxY >= newMaxY)
            return followedObjectMaxY - newMaxY;
        else if (followedObject.getY() <= newMinY)
            return followedObject.getY() - newMinY;
           
        return 0D;
    }
}

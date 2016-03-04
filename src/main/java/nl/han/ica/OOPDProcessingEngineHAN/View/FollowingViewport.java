package nl.han.ica.OOPDProcessingEngineHAN.View;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;

/**
 * Extend this class if you want to make your own following viewport implementation.
 */
public abstract class FollowingViewport extends Viewport {
	
    /**
     * The GameObject the Viewport is following.
     */
    protected GameObject followedObject;

    /**
     * The tolerance of the top side the object has to move before the viewport moves with it.
     */
    protected int topTolerance;
    
    /**
     * The tolerance of the bottom side the object has to move before the viewport moves with it.
     */
    protected int bottomTolerance;
    
    /**
     * The tolerance of the left side the object has to move before the viewport moves with it.
     */
    protected int leftTolerance;
    
    /**
     * The tolerance of the right side the object has to move before the viewport moves with it.
     */
    protected int rightTolerance;

    /**
     * Create a new FollowingViewport.
     * @param followedObject The GameObject the Viewport is following.
     * @param zoomWidth The width in pixels which the viewPort will cut out of the world.
     * @param zoomHeight The height in pixels which the viewPort will cut out of the world.
     */
	public FollowingViewport(GameObject followedObject, int zoomWidth, int zoomHeight) {
		super(-1, -1, zoomWidth, zoomHeight);

        setFollowedObject(followedObject);
	}

    /**
     * Gets the following GameObject.
     * @return GameObject the Viewport is following.
     */
    public GameObject getFollowedObject() {
        return followedObject;
    }

    /**
     * Sets the following GameObject.
     * @param followedObject The GameObject the Viewport is following.
     */
    public void setFollowedObject(GameObject followedObject) {
        this.followedObject = followedObject;
    }

    /**
     * Sets the tolerance of the edge to move the Viewport.
     * @param top The tolerance op the top side the object has to move before the Viewport moves with it.
     * @param bottom The tolerance op the bottom side the object has to move before the Viewport moves with it.
     * @param left The tolerance op the left side the object has to move before the Viewport moves with it.
     * @param right The tolerance op the right side the object has to move before the Viewport moves with it.
     */
    public void setTolerance(int top, int bottom, int left, int right) {
        setTopTolerance(top);
        setBottomTolerance(bottom);
        setLeftTolerance(left);
        setRightTolerance(right);
    }

    /**
     * Gets the top tolerance.
     * @return The tolerance op the top side the object has to move before the viewport moves with it.
     */
    public int getTopTolerance() {
        return topTolerance;
    }

    /**
     * Sets the top tolerance.
     * @param topTolerance The tolerance op the top side the object has to move before the viewport moves with it.
     */
    public void setTopTolerance(int topTolerance) {
        this.topTolerance = topTolerance;
    }

    /**
     * Gets the bottom tolerance.
     * @return The tolerance op the bottom side the object has to move before the viewport moves with it.
     */
    public int getBottomTolerance() {
        return bottomTolerance;
    }

    /**
     * Sets the bottom tolerance.
     * @param bottomTolerance The tolerance op the bottom side the object has to move before the viewport moves with it.
     */
    public void setBottomTolerance(int bottomTolerance) {
        this.bottomTolerance = bottomTolerance;
    }

    /**
     * Gets the left tolerance.
     * @return The tolerance op the Left side the object has to move before the viewport moves with it.
     */
    public int getLeftTolerance() {
        return leftTolerance;
    }

    /**
     * Sets the left tolerance.
     * @param leftTolerance The tolerance op the left side the object has to move before the viewport moves with it.
     */
    public void setLeftTolerance(int leftTolerance) {
        this.leftTolerance = leftTolerance;
    }

    /**
     * Gets the right tolerance.
     * @return The tolerance op the left side the object has to move before the viewport moves with it.
     */
    public int getRightTolerance() {
        return rightTolerance;
    }

    /**
     * Sets the right tolerance.
     * @param rightTolerance The tolerance op the left side the object has to move before the viewport moves with it.
     */
    public void setRightTolerance(int rightTolerance) {
        this.rightTolerance = rightTolerance;
    }
}

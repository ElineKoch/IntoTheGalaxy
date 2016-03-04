package nl.han.ica.OOPDProcessingEngineHAN.Objects;

import nl.han.ica.OOPDProcessingEngineHAN.UserInput.IKeyInput;
import nl.han.ica.OOPDProcessingEngineHAN.UserInput.IMouseInput;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PVector;

/**
 * Make your object, extend this class to use your object as a drawable object.
 */
public abstract class GameObject implements IKeyInput, IMouseInput, PConstants {

    protected float x;
    protected float y;
    protected float width;
    protected float height;
    protected float z;
    protected float prevX;
    protected float prevY;

    private PVector velocity = new PVector(0,0);
    private float speed;
    private float friction;
    private float direction;

    private float gravity = 0;

    private boolean isVisible = true;

    public GameObject()
    {
    }
    
    public GameObject(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Implement this method to update the objects that need to be drawn.
     */
    public abstract void update();

    /**
     * Implement this method to actually draw the GameObject.
     * @param g PGraphics object will be given by the GameEngine.
     */
    public abstract void draw(PGraphics g);

    /**
     * Moves this object and sets the previous location. This function only
     * works when this object has a speed.
     */
    public void move() {
        if (speed > 0) {

            prevX = x;
            prevY = y;

            velocity.x = calculateFriction(velocity.x);
            velocity.y = calculateFriction(velocity.y);

            x += velocity.x;
            y += velocity.y;
        }
    }

    /**
     * Calculates the changes in speed due to friction.
     *
     * @param speed
     *            the current speed
     * @return the new speed.
     */
    private float calculateFriction(float speed) {
        return (1 - friction) * speed;
    }

    /**
     * calculate speed based on x and y speed.
     * @param xSpeed
     * @param ySpeed
     * @return speed
     */
    private float calculateSpeed(float xSpeed, float ySpeed) {
        velocity.x = xSpeed;
        velocity.y = ySpeed;

        return velocity.mag();
    }

    /**
     * Calculates the direction this object travels to based on the x speed and
     * the y speed.
     *
     * @param xSpeed
     *            the x speed.
     * @param ySpeed
     *            the y speed.
     * @return the direction in degrees.
     */
    public float calculateDirection(float xSpeed, float ySpeed) {
        return calculatedAngle(xSpeed, ySpeed);
    }

    /**
     * Calculate new x and y speed based on the direction
     */
    private void calculateMovement(){
        //Calculate new X and Y speed
        double radianDirection = Math.toRadians(direction) - (0.5 * Math.PI);

        float ySpeed = (float)Math.sin(radianDirection) * speed;
        float xSpeed = (float)Math.cos(radianDirection) * speed;

        velocity.y = ySpeed;
        velocity.x = xSpeed;
    }

    /**
     * Set the Xspeed of a MovableGameObject.
     * @param xSpeed
     */
    public void setxSpeed(float xSpeed)
    {
        speed = calculateSpeed(xSpeed, velocity.y);
        direction = calculateDirection(xSpeed, velocity.y);

        this.velocity.x = xSpeed;
    }

    /**
     * Set the Yspeed of a MovableGameObject..
     * @param ySpeed
     */
    public void setySpeed(float ySpeed)
    {
        speed = calculateSpeed(velocity.x, ySpeed);
        direction = calculateDirection(velocity.x, ySpeed);

        this.velocity.y = ySpeed;
    }

    /**
     * Set the speed of a MovableGameObject.
     * @param speed
     */
    public void setSpeed(float speed)
    {
        this.speed = speed;
        calculateMovement();
    }

    /**
     *
     * @return speed
     */
    public float getSpeed()
    {
        return speed;
    }

    /**
     *
     * @return xSpeed
     */
    public float getxSpeed()
    {
        return velocity.x;
    }

    /**
     *
     * @return ySpeed
     */
    public float getySpeed()
    {
        return velocity.y;
    }

    public void setGravity(float value)
    {
        gravity = value;
    }

    public float getGravity()
    {
        return gravity;
    }

    /**
     * Sets the direction of this object in degrees
     * Direction 0 points up, directions go clockwise, so 90 is right, etc.
     *
     * @param inputDirection
     *            the direction in degrees.
     */
    public void setDirection(float inputDirection) {
        //Make sure direction is between 0 and 360
        float moduloDirection = inputDirection % 360;
        float newDirection = (moduloDirection + 360) % 360;

        //Set new direction
        direction = newDirection;

        calculateMovement();
    }

    /**
     * Gets the direction of the objects movement in degrees.
     * Direction 0 points up, directions go clockwise, so 90 is right, etc.
     *
     * @return the direction(angle) in degrees.
     */
    public float getDirection() {
        return direction;
    }

    /**
     * Sets both the direction and the speed of this object.
     * Direction 0 points up, directions go clockwise, so 90 is right, etc.
     *
     * @param direction
     *            the direction you want to set in the degrees
     * @param speed
     *            the speed you want to set.
     */
    public void setDirectionSpeed(float direction, float speed) {
        this.speed = speed;
        setDirection(direction);
    }

    /**
     * Sets the friction of this object, that is the amount of speed reduction.
     * <br />
     * The decrease in speed is measured as a fraction, if you want a 5% decrease
     * in speed per cycle of the game loop, use 0.05.
     *
     * @param friction
     * 			the fraction of decrease in speed per cycle of the game loop.
     * 			Must be a number between 0 and 1
     */
    public void setFriction(float friction) throws IllegalArgumentException
    {
        if ( friction >= 0 && friction < 1 ) {
            this.friction = friction;
        } else {
            throw new IllegalArgumentException("Input value must be between 0 and 1");
        }
    }

    /**
     * Gets the friction of this object
     *
     * @return the friction.
     */
    public float getFriction() {
        return friction;
    }

    /**
     * Gets the x position of the GameObject.
     * @return x
     */
    public float getX() {
        return x;
    }

    /**
     * Sets the x position of the GameObject.
     * @param value
     */
    public void setX(float value) {
        prevX = x;
        x = value;
    }

    /**
     * Gets the y position of the GameObject.
     * @return y
     */
    public float getY() {
        return y;
    }

    /**
     * Sets the y position of the GameObject.
     * @param value
     */
    public void setY(float value) {
        prevY = y;
        y = value;
    }

    /**
     * Gets the width of the GameObject.
     * @return width
     */
    public float getWidth() {
        return width;
    }

    /**
     * Sets the width of the GameObject.
     * @param value
     */
    public void setWidth(int value) {
        this.width = value;
    }

    /**
     * Gets the height of the GameObject.
     * @return height
     */
    public float getHeight() {
        return height;
    }

    /**
     * Sets the height of the GameObject.
     * @param value
     */
    public void setHeight(int value) {
        this.height = value;
    }

    /**
     * Gets the z position of the GameObject.
     * @return z
     */
    public float getZ() {
        return z;
    }

    /**
     * Sets the z position of the GameObject.
     * @param value
     */
    public void setZ(float value) {
        this.z = value;
    }

    /**
     * Returns whether the GameObject is visible or not.
     * @return isVisible
     */
    public boolean isVisible() {
        return isVisible;
    }
    
    /**
     * Used to set or unset the visibility of the GameObject.
     * @param isVisible
     */
    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    /**
     * Gets the center x position of the GameObject.
     * @return centerX
     */
    public float getCenterX() {
        return x + (width / 2);
    }

    /**
     * Gets the center y position of the GameObject.
     * @return centerY
     */
    public float getCenterY() {
        return y + (height / 2);
    }

    /**
     * Use this function to get the angle between you and another object. For
	 * example: You can use this function to check if you're approaching another
	 * object from the left or right.
	 * The angle is calculated with the center x and center y of both objects.
     * @param object GameObject of which you would like to know whether its position lays from your left or from your right.
     * @return You'll be returned an angle between 0 and 360 degrees. 0 degrees means the object is right above you, 90 degrees means the object is at your right side, etc.
     */
    public float getAngleFrom(GameObject object){
        float dx = object.getCenterX() - getCenterX();
        float dy = object.getCenterY() - getCenterY();

        return calculatedAngle(dx, dy);
    }

    public float getAngleFrom(int pointX, int pointY){
        float dx = pointX - getCenterX();
        float dy = pointY - getCenterY();

        return calculatedAngle(dx, dy);
    }

    private float calculatedAngle(float dx, float dy) {
        if(dx >= 0 || dy >= 0)
            return (float)Math.toDegrees(Math.atan2(dy, dx)) + 90;
        else
            return (float)Math.toDegrees(Math.atan2(dy, dx)) + 450;
    }

    /**
     * Use this function to get the distance between you and another object.
     * First, this method calculates the angle from one object to another object.
     * Second, this method checks if two objects intersect or not.
     * Third, if there is no intersect, the angle between both objects determines what corners of two objects should be
     * used to calculate the shortest distance between both objects.
     * If two objects are in line (horizontally or vertically), the shortest distance between both objects is the offset in either x- or y- values of those corners.
     * If not in line, Pythagoras is used to calculate the distance between two objects (better said; between two corners).
     * @param object of which you would like to know the distance.
     * @return distance
     */
    public double getDistanceFrom(GameObject object) {

        // Get angle to other object.
        double angle = this.getAngleFrom(object);

        // X-coords of main object
        float mainObjectTopLeftX = this.getX();
        float mainObjectTopRightX = this.getX() + this.getWidth();
        float mainObjectBottomRightX = mainObjectTopRightX;
        float mainObjectBottomLeftX = mainObjectTopLeftX;

        // Y-coords of main object
        float mainObjectTopLeftY = this.getY();
        float mainObjectTopRightY = mainObjectTopLeftY;
        float mainObjectBottomRightY = this.getY() + this.getHeight();
        float mainObjectBottomLeftY = mainObjectBottomRightY;

        // X-coords of other object
        float otherObjectTopLeftX = object.getX();
        float otherObjectTopRightX = object.getX() + object.getWidth();
        float otherObjectBottomRightX = otherObjectTopRightX;
        float otherObjectBottomLeftX = otherObjectTopLeftX;

        // Y-coords of other object
        float otherObjectTopLeftY = object.getY();
        float otherObjectTopRightY = otherObjectTopLeftY;
        float otherObjectBottomLeftY = object.getY() + object.getHeight();
        float otherObjectBottomRightY = otherObjectBottomLeftY;

        // Initialise distances
        double distance = 0;

        // If any of other objects corners is inside the main object.
        if(objectsIntersected(mainObjectTopLeftX, mainObjectTopRightX, mainObjectBottomRightX, mainObjectBottomLeftX,
                mainObjectTopLeftY, mainObjectTopRightY, mainObjectBottomRightY, mainObjectBottomLeftY,
                otherObjectTopLeftX, otherObjectTopRightX, otherObjectBottomRightX, otherObjectBottomLeftX,
                otherObjectTopLeftY, otherObjectTopRightY, otherObjectBottomLeftY, otherObjectBottomRightY))
        {
            distance = 0;
        }

        else if(otherObjectAtTopRightOfMainObject(angle)){
            distance = calculateDistanceTopRightAngle(mainObjectTopRightX, mainObjectTopRightY, otherObjectBottomLeftX, otherObjectBottomLeftY);
        }

        else if(otherObjectAtBottomRightOfMainObject(angle)){
            distance = calculateDistanceBottomRightAngle(mainObjectBottomRightX, mainObjectBottomRightY, otherObjectTopLeftX, otherObjectTopLeftY);
        }

        else if(otherObjectAtBottomLeftOfMainObject(angle)){
            distance = calculateDistanceBottomLeftAngle(mainObjectBottomLeftX, mainObjectBottomLeftY, otherObjectTopRightX, otherObjectTopRightY);
        }

        else if(otherObjectAtTopLeftOfMainObject(angle)){
            distance = calculateDistanceTopLeftAngle(mainObjectTopLeftX, mainObjectTopLeftY, otherObjectBottomRightX, otherObjectBottomRightY);
        }
        return distance;
    }

    private boolean otherObjectAtTopLeftOfMainObject(double angle) {
        return angle >= 270 && angle < 360;
    }

    private boolean otherObjectAtBottomLeftOfMainObject(double angle) {
        return angle >= 180 && angle < 270;
    }

    private boolean otherObjectAtBottomRightOfMainObject(double angle) {
        return angle >= 90 && angle < 180;
    }

    private boolean otherObjectAtTopRightOfMainObject(double angle) {
        return angle >= 0 && angle < 90;
    }

    private double calculateDistanceTopLeftAngle(float mainObjectTopLeftX, float mainObjectTopLeftY, float otherObjectBottomRightX, float otherObjectBottomRightY) {

        // If bottom right corner of other object is below the top left corner of the main object.
        if(otherObjectBottomRightY > mainObjectTopLeftY){
            // Then the shortest distance between both objects is the offset between both x-values of those corners.
            return calculateDistance(mainObjectTopLeftX, otherObjectBottomRightX);
        }

        // If bottom right corner of other object is at right side of the top left corner of the main object.
        else if(otherObjectBottomRightX > mainObjectTopLeftX){
            // Then the shortest distance between both objects is the offset between both y-values of those corners.
            return calculateDistance(otherObjectBottomRightY, mainObjectTopLeftY);
        }

        // In every other case, pythagoras is needed to calculate the distance.
        else{
            float deltaX = calculateDistance(otherObjectBottomRightX, mainObjectTopLeftX);
            float deltaY = calculateDistance(otherObjectBottomRightY, mainObjectTopLeftY);
            return calculateDistanceWithPythagoras(deltaX, deltaY);
        }
    }

    private double calculateDistanceBottomLeftAngle(float mainObjectBottomLeftX, float mainObjectBottomLeftY, float otherObjectTopRightX, float otherObjectTopRightY) {

        // If top right corner of other object is above left bottom corner of main object
        if(otherObjectTopRightY <= mainObjectBottomLeftY){
            // Then the shortest distance between both objects is the offset between both x-values of those corners.
            return calculateDistance(otherObjectTopRightX, mainObjectBottomLeftX);
        }

        // If top right corner of other object is at the right side of the bottom left corner of the main object.
        else if(otherObjectTopRightX > mainObjectBottomLeftX){
            // Then the shortest distance between both objects is the offset between both y-values of those corners.
            return calculateDistance(mainObjectBottomLeftY, otherObjectTopRightY);
        }

        // In every other case, pythagoras is needed to calculate the distance.
        else{
            float deltaX = calculateDistance(otherObjectTopRightX, mainObjectBottomLeftX);
            float deltaY = calculateDistance(otherObjectTopRightY, mainObjectBottomLeftY);
            return calculateDistanceWithPythagoras(deltaX, deltaY);
        }
    }

    private double calculateDistanceBottomRightAngle(float mainObjectBottomRightX, float mainObjectBottomRightY, float otherObjectTopLeftX, float otherObjectTopLeftY) {

        // If top left corner of other object is above right bottom corner of main object
        if(otherObjectTopLeftY <= mainObjectBottomRightY){
            // Then the shortest distance between both objects is the offset between both x-values of those corners
            return calculateDistance(mainObjectBottomRightX, otherObjectTopLeftX);
        }

        // If top left corner of other object is at the left side of the bottom right corner of the main object.
        else if(otherObjectTopLeftX < mainObjectBottomRightX){
            // The the shortest distance between both objects is the offset between both y-values of those corners.
            return calculateDistance(mainObjectBottomRightY, otherObjectTopLeftY);
        }

        // In every other case, pythagoras is needed to calculate the distance.
        else{
            float deltaX = calculateDistance(mainObjectBottomRightX, otherObjectTopLeftX);
            float deltaY = calculateDistance(mainObjectBottomRightY, otherObjectTopLeftY);
            return calculateDistanceWithPythagoras(deltaX, deltaY);
        }
    }

    private double calculateDistanceTopRightAngle(float mainObjectTopRightX, float mainObjectTopRightY, float otherObjectBottomLeftX, float otherObjectBottomLeftY) {

        // If bottom left corner of other object is below the top right corner of the main object
        if(otherObjectBottomLeftY >= mainObjectTopRightY) {
            // Then the shortest distance between both objects is the offset between both x-values of those corners.
            return calculateDistance(mainObjectTopRightX, otherObjectBottomLeftX);
        }

        // If bottom left corner of the other object is at the left side of the top right corner of the main object.
        else if(otherObjectBottomLeftX <= mainObjectTopRightX){
            // Then the shortest distance between both objects is the offset between both y-values of those corners.
            return calculateDistance(otherObjectBottomLeftY, mainObjectTopRightY);
        }

        // In every other case, pythagoras is needed to calculate the distance.
        else{
            float deltaX = calculateDistance(mainObjectTopRightX, otherObjectBottomLeftX);
            float deltaY = calculateDistance(mainObjectTopRightY, otherObjectBottomLeftY);
            return calculateDistanceWithPythagoras(deltaX, deltaY);
        }
    }

    private double calculateDistanceWithPythagoras(float deltaX, float deltaY) {
        return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
    }

    private float calculateDistance(float mainObjectTopRightX, float otherObjectBottomLeftX) {
        return Math.abs(otherObjectBottomLeftX - mainObjectTopRightX);
    }

    private boolean objectsIntersected(float mainObjectTopLeftX, float mainObjectTopRightX, float mainObjectBottomRightX, float mainObjectBottomLeftX,
                                       float mainObjectTopLeftY, float mainObjectTopRightY, float mainObjectBottomRightY, float mainObjectBottomLeftY,
                                       float otherObjectTopLeftX, float otherObjectTopRightX, float otherObjectBottomRightX, float otherObjectBottomLeftX,
                                       float otherObjectTopLeftY, float otherObjectTopRightY, float otherObjectBottomLeftY, float otherObjectBottomRightY) {
        return
        (otherObjectTopLeftX <= mainObjectTopRightX && otherObjectTopLeftX >= mainObjectTopLeftX && otherObjectTopLeftY <= mainObjectBottomLeftY && otherObjectTopLeftY >= mainObjectTopLeftY)
        // or top right corner of other object is inside main object.
        || (otherObjectTopRightX <= mainObjectTopRightX && otherObjectTopRightX >= mainObjectTopLeftX && otherObjectTopRightY <= mainObjectBottomRightY && otherObjectTopRightY >= mainObjectTopRightY)
        // or bottom left corner of other object is inside main object.
        || (otherObjectBottomLeftX <= mainObjectTopRightX && otherObjectBottomLeftX >= mainObjectTopLeftX && otherObjectBottomLeftY <= mainObjectBottomLeftY && otherObjectBottomLeftY >= mainObjectTopLeftY)
        // or bottom right corner of other object is inside main object.
        || (otherObjectBottomRightX <= mainObjectBottomRightX && otherObjectBottomRightX >= mainObjectBottomLeftX && otherObjectBottomRightY <= mainObjectBottomRightY && otherObjectBottomRightY >= mainObjectTopRightY);
    }

    /**
     * Gets the previous movement Y position.
     * @return prevX
     */
    public float getPrevX() {
        return prevX;
    }

    /**
     * Gets the previous movement Y position.
     * @return prevY
     */
    public float getPrevY() {
        return prevY;
    }

    /*
     * (non-Javadoc)
     * @see nl.han.ica.OOPDProcessingEngineHAN.UserInput.IKeyInput#keyPressed(int, char)
     */
    public void keyPressed(int keyCode, char key) {
        // This method can be overridden by the user.
    }

    /*
     * (non-Javadoc)
     * @see nl.han.ica.OOPDProcessingEngineHAN.UserInput.IKeyInput#keyReleased(int, char)
     */
    public void keyReleased(int keyCode, char key) {
        // This method can be overridden by the user.
    }

    /*
     * (non-Javadoc)
     * @see nl.han.ica.OOPDProcessingEngineHAN.UserInput.IMouseInput#mousePressed(int, int)
     */
    public void mousePressed(int x, int y, int button) {
        // This method can be overridden by the user.
    }

    /*
     * (non-Javadoc)
     * @see nl.han.ica.OOPDProcessingEngineHAN.UserInput.IMouseInput#mouseReleased(int, int)
     */
    public void mouseReleased(int x, int y, int button) {
        // This method can be overridden by the user.
    }

    /*
     * (non-Javadoc)
     * @see nl.han.ica.OOPDProcessingEngineHAN.UserInput.IMouseInput#mouseClicked(int, int)
     */
    public void mouseClicked(int x, int y, int button) {
        // This method can be overridden by the user.
    }

    /*
     * (non-Javadoc)
     * @see nl.han.ica.OOPDProcessingEngineHAN.UserInput.IMouseInput#mouseMoved(int, int)
     */
    public void mouseMoved(int x, int y) {
        // This method can be overridden by the user.
    }

    /*
     * (non-Javadoc)
     * @see nl.han.ica.OOPDProcessingEngineHAN.UserInput.IMouseInput#mouseDragged(int, int)
     */
    public void mouseDragged(int x, int y, int button) {
        // This method can be overridden by the user.
    }
    
    /*
     * (non-Javadoc)
     * @see nl.han.ica.OOPDProcessingEngineHAN.UserInput.IMouseInput#mouseWheel(int)
     */
    public void mouseWheel(int direction) {
    	//  This method can be overridden by the user.
    }
}

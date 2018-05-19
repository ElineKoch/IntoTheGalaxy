package nl.han.ica.oopg.objects;

import nl.han.ica.oopg.engine.GameEngine;

import org.junit.Before;
import org.junit.Test;

import processing.core.PGraphics;
import static org.junit.Assert.assertEquals;

public class GameObjectTest {
    
	private FakeGameObject gameObject;
    private GameEngine gameEngine;
	
	@Before
    public void setup()
    {
		this.gameObject = new FakeGameObject(0, 0, 20, 20);
        this.gameEngine = new GameEngineMock();
    }

    @Test
    public void setXShouldChangeTheXPosition()
    {
        gameObject.setX(5);
        
        assertEquals(5, gameObject.getX(), 0);
    }
    
    @Test
    public void setYShouldChangeTheYPosition()
    {
        gameObject.setY(5);
        
        assertEquals(5, gameObject.getY(), 0);
    }
    
    @Test
    public void setWidthShouldChangeTheWidth()
    {
        gameObject.setWidth(5);
        
        assertEquals(5, gameObject.getWidth(), 0);
    }
    
    @Test
    public void setHeightShouldChangeTheHeight()
    {
        gameObject.setHeight(5);
        
        assertEquals(5, gameObject.getHeight(), 0);
    }
    
    @Test
    public void setZShouldChangeTheZPosition()
    {
        gameObject.setZ(5F);
        
        assertEquals(5F, gameObject.getZ(), 0);
    } 
    
    @Test
    public void setVisibleShouldChangeTheVisibleValue()
    {
        gameObject.setVisible(true);
        
        assertEquals(true, gameObject.isVisible());
    }   
    
    @Test
    public void getCenterXShouldReturnACorrectValue()
    {
    	gameObject.setX(0);
    	gameObject.setY(0);
    	gameObject.setWidth(25);
    	gameObject.setHeight(0);

        double value = gameObject.getCenterX();
        
        assertEquals(12.50, value, 0);
    }
    
    @Test
    public void getCenterYShouldReturnACorrectValue()
    {
    	gameObject.setX(0);
    	gameObject.setY(0);
    	gameObject.setWidth(0);
    	gameObject.setHeight(25);

        double value = gameObject.getCenterY();
        
        assertEquals(12.50, value, 0);
    }
    
    @Test
    public void getAngleFromGameObjectShouldReturn90Degree()
    {
    	GameObject anotherGameObject = new FakeGameObject(100, 0, 20, 20);

        double value = gameObject.getAngleFrom(anotherGameObject);
    	
    	assertEquals(90, value, 0);
    }
    
    @Test
    public void getAngleFromGameObjectShouldReturn180Degree()
    {
    	GameObject anotherGameObject = new FakeGameObject(0, 100, 20, 20);

        double value = gameObject.getAngleFrom(anotherGameObject);
    	
    	assertEquals(180, value, 0);
    }
    
    @Test
    public void getAngleFromGameObjectShouldReturn315Degree()
    {
    	GameObject anotherGameObject = new FakeGameObject(100, 100, 20, 20);
    	
    	double value = anotherGameObject.getAngleFrom(gameObject);
    	
    	assertEquals(315, value, 0);
    }

    // Tests between 0 and 90 degrees
    @Test
    public void GetDistanceToOtherObjectThatIsAboveAndAtRightSideOfMainObject()
    {
        GameObject anotherGameObject = new FakeGameObject(50, -50, 20, 20);
        double value = gameObject.getDistanceFrom(anotherGameObject);
        assertEquals(Math.sqrt(1800), value, 1);
    }

    @Test
    public void GetDistanceToOtherObjectThatIsAtRightAndAboveMainObject()
    {
        GameObject anotherGameObject = new FakeGameObject(50, -10, 20, 20);
        double value = gameObject.getDistanceFrom(anotherGameObject);
        assertEquals(30, value, 0.01);
    }

    @Test
    public void GetDistanceToOtherObjectOver0DegreesThatNeedsPythagoras()
    {
        GameObject anotherGameObject = new FakeGameObject(10, -70, 20, 20);
        double value = gameObject.getDistanceFrom(anotherGameObject);
        assertEquals(50, value, 0.01);
    }

    // Tests between 90 and 180 degrees
    @Test
    public void GetDistanceToOtherObjectThatIsBelowAndAtRightOfMainObject()
    {
        GameObject anotherGameObject = new FakeGameObject(50, 10, 20, 20);
        double value = gameObject.getDistanceFrom(anotherGameObject);
        assertEquals(30, value, 0.01);
    }

    @Test
    public void GetDistanceToOtherObjectThatIsAtRightAndBelowMainObject()
    {
        GameObject anotherGameObject = new FakeGameObject(10, 60, 20, 20);
        double value = gameObject.getDistanceFrom(anotherGameObject);
        assertEquals(40, value, 0.01);
    }

    @Test
    public void GetDistanceToOtherObjectOver90DegreesThatNeedsPythagoras()
    {
        GameObject anotherGameObject = new FakeGameObject(40, 40, 20, 20);
        double value = gameObject.getDistanceFrom(anotherGameObject);
        assertEquals(Math.sqrt(800), value, 0.01);
    }

    // Tests between 180 and 270 degrees
    @Test
    public void GetDistanceToOtherObjectThatIsAtLeftAndBelowMainObject()
    {
        GameObject anotherGameObject = new FakeGameObject(-30, 10, 20, 20);
        double value = gameObject.getDistanceFrom(anotherGameObject);
        assertEquals(10, value, 0.01);
    }

    @Test
    public void GetDistanceToOtherObjectThatIsBelowAndAtLeftOfMainObject()
    {
        GameObject anotherGameObject = new FakeGameObject(-10, 40, 20, 20);
        double value = gameObject.getDistanceFrom(anotherGameObject);
        assertEquals(20, value, 0.01);
    }

    @Test
    public void GetDistanceToOtherObjectOver180DegreesThatNeedsPythagoras()
    {
        GameObject anotherGameObject = new FakeGameObject(-40, 40, 20, 20);
        double value = gameObject.getDistanceFrom(anotherGameObject);
        assertEquals(Math.sqrt(800), value, 0.01);
    }

    // Tests between 270 and 360 degrees
    @Test
    public void GetDistanceToOtherObjectThatIsAtLeftAndAboveMainObject()
    {
        GameObject anotherGameObject = new FakeGameObject(-40, -10, 20, 20);
        double value = gameObject.getDistanceFrom(anotherGameObject);
        assertEquals(20, value, 0.01);
    }

    @Test
    public void GetDistanceToOtherObjectThatIsAboveAndAtLeftOfMainObject()
    {
        GameObject anotherGameObject = new FakeGameObject(-10, -40, 20, 20);
        double value = gameObject.getDistanceFrom(anotherGameObject);
        assertEquals(20, value, 0.01);
    }

    @Test
    public void GetDistanceToOtherObjectOver270DegreesThatNeedsPythagoras()
    {
        GameObject anotherGameObject = new FakeGameObject(-40, -40, 20, 20);
        double value = gameObject.getDistanceFrom(anotherGameObject);
        assertEquals(Math.sqrt(800), value, 0.01);
    }

    @Test
    public void GetDistanceIfOtherObjectsTopLeftIsInsideMainObject()
    {
        GameObject anotherGameObject = new FakeGameObject(10, 10, 20, 20);
        double value = gameObject.getDistanceFrom(anotherGameObject);
        assertEquals(0, value, 0.01);
    }

    @Test
    public void GetDistanceIfOtherObjectsTopRightIsInsideMainObject()
    {
        GameObject anotherGameObject = new FakeGameObject(-10, 10, 20, 20);
        double value = gameObject.getDistanceFrom(anotherGameObject);
        assertEquals(0, value, 0.01);
    }

    @Test
    public void GetDistanceIfOtherObjectsBottomLeftIsInsideMainObject()
    {
        GameObject anotherGameObject = new FakeGameObject(10, -10, 20, 20);
        double value = gameObject.getDistanceFrom(anotherGameObject);
        assertEquals(0, value, 0.01);
    }

    @Test
    public void GetDistanceIfOtherObjectsBottomRightIsInsideMainObject()
    {
        GameObject anotherGameObject = new FakeGameObject(-10, -10, 20, 20);
        double value = gameObject.getDistanceFrom(anotherGameObject);
        assertEquals(0, value, 0.01);
    }

    @Test
    public void testSetSpeed()
    {
        gameObject.setSpeed(10);
        assertEquals(10, gameObject.getSpeed(),0.1);
    }

    @Test
    public void testNegativeSetSpeed()
    {
        gameObject.setSpeed(-10);
        assertEquals(-10, gameObject.getSpeed(),0.1);
    }

    @Test
    public void testUnsetGetSpeed()
    {
        assertEquals(0,gameObject.getSpeed(), 0);
    }

    @Test
    public void testSetXSpeed()
    {
        gameObject.setxSpeed(10);
        assertEquals(10, gameObject.getxSpeed(),0.1);
    }

    @Test
    public void testNegativeSetXSpeed()
    {
        gameObject.setxSpeed(-10);
        assertEquals(-10, gameObject.getxSpeed(),0.1);
    }

    @Test
    public void testUnsetXGetSpeed()
    {
        assertEquals(0,gameObject.getxSpeed(), 0);
    }

    @Test
    public void testSetYSpeed()
    {
        gameObject.setySpeed(10);
        assertEquals(10, gameObject.getySpeed(),0.1);
    }

    @Test
    public void testNegativeSetYSpeed()
    {
        gameObject.setySpeed(-10);
        assertEquals(-10, gameObject.getySpeed(),0.1);
    }

    @Test
    public void testUnsetYGetSpeed()
    {
        assertEquals(0,gameObject.getySpeed(), 0);
    }

    @Test
    public void testSetXandYspeed()
    {
        gameObject.setxSpeed(10);
        gameObject.setySpeed(10);
        assertEquals(14.1, gameObject.getSpeed(), 0.1);
    }

    @Test
    public void testSetFrictionOf0point05()
    {
        try {
            gameObject.setFriction((float)0.05);
            assertEquals((float)0.05, gameObject.getFriction(), 0.1);
        }catch (Exception e){

        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetFrictionOf2AndThrowException() throws IllegalArgumentException{
        gameObject.setFriction(2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetFrictionNegativeAndThrowException() throws IllegalArgumentException{
        gameObject.setFriction(-1);
    }

    @Test
    public void testUnsetDirection()
    {
        assertEquals(0,gameObject.getDirection(), 0);
    }

    @Test
    public void testNegativeDirection()
    {
        gameObject.setDirection(-20);
        assertEquals(340, gameObject.getDirection(), 0);
    }

    @Test
    public void testPostitveDirection() {
        gameObject.setDirection(420);
        assertEquals(60, gameObject.getDirection(), 0);
    }

    @Test
    public void testCalculateXspeed() {
        gameObject.setSpeed(1);
        gameObject.setDirection(270);
        assertEquals(-1, gameObject.getxSpeed(), 0);
    }

    @Test
    public void testCalculateYspeed() {
        gameObject.setSpeed(1);
        gameObject.setDirection(0);
        assertEquals(-1, gameObject.getySpeed(), 0);
    }

    @Test
    public void testCalculateDirectionBasedOnPositiveSpeed() {
        assertEquals(90,gameObject.calculateDirection(1,0),0);
    }

    @Test
    public void testCalculateDirectionBasedOnNegativeSpeed() {
        assertEquals(315,gameObject.calculateDirection(-1,-1),0);
    }

    @Test
    public void testGetFriction() {
        try {
            gameObject.setFriction((float)0.2);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        assertEquals((float)0.2, gameObject.getFriction(),0);

    }

    @Test
    public void testSetDirectionSpeedDirection() {
        gameObject.setDirectionSpeed(90,5);
        assertEquals(90,gameObject.getDirection(),0);
    }

    @Test
    public void testSetDirectionSpeedSpeed() {
        gameObject.setDirectionSpeed(90,5);
        assertEquals(5,gameObject.getSpeed(),0);
    }

    @Test
    public void testMoveObjectXspeedShouldBeZero() {
        try {
            gameObject.setFriction((float)0.2);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        gameObject.setDirectionSpeed(90,10);
        for (int i = 0; i < 30; i++) {
            gameObject.move();
        }
        assertEquals(0, gameObject.getxSpeed(),0.1);
    }

    @Test
    public void testMoveObjectYspeedShouldBeZero() {
        try {
            gameObject.setFriction((float)0.2);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        gameObject.setDirectionSpeed(90,10);
        for (int i = 0; i < 30; i++) {
            gameObject.move();
        }
        assertEquals(0, gameObject.getySpeed(),0);
    }

    @Test
    public void testUpdateObjectXspeedShouldBeZero() {
        try {
            gameObject.setFriction((float)0.2);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        gameObject.setDirectionSpeed(90,10);
        for (int i = 0; i < 30; i++) {
            gameObject.update();
        }
        assertEquals(0, gameObject.getxSpeed(),0.1);
    }

    @Test
    public void testUpdateObjectYspeedShouldBeZero() {
        try {
            gameObject.setFriction((float)0.2);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        gameObject.setDirectionSpeed(90,10);
        for (int i = 0; i < 30; i++) {
            gameObject.update();
        }
        assertEquals(0, gameObject.getySpeed(),0);
    }

    @Test
    public void testGetAngleFromPossitiveX(){
        assertEquals(gameObject.getAngleFrom(180,-1), 86.2f, 0.1);
    }

    @Test
    public void testGetAngleFromPossitiveY(){
        assertEquals(gameObject.getAngleFrom(-1,180), 183.7f, 0.1);
    }

    public class FakeGameObject extends GameObject
    {
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

    private class GameEngineMock extends GameEngine{

        @Override
        public void setupGame() {

        }

        @Override
        public void update() {

        }
    }
}

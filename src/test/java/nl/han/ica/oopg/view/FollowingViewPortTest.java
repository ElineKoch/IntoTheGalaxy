package nl.han.ica.oopg.view;


import nl.han.ica.oopg.objects.GameObject;
import org.junit.Before;
import org.junit.Test;
import processing.core.PGraphics;

import static org.junit.Assert.assertEquals;

public class FollowingViewPortTest {
	
    FollowingViewport followingViewPort;
    FakeGameObject fakeGameObject;

    @Before
    public void setup()
    {
        fakeGameObject = new FakeGameObject(0, 0, 0, 0);
        followingViewPort = new FollowingViewport(fakeGameObject, 0, 0) { };
    }

    @Test
    public void getSetFollowedObject()
    {
        assertEquals(followingViewPort.getFollowedObject(),fakeGameObject);
        fakeGameObject = new FakeGameObject(1, 1, 1, 1);
        followingViewPort.setFollowedObject(fakeGameObject);
        assertEquals(followingViewPort.getFollowedObject(), fakeGameObject);
    }

    @Test
    public void getSetTopTollerance()
    {
        assertEquals(followingViewPort.getTopTolerance(), 0);
        followingViewPort.setTopTolerance(10);
        assertEquals(followingViewPort.getTopTolerance(), 10);
    }

    @Test
    public void getSetBottomTollerance()
    {
        assertEquals(followingViewPort.getBottomTolerance(), 0);
        followingViewPort.setBottomTolerance(10);
        assertEquals(followingViewPort.getBottomTolerance(), 10);
    }

    @Test
    public void getSetLeftTollerance()
    {
        assertEquals(followingViewPort.getLeftTolerance(), 0);
        followingViewPort.setLeftTolerance(10);
        assertEquals(followingViewPort.getLeftTolerance(), 10);
    }

    @Test
    public void getSetRightTollerance()
    {
        assertEquals(followingViewPort.getRightTolerance(), 0);
        followingViewPort.setRightTolerance(10);
        assertEquals(followingViewPort.getRightTolerance(), 10);
    }

    @Test
    public void getTollerance()
    {
        assertEquals(followingViewPort.getTopTolerance(), 0);
        assertEquals(followingViewPort.getBottomTolerance(), 0);
        assertEquals(followingViewPort.getLeftTolerance(), 0);
        assertEquals(followingViewPort.getRightTolerance(), 0);
        followingViewPort.setTolerance(10, 10, 10 ,10);
        assertEquals(followingViewPort.getTopTolerance(), 10);
        assertEquals(followingViewPort.getBottomTolerance(), 10);
        assertEquals(followingViewPort.getLeftTolerance(), 10);
        assertEquals(followingViewPort.getRightTolerance(), 10);
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

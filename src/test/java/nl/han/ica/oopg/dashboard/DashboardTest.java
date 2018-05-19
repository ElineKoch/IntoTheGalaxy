package nl.han.ica.oopg.dashboard;

import static org.junit.Assert.*;

import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import nl.han.ica.oopg.pgraphicsstub.PGraphicsCreatorMock;
import nl.han.ica.oopg.pgraphicsstub.PGraphicsGameObject;
import nl.han.ica.oopg.pgraphicsstub.PGraphicsMock;

import org.junit.Before;
import org.junit.Test;

import processing.core.PGraphics;
import processing.core.PImage;

import java.util.List;
import java.util.stream.Collectors;

public class DashboardTest {

    private Dashboard dashboard;

    @Before
    public void setup() {
        dashboard = new Dashboard(0, 0, 500, 100);
        dashboard.setPGraphicsCreator(new PGraphicsCreatorMock());
    }

    @Test
    public void DashboardShouldContainFakeGameObject() throws Exception{
        FakeGameObject fakeGameObject = new FakeGameObject(0,0,30,30);
        dashboard.addGameObject(fakeGameObject);
        assertTrue(dashboard.getGameObjects().contains(fakeGameObject));
    }

    @Test
    public void DashboardShouldContainTwoFakeGameObjects() throws Exception{
        FakeGameObject fakeGameObject1 = new FakeGameObject(0,0,30,30);
        FakeGameObject fakeGameObject2 = new FakeGameObject(0,0,60,60);
        dashboard.addGameObject(fakeGameObject1);
        dashboard.addGameObject(fakeGameObject2);
        assertTrue(dashboard.getGameObjects().contains(fakeGameObject1) && dashboard.getGameObjects().contains(fakeGameObject2));
    }

    @Test
    public void DashboardShouldContainExactlyThreeFakeGameObjects() throws Exception{
        FakeGameObject fakeGameObject1 = new FakeGameObject(0,0,30,30);
        FakeGameObject fakeGameObject2 = new FakeGameObject(0,0,60,60);
        FakeGameObject fakeGameObject3 = new FakeGameObject(0,0,90,90);
        dashboard.addGameObject(fakeGameObject1);
        dashboard.addGameObject(fakeGameObject2);
        dashboard.addGameObject(fakeGameObject3);
        assertTrue(dashboard.getGameObjects().size() == 3);
    }

    @Test
    public void addFakeGameObjectToDashboardShouldSetXAndYOfFakeGameObject() throws Exception{
        FakeGameObject fakeGameObject = new FakeGameObject(0,0,30,30);
        dashboard.addGameObject(fakeGameObject, 50, 60);
        assertTrue((dashboard.getGameObjects().elementAt(0).getX() == 50) && (dashboard.getGameObjects().elementAt(0).getY() == 60));
    }

    @Test
    public void addFakeGameObjectToDashboardShouldSetZValueOfFakeGameObject() throws Exception{
        FakeGameObject fakeGameObject = new FakeGameObject(0,0,30,30);
        dashboard.addGameObject(fakeGameObject, 3);
        assertTrue(dashboard.getGameObjects().elementAt(0).getZ() == 3);
    }

    @Test
    public void addFakeGameObjectToDashboardShouldSetXAndYAndZOfFakeGameObject() throws Exception{
        FakeGameObject fakeGameObject = new FakeGameObject(0,0,30,30);
        dashboard.addGameObject(fakeGameObject, 50, 60, 3);
        assertTrue((dashboard.getGameObjects().elementAt(0).getX() == 50) && (dashboard.getGameObjects().elementAt(0).getY() == 60) && (dashboard.getGameObjects().elementAt(0).getZ() == 3));
    }

    @Test
    public void deleteGameObjectShouldDeleteGameObjectFromDashboard() throws Exception{
        FakeGameObject fakeGameObject = new FakeGameObject(0,0,40,40);
        dashboard.addGameObject(fakeGameObject);
        dashboard.deleteGameObject(fakeGameObject);
        assertFalse(dashboard.getGameObjects().contains(fakeGameObject));
    }

    @Test
    public void deleteAllGameObjectsShouldDeleteAllGameObjectsFromDashboard() throws Exception{
        FakeGameObject fakeGameObject1 = new FakeGameObject(0,0,30,30);
        FakeGameObject fakeGameObject2 = new FakeGameObject(0,0,60,60);
        FakeGameObject fakeGameObject3 = new FakeGameObject(0,0,90,90);
        dashboard.addGameObject(fakeGameObject1);
        dashboard.addGameObject(fakeGameObject2);
        dashboard.addGameObject(fakeGameObject3);
        dashboard.deleteAllDashboardObjects();
        assertTrue(dashboard.getGameObjects().size() == 0);
    }

    @Test
    public void deleteAllGameObjectsOfTypeShouldDeleteOnlyGameObjectsOfTypeSecondFakeGameObject() throws Exception{
        FakeGameObject fakeGameObject1 = new FakeGameObject(0,0,30,30);
        FakeGameObject fakeGameObject2 = new FakeGameObject(0,0,60,60);
        FakeGameObject fakeGameObject3 = new FakeGameObject(0,0,90,90);
        dashboard.addGameObject(fakeGameObject1);
        dashboard.addGameObject(fakeGameObject2);
        dashboard.addGameObject(fakeGameObject3);
        SecondFakeGameObject secondFakeGameObject1 = new SecondFakeGameObject(0,0,15,15);
        SecondFakeGameObject secondFakeGameObject2 = new SecondFakeGameObject(0,0,25,25);
        dashboard.addGameObject(secondFakeGameObject1);
        dashboard.addGameObject(secondFakeGameObject2);
        dashboard.deleteAllGameObjectsOfType(FakeGameObject.class);
        assertTrue(dashboard.getGameObjects().size() == 2);
    }

    @Test
    public void setBackgroundShouldSetBackgroundWithColors() throws Exception{

        PGraphicsCreatorMock pGraphicsCreator = new PGraphicsCreatorMock();
        PGraphicsMock g = pGraphicsCreator.createPGraphics(50, 50);

        dashboard.setBackground(255, 255, 255);
        dashboard.draw(g);
        
        PGraphicsMock canvas = (PGraphicsMock)((PGraphicsGameObject)g.getGameObjects().get(0)).getCanvas();
        
        assertArrayEquals(new float[]{255, 255, 255}, canvas.getBackground(), 0);
    }

    @Test
    public void setBackgroundImageShouldSetBackgroundWithGifImage() throws Exception{
        PGraphicsCreatorMock pGraphicsCreator = new PGraphicsCreatorMock();
        PGraphicsMock g = pGraphicsCreator.createPGraphics(100, 50);

        FakeSprite fakeSprite = new FakeSprite("src/test/resources/swordfish.gif");
        dashboard.setBackgroundImage(fakeSprite);
        dashboard.draw(g);

        PGraphicsMock canvas = (PGraphicsMock)((PGraphicsGameObject)g.getGameObjects().get(0)).getCanvas();
        List<GameObject> list = canvas.getGameObjects();

        assertEquals(((PGraphicsGameObject) (list.get(0))).getCanvas(), fakeSprite.getPImage());
    }

    @Test
    public void setBackgroundImageShouldSetBackgroundWithPngImage() throws Exception{
        PGraphicsCreatorMock pGraphicsCreator = new PGraphicsCreatorMock();
        PGraphicsMock g = pGraphicsCreator.createPGraphics(100, 50);

        FakeSprite fakeSprite = new FakeSprite("src/test/resources/swordfish.png");
        dashboard.setBackgroundImage(fakeSprite);
        dashboard.draw(g);

        PGraphicsMock canvas = (PGraphicsMock)((PGraphicsGameObject)g.getGameObjects().get(0)).getCanvas();
        List<GameObject> list = canvas.getGameObjects();

        assertEquals(((PGraphicsGameObject) (list.get(0))).getCanvas(), fakeSprite.getPImage());
    }

    @Test
    public void setBackgroundImageShouldSetBackgroundWithJpgImage() throws Exception{
        PGraphicsCreatorMock pGraphicsCreator = new PGraphicsCreatorMock();
        PGraphicsMock g = pGraphicsCreator.createPGraphics(100, 50);

        FakeSprite fakeSprite = new FakeSprite("src/test/resources/background.jpg");
        dashboard.setBackgroundImage(fakeSprite);
        dashboard.draw(g);

        PGraphicsMock canvas = (PGraphicsMock)((PGraphicsGameObject)g.getGameObjects().get(0)).getCanvas();
        List<GameObject> list = canvas.getGameObjects();

        assertEquals(((PGraphicsGameObject) (list.get(0))).getCanvas(), fakeSprite.getPImage());
    }

    @Test
    public void drawObjectsToCanvasShouldDrawObjects() throws Exception {
        PGraphicsCreatorMock pGraphicsCreator = new PGraphicsCreatorMock();
        PGraphicsMock g = pGraphicsCreator.createPGraphics(100, 50);

        FakeSprite fakeSprite = new FakeSprite("src/test/resources/swordfish.png");
        FakeSpriteObject fakeSpriteObject = new FakeSpriteObject(fakeSprite);
        dashboard.addGameObject(fakeSpriteObject);
        dashboard.draw(g);

        PGraphicsMock canvas = (PGraphicsMock)((PGraphicsGameObject)g.getGameObjects().get(0)).getCanvas();
        List<GameObject> list = canvas.getGameObjects();

        List<PImage> lijstje = list.stream().map(o -> ((PGraphicsGameObject)o).getCanvas()).collect(Collectors.toList());
        assertTrue(lijstje.contains(fakeSpriteObject.getImage()));
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

    private class SecondFakeGameObject extends GameObject {
        public SecondFakeGameObject(int x, int y, int width, int height) {
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

    private class FakeSprite extends Sprite {

        public FakeSprite(String fileName) {
            super(fileName);
        }
    }

    private class FakeSpriteObject extends SpriteObject {

        /**
         * Create a new SpriteObject with a Sprite object.
         *
         * @param sprite
         */
        public FakeSpriteObject(Sprite sprite) {
            super(sprite);
        }

        /**
         * Implement this method to update the objects that need to be drawn.
         */
        @Override
        public void update() {

        }
    }
}

package nl.han.ica.oopg.engine;

import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.exceptions.GameEngineRuntimeException;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.tile.TileType;
import nl.han.ica.oopg.userinput.IKeyInput;
import nl.han.ica.oopg.userinput.IMouseInput;
import nl.han.ica.oopg.view.View;
import org.junit.Before;
import org.junit.Test;
import processing.core.PGraphics;
import processing.event.MouseEvent;

import java.util.Vector;

import static org.junit.Assert.*;

public class GameEngineTest {

	GameEngine gameEngine;
    MouseEvent mouseEvent = new MouseEvent(null, 0, 0, 0, 0, 0, 0, 50);
	
    // Foo & bar are dummys for gameobject
    Foo foo;
    Foo foo1;
    Bar bar;
    Bar bar1;

    @Before
    public void setup()
    {
    	gameEngine = new FakeGameEngine();
    	gameEngine.setView(new View(100, 100));

    	foo = new Foo(1,1,1,1);
        foo1 = new Foo(1,1,1,1);
        bar = new Bar(1,1,1,1);
        bar1 = new Bar(1,1,1,1);
    }

    @Test
    public void testIfGameobjectIsAddedToItems() throws Exception {
        gameEngine.addGameObject(foo);
        Vector<GameObject> foo = gameEngine.getGameObjectItems();

        assertEquals(this.foo,foo.elementAt(0));
    }

    @Test
    public void testIfGameobjectWithXYPositionIsAddedToItems() throws Exception {
        int x = 10;
        int y = 10;
        gameEngine.addGameObject(foo, x, y);
        Vector<GameObject> foo = gameEngine.getGameObjectItems();

        assertEquals(this.foo,foo.elementAt(0));
    }

    @Test
    public void testIfGameobjectWithXYZPositionIsAddedToItems() throws Exception {
        int x = 10;
        int y = 10;
        float z = 0.8f;
        gameEngine.addGameObject(foo, x, y, z);
        Vector<GameObject> foo = gameEngine.getGameObjectItems();

        assertEquals(this.foo,foo.elementAt(0));
    }

    @Test
    public void testIfGameobjectWithZPositionIsAddedToItems() throws Exception {
        float z = 0.8f;
        gameEngine.addGameObject(foo, z);
        Vector<GameObject> foo = gameEngine.getGameObjectItems();

        assertEquals(this.foo,foo.elementAt(0));
    }

    @Test
    public void testCallingWrongGameobject() throws Exception {
    	gameEngine.addGameObject(foo);
    	gameEngine.addGameObject(foo1);
        Vector<GameObject> foo = gameEngine.getGameObjectItems();

        assertNotSame(foo1, foo.elementAt(0));
    }

    @Test(expected = GameEngineRuntimeException.class)
    public void testAddingSameObjectMultipleTimesThrowsException() {
    	gameEngine.addGameObject(foo);
    	gameEngine.addGameObject(foo);
    }
    
    @Test
    public void testDeletingGameOneObjectFromItems() throws Exception {
    	gameEngine.addGameObject(foo);
    	gameEngine.deleteGameObject(foo);
        Vector<GameObject> foo = gameEngine.getGameObjectItems();
        
        assertEquals(true, foo.isEmpty());
    }

    @Test
    public void testDeletingAllGameObjectsFromItems() throws Exception {
    	gameEngine.addGameObject(foo);
    	gameEngine.addGameObject(foo1);
    	gameEngine.deleteAllGameOBjects();
        Vector<GameObject> foo = gameEngine.getGameObjectItems();
        
        assertEquals(true, foo.isEmpty());
    }

    @Test
    public void testHowManyFooLeftInItemsAfterDeleteAllFoo() throws Exception {
    	gameEngine.addGameObject(foo);
    	gameEngine.addGameObject(foo1);
    	gameEngine.addGameObject(bar);
    	gameEngine.addGameObject(bar1);
    	gameEngine.deleteAllGameObjectsOfType(Foo.class);
    	
        Vector<GameObject> foo = gameEngine.getGameObjectItems();
        int foundFoo = 0;

        for(int i =foo.size()-1; i >= 0; i--){
            if(foo.get(i).getClass() == Foo.class){
                foundFoo++;
            }
        }
        
        assertEquals(0, foundFoo);
    }
    @Test
    public void testHowManyBarLeftInItemsAfterDeletingAllFoo() throws Exception {
    	gameEngine.addGameObject(foo);
    	gameEngine.addGameObject(foo1);
    	gameEngine.addGameObject(bar);
    	gameEngine.addGameObject(bar1);
    	gameEngine.deleteAllGameObjectsOfType(Foo.class);
    	
        Vector<GameObject> foo = gameEngine.getGameObjectItems();
        int foundBar = 0;

        for(int i =foo.size()-1; i >= 0; i--){
            if(foo.get(i).getClass() == Bar.class){
                foundBar++;
            }
        }
        
        assertEquals(2, foundBar);
    }

    @Test
    public void testIfEnteredGamespeedIsCorrectlyConvertedToThreadspeed() throws Exception {

    	gameEngine.setGameSpeed(20);

        assertEquals(20, gameEngine.getGameSpeed());
    }

    @Test (expected=IllegalArgumentException.class)
    public void testIfNegativelyEnteredGamespeedGivesException() throws Exception {

    	gameEngine.setGameSpeed(-20);
    }

    @Test
    public void testPauseGameReallyPausesThread()
    {
        gameEngine.pauseGame();
        assertTrue(gameEngine.getThreadState());
    }

    @Test
    public void testResumeGameReallyResumesThread()
    {
        gameEngine.pauseGame();
        gameEngine.resumeGame();
        assertFalse(gameEngine.getThreadState());
    }

    @Test
    public void testSetAndGetView()
    {
        View view = new View(0,0);
        gameEngine.setView(view);
        assertEquals(view, gameEngine.getView());
    }

    @Test
    public void testAddDashboardReallyAddsDashboard()
    {
        Dashboard dashboard = new Dashboard(0,0,0,0);
        gameEngine.addDashboard(dashboard);
        assertTrue(gameEngine.getDashboards().contains(dashboard));
    }

    @Test
    public void testDeleteDashboardReallyDeletesDashboard()
    {
        Dashboard dashboard = new Dashboard(0,0,0,0);
        gameEngine.addDashboard(dashboard);
        gameEngine.deleteDashboard(dashboard);
        assertFalse(gameEngine.getDashboards().contains(dashboard));
    }

    @Test
    public void testDeleteAllDashboardsReallyDeletesAllDashboards()
    {
        Dashboard dashboard = new Dashboard(0,0,0,0);
        gameEngine.addDashboard(dashboard);
        gameEngine.deleteAllDashboards();
        assertTrue(gameEngine.getDashboards().isEmpty());
    }

    @Test
    public void testSetandGetTilemap()
    {
        TileType[] tileTypes = { null};
        int [][] map = {{-1,-1}};
        TileMap tileMap = new TileMap(30, tileTypes, map);
        gameEngine.setTileMap(tileMap);
        assertEquals(tileMap, gameEngine.getTileMap());
    }

    @Test
    public void testAddDashboardWithXandY()
    {
        Dashboard dashboard = new Dashboard(0,0,0,0);
        gameEngine.addDashboard(dashboard,5,5);
        assertEquals(5, dashboard.getX(), 0);
        assertEquals(5, dashboard.getY(),0);
    }



    @Test
    public void keyPressedShouldSetKeyCodeAndKey() {
        gameEngine.addGameObject(foo);
        gameEngine.keyCode = 25;
        gameEngine.key = 'a';
        gameEngine.keyPressed();
        assertEquals(25, foo.keyCode);
        assertEquals('a', foo.key);
    }

    @Test
    public void keyReleasedShouldSetKeyCodeAndKey(){
        gameEngine.addGameObject(foo);
        gameEngine.keyCode = 25;
        gameEngine.key = 'a';
        gameEngine.keyReleased();
        assertEquals(25, foo.keyCode);
        assertEquals('a', foo.key);
    }

    @Test
    public void mousePressedShouldSetXYAndButton(){
        gameEngine.addGameObject(foo);
        gameEngine.mouseX = 25;
        gameEngine.mouseY = 30;
        gameEngine.mouseButton = 35;
        gameEngine.mousePressed();
        assertEquals(25, foo.mouseX);
        assertEquals(30, foo.mouseY);
        assertEquals(35, foo.mouseButton);
    }

    @Test
    public void mouseReleasedShouldSetXYAndButton(){
        gameEngine.addGameObject(foo);
        gameEngine.mouseX = 25;
        gameEngine.mouseY = 30;
        gameEngine.mouseButton = 35;
        gameEngine.mouseReleased();
        assertEquals(25, foo.mouseX);
        assertEquals(30, foo.mouseY);
        assertEquals(35, foo.mouseButton);
    }

    @Test
    public void mouseClickedShouldSetXYAndButton(){
        gameEngine.addGameObject(foo);
        gameEngine.mouseX = 25;
        gameEngine.mouseY = 30;
        gameEngine.mouseButton = 35;
        gameEngine.mouseClicked();
        assertEquals(25, foo.mouseX);
        assertEquals(30, foo.mouseY);
        assertEquals(35, foo.mouseButton);
    }

    @Test
    public void mouseMovedShouldSetXAndY(){
        gameEngine.addGameObject(foo);
        gameEngine.mouseX = 25;
        gameEngine.mouseY = 30;
        gameEngine.mouseMoved();
        assertEquals(25, foo.mouseX);
        assertEquals(30, foo.mouseY);
    }

    @Test
    public void mouseDraggedShouldSetXYAndButton(){
        gameEngine.addGameObject(foo);
        gameEngine.mouseX = 25;
        gameEngine.mouseY = 30;
        gameEngine.mouseButton = 35;
        gameEngine.mouseDragged();
        assertEquals(25, foo.mouseX);
        assertEquals(30, foo.mouseY);
        assertEquals(35, foo.mouseButton);
    }

//    @Test
//    public void mouseWheelShouldSetMouseEvent(){
//        gameEngine.addGameObject(foo);
//        gameEngine.mouseWheel(mouseEvent);
//        assertEquals(50, foo.direction);
//    }

    private class FakeGameEngine extends GameEngine
    {

        @Override
        public void setupGame() {

        }

        @Override
        public void update() {

        }
    }
    
    private class Foo extends GameObject implements IKeyInput, IMouseInput {

        public int keyCode;
        public char key;
        public int mouseX;
        public int mouseY;
        public int mouseButton;
        public int direction;

        public Foo(float x, float y, float height, float width) {
            super(x, y, height, width);
        }

        @Override
        public void update() {

        }

        @Override
        public void draw(PGraphics g) {

        }

        @Override
        public void keyPressed(int keyCode, char key) {
            this.keyCode = keyCode;
            this.key = key;
        }

        @Override
        public void keyReleased(int keyCode, char key) {
            this.keyCode = keyCode;
            this.key = key;
        }

        @Override
        public void mousePressed(int x, int y, int button) {
            this.mouseX = x;
            this.mouseY = y;
            this.mouseButton = button;
        }

        @Override
        public void mouseReleased(int x, int y, int button) {
            this.mouseX = x;
            this.mouseY = y;
            this.mouseButton = button;
        }

        @Override
        public void mouseClicked(int x, int y, int button) {
            this.mouseX = x;
            this.mouseY = y;
            this.mouseButton = button;
        }

        @Override
        public void mouseMoved(int x, int y) {
            this.mouseX = x;
            this.mouseY = y;
        }

        @Override
        public void mouseDragged(int x, int y, int button) {
            this.mouseX = x;
            this.mouseY = y;
            this.mouseButton = button;
        }

        @Override
        public void mouseWheel(int direction) {
            this.direction = (int)mouseEvent.getAmount();
        }
    }

    private class Bar extends GameObject {

        public Bar(float x, float y, float height, float width) {
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
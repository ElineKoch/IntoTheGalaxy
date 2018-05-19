package nl.han.ica.oopg.collision;

import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.MockTile;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.tile.TileType;
import org.junit.Before;
import org.junit.Test;
import processing.core.PGraphics;
import processing.core.PImage;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class TileCollisionTest {
    private GameEngine gameEngine = new GameEngineMock();
    private FakeGameObject fakeGameObject;
    private TileMap tileMap;

    private Sprite testSprite = new Sprite(new PImage(50, 50));
    TileType<MockTile> mockTileTileType = new TileType<>(MockTile.class, testSprite);
    TileType[] testTileTypes = {mockTileTileType};

    private int[][] testMap = {
            {-1, -1, -1, -1, -1},
            {-1, 0, -1, -1, -1},
            {-1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1}
    };
    private int testTileSize = 50;

    @Before
    public void setup() {
        tileMap = new TileMap(testTileSize, testTileTypes, testMap);
        fakeGameObject = new FakeGameObject(50, 50, 50, 50);
        gameEngine.addGameObject(fakeGameObject);
        gameEngine.setTileMap(tileMap);
    }

    // Tests topside tile
    @Test
    public void gameObjectCollisionWithTileTopSideMinimumPixelAway() {
        fakeGameObject.setY(24);
        fakeGameObject.setX(50);
        gameEngine.updateGame();
        assertTrue(fakeGameObject.collisionTop);
    }

    @Test
    public void gameObjectCollisionWithTileTopSideMaximumPixelsAway() {
        fakeGameObject.setY(1);
        fakeGameObject.setX(50);
        gameEngine.updateGame();
        assertTrue(fakeGameObject.collisionTop);
    }

    @Test
    public void gameObjectCollisionWithTileTopSideWithMovingGameObject() {
        fakeGameObject.setX(50);
        fakeGameObject.setY(0);
        fakeGameObject.setFriction(0.1f);
        fakeGameObject.setDirectionSpeed(180, 600);
        gameEngine.updateGame();
        assertTrue(fakeGameObject.collisionTop);
    }

    @Test
    public void gameObjectCollisionWithTileTopSide136Degrees() {
        double angleToTest = 136;
        double tileToGameObjectAngle = getReverseDirection(angleToTest);
        int distanceBetweenTileAndGameObject = 40;
        int tileX = 50;
        int tileY = 50;

        double xPosObject = tileX + distanceBetweenTileAndGameObject * Math.sin(Math.toRadians(tileToGameObjectAngle));
        double yPosObject = tileY - distanceBetweenTileAndGameObject * Math.cos(Math.toRadians(tileToGameObjectAngle));

        fakeGameObject.setX(Math.round((float) xPosObject));
        fakeGameObject.setY(Math.round((float) yPosObject));

        gameEngine.updateGame();

        assertTrue(fakeGameObject.collisionTop);
    }

    @Test
    public void gameObjectCollisionWithTileTopSide225Degrees() {
        double angleToTest = 225;
        double tileToGameObjectAngle = getReverseDirection(angleToTest);
        int distanceBetweenTileAndGameObject = 40;
        int tileX = 50;
        int tileY = 50;

        double xPosObject = tileX + distanceBetweenTileAndGameObject * Math.sin(Math.toRadians(tileToGameObjectAngle));
        double yPosObject = tileY - distanceBetweenTileAndGameObject * Math.cos(Math.toRadians(tileToGameObjectAngle));

        fakeGameObject.setX(Math.round((float) xPosObject));
        fakeGameObject.setY(Math.round((float) yPosObject));

        gameEngine.updateGame();

        assertTrue(fakeGameObject.collisionTop);
    }

    @Test
    public void wideGameObjectCollisionWithSmallTileTopSideWithTileAtMostLeftPoint() {
        fakeGameObject.setWidth(400);
        fakeGameObject.setX(50);
        fakeGameObject.setY(10);
        gameEngine.updateGame();
        assertTrue(fakeGameObject.collisionTop);
    }

    @Test
    public void wideGameObjectCollisionWithSmallTileTopSideWithTileAtMostRightPoint() {
        fakeGameObject.setWidth(400);
        fakeGameObject.setX(-300);
        fakeGameObject.setY(10);
        gameEngine.updateGame();
        assertTrue(fakeGameObject.collisionTop);
    }

    // Tests rightside tile
    @Test
    public void gameObjectCollisionWithTileRightSideMinimumPixelAway() {
        fakeGameObject.setX(76);
        gameEngine.updateGame();
        assertTrue(fakeGameObject.collisionRight);
    }

    @Test
    public void gameObjectCollisionWithTileRightSideMaximumPixelsAway() {
        fakeGameObject.setX(99);
        gameEngine.updateGame();
        assertTrue(fakeGameObject.collisionRight);
    }

    @Test
    public void gameObjectCollisionWithTileRightSide226Degrees() {
        double angleToTest = 226;
        double tileToGameObjectAngle = getReverseDirection(angleToTest);
        int distanceBetweenTileAndGameObject = 40;
        int tileX = 50;
        int tileY = 50;

        double xPosObject = tileX + distanceBetweenTileAndGameObject * Math.sin(Math.toRadians(tileToGameObjectAngle));
        double yPosObject = tileY - distanceBetweenTileAndGameObject * Math.cos(Math.toRadians(tileToGameObjectAngle));

        fakeGameObject.setX(Math.round((float) xPosObject));
        fakeGameObject.setY(Math.round((float) yPosObject));

        gameEngine.updateGame();

        assertTrue(fakeGameObject.collisionRight);
    }

    @Test
    public void gameObjectCollisionWithTileRightSide315Degrees() {
        double angleToTest = 315;
        double tileToGameObjectAngle = getReverseDirection(angleToTest);
        int distanceBetweenTileAndGameObject = 40;
        int tileX = 50;
        int tileY = 50;

        double xPosObject = tileX + distanceBetweenTileAndGameObject * Math.sin(Math.toRadians(tileToGameObjectAngle));
        double yPosObject = tileY - distanceBetweenTileAndGameObject * Math.cos(Math.toRadians(tileToGameObjectAngle));

        fakeGameObject.setX(Math.round((float) xPosObject));
        fakeGameObject.setY(Math.round((float) yPosObject));

        gameEngine.updateGame();

        assertTrue(fakeGameObject.collisionRight);
    }

    @Test
    public void gameObjectCollisionWithTileRightSideWithMovingGameObject() {
        fakeGameObject.setX(110);
        fakeGameObject.setY(50);
        fakeGameObject.setFriction(0.1f);
        fakeGameObject.setDirectionSpeed(270, 600);
        gameEngine.updateGame();
        assertTrue(fakeGameObject.collisionRight);
    }

    @Test
    public void tallGameObjectCollisionWithSmallTileRightSideWithTileAtLowestPoint() {
        fakeGameObject.setHeight(400);
        fakeGameObject.setX(90);
        fakeGameObject.setY(-300);
        gameEngine.updateGame();
        assertTrue(fakeGameObject.collisionRight);
    }

    @Test
    public void tallGameObjectCollisionWithSmallTileRightSideWithTileAtHighestPoint() {
        fakeGameObject.setHeight(400);
        fakeGameObject.setX(90);
        fakeGameObject.setY(50);
        gameEngine.updateGame();
        assertTrue(fakeGameObject.collisionRight);
    }

    // Tests bottomside tile
    @Test
    public void gameObjectCollisionWithTileBottomSideMinimumPixelAway() {
        fakeGameObject.setY(76);
        fakeGameObject.setX(50);
        gameEngine.updateGame();
        assertTrue(fakeGameObject.collisionBottom);
    }

    @Test
    public void gameObjectCollisionWithTileBottomSideMaximumPixelsAway() {
        fakeGameObject.setY(99);
        fakeGameObject.setX(50);
        gameEngine.updateGame();
        assertTrue(fakeGameObject.collisionBottom);
    }

    @Test
    public void gameObjectCollisionWithTileBottomSideWithMovingGameObject() {
        fakeGameObject.setX(50);
        fakeGameObject.setY(100);
        fakeGameObject.setFriction(0.1f);
        fakeGameObject.setDirectionSpeed(0, 600);
        gameEngine.updateGame();
        assertTrue(fakeGameObject.collisionBottom);
    }

    @Test
    public void gameObjectCollisionWithTileBottomSide316Degrees() {
        double angleToTest = 316;
        double tileToGameObjectAngle = getReverseDirection(angleToTest);
        int distanceBetweenTileAndGameObject = 40;
        int tileX = 50;
        int tileY = 50;

        double xPosObject = tileX + distanceBetweenTileAndGameObject * Math.sin(Math.toRadians(tileToGameObjectAngle));
        double yPosObject = tileY - distanceBetweenTileAndGameObject * Math.cos(Math.toRadians(tileToGameObjectAngle));

        fakeGameObject.setX(Math.round((float) xPosObject));
        fakeGameObject.setY(Math.round((float) yPosObject));

        gameEngine.updateGame();

        assertTrue(fakeGameObject.collisionBottom);
    }

    @Test
    public void gameObjectCollisionWithTileBottomSide45Degrees() {
        double angleToTest = 45;
        double tileToGameObjectAngle = getReverseDirection(angleToTest);
        int distanceBetweenTileAndGameObject = 40;
        int tileX = 50;
        int tileY = 50;

        double xPosObject = tileX + distanceBetweenTileAndGameObject * Math.sin(Math.toRadians(tileToGameObjectAngle));
        double yPosObject = tileY - distanceBetweenTileAndGameObject * Math.cos(Math.toRadians(tileToGameObjectAngle));

        fakeGameObject.setX(Math.round((float) xPosObject));
        fakeGameObject.setY(Math.round((float) yPosObject));

        gameEngine.updateGame();

        assertTrue(fakeGameObject.collisionBottom);
    }

    @Test
    public void wideGameObjectCollisionWithSmallTileBottomSideWithTileAtMostLeftPoint() {
        fakeGameObject.setWidth(400);
        fakeGameObject.setX(50);
        fakeGameObject.setY(90);
        gameEngine.updateGame();
        assertTrue(fakeGameObject.collisionBottom);
    }

    @Test
    public void wideGameObjectCollisionWithSmallTileBottomSideWithTileAtMostRightPoint() {
        fakeGameObject.setWidth(400);
        fakeGameObject.setX(-300);
        fakeGameObject.setY(90);
        gameEngine.updateGame();
        assertTrue(fakeGameObject.collisionBottom);
    }

    // Tests lefside tile
    @Test
    public void gameObjectCollisionWithTileLeftSideMinimumPixelAway() {
        fakeGameObject.setX(24);
        gameEngine.updateGame();
        assertTrue(fakeGameObject.collisionLeft);
    }

    @Test
    public void gameObjectCollisionWithTileLeftSideMaximumPixelsAway() {
        fakeGameObject.setX(1);
        gameEngine.updateGame();
        assertTrue(fakeGameObject.collisionLeft);
    }

    @Test
    public void gameObjectCollisionWithTileLeftSideWithMovingGameObject() {
        fakeGameObject.setX(1);
        fakeGameObject.setY(50);
        fakeGameObject.setFriction(0.1f);
        fakeGameObject.setDirectionSpeed(90, 600);
        gameEngine.updateGame();
        assertTrue(fakeGameObject.collisionLeft);
    }

    @Test
    public void gameObjectCollisionWithTileLeftSide46Degrees() {
        double angleToTest = 46;
        double tileToGameObjectAngle = getReverseDirection(angleToTest);
        int distanceBetweenTileAndGameObject = 40;
        int tileX = 50;
        int tileY = 50;

        double xPosObject = tileX + distanceBetweenTileAndGameObject * Math.sin(Math.toRadians(tileToGameObjectAngle));
        double yPosObject = tileY - distanceBetweenTileAndGameObject * Math.cos(Math.toRadians(tileToGameObjectAngle));

        fakeGameObject.setX(Math.round((float) xPosObject));
        fakeGameObject.setY(Math.round((float) yPosObject));

        gameEngine.updateGame();

        assertTrue(fakeGameObject.collisionLeft);
    }

    @Test
    public void gameObjectCollisionWithTileLeftSide135Degrees() {
        double angleToTest = 135;
        double tileToGameObjectAngle = getReverseDirection(angleToTest);
        int distanceBetweenTileAndGameObject = 40;
        int tileX = 50;
        int tileY = 50;

        double xPosObject = tileX + distanceBetweenTileAndGameObject * Math.sin(Math.toRadians(tileToGameObjectAngle));
        double yPosObject = tileY - distanceBetweenTileAndGameObject * Math.cos(Math.toRadians(tileToGameObjectAngle));

        fakeGameObject.setX(Math.round((float) xPosObject));
        fakeGameObject.setY(Math.round((float) yPosObject));

        gameEngine.updateGame();

        assertTrue(fakeGameObject.collisionLeft);
    }

    @Test
    public void tallGameObjectCollisionWithSmallTileLeftSide() {
        fakeGameObject.setHeight(400);
        fakeGameObject.setX(10);
        fakeGameObject.setY(-300);
        gameEngine.updateGame();
        assertTrue(fakeGameObject.collisionLeft);
    }

    @Test
    public void tallGameObjectCollisionWithSmallTileLeftSideWithTileAtLowestPoint() {
        fakeGameObject.setHeight(400);
        fakeGameObject.setX(10);
        fakeGameObject.setY(-300);
        gameEngine.updateGame();
        assertTrue(fakeGameObject.collisionLeft);
    }

    @Test
    public void tallGameObjectCollisionWithSmallTileLeftSideWithTileAtHighestPoint() {
        fakeGameObject.setHeight(400);
        fakeGameObject.setX(10);
        fakeGameObject.setY(50);
        gameEngine.updateGame();
        assertTrue(fakeGameObject.collisionLeft);
    }

    // Other tile tests
    @Test
    public void gameObjectCollisionOnWholeTileWithoutSide() {
        gameEngine.updateGame();
        assertTrue(fakeGameObject.collision);
    }

    @Test
    public void NoCollisionOnEmptyTile() {
        fakeGameObject.setY(150);
        fakeGameObject.setX(150);
        gameEngine.updateGame();
        assertFalse(fakeGameObject.collision);
    }


    private class FakeGameObject extends GameObject implements ICollidableWithTiles {
        public boolean collision = false;
        public boolean collisionTop = false;
        public boolean collisionRight = false;
        public boolean collisionBottom = false;
        public boolean collisionLeft = false;


        public FakeGameObject(float x, float y, float width, float height) {
            super(x, y, width, height);
        }

        @Override
        public void update() {

        }

        @Override
        public void draw(PGraphics g) {

        }

        @Override
        public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
            collision = true;

            for (CollidedTile cd : collidedTiles) {
                if (cd.collisionSide.equals(CollisionSide.TOP)) {
                    collisionTop = true;
                } else if (cd.collisionSide.equals(CollisionSide.RIGHT)) {
                    collisionRight = true;
                } else if (cd.collisionSide.equals(CollisionSide.BOTTOM)) {
                    collisionBottom = true;
                } else if (cd.collisionSide.equals(CollisionSide.LEFT)) {
                    collisionLeft = true;
                }
            }
        }
    }

    private double getReverseDirection(double angle) {
        if (angle >= 180) {
            return angle - 180;
        }
        return angle + 180;
    }

    private class GameEngineMock extends GameEngine {

        @Override
        public void setupGame() {

        }

        @Override
        public void update() {

        }
    }
}

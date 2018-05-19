package nl.han.ica.oopg.collision;

import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.GameObject;
import org.junit.Before;
import org.junit.Test;
import processing.core.PGraphics;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameObjectCollisionTest {

    public Vis vis;
    public Muur muur;
    public GameEngine gameEngine = new GameEngineMock();

    @Before
    public void setup() {

    }

    @Test
    public void CollisionOccuredWhenVisIsOnePixelInLeftSideOfMuur() {
        int visX = 1;
        int visY = 0;
        int visWidth = 6;
        int visHeight = 5;
        vis = new Vis(visX,visY,visHeight,visWidth);
        int muurX = 5;
        int muurY = 0;
        int muurWidth = 5;
        int muurHeight = 5;
        muur = new Muur(muurX,muurY,muurHeight,muurWidth);
        gameEngine.addGameObject(muur);
        gameEngine.addGameObject(vis);

        gameEngine.updateGame();
        assertTrue(vis.getGebotst());
    }

    @Test
    public void NoCollisionOccuredWhenVisIsOnePixelAwayFromLeftSideOfMuur() {
        int visX = 0;
        int visY = 0;
        int visWidth = 5;
        int visHeight = 5;
        vis = new Vis(visX,visY,visHeight,visWidth);
        int muurX = 5;
        int muurY = 0;
        int muurWidth = 5;
        int muurHeight = 5;
        muur = new Muur(muurX,muurY,muurHeight,muurWidth);
        gameEngine.addGameObject(muur);
        gameEngine.addGameObject(vis);

        gameEngine.updateGame();

        assertFalse(vis.getGebotst());
    }

    @Test
    public void CollisionOccuredWhenVisIsOnePixelInRightSideOfMuur() {
        int visX = 10;
        int visY = 0;
        int visWidth = 5;
        int visHeight = 5;
        vis = new Vis(visX,visY,visHeight,visWidth);
        int muurX = 6;
        int muurY = 0;
        int muurWidth = 5;
        int muurHeight = 5;
        muur = new Muur(muurX,muurY,muurHeight,muurWidth);
        gameEngine.addGameObject(muur);
        gameEngine.addGameObject(vis);

        gameEngine.updateGame();

        assertTrue(vis.getGebotst());
    }

    @Test
    public void NoCollisionOccuredWhenVisIsOnePixelAwayFromRightSideOfMuur() {
        int visX = 10;
        int visY = 0;
        int visWidth = 5;
        int visHeight = 5;
        vis = new Vis(visX,visY,visHeight,visWidth);
        int muurX = 5;
        int muurY = 0;
        int muurWidth = 5;
        int muurHeight = 5;
        muur = new Muur(muurX,muurY,muurHeight,muurWidth);
        gameEngine.addGameObject(muur);
        gameEngine.addGameObject(vis);

        gameEngine.updateGame();

        assertFalse(vis.getGebotst());
    }

    @Test
    public void CollisionOccuredWhenVisIsOnePixelInTopSideOfMuur() {
        int visX = 0;
        int visY = 0;
        int visWidth = 5;
        int visHeight = 5;
        vis = new Vis(visX,visY,visHeight,visWidth);
        int muurX = 0;
        int muurY = 4;
        int muurWidth = 5;
        int muurHeight = 5;
        muur = new Muur(muurX,muurY,muurHeight,muurWidth);
        gameEngine.addGameObject(muur);
        gameEngine.addGameObject(vis);

        gameEngine.updateGame();

        assertTrue(vis.getGebotst());
    }

    @Test
    public void NoCollisionOccuredWhenVisIsOnePixelAwayFromTopSideOfMuur() {
        int visX = 0;
        int visY = 0;
        int visWidth = 5;
        int visHeight = 5;
        vis = new Vis(visX,visY,visHeight,visWidth);
        int muurX = 0;
        int muurY = 5;
        int muurWidth = 5;
        int muurHeight = 5;
        muur = new Muur(muurX,muurY,muurHeight,muurWidth);
        gameEngine.addGameObject(muur);
        gameEngine.addGameObject(vis);

        gameEngine.updateGame();

        assertFalse(vis.getGebotst());
    }

    @Test
    public void CollisionOccuredWhenVisIsOnePixelInBottomSideOfMuur() {
        int visX = 0;
        int visY = 10;
        int visWidth = 5;
        int visHeight = 5;
        vis = new Vis(visX,visY,visHeight,visWidth);
        int muurX = 0;
        int muurY = 6;
        int muurWidth = 5;
        int muurHeight = 5;
        muur = new Muur(muurX,muurY,muurHeight,muurWidth);
        gameEngine.addGameObject(muur);
        gameEngine.addGameObject(vis);

        gameEngine.updateGame();

        assertTrue(vis.getGebotst());
    }

    @Test
    public void NoCollisionOccuredWhenVisIsOnePixelAwayFromBottomSideOfMuur() {
        int visX = 0;
        int visY = 10;
        int visWidth = 5;
        int visHeight = 5;
        vis = new Vis(visX,visY,visHeight,visWidth);
        int muurX = 0;
        int muurY = 5;
        int muurWidth = 5;
        int muurHeight = 5;
        muur = new Muur(muurX,muurY,muurHeight,muurWidth);
        gameEngine.addGameObject(muur);
        gameEngine.addGameObject(vis);

        gameEngine.updateGame();

        assertFalse(vis.getGebotst());
    }

    @Test
    public void CollisionOccuredWhenSlowMovingVisCollidesWithMuur() {
        int visX = 0;
        int visY = 0;
        int visWidth = 5;
        int visHeight = 5;
        vis = new Vis(visX,visY,visHeight,visWidth);
        int muurX = 9;
        int muurY = 0;
        int muurWidth = 5;
        int muurHeight = 5;
        muur = new Muur(muurX,muurY,muurHeight,muurWidth);
        gameEngine.addGameObject(muur);
        gameEngine.addGameObject(vis);
        vis.setDirectionSpeed(90,5);

        gameEngine.updateGame();

        assertTrue(vis.getGebotst());
    }

    @Test
    public void CollisionOccuredWhenFastMovingVisCollidesWithMuur() {
        int visX = 0;
        int visY = 0;
        int visWidth = 5;
        int visHeight = 5;
        vis = new Vis(visX,visY,visHeight,visWidth);
        int muurX = 200;
        int muurY = 0;
        int muurWidth = 5;
        int muurHeight = 5;
        muur = new Muur(muurX,muurY,muurHeight,muurWidth);
        gameEngine.addGameObject(muur);
        gameEngine.addGameObject(vis);
        vis.setDirectionSpeed(90,500);

        gameEngine.updateGame();

        assertTrue(vis.getGebotst());
    }

    private class Vis extends GameObject implements ICollidableWithGameObjects {

        public Vis(float x, float y, float height, float width) {
            super(x, y, height, width);
        }

        private boolean gebotst = false;

        @Override
        public void update() {

        }

        public boolean getGebotst() {
            return gebotst;
        }

        @Override
        public void draw(PGraphics g) {

        }

        @Override
        public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        	
            if(collidedGameObjects != null && collidedGameObjects.size() > 0)
            {
                gebotst = true;
            }
        }
    }

    private class Muur extends GameObject {

        public Muur(float x, float y, float width, float height) {
            super(x, y, width, height);
        }

        @Override
        public void update() {

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
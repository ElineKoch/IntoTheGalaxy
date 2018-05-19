package nl.han.ica.oopg.tile;


import nl.han.ica.oopg.exceptions.TileNotFoundException;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.pgraphicsstub.PGraphicsCreatorMock;
import nl.han.ica.oopg.pgraphicsstub.PGraphicsMock;

import org.junit.Before;
import org.junit.Test;

import processing.core.PImage;
import processing.core.PVector;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class TileMapTest {

    TileMap tileMap;

    Sprite testSprite = new Sprite(new PImage(50, 50));

    TileType<MockTile> mockTileTileType = new TileType<>(MockTile.class, testSprite);

    TileType[] testTileTypes = {mockTileTileType};

    int[][] testMap = {
            {-1, -1, -1, -1, -1},
            {-1, -1, -1, -1, 1},
            {-1, -1, -1, -1, 1},
            {-1, -1, -1, -1, 1},
            {3, 0, 0, 0, 1}
    };

    int testTileSize = 50;

    @Before
    public void setup() {
        tileMap = new TileMap(testTileSize, testTileTypes, testMap);
    }

    @Test
    public void tileMapNormalConstructor() {
        assertArrayEquals(tileMap.getTileTypes(), testTileTypes);
        assertArrayEquals(tileMap.getTileMap(), testMap);
        assertEquals(tileMap.getTileSize(), testTileSize);
    }

    @Test
    public void tileMapTileSizeConstructor() {
        tileMap = new TileMap(testTileSize);
        assertArrayEquals(tileMap.getTileTypes(), null);
        assertArrayEquals(tileMap.getTileMap(), null);
        assertEquals(tileMap.getTileSize(), testTileSize);
    }

    @Test
    public void testTileSizeSetTo10() {
        tileMap.setTileSize(10);
        assertEquals(10, tileMap.getTileSize(), 0);
    }

    @Test
    public void setNewTileMap() {
        tileMap.setTileMap(testMap);
        assertArrayEquals(tileMap.getTileTypes(), testTileTypes);
        assertArrayEquals(tileMap.getTileMap(), testMap);
        assertEquals(tileMap.getTileSize(), testTileSize);

    }

    @Test
    public void getMapHeight() {
        tileMap.setTileSize(10);
        tileMap.setTileMap(testMap);
        assertEquals(50, tileMap.getMapHeight(), 0);
    }

    @Test
    public void getMapWidth() {
        tileMap.setTileSize(10);
        tileMap.setTileMap(testMap);
        assertEquals(50, tileMap.getMapWidth(), 0);
    }

    @Test
    public void setNewTileOnIndex() {
        testMap[0][0] = 1;
        tileMap.setTile(0, 0, 1);
        assertArrayEquals(tileMap.getTileMap(), testMap);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void getTileOnPositionWithXAndYOutOfBounds() {
        tileMap.setTileSize(10);
        tileMap.getTileOnPosition(60, 60);
    }

    @Test
    public void getEmptyTileOnPositionWhereRequestedTileIsNegative() {
        tileMap.setTileSize(50);
        assertEquals(tileMap.getTileOnPosition(0, 0).getClass(), EmptyTile.class);
    }

    @Test
    public void getEmptyTileOnPositionWhereRequestedTileIsToHigh() {
        tileMap.setTileSize(50);
        assertEquals(tileMap.getTileOnPosition(0, 4).getClass(), EmptyTile.class);
    }

    @Test
    public void getTileOnPosition() {
        tileMap.setTileSize(10);
        assertEquals(tileMap.getTileOnPosition(10, 40).getClass(), testTileTypes[0].getClassType());
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void getTileOnIndexExceptionWithXAndYOutOfBounds() {
        tileMap.setTileSize(10);
        tileMap.getTileOnIndex(6, 6);
    }

    @Test
    public void getEmptyTileOnIndexWhereRequestedTileIsNegative() {
        assertEquals(tileMap.getTileOnIndex(0, 0).getClass(), EmptyTile.class);
    }

    @Test
    public void getEmptyTileOnIndexWhereRequestedTileIsToHigh() {
        assertEquals(tileMap.getTileOnIndex(0, 4).getClass(), EmptyTile.class);
    }

    @Test
    public void getTileOnIndex() {
        assertEquals(tileMap.getTileOnIndex(1, 4).getClass(), testTileTypes[0].classType);
    }

    @Test
    public void getTileIndex() throws TileNotFoundException {
        Tile tile = tileMap.getTileOnIndex(1, 4);

        assertEquals(new PVector(1, 4), tileMap.getTileIndex(tile));
    }

    @Test
    public void getTilePixelLocation() throws TileNotFoundException {
        Tile tile = tileMap.getTileOnIndex(1, 4);

        assertEquals(new PVector(1 * tileMap.getTileSize(), 4 * tileMap.getTileSize()), tileMap.getTilePixelLocation(tile));
    }

    @Test
    public void drawShouldDrawTheTileMap() {
        PGraphicsCreatorMock pGraphicsCreator = new PGraphicsCreatorMock();
        PGraphicsMock g = pGraphicsCreator.createPGraphics(tileMap.getMapWidth(), tileMap.getMapHeight());

        tileMap.draw(g);

        assertEquals(25, g.getGameObjects().size()); // The amount of tiles in the TileMap.
    }
}

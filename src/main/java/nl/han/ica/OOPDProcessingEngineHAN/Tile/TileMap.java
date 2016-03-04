package nl.han.ica.OOPDProcessingEngineHAN.Tile;

import nl.han.ica.OOPDProcessingEngineHAN.Exceptions.TileNotFoundException;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;

/**
 * This class contains the map of Tiles which will be drawn within the game.
 */
public class TileMap {

    private int tileSize;
    private int[][] indexMap;
    private Tile[][] tileMap;
    private TileType[] tileTypes;
    private Sprite emptySprite = new Sprite(new PImage(0, 0));
    private TileType<EmptyTile> emptyTileTileType = new TileType<>(EmptyTile.class, emptySprite);

    /**
     * Create a new TileMap.
     * @param tileSize The size the tiles will be.
     * @param tileTypes The Array with factory's which will be creating the different types of tiles.
     * @param indexMap The Map consisting of tileType indexes which will be used to create and draw the Map.
     */
    public TileMap(int tileSize, TileType[] tileTypes, int[][] indexMap) {
        this.tileSize = tileSize;
        setTileTypes(tileTypes);
        setTileMap(indexMap);
    }

    /**
     * Create a new TileMap.
     * @param tileSize The size the tiles will be.
     */
    public TileMap(int tileSize) {
        this.tileSize = tileSize;
    }

    /**
     * Gets the tile size of the tiles inside the TileMap.
     * @return The size of the tiles in the Map.
     */
    public int getTileSize() {
        return tileSize;
    }

    /**
     * Resize the tiles currently used in the map and sets the tileSize for new Tile.
     * @param tileSize  The size the tiles will be.
     */
    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
        
        for (Tile[] tileRow : tileMap) {
            for (Tile tile : tileRow) {
                if (!tile.getClass().equals(emptyTileTileType.getClassType())){
                    tile.setSpriteSize(tileSize);
                }
            }
        }
    }

    /**
     * Gets the map width.
     * @return The width in pixels of the first row of tiles in the map.
     */
    public int getMapWidth() {
        return indexMap[0].length * tileSize;
    }

    /**
     * Gets the map height.
     * @return the Height in pixels of the first column of tiles in the Map
     */
    public int getMapHeight() {
        return indexMap.length * tileSize;
    }

    /**
     * Sets the indexMap used to create the tiles.
     * @param indexMap The two dimensional int array which contains the indexes of the tileTypes.
     */
    public void setTileMap(int[][] indexMap) {
        this.indexMap = indexMap;
        createTileMap();
    }

    /**
     * Creates a TileMap with tile instances made out of indexes and TileTypes.
     */
    private void createTileMap() {
        if (indexMap != null) {
            tileMap = new Tile[indexMap.length][indexMap[0].length];
            
            for (int i = 0; i < indexMap.length; i++) {
                for (int j = 0; j < indexMap[i].length; j++) {
                    tileMap[i][j] = createTile(indexMap[i][j]);
                }
            }
        }
    }

    /**
     * Creates a new Tile made out of the TileType.
     * @param tileTypeIndex
     * @return Tile
     */
    private Tile createTile(int tileTypeIndex){
    	
        if (tileTypeIndex < 0 || tileTypeIndex >= tileTypes.length) 
            return emptyTileTileType.createNewInstanceOfTile(tileSize);
        else 
            return tileTypes[tileTypeIndex].createNewInstanceOfTile(tileSize);
    }

    /**
     * Gets the TileMap.
     * @return The two dimensional int array which contains the indexes of the tileTypes.
     */
    public int[][] getTileMap() {
        return indexMap;
    }

    /**
     * The method the View calls to draw the tiles of the TileMap.
     * @param pGraphics The canvas on which the tiles will be drawn.
     */
    public void draw(PGraphics pGraphics) {
    	
        if (tileMap != null && indexMap != null) {
            for (int i = 0; i < tileMap.length; i++) {
                for (int j = 0; j < tileMap[i].length; j++) {
                    pGraphics.image(tileMap[i][j].getSprite().getPImage(), j * tileSize, i * tileSize);
                }
            }
        }
    }

    /**
     * Sets a Tile on a specific position.
     * @param x The location index of the Tile on the x axis.
     * @param y The location index of the Tile on the y axis.
     * @param tileType The index of the tileType which the tile must become.
     */
    public void setTile(int x, int y, int tileType) {
        indexMap[y][x] = tileType;
        tileMap[y][x] = createTile(tileType);
    }

    /**
     * Gets a Tile from a given position.
     * @param x The location in pixels of the Tile on the x axis.
     * @param y The location in pixels of the Tile on the y axis.
     * @return The instance of the Tile on the given position
     */
    public Tile getTileOnPosition(int x, int y) {
        return getTileOnIndex(y / tileSize, x / tileSize);
    }

    /**
     * Gets the Tile index from a given position.
     * @param x The location index of the Tile on the x axis.
     * @param y The location index of the Tile on the y axis.
     * @return The instance of the Tile on the given position
     */
    public Tile getTileOnIndex(int x, int y) {
        return tileMap[y][x];
    }

    /**
     * Sets the TileTypes of the TileMap and adjusts the current map to the new Types
     * @param tileTypes The Array which contains the tileTypes of the tileMap
     */
    public void setTileTypes(TileType[] tileTypes) {
        this.tileTypes = tileTypes;
        createTileMap();
    }

    /**
     * Gets the TileTypes inside the TileMap.
     * @return The Array which contains the tileTypes of the tileMap
     */
    public TileType[] getTileTypes() {
        return tileTypes;
    }

    /**
     * Gets the index from the TileMap of the given Tile.
     * @param tile The instance of Tile of which the programmer wants the tileType.
     * @return The index of the tileType of the given Tile.
     */
    public int findTileTypeIndex(Tile tile) {
    	
        for (int i = 0; i < tileTypes.length; i++){
            if (tileTypes[i].getClassType().equals(tile.getClass())) {
                return i;
            }
        }
        
        return -1;
    }

    /**
     * Gets the Tile index from the TileMap;
     * @param tile The instance of Tile of which the programmer wants the Location.
     * @return An PVector which contains the indexes of the Tile on the x and y axis of the tileMap.
     * @throws TileNotFoundException Is being thrown when the given tile is not present in the current tileMap.
     */
    public PVector getTileIndex(Tile tile) {
    	
        for (int i = 0; i < tileMap.length; i++) {
            for (int j = 0; j < tileMap[i].length; j++) {

                if (tile.equals(tileMap[i][j])){
                    return new PVector(j,i);
                }
            }
        }
        
        throw new TileNotFoundException(tile.getClass().toString());
    }

    /**
     * Gets the x and y location of the Tile.
     * @param tile The instance of Tile of which the programmer wants the Location.
     * @return An PVector which contains the location of the Tile in Pixels.
     * @throws TileNotFoundException Is being thrown when the given tile is not present in the current tileMap.
     */
    public PVector getTilePixelLocation(Tile tile) {
        PVector tileIndex = getTileIndex(tile);
        
        return new PVector(tileIndex.x * tileSize, tileIndex.y * tileSize);
    }
}









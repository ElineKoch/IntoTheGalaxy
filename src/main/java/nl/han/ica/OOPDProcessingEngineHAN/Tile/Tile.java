package nl.han.ica.OOPDProcessingEngineHAN.Tile;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

/**
 * The Tile Object is used to bind a Sprite to an location in a TileMap
 * Extend Tile to create different types of tiles.
 */
public class Tile {

    private Sprite sprite;

    /**
     *
     * @param sprite The image which will be drawn whenever the draw method of the Tile is called.
     */
    public Tile(Sprite sprite) {
        setSprite(sprite);
    }

    /**
     * Use to get the Sprite object which is used to draw the image of the Tile.
     *
     * @return sprite
     */
    public Sprite getSprite() {
        return sprite;
    }

    /**
     * Use to set an new Sprite object to replace the image used to draw the Tile.
     *
     * @param sprite The new image which will be drawn whenever the draw method of the Tile is called.
     */
    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    /**
     * Use to set the height and width of a Sprite object to an single size to ensure the sprite is an rectangle.
     *
     * @param size int which stands for the amount of pixels the Sprite must set for its height and width.
     */
    public void setSpriteSize(int size) {
        sprite.resize(size, size);
    }

}

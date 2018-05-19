package nl.han.ica.oopg.tile;

import nl.han.ica.oopg.objects.Sprite;

/**
 * This is the standard extension of tile for an empty space in the tileMap.
 * It will be used whenever a programmer uses a -1 or an index outside of the tileType array.
 */
public class EmptyTile extends Tile{
    /**
     *
     * @param sprite The image which will be drawn whenever the draw method of the tile is called.
     */
    public EmptyTile(Sprite sprite) {
        super(sprite);
    }

    /**
     * Empty Override of the setSpriteSize method to prevent the Sprite from resizing.
     *
     * @param size int which stands for the amount of pixels the Sprite must set for its height and width.
     */
    @Override
    public void setSpriteSize(int size) {
        //Empty Override of the setSpriteSize method to prevent the Sprite from resizing.
    }
}

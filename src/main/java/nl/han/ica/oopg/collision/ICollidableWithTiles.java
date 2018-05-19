package nl.han.ica.oopg.collision;

import java.util.List;

/**
 * Make your GameObject implement this interface if you want your GameObject to
 * be collidable with Tiles.
 */
public interface ICollidableWithTiles {

    /**
     * This method will be triggered when a GameObject has collided with Tiles.
     *
     * @param collidedTiles The tiles with which a collision should be detected
     */
    void tileCollisionOccurred(List<CollidedTile> collidedTiles);
}

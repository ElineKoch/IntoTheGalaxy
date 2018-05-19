package nl.han.ica.oopg.collision;

import nl.han.ica.oopg.tile.Tile;

/**
 * This object indicates the tile which is collided with an object.
 */
public class CollidedTile {
	/**
	 * Constant indicating that collisionSide is the top of the tile.
     * @deprecated
	 */
	public static final int TOP = 0;

	/**
	 * Constant indicating that collisionSide is the right side of the tile.
     * @deprecated
	 */
	public static final int RIGHT = 1;

	/**
	 * Constant indicating that collisionSide is the bottom of the tile.
     * @deprecated
	 */
	public static final int BOTTOM = 2;

	/**
	 * Constant indicating that collisionSide is the left side of the tile.
     * @deprecated
	 */
	public static final int LEFT = 3;

	/**
	 * Constant indicating that collisionSide is inside the tile.
     * @deprecated
	 */
	public static final int INSIDE = 4;

	/**
	 * The tile involved in the collision.
	 */
	protected Tile theTile;

	/**
	 * The side of the tile onto which the Object has collided. See constants
	 * for values.
	 */
	protected CollisionSide collisionSide;

	/**
	 * Create a simple TileCollision object.
	 *
	 * @param tile The tile
	 * @param collissionSide The collisionSide as an int.
	 * @deprecated Use constructor with CollisionSide enum parameter instead
	 */
	public CollidedTile(Tile tile, int collissionSide) {
		theTile = tile;
		collisionSide = (CollisionSide) collisionSide;
	}
	
	/**
	 * Create a simple TileCollision object.
	 *
	 * @param tile The tile
	 * @param collissionSide The collisionSide (enum)
	 */
	public CollidedTile(Tile tile, CollisionSide collissionSide) {
		theTile = tile;
		collisionSide = collissionSide;
	}

	public Tile getTile() {
		return theTile;
	}

	public CollisionSide getCollisionSide() {
		return collisionSide;
	}
}

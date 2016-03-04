package nl.han.ica.OOPDProcessingEngineHAN.Collision;

import nl.han.ica.OOPDProcessingEngineHAN.Tile.Tile;

/**
 * This object indicates the tile which is collided with an object.
 */
public class CollidedTile {
	/**
	 * Constant indicating that collisionSide is the top of the tile.
	 */
	public static final int TOP = 0;

	/**
	 * Constant indicating that collisionSide is the right side of the tile.
	 */
	public static final int RIGHT = 1;

	/**
	 * Constant indicating that collisionSide is the bottom of the tile.
	 */
	public static final int BOTTOM = 2;

	/**
	 * Constant indicating that collisionSide is the left side of the tile.
	 */
	public static final int LEFT = 3;

	/**
	 * Constant indicating that collisionSide is inside the tile.
	 */
	public static final int INSIDE = 4;

	/**
	 * The tile involved in the collision.
	 */
	public Tile theTile;

	/**
	 * The side of the tile onto which the Object has collided. See constants
	 * for values.
	 */
	public int collisionSide;

	/**
	 * Create a simple TileCollision object.
	 *
	 * @param t
	 *            The tile
	 * @param cs
	 *            The collisionSide
	 */
	public CollidedTile(Tile t, int cs) {
		
		theTile = t;
		collisionSide = cs;
	}
}

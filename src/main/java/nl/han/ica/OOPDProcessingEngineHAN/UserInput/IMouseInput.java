package nl.han.ica.OOPDProcessingEngineHAN.UserInput;

/**
 * This interface is used to implement in objects that need to listen to the GameEngine mouse input.
 */
public interface IMouseInput {

	/**
	 * This event is fired when the GameEngine received a mouse press action.
	 * @param x The x value is the relative mouse position calculated with the position of the world (TileMap).
	 * @param y The y value is the relative mouse position calculated with the position of the world (TileMap).
	 */
	void mousePressed(int x, int y, int button);

	/**
	 * This event is fired when the GameEngine received a mouse release action.
	 * @param x The x value is the relative mouse position calculated with the position of the world (TileMap).
	 * @param y The y value is the relative mouse position calculated with the position of the world (TileMap).
	 */
	void mouseReleased(int x, int y, int button);
	
	/**
	 * This event is fired when the GameEngine received a mouse click action.
	 * @param x The x value is the relative mouse position calculated with the position of the world (TileMap).
	 * @param y The y value is the relative mouse position calculated with the position of the world (TileMap).
	 */
	void mouseClicked(int x, int y, int button);
	
	/**
	 * This event is fired when the GameEngine received a mouse move action.
	 * @param x The x value is the relative mouse position calculated with the position of the world (TileMap).
	 * @param y The y value is the relative mouse position calculated with the position of the world (TileMap).
	 */
	void mouseMoved(int x, int y);
	
	/**
	 * This event is fired when the GameEngine received a mouse drag action.
	 * @param x The x value is the relative mouse position calculated with the position of the world (TileMap).
	 * @param y The y value is the relative mouse position calculated with the position of the world (TileMap).
	 */
	void mouseDragged(int x, int y, int button);
	
	/**
	 * This event is fired when the GameEngine received a mouse wheel action.
	 * @param direction
	 */
	void mouseWheel(int direction);
}

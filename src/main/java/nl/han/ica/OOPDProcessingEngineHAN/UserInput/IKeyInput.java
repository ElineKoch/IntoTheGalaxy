package nl.han.ica.OOPDProcessingEngineHAN.UserInput;

/**
 * This interface is used to implement in objects that need to listen to the GameEngine keyboard input. 
 */
public interface IKeyInput {

	/**
	 * This event is fired when the GameEngine received a key press.
	 * @param keyCode
	 * @param key
	 */
	void keyPressed(int keyCode, char key);
	
	/**
	 * This event is fired when the GameEngine received a key release.
	 * @param keyCode
	 * @param key
	 */
	void keyReleased(int keyCode, char key);
}

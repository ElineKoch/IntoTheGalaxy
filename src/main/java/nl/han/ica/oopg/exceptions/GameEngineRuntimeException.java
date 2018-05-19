package nl.han.ica.oopg.exceptions;

/**
 * RuntimeExceptions thrown by the engine, to be able to catch (specifically/only) our own exceptions.
 * OO/Java best practice: Prefer specific exceptions.
 */
public class GameEngineRuntimeException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public GameEngineRuntimeException(String message) {
        super(message);
    }
}

package nl.han.ica.oopg.exceptions;

/**
 * Used by the TileType object.
 */
public class TileNotFoundException extends RuntimeException {

    public TileNotFoundException(String message) {
        super(message);
    }
}

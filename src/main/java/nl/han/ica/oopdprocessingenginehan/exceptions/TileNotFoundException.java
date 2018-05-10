package nl.han.ica.oopdprocessingenginehan.exceptions;

/**
 * Used by the TileType object.
 */
public class TileNotFoundException extends RuntimeException {

    public TileNotFoundException(String message) {
        super(message);
    }
}

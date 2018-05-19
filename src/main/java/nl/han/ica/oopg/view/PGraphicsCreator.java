package nl.han.ica.oopg.view;

import nl.han.ica.oopg.engine.GameEngine;
import processing.core.PGraphics;

/**
 * This object can make new PGraphics (canvas) objects, this is a layer used to switch between different canvasses.
 */
public class PGraphicsCreator {

    /**
     * Creates a new PGraphics object.
     *
     * @param worldWidth  Width of the world
     * @param worldHeight Height of the world
     * @return Th Canvas as a PGraphics object
     */
    public PGraphics createPGraphics(int worldWidth, int worldHeight) {

        return GameEngine.createPGraphics(worldWidth, worldHeight);
    }
}

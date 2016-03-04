package nl.han.ica.OOPDProcessingEngineHAN.View;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.Dashboard;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.TileMap;
import processing.core.PGraphics;
import processing.core.PImage;

/**
 * The view draws the world and displays the generated viewport on screen.
 */
public class View {

    protected Viewport viewport;

    protected Integer worldWidth;
    protected Integer worldHeight;

    protected int backgroundR, backgroundG, backgroundB;
    protected PImage backgroundImage;

    protected PGraphicsCreator pGraphicsCreator = new PGraphicsCreator();


    /**
     * Basic constructor of the View which creates its own viewPort which shows the entire world.
     *
     * @param worldWidth The width of the world in which all the objects will be drawn.
     * @param worldHeight The height of the world in which all the objects will be drawn.
     */
    public View(int worldWidth, int worldHeight) {
        setWorldSize(worldWidth, worldHeight);
        this.viewport = new Viewport(0, 0, worldWidth, worldHeight);
    }

    /**
     * Create a new View object with an exsisting Viewport.
     * @param viewport The instance of viewPort which generates what is shown of the world
     * @param worldWidth The width of the world in which all the objects will be drawn.
     * @param worldHeight The height of the world in which all the objects will be drawn.
     */
    public View(Viewport viewport, int worldWidth, int worldHeight) {
        setWorldSize(worldWidth, worldHeight);
        this.viewport = viewport;
    }

    /**
     * The main draw method of the engine.
     * It will be called by de engine and handles calling all the draw methods of the objects it gets in the parameters.
     * It also handles the generation of the Viewport.
     *
     * @param g The canvas on which the view will be drawn.
     * @param tileMap The instance of tileMap which will be drawn after the background and before the gameObjects and the generation of the viewPort
     * @param gameObjects The array with instances of gameObjects which will be drawn after the tileMap and before the viewport is generated
     * @param dashboards The array with instances of dashboards which will be drawn after the viewport is generated
     */
    public void draw(PGraphics g, TileMap tileMap, List<GameObject> gameObjects, List<Dashboard> dashboards) {
    	
    	g.clear();
    	g.background(backgroundR, backgroundG, backgroundB);

    	PGraphics viewGraphics = drawWorld(tileMap, gameObjects);

        drawViewport(g, viewGraphics);
            
        drawDashboards(g, dashboards);
    }

    /**
     * Creates a new canvas and draws the TileMap & GameObjects on it.
     * @param tileMap
     * @param gameObjects
     * @return PGraphics
     */
    protected PGraphics drawWorld(TileMap tileMap, List<GameObject> gameObjects) {
		PGraphics viewGraphics = pGraphicsCreator.createPGraphics(worldWidth, worldHeight);

        viewGraphics.beginDraw();
        if (backgroundImage != null) {
            viewGraphics.background(backgroundImage);
        }

        if (tileMap != null) {
            tileMap.draw(viewGraphics);
        }

        for (int i = 0; i < gameObjects.size(); i++) {
            drawVisibleGameObjects(gameObjects, viewGraphics, i);
        }
		viewGraphics.endDraw();
		
		return viewGraphics;
	}

    /**
     * Actually draws GameObjects that are set visible.
     * @param gameObjects
     * @param viewGraphics
     * @param i
     */
    private void drawVisibleGameObjects(List<GameObject> gameObjects, PGraphics viewGraphics, int i) {
        if(gameObjects.get(i).isVisible()) {
            gameObjects.get(i).draw(viewGraphics);
        }
    }

    /**
     * Creates a Viewport out of the TileMap and draws it on the main canvas.
     * @param g
     * @param viewGraphics
     */
    protected void drawViewport(PGraphics g, PGraphics viewGraphics) {
		PImage viewportImage = viewport.createImage(viewGraphics);
        viewportImage.resize(g.width, g.height);
        g.image(viewportImage, 0, 0);
	}

    /**
     * Draws all the Dashboard objects on the given canvas.
     * @param g
     * @param dashboards
     */
    protected void drawDashboards(PGraphics g, List<Dashboard> dashboards) {
		for (int i = 0; i < dashboards.size(); i++) {
            drawVisibleDashboards(g, dashboards, i);
        }
	}

    /**
     * Actually draws Dashboards that are set visible.
     * @param g
     * @param dashboards
     * @param i
     */
    private void drawVisibleDashboards(PGraphics g, List<Dashboard> dashboards, int i) {
        if(dashboards.get(i).isVisible()) {
            dashboards.get(i).draw(g);
        }
    }

    /**
     * Sets the size of the world which will be the canvas all the objects will be drawn on before the viewPort is generated.
     * @param worldWidth The width of the world in which all the objects will be drawn.
     * @param worldHeight The height of the world in which all the objects will be drawn.
     */
    public void setWorldSize(int worldWidth, int worldHeight) {
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
    }

    /**
     * Gets the world width.
     * @return The width of the world in which all the objects will be drawn.
     */
    public int getWorldWidth() {
        return worldWidth;
    }

    /**
     * Gets the world height.
     * @return The height of the world in which all the objects will be drawn.
     */
    public int getWorldHeight() {
        return worldHeight;
    }


    /**
     * Sets the background color of your game in RGB format which is used in the draw method.
     * @param r Red.
     * @param g Green.
     * @param b Blue.
     */
    public void setBackground(int r, int g, int b) {
        backgroundR = r;
        backgroundG = g;
        backgroundB = b;
    }

    /**
     * Sets the background image.
     * @param pImage The Image which is drawn as background for your game.
     */
    public void setBackground(PImage pImage) {
        backgroundImage = pImage;
    }

    /**
     * Gets the Viewport.
     * @return The viewPort which is currently used by the view.
     */
    public Viewport getViewport() {
        return viewport;
    }

    /**
     * Sets the Viewport.
     * @param viewport An instance of viewPort to change the viewPorts behavior.
     */
    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
    }
    
    /**
     * Sets the PGraphicsCreator which can create the canvas where to draw on.
     * @param pGraphicsCreator
     */
    public void setPGraphicsCreator(PGraphicsCreator pGraphicsCreator) {
    
    	this.pGraphicsCreator = pGraphicsCreator;
    }
}

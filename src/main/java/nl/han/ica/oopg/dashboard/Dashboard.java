package nl.han.ica.oopg.dashboard;

import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.view.PGraphicsCreator;
import processing.core.PGraphics;

import java.util.Vector;

/**
 * Create or extend this class to create a new dashboard, a dashboard object
 * will be drawn above the ViewPort when added to the dashboard list inside the
 * GameEngine (addDashboard).
 */
public class Dashboard extends GameObject {

    private Integer backgroundR;
    private Integer backgroundG;
    private Integer backgroundB;

    private Sprite backgroundImage;

    private PGraphicsCreator pGraphicsCreator = new PGraphicsCreator();

    private Vector<GameObject> gameObjects = new Vector<>();

    public Dashboard(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    /**
     * Override this method to update the objects that need to be drawn.
     */
    @Override
    public void update() {
        // Override this method to update the objects that need to be drawn.
    }

    /**
     * Draws all the GameObjects inside the dashboard on the given canvas.
     *
     * @param g PGraphics object will be given by the GameEngine.
     */
    @Override
    public void draw(PGraphics g) {

        PGraphics canvas = drawCanvas();
        g.image(canvas, this.getX(), this.getY());
    }

    /**
     * Draws the canvas (dashboard).
     */
    private PGraphics drawCanvas() {

        PGraphics canvas = pGraphicsCreator.createPGraphics(
                (int) this.getWidth(), (int) this.getHeight());

        canvas.beginDraw();
        canvas.noStroke();
        setBackgroundFor(canvas);
        drawObjectsTo(canvas);
        canvas.endDraw();

        return canvas;
    }

    /**
     * Draw the dashboardObjects from type GameObject on the canvas which are set visible.
     *
     * @param canvas The Canvas as an PGraphics
     */
    private void drawObjectsTo(PGraphics canvas) {

        for (int i = 0; i < gameObjects.size(); i++) {
            drawVisibleGameObjects(canvas, i);
        }
    }

    /**
     * Actually draws GameObjects that are set visible to the dashboard.
     *
     * @param canvas The Canvas
     * @param i      The index of the GameObject that should be mae visible
     */
    private void drawVisibleGameObjects(PGraphics canvas, int i) {
        if (gameObjects.get(i).isVisible()) {
            gameObjects.get(i).draw(canvas);
        }
    }

    /**
     * Sets background for the dashboard. RGB when backgroundActive is false,
     * Image when backgroundActive is true.
     *
     * @param canvas The Canvas that should have a backgrund set
     */
    private void setBackgroundFor(PGraphics canvas) {

        if (backgroundR != null && backgroundG != null && backgroundB != null) {
            canvas.background(backgroundR, backgroundG, backgroundB);
        }

        if (backgroundImage != null) {
            canvas.image(backgroundImage.getPImage(), 0, 0, width, height);
        }
    }

    /**
     * Add a GameObject to the dashboard.
     *
     * @param gameObject The GameObject that will be added to the canvas. Sets the X
     *                   and Y relatively to the canvas, so the GameObjects will move
     *                   with the dashboard.
     */
    public void addGameObject(GameObject gameObject) {

        gameObject.setX(this.getX() + gameObject.getX());
        gameObject.setY(this.getY() + gameObject.getY());

        gameObjects.add(gameObject);
    }

    /**
     * Add a GameObject to the dashboard.
     *
     * @param gameObject The GameObject that should be added
     * @param x          The x cooridinate on which the GameObject should be added
     * @param y          The y cooridinate on which the GameObject should be added
     */
    public void addGameObject(GameObject gameObject, int x, int y) {

        gameObjects.add(gameObject);

        gameObject.setX(this.getX() + (float) x);
        gameObject.setY(this.getY() + (float) y);
    }

    /**
     * Add a GameObject to the dashboard.
     *
     * @param gameObject    The GameObject that should be added
     * @param x             The x cooridinate on which the GameObject should be added
     * @param y             The y cooridinate on which the GameObject should be added
     * @param layerposition The layer position on which the GameObjects should be created
     */
    public void addGameObject(GameObject gameObject, int x, int y,
                              float layerposition) {

        gameObjects.add(gameObject);

        gameObject.setX((float) x);
        gameObject.setY((float) y);
        gameObject.setZ(layerposition);
    }

    /**
     * Add a GameObject to the dashboard.
     *
     * @param gameObject    The GameObject that should be added
     * @param layerposition The layer position on which the GameObjects should be created
     */
    public void addGameObject(GameObject gameObject, float layerposition) {

        gameObjects.add(gameObject);

        gameObject.setZ(layerposition);
    }

    /**
     * Get a list of all the GameObjects inside the dashboard.
     *
     * @return All GameObjects
     */
    public Vector<GameObject> getGameObjects() {

        return gameObjects;
    }

    /**
     * Delete a GameObject from the dashboard.
     *
     * @param gameObject The GameObject that should be deleted
     */
    public void deleteGameObject(GameObject gameObject) {

        gameObjects.remove(gameObject);
    }

    /**
     * Deletes all GameObjects from the dashboard.
     */
    public void deleteAllDashboardObjects() {

        gameObjects.removeAllElements();
    }

    /**
     * Deletes all GameObjects with the given type from the dashboard.
     * <p>
     * Example paramater: Player.class
     *
     * @param type The type of the gameobjects
     * @param <T>  Generic type, should extend GameObject
     */
    public <T extends GameObject> void deleteAllGameObjectsOfType(Class<T> type) {
        gameObjects.removeIf(p -> type.equals(p.getClass()));
    }

    /**
     * Set the background of the dashboard with RGB-values.
     *
     * @param r Red value of the background
     * @param g Green value of the background
     * @param b Blue value of the background
     */
    public void setBackground(Integer r, Integer g, Integer b) {

        backgroundR = r;
        backgroundG = g;
        backgroundB = b;
    }

    /**
     * Set the background of the dashboard with a Sprite object (image).
     *
     * @param sprite The Sprite that should be used as the background
     */
    public void setBackgroundImage(Sprite sprite) {

        backgroundImage = sprite;
    }

    /**
     * Sets the PGraphicsCreator which can create the canvas where to draw on.
     *
     * @param pGraphicsCreator The PGraphicsCreator that should be used
     */
    public void setPGraphicsCreator(PGraphicsCreator pGraphicsCreator) {

        this.pGraphicsCreator = pGraphicsCreator;
    }
}

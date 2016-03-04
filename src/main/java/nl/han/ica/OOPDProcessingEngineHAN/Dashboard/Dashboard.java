package nl.han.ica.OOPDProcessingEngineHAN.Dashboard;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.View.PGraphicsCreator;
import processing.core.PGraphics;

import java.util.Vector;

/**
 * Create or extend this class to create a new Dashboard, a Dashboard object
 * will be drawn above the ViewPort when added to the Dashboard list inside the
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
	 * Draws all the GameObjects inside the Dashboard on the given canvas.
	 *
	 * @param g
	 *            PGraphics object will be given by the GameEngine.
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
	 */
	private void drawObjectsTo(PGraphics canvas) {

		for (int i = 0; i < gameObjects.size(); i++) {
			drawVisibleGameObjects(canvas, i);
		}
	}

	/**
	 * Actually draws GameObjects that are set visible to the Dashboard.
	 * @param canvas
	 * @param i
	 */
	private void drawVisibleGameObjects(PGraphics canvas, int i) {
		if(gameObjects.get(i).isVisible()) {
            gameObjects.get(i).draw(canvas);
        }
	}

	/**
	 * Sets background for the dashboard. RGB when backgroundActive is false,
	 * Image when backgroundActive is true.
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
	 * Add a GameObject to the Dashboard.
	 *
	 * @param gameObject
	 *            The GameObject that will be added to the canvas. Sets the X
	 *            and Y relatively to the canvas, so the GameObjects will move
	 *            with the Dashboard.
	 */
	public void addGameObject(GameObject gameObject) {

		gameObject.setX(this.getX() + gameObject.getX());
		gameObject.setY(this.getY() + gameObject.getY());

		gameObjects.add(gameObject);
	}

	/**
	 * Add a GameObject to the Dashboard.
	 *
	 * @param gameObject
	 * @param x
	 * @param y
	 */
	public void addGameObject(GameObject gameObject, int x, int y) {

		gameObjects.add(gameObject);

		gameObject.setX(this.getX() + (float) x);
		gameObject.setY(this.getY() + (float) y);
	}

	/**
	 * Add a GameObject to the Dashboard.
	 * 
	 * @param gameObject
	 * @param x
	 * @param y
	 * @param layerposition
	 */
	public void addGameObject(GameObject gameObject, int x, int y,
			float layerposition) {

		gameObjects.add(gameObject);

		gameObject.setX((float) x);
		gameObject.setY((float) y);
		gameObject.setZ(layerposition);
	}

	/**
	 * Add a GameObject to the Dashboard.
	 * 
	 * @param gameObject
	 * @param layerposition
	 */
	public void addGameObject(GameObject gameObject, float layerposition) {

		gameObjects.add(gameObject);

		gameObject.setZ(layerposition);
	}

	/**
	 * Get a list of all the GameObjects inside the Dashboard.
	 */
	public Vector<GameObject> getGameObjects() {

		return gameObjects;
	}

	/**
	 * Delete a GameObject from the Dashboard.
	 *
	 * @param gameObject
	 */
	public void deleteGameObject(GameObject gameObject) {

		gameObjects.remove(gameObject);
	}

	/**
	 * Deletes all GameObjects from the Dashboard.
	 */
	public void deleteAllDashboardObjects() {

		gameObjects.removeAllElements();
	}

	/**
	 * Deletes all GameObjects with the given type from the Dashboard.
	 *
	 * Example paramater: Player.class
	 *
	 * @param type
	 * @param <T>
	 */
	public <T extends GameObject> void deleteAllGameObjectsOfType(Class<T> type) {
		gameObjects.removeIf(p -> type.equals(p.getClass()));
	}

	/**
	 * Set the background of the Dashboard with RGB-values.
	 * 
	 * @param r
	 * @param g
	 * @param b
	 */
	public void setBackground(Integer r, Integer g, Integer b) {

		backgroundR = r;
		backgroundG = g;
		backgroundB = b;
	}

	/**
	 * Set the background of the Dashboard with a Sprite object (image).
	 * 
	 * @param sprite
	 */
	public void setBackgroundImage(Sprite sprite) {

		backgroundImage = sprite;
	}

	/**
	 * Sets the PGraphicsCreator which can create the canvas where to draw on.
	 * 
	 * @param pGraphicsCreator
	 */
	public void setPGraphicsCreator(PGraphicsCreator pGraphicsCreator) {

		this.pGraphicsCreator = pGraphicsCreator;
	}
}

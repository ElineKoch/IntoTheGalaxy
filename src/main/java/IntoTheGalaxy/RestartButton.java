package IntoTheGalaxy;

/**
 * A button that you can click to delete the endscreen and create the startscreen.
 */
public class RestartButton extends Button {
	
	public RestartButton(IntoTheGalaxy world, float x, float y, float width, float height, String text, int color1, int color2) {
		super(world, x, y, width, height, text, color1, color2);
	}
	
	/**
	 * Deletes the endscreen and creates the startscreen.
	 */
	@Override
	public void doButtonAction() {
		world.deleteAllGameOBjects();
		world.deleteAllDashboards();
		world.setGameState(0);
		world.setupGame();
	}
}

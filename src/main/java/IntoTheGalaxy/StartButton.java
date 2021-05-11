package IntoTheGalaxy;

/**
 * A button that you can click to delete the startscreen and create the gamescreen.
 */
public class StartButton extends Button {
	
	public StartButton(IntoTheGalaxy world, float x, float y, float width, float height, String text, int color1, int color2) {
		super(world, x, y, width, height, text, color1, color2);
	}
	
	/**
	 * Deletes the startscreen and creates the gamescreen.
	 */
	@Override
	public void doButtonAction() {
		world.deleteAllGameOBjects();
		world.deleteAllDashboards();
		world.startGame();
	}
	
}

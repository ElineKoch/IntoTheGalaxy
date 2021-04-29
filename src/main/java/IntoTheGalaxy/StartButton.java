package IntoTheGalaxy;

public class StartButton extends Button {
	
	public StartButton(IntoTheGalaxy world, float x, float y, float width, float height, String text, int color1, int color2) {
		super(world, x, y, width, height, text, color1, color2);
	}

	@Override
	public void doButtonAction() {
		world.deleteAllGameOBjects();
		world.deleteAllDashboards();
		world.setGameState(1);
		world.setupGame();
	}
}

package IntoTheGalaxy;

import nl.han.ica.oopg.objects.GameObject;
import processing.core.PConstants;
import processing.core.PGraphics;

/**
 * An abstract class to create a button that can interact with the world when you click on it.
 */
public abstract class Button extends GameObject {
	protected IntoTheGalaxy world;
	protected String text;
	protected int color1, color2;

	public Button(IntoTheGalaxy world, float x, float y, float width, float height, String text, int color1, int color2) {
		super(x, y, width, height);
		this.text = text;
		this.color1 = color1;
		this.color2 = color2;
		this.world = world;
	}
	
	/**
	 * Currently does nothing.
	 */
	@Override
	public void update() {
	}
	
	/**
	 * When you click with the mouse, checks if you click on the button, then activates button action.
	 */
	@Override
	public void mouseClicked(int mouseX, int mouseY, int button) {
		if (mouseX > x && mouseX < x + width && mouseY > y && mouseY < y + height) {
			doButtonAction();
		}
	}
	
	/**
	 * What the button does, an abstract method to be implemented in the subclass.
	 */
	public abstract void doButtonAction();
	
	/**
	 * Overrides GameObject.draw() to draw the button.
	 */
	@Override
	public void draw(PGraphics g) {
		g.hint(ENABLE_STROKE_PURE);
		g.stroke(color1);
		g.fill(color2);
		g.rect(x, y, width, height);
		
		g.fill(color1);
		g.textAlign(PConstants.CENTER, PConstants.CENTER);
		g.textSize(width / 8);
		g.text(text, x + width / 2, y + height / 2);
		g.stroke(0);
	}
}

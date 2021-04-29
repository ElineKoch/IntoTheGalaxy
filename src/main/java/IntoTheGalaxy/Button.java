package IntoTheGalaxy;

import nl.han.ica.oopg.objects.GameObject;
import processing.core.PConstants;
import processing.core.PGraphics;

public abstract class Button extends GameObject {
	IntoTheGalaxy world;
	String text;
	int color1, color2;

	public Button(IntoTheGalaxy world, float x, float y, float width, float height, String text, int color1, int color2) {
		super(x, y, width, height);
		this.text = text;
		this.color1 = color1;
		this.color2 = color2;
		this.world = world;
	}

	@Override
	public void update() {
	}

	public void mouseClicked(int mouseX, int mouseY, int button) {
		if (mouseX > x && mouseX < x + width && mouseY > y && mouseY < y + height) {
			doButtonAction();
		}
	}

	public abstract void doButtonAction();

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

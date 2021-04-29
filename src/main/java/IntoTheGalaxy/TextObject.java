package IntoTheGalaxy;

import nl.han.ica.oopg.objects.GameObject;
import processing.core.PConstants;
import processing.core.PGraphics;

public class TextObject extends GameObject{
	private String text;
	
	public TextObject() {
		text = "";
	}

	public void setText(String text) {
		this.text = text;
	}
	
	@Override
	public void update() {
	}

	@Override
	public void draw(PGraphics g) {
		g.fill(255);
		g.textAlign(PConstants.CENTER, PConstants.CENTER);
		g.textSize(16);
		g.text(text, getX(), getY());
	}

}

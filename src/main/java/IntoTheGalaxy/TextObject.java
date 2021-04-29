package IntoTheGalaxy;

import nl.han.ica.oopg.objects.GameObject;
import processing.core.PConstants;
import processing.core.PGraphics;

public class TextObject extends GameObject{
	private String text;
	private int fontSize;
	int color;
	
	public TextObject(String text, int fontSize, int color) {
		super();
		this.text = text;
		this.fontSize = fontSize;
		this.color = color;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	@Override
	public void update() {
	}

	@Override
	public void draw(PGraphics g) {
		g.fill(color);
		g.textAlign(PConstants.CENTER, PConstants.CENTER);
		g.textSize(fontSize);
		g.text(text, getX(), getY());
	}

}

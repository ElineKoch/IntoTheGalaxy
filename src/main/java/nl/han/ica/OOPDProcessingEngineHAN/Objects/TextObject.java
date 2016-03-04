package nl.han.ica.OOPDProcessingEngineHAN.Objects;

import processing.core.PGraphics;

/**
 * Use this class to draw text. You could also implement it by your own by overriding the GameObject class and use Processing to draw text.
 */
public class TextObject extends GameObject {

	private String text;
	private int fontSize;
	private int r, g, b, alpha = 255;
	
	/**
	 * Create a new TextObject.
	 * @param text
	 * @param fontSize
	 */
	public TextObject(String text, int fontSize) {
		
		setText(text);
		setFontSize(fontSize);
	}
	
	/**
	 * Override or implement this method to handle updates inside this object.
	 */
	@Override
	public void update() {
		
	}

	/**
	 * Draws the text on the PGraphics object, this is fired by the GameEngine.
	 */
	@Override
	public void draw(PGraphics g) {
	
		g.fill(this.r, this.g, this.b, this.alpha);
		g.textSize(fontSize);
		g.text(text, x, y);
	}
	
	/**
	 * Sets the text that will be displayed.
	 * @param text
	 */
	public void setText(String text) {
		
		this.text = text;
	}
	
	/**
	 * Gets the text thats is being displayed while drawing.
	 * @return String
	 */
	public String getText() {
		
		return text;
	}
	
	/**
	 * Sets the font size of the text.
	 * @param fontSize The value has to be higher than 0.
	 */
	public void setFontSize(int fontSize) {
		
		this.fontSize = fontSize;
	}
	
	/**
	 * Gets the font size of the text.
	 * @return int
	 */
	public int getFontSize() {
		
		return fontSize;
	}
	
	/**
	 * Sets the fore color of the text.
	 * @param r The min and max value are 0-255.
	 * @param g The min and max value are 0-255.
	 * @param b The min and max value are 0-255.
	 * @param alpha The min and max value are 0-255.
	 */
	public void setForeColor(int r, int g, int b, int alpha) {
		
		this.r = r;
		this.g = g;
		this.b = b;
		
		this.alpha = alpha;
	}
	
	/**
	 * Gets the red value. (0-225)
	 * @return int
	 */
	public int getRedValue() {
		
		return r;
	}
	
	/**
	 * Gets the green value. (0-225)
	 * @return int
	 */
	public int getGreenValue() {
			
		return g;
	}

	/**
	 * Gets the blue value. (0-225)
	 * @return int
	 */
	public int getBlueValue() {
		
		return b;
	}

	/**
	 * Gets the alpha value. (0-225)
	 * @return int
	 */
	public int getAlphaValue() {
		
		return alpha;
	}
}

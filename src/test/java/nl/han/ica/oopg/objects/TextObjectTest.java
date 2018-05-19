package nl.han.ica.oopg.objects;

import static org.junit.Assert.*;
import nl.han.ica.oopg.pgraphicsstub.PGraphicsCreatorMock;
import nl.han.ica.oopg.pgraphicsstub.PGraphicsMock;

import org.junit.Before;
import org.junit.Test;

public class TextObjectTest {

	private PGraphicsMock g;
	private TextObject obj;
	
	@Before
	public void setup() {
		
		PGraphicsCreatorMock pGraphicsCreator = new PGraphicsCreatorMock();
		this.g = pGraphicsCreator.createPGraphics(100, 100);
		
		this.obj = new TextObject("TEST", 12);
	}
	
	@Test
	public void setTextShouldSetCorrectly() {
		
		String value = "This string should not match the value set on the setup.";
		obj.setText(value);
		
		assertEquals(value, obj.getText());
	}
	
	@Test
	public void setFontSizeShouldSetCorrectly() {
		
		obj.setFontSize(44);
		
		assertEquals(44, obj.getFontSize());
	}
	
	@Test
	public void setForeColorShouldSetCorrectly() {
		
		int r = 111;
		int g = 112;
		int b = 113;
		int alpha = 114;
		
		obj.setForeColor(r, g, b, alpha);
		
		assertEquals(r, obj.getRedValue());
		assertEquals(g, obj.getGreenValue());
		assertEquals(b, obj.getBlueValue());
		assertEquals(alpha, obj.getAlphaValue());
	}
	
	@Test
	public void drawShouldDisplayTheGivenText() {
		
		String value = "This string should not match the value set on the setup.";
		
		obj.setText(value);
		obj.draw(g);
		
		TextObject drawedObj = (TextObject)g.getGameObjects().get(0);
		
		assertEquals(value, drawedObj.getText());
	}
	
	@Test
	public void drawShouldDisplayTheGivenTextWithTheCorrectFontSize() {
		
		obj.setFontSize(46);
		obj.draw(g);

		TextObject drawedObj = (TextObject)g.getGameObjects().get(0);
		
		assertEquals(46, drawedObj.getFontSize());
	}
	
	@Test
	public void drawShouldDisplayTheGivenTextWithTheCorrectForeColor() {
		
		int r = 111;
		int g = 112;
		int b = 113;
		int alpha = 114;
		
		obj.setForeColor(r, g, b, alpha);
		obj.draw(this.g);

		TextObject drawedObj = (TextObject)this.g.getGameObjects().get(0);
		
		assertEquals(r, obj.getRedValue());
		assertEquals(g, obj.getGreenValue());
		assertEquals(b, obj.getBlueValue());
		assertEquals(alpha, obj.getAlphaValue());
	}
	
	@Test
	public void update() {
		
		obj.update();
	}
}

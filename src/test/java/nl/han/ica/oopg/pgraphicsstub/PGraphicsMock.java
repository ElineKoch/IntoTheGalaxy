package nl.han.ica.oopg.pgraphicsstub;

import java.util.ArrayList;
import java.util.List;

import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.TextObject;
import processing.core.PGraphics;
import processing.core.PImage;

public class PGraphicsMock extends PGraphics // May only be used inside the test package
{
	private List<GameObject> objects = new ArrayList<GameObject>();
	private float[] backgroundRGB = new float[] { -1, -1, -1 };
	private int[] fillRGBA = new int[] { -1, -1, -1, -1 };
	private int textSize = -1;
	
	public PGraphicsMock(int worldWidth, int worldHeight) {
		super();
		
		this.init(worldWidth, worldHeight, 0);
	}
	
	@Override
	public void image(PImage img, float a, float b) {
		
		GameObject obj = new PGraphicsGameObject(img);
		obj.setX(a);
		obj.setY(b);
		
		objects.add(obj);
	}

	@Override
	public void image(PImage img, float a, float b, float c, float d) {

		image(img, a, b);
	}
	
	@Override 
	public void background(float v1, float v2, float v3) {
		
		this.backgroundRGB = new float[] { v1, v2, v3 };
	}
	
	@Override 
	public void text(String str, float x, float y)  {
	
		TextObject obj = new TextObject(str, (int)textSize);
		obj.setX(x);
		obj.setY(y);
		obj.setForeColor(fillRGBA[0], fillRGBA[1], fillRGBA[2], fillRGBA[3]);
		
		objects.add(obj);
	}
	
	@Override 
	public void fill(float v1, float v2, float v3, float alpha) { 
		
		this.fillRGBA = new int[] { (int)v1, (int)v2, (int)v3, (int)alpha };
	}
	
	@Override 
	public void textSize(float size) {
		
		this.textSize = (int)size;
	};
	
	public List<GameObject> getGameObjects() {
		
		return objects;
	}
	
	public float[] getBackground() {
		
		return backgroundRGB;
	}
	
	// These overrides are neccesarrily because PImages throws runtime errors.
	@Override public void pushMatrix() { };
	@Override public void resetMatrix() { };
	@Override public void popMatrix() { };
	@Override public void blendMode(int mode) { };
}

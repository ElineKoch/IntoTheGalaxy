package nl.han.ica.OOPDProcessingEngineHAN.PGraphicsStub;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;
import processing.core.PImage;

public class PGraphicsGameObject extends GameObject {

	private PImage canvas;

	public PGraphicsGameObject(PImage canvas) {

		this.canvas = canvas;
	}

	@Override
	public void update() {

	}

	@Override
	public void draw(PGraphics g) {

	}

	public PImage getCanvas() {

		return canvas;
	}
}

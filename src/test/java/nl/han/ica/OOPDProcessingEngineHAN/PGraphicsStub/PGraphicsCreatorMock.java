package nl.han.ica.OOPDProcessingEngineHAN.PGraphicsStub;

import nl.han.ica.OOPDProcessingEngineHAN.View.PGraphicsCreator;

public class PGraphicsCreatorMock extends PGraphicsCreator // May only be used inside the test package
{
	@Override
	public PGraphicsMock createPGraphics(int worldWidth, int worldHeight) {
		
		return new PGraphicsMock(worldWidth, worldHeight);
	}
}

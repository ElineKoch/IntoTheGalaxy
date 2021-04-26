package IntoTheGalaxy;

import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.sound.Sound;

public class Butterfly extends Alien {

	public Butterfly(IntoTheGalaxy world, Sound explosionSound, int xPos, int yPos) {
		super(world, explosionSound, xPos, yPos);
		sprite = new Sprite(IntoTheGalaxy.MEDIA_URL.concat("Butterfly.png"));
	}

	@Override
	public void doDiveAction() {
		System.out.println("Butterfly");
	}
}

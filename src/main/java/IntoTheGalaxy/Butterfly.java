package IntoTheGalaxy;

import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.sound.Sound;

public class Butterfly extends Alien {

	public Butterfly(IntoTheGalaxy world, float xPos, float yPos, Sound shootSound, Sound explosionSound) {
		super(world, xPos, yPos, shootSound, explosionSound);
		sprite = new Sprite(IntoTheGalaxy.MEDIA_URL.concat("Butterfly.png"));
	}

	@Override
	public void doDiveAction() {
		setySpeed(1);
	}
}

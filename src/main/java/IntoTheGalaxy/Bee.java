package IntoTheGalaxy;

import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.sound.Sound;

public class Bee extends Alien {

	public Bee(IntoTheGalaxy world, int xPos, int yPos, Sound shootSound, Sound explosionSound) {
		super(world, xPos, yPos, shootSound, explosionSound);
		sprite = new Sprite(IntoTheGalaxy.MEDIA_URL.concat("Bee.png"));
	}

	@Override
	public void doDiveAction() {
		setDirectionSpeed(180, 2);
	}

	@Override
	public void returnToStart() {
		setY(0);
	}
}

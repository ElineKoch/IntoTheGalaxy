package IntoTheGalaxy;

import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.sound.Sound;

public class Boss extends Alien {

	public Boss(IntoTheGalaxy world, float xPos, float yPos, Sound shootSound, Sound explosionSound) {
		super(world, xPos, yPos, shootSound, explosionSound);
		sprite = new Sprite(IntoTheGalaxy.MEDIA_URL.concat("Boss1.png"));
		setLives(2);
		setStartX(xPos * 150);
	}

	@Override
	public void doDiveAction() {
		setySpeed(3);
		setxSpeed(getxSpeed() * 4);
	}
}

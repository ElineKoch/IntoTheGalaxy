package IntoTheGalaxy;

import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.sound.Sound;

/**
 *A type of alien that looks like a butterfly. Dives in a zigzag pattern.
 */
public class Butterfly extends Alien {

	public Butterfly(IntoTheGalaxy world, float xPos, float yPos, Sound shootSound, Sound explosionSound) {
		super(world, xPos, yPos, shootSound, explosionSound);
		sprite = new Sprite(IntoTheGalaxy.MEDIA_URL.concat("Butterfly.png"));
	}
	
	/**
	 *While diving keeps moving side to side, moves down with speed 1.
	 */
	@Override
	public void doDiveAction() {
		setySpeed(1);
	}
}

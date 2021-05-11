package IntoTheGalaxy;

import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.sound.Sound;

/**
 *A type of alien that looks like a bee. Doesn't return to the startposition after a dive, 
 *instead continues the dive from the top of the screen.
 */
public class Bee extends Alien {

	public Bee(IntoTheGalaxy world, int xPos, int yPos, Sound shootSound, Sound explosionSound) {
		super(world, xPos, yPos, shootSound, explosionSound, new Sprite(IntoTheGalaxy.MEDIA_URL.concat("Bee.png")));
	}
	
	/**
	 *Dives straight down with speed 2.
	 */
	@Override
	public void doDiveAction() {
		setDirectionSpeed(180, 2);
	}
	
	/**
	 *Overrides the method in Alien to go to the top of the screen at the same x value as the startposition, 
	 *also keep the ySpeed, so keeps moving down.
	 */
	@Override
	public void returnToStart() {
		setY(0);
	}
}

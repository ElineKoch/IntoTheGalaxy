package IntoTheGalaxy;

import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.sound.Sound;

public class Boss extends Alien {
	
	public Boss(IntoTheGalaxy world, Sound explosionSound, int xPos, int yPos) {
		super(world, explosionSound, xPos, yPos);
		sprite = new Sprite(IntoTheGalaxy.MEDIA_URL.concat("Boss1.png"));
		setLives(10);
		setStartX(xPos * 150);
	}

	@Override
	public void doDiveAction() {
		System.out.println("Boss");
	}
}

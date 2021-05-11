package IntoTheGalaxy;

import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.sound.Sound;

public class Cat extends Alien {

	public Cat(IntoTheGalaxy world, float xPos, float yPos, Sound shootSound, Sound explosionSound) {
		super(world, xPos, yPos, shootSound, explosionSound, new Sprite(""));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doDiveAction() {
		set()
		
	}
	
	@Override
	public void shoot() {
		
	}
}

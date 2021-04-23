package TutorialWorld;

import java.util.Random;

import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;
import nl.han.ica.oopg.sound.Sound;

public class DiscSpawner implements IAlarmListener {
	private Random random;
	private TutorialWorld world;
	private int discsPerSecond;
	private Sound explosionSound;
	
	public DiscSpawner(TutorialWorld world, int discsPerSecond, Sound explosionSound) {
		this.world = world;
		random = new Random();
		this.discsPerSecond = discsPerSecond;
		this.explosionSound = explosionSound;
		startAlarm();
	}
	
	private void startAlarm() {
	    Alarm alarm = new Alarm("New Disc", 1 / discsPerSecond);
	    alarm.addTarget(this);
	    alarm.start();
	}

	
	@Override
	public void triggerAlarm(String alarmName) {
	    Disc disc = new Disc(world, explosionSound);
	    world.addGameObject(disc, random.nextInt(world.width), 50);
	    startAlarm();
	}

}
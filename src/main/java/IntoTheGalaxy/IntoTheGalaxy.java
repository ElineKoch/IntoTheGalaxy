package IntoTheGalaxy;

import java.util.*;
import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.view.View;
import nl.han.ica.oopg.sound.Sound;

@SuppressWarnings("serial")
public class IntoTheGalaxy extends GameEngine {
	private int time;
	private int numWaves;
	private int currentWave;

	private Sound fighterShootSound, fighterExplosionSound, alienShootSound, alienExplosionSound;

	private Dashboard gameDashboard;
	private float widthDashboard;
	private float widthGameScreen;

	private Fighter fighter;
	private float xFighter;
	private float yFighter;

	public ArrayList<Alien> alienList;
	public int[] numAliens = { 3, 20, 20 };

	public static String MEDIA_URL = "src/main/java/IntoTheGalaxy/media/";

	public static void main(String[] args) {
		IntoTheGalaxy itg = new IntoTheGalaxy();
		itg.runSketch();
	}

	@Override
	public void setupGame() {
		int worldWidth = 800;
		int worldHeight = 600;
		time = 0;
		numWaves = 3;
		currentWave = 1;

		fighterShootSound = new Sound(this, MEDIA_URL.concat("fighterShoot.wav"));
		fighterExplosionSound = new Sound(this, MEDIA_URL.concat("fighterExplosion.wav"));
		alienShootSound = new Sound(this, MEDIA_URL.concat("alienShoot.wav"));
		alienExplosionSound = new Sound(this, MEDIA_URL.concat("alienExplosion.wav"));

		widthDashboard = worldWidth / 6;
		widthGameScreen = worldWidth - widthDashboard;

		xFighter = widthGameScreen / 2;
		yFighter = widthGameScreen * 7 / 8;

		createObjects(worldWidth, worldHeight);
		createView(worldWidth, worldHeight);
	}

	public void createObjects(int worldWidth, int worldHeight) {
		createDashboard(worldWidth, worldHeight);
		createFighter();
		createAliens();
	}

	public void createDashboard(int worldWidth, int worldHeight) {
		gameDashboard = new Dashboard(worldWidth - widthDashboard, 0, widthDashboard, worldHeight);
		gameDashboard.setBackground(0, 0, 0);
		addDashboard(gameDashboard);
	}

	public void createFighter() {
		fighter = new Fighter(this, fighterShootSound, fighterExplosionSound);
		addGameObject(fighter, xFighter, yFighter);
	}

	public void createAliens() {
		alienList = new ArrayList<Alien>();

		for (int i = 0; i < numAliens[0]; i++) {
			Alien boss = new Boss(this, i + 1, i / 10, alienShootSound, alienExplosionSound);
			alienList.add(boss);
		}

		for (int i = 0; i < numAliens[1]; i++) {
			Alien butterfly = new Butterfly(this, i % 10 + 2, i / 10 + 1, alienShootSound, alienExplosionSound);
			alienList.add(butterfly);
		}

		for (int i = 0; i < numAliens[2]; i++) {
			Alien bee = new Bee(this, i % 10 + 2, i / 10 + 3, alienShootSound, alienExplosionSound);
			alienList.add(bee);
		}

		for (Alien alien : alienList) {
			addGameObject(alien, alien.getX(), alien.getY());
		}
	}

	private void createView(int width, int height) {
		View view = new View(width, height);
		setView(view);
		size(width, height);
		view.setBackground(loadImage(IntoTheGalaxy.MEDIA_URL.concat("SpaceBackground.png")));
	}

	@Override
	public void update() {
		time++;
		spawnNewAliens();
		
		endGame();
	}
	
	public void spawnNewAliens() {
		if (alienList.size() == 0) {
			currentWave++;
			createAliens();
		}
	}
	
	public void endGame() {
		if (currentWave > numWaves) {
			deleteAllGameOBjects();
		}
	}

	public int getTime() {
		return time;
	}

	public float getXFighter() {
		return xFighter;
	}

	public float getYFighter() {
		return yFighter;
	}
}
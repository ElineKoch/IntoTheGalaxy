package IntoTheGalaxy;

import java.util.*;

import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.persistence.FilePersistence;
import nl.han.ica.oopg.view.View;
import nl.han.ica.oopg.sound.Sound;

@SuppressWarnings("serial")
public class IntoTheGalaxy extends GameEngine {
	private int time;
	private int numWaves;
	private int currentWave;
	private int highscore;
	private int score;
	private FilePersistence persistence;

	public static String MEDIA_URL = "src/main/java/IntoTheGalaxy/media/";
	private Sound fighterShootSound, fighterExplosionSound, alienShootSound, alienExplosionSound;

	private Dashboard gameDashboard;
	private float widthDashboard;
	private float widthGameScreen;
	private TextObject[] textObjects = new TextObject[4];

	private Fighter fighter;

	public ArrayList<Alien> alienList;
	public int[] numAliens = { 3, 20, 20 };

	public static void main(String[] args) {
		IntoTheGalaxy itg = new IntoTheGalaxy();
		itg.runSketch();
	}

	@Override
	public void setupGame() {
		int worldWidth = 800;
		int worldHeight = 600;

		widthDashboard = worldWidth / 6;
		widthGameScreen = worldWidth - widthDashboard;

		time = 0;
		numWaves = 3;
		currentWave = 1;
		score = 0;

		initializePersistence();
		initializeSound();
		createObjects(worldWidth, worldHeight);
		createView(worldWidth, worldHeight);
	}

	private void initializePersistence() {
		persistence = new FilePersistence("main/java/IntoTheGalaxy/media/highscore.txt");
		if (persistence.fileExists()) {
			highscore = Integer.parseInt(persistence.loadDataString());
		}
	}

	private void initializeSound() {
		fighterShootSound = new Sound(this, MEDIA_URL.concat("fighterShoot.wav"));
		fighterExplosionSound = new Sound(this, MEDIA_URL.concat("fighterExplosion.wav"));
		alienShootSound = new Sound(this, MEDIA_URL.concat("alienShoot.wav"));
		alienExplosionSound = new Sound(this, MEDIA_URL.concat("alienExplosion.wav"));
	}

	public void createObjects(int worldWidth, int worldHeight) {
		createFighter(worldHeight);
		createAliens();
		createDashboard(worldWidth, worldHeight);
	}

	private void createDashboard(int worldWidth, int worldHeight) {
		gameDashboard = new Dashboard(worldWidth - widthDashboard, 0, widthDashboard, worldHeight);
		gameDashboard.setBackground(0, 0, 0);
		for (int i = 0; i < textObjects.length; i++) {
			textObjects[i] = new TextObject();
			gameDashboard.addGameObject(textObjects[i], (int) widthDashboard / 2, (int) ((i + 0.5) * 100), 10);
		}
		addDashboard(gameDashboard);
	}

	private void createFighter(int worldHeight) {
		fighter = new Fighter(this, widthGameScreen / 2, worldHeight * 7 / 8, fighterShootSound, fighterExplosionSound);
		addGameObject(fighter);
	}

	private void createAliens() {
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
		refreshDashboardText();
		endGame();
	}

	private void spawnNewAliens() {
		if (alienList.size() == 0) {
			currentWave++;
			createAliens();
		}
	}

	private void refreshDashboardText() {
		textObjects[0].setText("Highscore: " + highscore);
		textObjects[1].setText("Score: " + score);
		textObjects[2].setText("Wave: " + currentWave);
		textObjects[3].setText("Lives: " + fighter.getNumLives());
	}

	public void endGame() {
		if (currentWave > numWaves || fighter.getNumLives() == 0) {
			deleteAllGameOBjects();

			if (score > highscore) {
				persistence.saveData(Integer.toString(score));
				highscore = Integer.parseInt(persistence.loadDataString());
				refreshDashboardText();
			}
		}
	}

	public int getTime() {
		return time;
	}

	public void increaseScore(int points) {
		score += points;
	}
}
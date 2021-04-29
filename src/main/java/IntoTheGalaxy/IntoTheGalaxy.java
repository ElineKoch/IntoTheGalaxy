package IntoTheGalaxy;

import java.util.*;
import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.persistence.FilePersistence;
import nl.han.ica.oopg.view.View;
import nl.han.ica.oopg.sound.Sound;

@SuppressWarnings("serial")
public class IntoTheGalaxy extends GameEngine {
	private int gameState = 0;
	private int worldWidth;
	private int worldHeight;
	private int time;
	private int numWaves;
	private int currentWave;
	private int highscore;
	private int score;
	private FilePersistence persistence;

	public static String MEDIA_URL = "src/main/java/IntoTheGalaxy/media/";
	private Sound fighterShootSound, fighterExplosionSound, alienShootSound, alienExplosionSound;

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
		worldWidth = 800;
		worldHeight = 600;

		initializePersistence();
		initializeSound();

		switch (gameState) {
			case 0:
				createStartScreen();
				break;
			case 1:
				time = 0;
				numWaves = 3;
				currentWave = 1;
				score = 0;
				
				widthDashboard = worldWidth / 6;
				widthGameScreen = worldWidth - widthDashboard;
				
				createObjects();
				gameState = 2;
				break;
			case 3:
				createEndScreen();
				break;
		}
		
		createView();
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
	
	private void createStartScreen() {
		Dashboard startDashboard = new Dashboard(0, 0, worldWidth, worldHeight);
		
		TextObject title = new TextObject("Into The Galaxy", 60, 255);
		startDashboard.addGameObject(title, worldWidth / 2, worldHeight / 4);
		
		TextObject highscoreText = new TextObject("Highscore: " + highscore, 20, 255);
		startDashboard.addGameObject(highscoreText, worldWidth / 2, worldHeight * 8 / 10);
		
		addDashboard(startDashboard);
		
		Button startButton = new StartButton(this, worldWidth / 2 - 50, worldHeight / 2 - 30, 100, 60, "Start Game", 255, 0);
		addGameObject(startButton);
	}
	
	public void createObjects() {
		createFighter();
		createAliens();
		createDashboard();
	}

	private void createDashboard() {
		Dashboard gameDashboard = new Dashboard(worldWidth - widthDashboard, 0, widthDashboard, worldHeight);
		gameDashboard.setBackground(0, 0, 0);
		for (int i = 0; i < textObjects.length; i++) {
			textObjects[i] = new TextObject("", 16, 160 + i * 30);
			gameDashboard.addGameObject(textObjects[i], (int) widthDashboard / 2, (int) ((i + 0.5) * 100), 10);
		}
		addDashboard(gameDashboard);
	}

	private void createFighter() {
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

	public void createView() {
		View view = new View(worldWidth, worldHeight);
		setView(view);
		size(worldWidth, worldHeight);
		view.setBackground(loadImage(IntoTheGalaxy.MEDIA_URL.concat("SpaceBackground.png")));
	}
	
	public void createEndScreen() {
		Dashboard startDashboard = new Dashboard(0, 0, worldWidth, worldHeight);
		
		TextObject message;
		if (fighter.getNumLives() <= 0) {
			message = new TextObject("Game Over", 60, 120);
		} else {
			message = new TextObject("You Won!", 60, 240);
		}
		startDashboard.addGameObject(message, worldWidth / 2, worldHeight / 4);
		
		TextObject scoreText = new TextObject("Your Score: " + score, 20, 255);
		startDashboard.addGameObject(scoreText, worldWidth / 2, worldHeight * 7 / 10);
		
		TextObject highscoreText = new TextObject("Highscore: " + highscore, 20, 255);
		startDashboard.addGameObject(highscoreText, worldWidth / 2, worldHeight * 8 / 10);
		
		addDashboard(startDashboard);
		
		Button button = new RestartButton(this, worldWidth / 2 - 50, worldHeight / 2 - 30, 100, 60, "Play Again?", 255, 0);
		addGameObject(button);
	}
	
	@Override
	public void update() {
		if (gameState == 2) {
			time++;
			spawnNewAliens();
			refreshDashboardText();
			endGame();
		}
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
			deleteAllDashboards();
			saveHighscore();
			gameState = 3;
			setupGame();
		}
	}
	
	private void saveHighscore() {
		if (score > highscore) {
			persistence.saveData(Integer.toString(score));
			highscore = Integer.parseInt(persistence.loadDataString());
		}
	}

	public void setGameState(int state) {
		gameState = state;
	}

	public int getTime() {
		return time;
	}

	public void increaseScore(int points) {
		score += points;
	}
}
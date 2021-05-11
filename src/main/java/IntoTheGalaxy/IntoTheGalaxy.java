package IntoTheGalaxy;

import java.util.*;
import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.view.View;
import nl.han.ica.oopg.sound.Sound;

/**
 * The main class of the IntoTheGalaxy program.
 * 
 */
@SuppressWarnings("serial")
public class IntoTheGalaxy extends GameEngine {
	private int gameState = 0;
	private int worldWidth;
	private int worldHeight;
	private int time;
	private int numWaves;
	private int currentWave;

	public static String MEDIA_URL = "src/main/java/IntoTheGalaxy/media/";
	private Sound fighterShootSound, fighterExplosionSound, alienShootSound, alienExplosionSound;

	private float widthDashboard;
	private float widthGameScreen;
	
	public Score score = new Score();
	private TextObject[] textObjects = new TextObject[4];

	private Fighter fighter;
	public ArrayList<Alien> alienList;
	private int[] numAliens = { 3, 20, 20 };

	public static void main(String[] args) {
		IntoTheGalaxy itg = new IntoTheGalaxy();
		itg.runSketch();
	}
	
	/**
	 * Overrides GameEngine.setupGame(), sets the window size, initializes sound, creates the view 
	 * and loads the startscreen, gamescreen or endscreen depending on the gamestate. 
	 * If the gamescreen is loaded: sets time and score to 0, current wave to 1 and sets number of waves.
	 */
	@Override
	public void setupGame() {
		worldWidth = 800;
		worldHeight = 600;
		
		initializeSound();
		createStartScreen();
		createView();
	}
	
	public void startGame() {
		time = 0;
		numWaves = 3;
		currentWave = 1;
		score.setScore(0);
		
		widthDashboard = worldWidth / 6;
		widthGameScreen = worldWidth - widthDashboard;
		
		createObjects();
		gameState = 1;
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
		
		TextObject highscoreText = new TextObject("Highscore: " + score.getHighscore(), 20, 255);
		startDashboard.addGameObject(highscoreText, worldWidth / 2, worldHeight * 8 / 10);
		
		addDashboard(startDashboard);
		
		Button startButton = new StartButton(this, worldWidth / 2 - 50, worldHeight / 2 - 30, 100, 60, "Start Game", 255, 0);
		addGameObject(startButton);
	}
	
	/**
	 * Create the objects for the gamescreen and add them to the world.
	 */
	public void createObjects() {
		createFighter();
		createAliens();
		createGameDashboard();
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
	
	private void createGameDashboard() {
		Dashboard gameDashboard = new Dashboard(worldWidth - widthDashboard, 0, widthDashboard, worldHeight);
		gameDashboard.setBackground(0, 0, 0);
		for (int i = 0; i < textObjects.length; i++) {
			textObjects[i] = new TextObject("", 16, 160 + i * 30);
			gameDashboard.addGameObject(textObjects[i], (int) widthDashboard / 2, (int) ((i + 0.5) * 100), 10);
		}
		addDashboard(gameDashboard);
	}
	
	/**
	 * Create the view that shows the GameoBjects in the world and the set background.
	 */
	public void createView() {
		View view = new View(worldWidth, worldHeight);
		setView(view);
		size(worldWidth, worldHeight);
		view.setBackground(loadImage(IntoTheGalaxy.MEDIA_URL.concat("SpaceBackground.png")));
	}
	
	private void createEndScreen() {
		Dashboard startDashboard = new Dashboard(0, 0, worldWidth, worldHeight);
		
		TextObject message;
		if (fighter.getNumLives() <= 0) {
			message = new TextObject("Game Over", 60, 120);
		} else {
			message = new TextObject("You Won!", 60, 240);
		}
		startDashboard.addGameObject(message, worldWidth / 2, worldHeight / 4);
		
		TextObject scoreText = new TextObject("Your Score: " + score.getScore(), 20, 255);
		startDashboard.addGameObject(scoreText, worldWidth / 2, worldHeight * 7 / 10);
		
		TextObject highscoreText = new TextObject("Highscore: " + score.getHighscore(), 20, 255);
		startDashboard.addGameObject(highscoreText, worldWidth / 2, worldHeight * 8 / 10);
		
		addDashboard(startDashboard);
		
		Button button = new RestartButton(this, worldWidth / 2 - 50, worldHeight / 2 - 30, 100, 60, "Play Again?", 255, 0);
		addGameObject(button);
	}
	
	/**
	 * Overrides GameEngine.update(), increases the time attribute, 
	 * spawns new Aliens if all are gone and there are more waves left to go,
	 * updates the dashboardtext, contains endGame().
	 */
	@Override
	public void update() {
		if (gameState == 1) {
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
		textObjects[0].setText("Highscore: " + score.getHighscore());
		textObjects[1].setText("Score: " + score.getScore());
		textObjects[2].setText("Wave: " + currentWave);
		textObjects[3].setText("Lives: " + fighter.getNumLives());
	}
	
	/**
	 * Loads endscreen if all waves are done or fighter is out of lives.
	 */
	public void endGame() {
		if (currentWave > numWaves || fighter.getNumLives() == 0) {
			deleteAllGameOBjects();
			deleteAllDashboards();
			score.saveHighscore();
			gameState = 2;
			createEndScreen();
		}
	}
	
	/**
	 * Set the gamestate, '0' is startscreen, '1' is gamescreen, '3' is endscreen.
	 */
	public void setGameState(int state) {
		gameState = state;
	}
	
	/**
	 * Returns the value of the time attribute.
	 */
	public int getTime() {
		return time;
	}
}
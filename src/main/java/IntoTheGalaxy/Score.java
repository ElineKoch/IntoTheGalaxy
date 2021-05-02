package IntoTheGalaxy;

import nl.han.ica.oopg.persistence.FilePersistence;

/**
 * Keeps the score of the game and reads and saves the highscore in a text file using FilePersistence. 
 * Methods are called in the main class.
 */
public class Score {
	private int highscore;
	private int score;
	private FilePersistence persistence;
	
	public Score() {
		score = 0;
		persistence = new FilePersistence("main/java/IntoTheGalaxy/media/highscore.txt");
		if (persistence.fileExists()) {
			highscore = Integer.parseInt(persistence.loadDataString());
		}
	}
	

	/**
	 * Return the value of the score attribute.
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Set the value of the score attribute.
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
	/**
	 * Increase the score by points.
	 */
	public void increaseScore(int points) {
		score += points;
	}
	
	/**
	 * Return the value of the highscore attribute.
	 */
	public int getHighscore() {
		return highscore;
	}
	
	/**
	 * Save the highscore in the text file if the value of score is higher than the current highscore value.
	 */
	public void saveHighscore() {
		if (score > highscore) {
			persistence.saveData(Integer.toString(score));
			highscore = Integer.parseInt(persistence.loadDataString());
		}
	}
}

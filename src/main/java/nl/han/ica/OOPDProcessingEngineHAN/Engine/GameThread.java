package nl.han.ica.OOPDProcessingEngineHAN.Engine;


import nl.han.ica.OOPDProcessingEngineHAN.Logger.FileLogHandler;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.LogFactory;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.Logger;

/**
 * GameThread handles the timing of the gamelogic.
 */
public class GameThread implements Runnable {

    /**
     * This logger is used to output information to a console or file.
     */
    private Logger logger = LogFactory.getLogger();

    /**
     * Needed to call update method from GameEngine
     */
    private GameEngine gameEngine;

    /**
     * Thread for the gamelogic.
     */
    private Thread thread;

    /**
     * Boolean that checks if the game is paused.
     */
    private boolean isGamePaused;

    /**
     * Holds how many times the game should update.
     */
    private int updatesPerSecond;

    /**
     * Holds the actual time between updates.
     */
    private double timeBetweenUpdates;

    //At the very most we will update the game this many times before a new render. Standard 5.
    //If you're worried about visual hitches more than perfect timing, set this to 1.
    private static final int MAX_UPDATES_BEFORE_RENDER = 1;
    /**
     * Holds the last time an update has executed.
     */
    private double lastUpdateTime = System.nanoTime();

    /**
     * Create a new GameThread for the GameEngine, updates every GameObject in the GameEngine on a different thread.
     *
     * @param gameEngine
     */
    public GameThread(GameEngine gameEngine) {

        logger.addLogHandler(new FileLogHandler());
        setGameSpeed(60);
        this.gameEngine = gameEngine;
    }

    public void start() {

        thread = new Thread(this);
        thread.start();
        thread.setPriority(Thread.MAX_PRIORITY);
    }


    /**
     * Start the thread.
     */
    public void run() {

        while (!isGamePaused) {
            int updateCount = 0;

            //Do as many game updates as we need to, potentially playing catchup.
            while (System.nanoTime() - lastUpdateTime > timeBetweenUpdates && updateCount < MAX_UPDATES_BEFORE_RENDER) {
                gameEngine.updateGame();
                gameEngine.update();
                lastUpdateTime += timeBetweenUpdates;
                updateCount++;
            }

            try {
                Thread.sleep(1);
            } catch (Exception e) {
                logger.logln(0, e.toString());
            }
        }
    }

    /**
     * Sets the amount of updates per second.
     *
     * @param updatesPerSecond
     */
    public void setGameSpeed(int updatesPerSecond) {

        this.updatesPerSecond = updatesPerSecond;
        timeBetweenUpdates = 1000000000 / updatesPerSecond;
    }

    /**
     * Gets the amount of updates per second.
     *
     * @return int
     */
    public float getGameSpeed() {

        return updatesPerSecond;
    }

    /**
     * Pauses the thread.
     */
    public void pauseGame() {

        isGamePaused = true;
    }

    /**
     * Resumes the thread.
     */
    public void resumeGame() {

        if (isGamePaused) {
            lastUpdateTime = System.nanoTime();
            isGamePaused = false;

            thread = new Thread(this);
            thread.start();
        }
    }

    /**
     * Returns if the game thread is paused or not
     *
     * @return isGamePaused or !isGamePaused
     */
    public boolean getThreadState() {
        return isGamePaused;
    }
}


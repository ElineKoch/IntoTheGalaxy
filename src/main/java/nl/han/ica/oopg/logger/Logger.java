package nl.han.ica.oopg.logger;

/**
 * Indicates if the object is a logger.
 */
public interface Logger {
	
    void addLogHandler(LogHandler logHandler);

    void removeLogHandler(LogHandler logHandler);

    boolean logln(int level, String message);
}

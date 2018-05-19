package nl.han.ica.oopg.logger;

import java.util.ArrayList;

/**
 *
 */
public final class DefaultLogger implements Logger {

    public static final int LOG_FAILURE = 0;
    public static final int LOG_ERROR = 1;
    public static final int LOG_WARNING = 2;
    public static final int LOG_INFO = 3;
    public static final int LOG_DEBUG = 4;

    public ArrayList<LogHandler> logHandlers = new ArrayList<LogHandler>();

    /**
     * Add a log handler to the logger.
     */
    @Override
    public void addLogHandler(LogHandler logHandler) {
        logHandlers.add(logHandler);
    }

    /**
     * Remove a log handler from the logger.
     */
    @Override
    public void removeLogHandler(LogHandler logHandler) {
        for (int i = 0; i < logHandlers.size(); i++) {
            if (logHandlers.get(i).equals(logHandler)) {
                logHandlers.remove(i);
                break;
            }

        }
    }

    /**
     * Checks if the given integer an valid log option.
     *
     * @param level The log lever, between 0 and 4
     * @return True if the loglevel is valid
     */
    public boolean isValidLogLevel(int level) {
        switch (level) {
            case LOG_FAILURE:
            case LOG_ERROR:
            case LOG_WARNING:
            case LOG_INFO:
            case LOG_DEBUG:
                return true;
            default:
                return false;
        }
    }

    /**
     * Logs the message with an log option.
     *
     * @param level   The loglevel
     * @param message The message
     * @return true when method was successful
     */
    @Override
    public boolean logln(int level, String message) {

        // Valid log level?
        if (!isValidLogLevel(level)) {
            return false;
        }

        for (int i = 0; i < logHandlers.size(); i++) {
            logHandlers.get(i).logln(level, message);
        }

        return true;
    }
}
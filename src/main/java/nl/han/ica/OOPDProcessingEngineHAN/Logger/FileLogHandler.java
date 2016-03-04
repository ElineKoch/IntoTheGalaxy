package nl.han.ica.OOPDProcessingEngineHAN.Logger;

import nl.han.ica.OOPDProcessingEngineHAN.Exceptions.LoggerRuntimeException;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Logs messages to a file.
 */
public class FileLogHandler implements LogHandler {
	
    FileOutputStream fos;
    File file;
    OutputStreamWriter osw;
    
    /**
     * Create a new Logger which outputs to a file.
     */
    public FileLogHandler()
    {
        try {
            file = new File("src/main/java/nl/han/ica/OOPDProcessingEngineHAN/Logger/Log.log");
            if(!file.exists()){
                file.createNewFile();
            }
            fos = new FileOutputStream(file, true);
            osw = new OutputStreamWriter(fos);
        } catch (IOException e) {
            throw new LoggerRuntimeException(e);
        }
    }

    /**
     * Writes the message.
     */
    @Override
    public synchronized void logln(int level, String message) {
        try {
            //Print timestamp
            osw.write("[" + new SimpleDateFormat("yyyy-mm-dd hh:mm:ss.S").format(new Date()) + "] \n");

            //Print level prefix
            osw.write(getLevelPrefix(level));

            //Print message
            osw.write(message + "\n");
            osw.flush();
        } catch (IOException e) {
            throw new LoggerRuntimeException(e);
        }
    }

    /**
     * Gets the prefix of the given level.
     * @param level
     * @return String
     */
    private String getLevelPrefix(int level)
    {
        switch (level)
        {
            case DefaultLogger.LOG_FAILURE:
                return "FAILURE: ";
            case DefaultLogger.LOG_ERROR:
                return "  ERROR: ";
            case DefaultLogger.LOG_WARNING:
                return "WARNING: ";
            case DefaultLogger.LOG_INFO:
                return "   INFO: ";
            case DefaultLogger.LOG_DEBUG:
                return "  DEBUG: ";
            default:
                return "     INFO";
        }
    }
}

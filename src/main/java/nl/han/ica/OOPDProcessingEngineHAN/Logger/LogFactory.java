package nl.han.ica.OOPDProcessingEngineHAN.Logger;

/**
 * The LogFactory holds the current Logger object.
 */
public class LogFactory {
	
    private static Logger logger;

    /**
     * Create a new LogFactory.
     */
    private LogFactory() {}

    /**
     * Gets the current Logger.
     * @return
     */
    public static synchronized Logger getLogger() {
        
    	if (logger == null)
        {
            logger = new DefaultLogger();
        }
        
        return logger;
    }
}

package nl.han.ica.OOPDProcessingEngineHAN.Persistence;

import nl.han.ica.OOPDProcessingEngineHAN.Logger.LogFactory;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Class for user persistence. With this Class you can save Strings in a File to
 * your internal storage. Both real devices and emulators can use the internal
 * storage.
 *
 * You can use the game persistance for example to save variables or,
 * highscores. You are allowed to use multiple game persistances, each
 * additional persistance saves an additional file on your system if the
 * filename name is different.
 */
public class FilePersistence implements IPersistence {
	
    private Logger logger = LogFactory.getLogger();

    private String filename;

    /**
     * The constructor allows you to specify the filename the internal storage
     * will use.
     *
     * @param filename
     *            The name of the file that will be used for this persistance.
     */
    public FilePersistence(String filename) {
        this.filename = filename;
    }

    /**
     * Saves the data to the earlier specified file. All previous saved data
     * will be overwritten. You can get your previous data by loading it first
     * and then appending your new data to the String.
     *
     * @param data The data that should be saved to the file, the data must be
     *             one entire String.
     */
    public void saveData(String data) {
    	
        FileOutputStream fos;
        File file;
        try {
            file = new File("src/", filename);
            fos = new FileOutputStream(file);
            if(!file.exists()){
                file.createNewFile();
            }
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            osw.write(data);
            osw.flush();
            osw.close();
        } catch (IOException e) {
            logger.logln(0, e.toString());
        }
    }

    /**
     *
     * @param data
     * @param separator
     */
    public void saveData(String[] data, String separator) {
    	
        StringBuilder sb = new StringBuilder();
        
        if (data != null) {
        	
            checkSeparatorUsage(data[0], separator);
            sb.append(data[0]);
            for (int i = 1; i < data.length; i++){
                checkSeparatorUsage(data[1], separator);
                sb.append(separator + data[i]);
            }
            saveData(sb.toString());
        }
    }

    private void checkSeparatorUsage(String data, String separator) {
    	
        if (data.contains(separator))
            throw new IllegalArgumentException("A value in the Array contains the separator");
    }

    /**
     * Returns the full data (in String format) that is stored in the file. This
     * function will return the data or an empty String if the file does not
     * exist, or the file is empty.
     *
     * @return String The data saved earlier.
     */
    public String loadDataString() {
    	
        String content = "";
        File file = new File("src/", filename);
        
        if (file.exists()) {
            int bufferSize = (int) file.length();
            char[] buffer = new char[bufferSize];
            try {
                FileInputStream fIn = new FileInputStream(file);
                InputStreamReader isr = new InputStreamReader(fIn);
                isr.read(buffer);
                isr.close();
                fIn.close();
            } catch (IOException e) {
                logger.logln(0, e.toString());
            }
            content = new String(buffer);
        }

        return content;
    }

    /**
     *
     * @param separator The specific String which is used to separate the String from the earlier specified file.
     * @return The generated Array made out of the String from the earlier specified file.
     */
    public String[] loadDataStringArray(String separator) {
        String dataString = loadDataString();
        
        return dataString.split(separator);
    }

    /**
     * This function deletes the earlier specified file.
     */
    public void deleteData() {
        File file = new File("src/", filename);
        file.delete();
    }

    /**
     * This function checks if the earlier specified file exists.
     * @return Boolean with the result.
     */
    public boolean fileExists(){
        return new File("src/", filename).exists();
    }
}

package nl.han.ica.oopg.persistence;

/**
 * Indicates an object which can implement persistence logic.
 */
public interface IPersistence {

    void saveData(String data);
    void saveData(String[] data, String separator);

    String loadDataString();
    String[] loadDataStringArray(String separator);

    boolean fileExists();
}

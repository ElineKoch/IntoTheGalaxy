package nl.han.ica.OOPDProcessingEngineHAN.Tile;

import nl.han.ica.OOPDProcessingEngineHAN.Logger.LogFactory;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.Logger;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

/**
 * This factory creates new instances of the class the programmer specifies in the constructor.
 * @param <T> The class this factory will be creating instances of.
 */
public class TileType <T extends Tile> {
	
    Logger logger = LogFactory.getLogger();
    
    /**
     * The type of class this factory will be creating instances of.
     */
	protected Class<T> classType;
	
    /**
     * The image which will be drawn whenever the draw method of the created tiles is called.
     */
	protected Sprite sprite;

    /**
     * Create a new TileType object.
     * @param classType The type of class this factory will be creating instances of.
     * @param sprite The image which will be drawn whenever the draw method of the created tiles is called.
     */
	public TileType(Class<T> classType, Sprite sprite)
	{
		this.classType = classType;
		this.sprite = sprite;
	}

    /**
     * This method returns new instances of the class which is specified in the constructor.
     * @param tileSize the size of the Tile which wil be used to resize the image of the Tile.
     * @return Tile
     */
	public Tile createNewInstanceOfTile(int tileSize)
	{
        Tile tile;
        try {
            tile = classType.getDeclaredConstructor(Sprite.class).newInstance(sprite);
            tile.setSpriteSize(tileSize);
        }catch (Exception e){
            logger.logln(0, e.toString());
            
            return null;
        }

        return tile;
    }

    /**
     * Gets the class type.
     * @return The Type of class which this factory creates instances of.
     */
    public Class<T> getClassType(){
        return classType;
    }
}

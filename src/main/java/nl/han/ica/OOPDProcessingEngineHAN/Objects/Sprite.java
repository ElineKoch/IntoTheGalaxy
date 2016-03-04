package nl.han.ica.OOPDProcessingEngineHAN.Objects;

import gifAnimation.Gif;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * The Sprite object is used in the GameEngine and is a wrapper for the PImage object from the Processing library.
 */
public class Sprite {

	private PImage image;
	
	/**
	 * Create a new Sprite object with a path to the file.
	 * @param fileName
	 */
	public Sprite(String fileName)
	{
		this(SpriteHelper.loadImage(fileName));
	}
	
	/**
	 * Create a new Sprite object with a PImage.
	 * @param image
	 */
	public Sprite(PImage image)
	{
		this.image = image;
	}
	
	/**
	 * Get the PImage object from the sprite.
	 * @return PImage
	 */
	public PImage getPImage()
	{
		return image;
	}
	
	/**
	 * Set the sprite with a PImage.
	 * @param image
	 */
	public void setSprite(PImage image)
	{
		this.image = image;
	}

    /**
     * Set the sprite with a imagefile.
     * @param fileName
     */
    public void setSprite(String fileName)
    {
        image = SpriteHelper.loadImage(fileName);
    }
	
	/**
	 * Get the width of the sprite.
	 * @return int
	 */
	public int getWidth()
	{
		return image.width;
	}
	
	/**
	 * Get the height of the sprite.
	 * @return int
	 */
	public int getHeight()
	{
		return image.height;
	}
	
	/**
	 * Get the center x position of the sprite.
	 * @return float
	 */
	public float getSpriteCenterX()
	{
		return image.width / 2;
	}
	
	/**
	 * Get the center y position of the sprite.
	 * @return float
	 */
	public float getSpriteCenterY()
	{
		return image.height / 2;
	}

	/**
	 * Resize the sprite with a new width and height.
	 * @param width
	 * @param height
	 */
    public void resize(int width, int height){
        image.resize(width, height);
    }
    
    /**
     * The SpriteHelper is a helper class which is only be used to load images from a file.
     * This is necessarily because Processing doesn't allow any other way.
     */
	private static class SpriteHelper
	{

        private SpriteHelper(){}
		/**
		 * Loads the image from a file.
		 */
		public static PImage loadImage(String fileName)
		{
			PApplet applet = new PApplet();

			if(fileName.endsWith(".gif"))
			{
				Gif gifje = new Gif(applet, fileName);
				gifje.play();
				
				return gifje;
			}
			else
			{
				return applet.loadImage(fileName);
			}
		}
	}
}

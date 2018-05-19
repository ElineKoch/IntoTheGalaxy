package nl.han.ica.oopg.objects;

import java.awt.Rectangle;

import processing.core.PGraphics;
import processing.core.PImage;

/**
 * The AnimatedSpriteObject is an extension of SpriteObject.
 * It expects a Sprite object which is one image and includes multiple Sprites. This object cuts the Sprite in separate Sprites.
 */
public abstract class AnimatedSpriteObject extends SpriteObject {

    private Rectangle currentFrame;

    private int currentFrameIndex;
    private int totalFrames;

    private int frameWidth;

    /**
     * Create a new AnimatedSpriteObject with a Sprite and set the amount of total frames.
     *
     * @param sprite      The Sprite to be used
     * @param totalFrames Total number of frames
     */
    public AnimatedSpriteObject(Sprite sprite, int totalFrames) {
        super(sprite);

        setTotalFrames(totalFrames);
        frameWidth = getImage().width / totalFrames;
        setCurrentFrameIndex(0);
        width = frameWidth;
    }

    /**
     * Draws the AnimatedSprite. This is fired by the GameEngine.
     */
    @Override
    public void draw(PGraphics g) {
        PImage img = getImage().get(currentFrame.x, currentFrame.y, currentFrame.width, currentFrame.height);
        g.image(img, x, y);
    }

    /**
     * Go to the next frame of the Sprite.
     */
    public void nextFrame() {
        if (currentFrameIndex == (totalFrames - 1))
            setCurrentFrameIndex(0);
        else
            setCurrentFrameIndex(currentFrameIndex + 1);
    }

    /**
     * Get the current frame index of the Sprite.
     *
     * @return The current frame index
     */
    public int getCurrentFrameIndex() {
        return currentFrameIndex;
    }

    /**
     * Set the frame index of the Sprite.
     *
     * @param currentFrameIndex The frameindex
     */
    public void setCurrentFrameIndex(int currentFrameIndex) {
        this.currentFrameIndex = currentFrameIndex;
        currentFrame = new Rectangle(frameWidth * currentFrameIndex, 0, frameWidth, getImage().height);
    }

    /**
     * Get the total amount of frames of the Sprite.
     *
     * @return The total number of frames
     */
    public int getTotalFrames() {
        return totalFrames;
    }

    /**
     * Set the total amount of frames of the Sprite.
     *
     * @param totalFrames The total number of frames
     */
    public void setTotalFrames(int totalFrames) {
        this.totalFrames = totalFrames;
    }
}

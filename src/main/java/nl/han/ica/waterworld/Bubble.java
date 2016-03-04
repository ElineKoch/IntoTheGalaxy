package nl.han.ica.waterworld;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;
import processing.core.PGraphics;

import java.util.List;

/**
 * @author Ralph Niels
 * Bel-klasse
 */
public class Bubble extends GameObject implements ICollidableWithGameObjects{

    private final Sound popSound;
    private WaterWorld world;
    private int bubbleSize;

    /**
     * Constructor
     * @param bubbleSize Afmeting van de bel
     * @param world Referentie naar de wereld
     * @param popSound Geluid dat moet klinken als de bel knapt
     */
    public Bubble(int bubbleSize,WaterWorld world,Sound popSound) {
        this.bubbleSize=bubbleSize;
        this.popSound=popSound;
        this.world=world;
        setySpeed(-bubbleSize/10f);
        /* De volgende regels zijn in een zelfgekend object nodig
            om collisiondetectie mogelijk te maken.
         */
        setHeight(bubbleSize);
        setWidth(bubbleSize);
    }

    @Override
    public void update() {
        if (getY() <=100) {
            world.deleteGameObject(this);
        }
    }

    @Override
    public void draw(PGraphics g) {
        g.ellipseMode(g.CORNER); // Omdat cirkel anders vanuit midden wordt getekend en dat problemen geeft bij collisiondetectie
        g.stroke(0, 50, 200, 100);
        g.fill(0, 50, 200, 50);
        g.ellipse(getX(), getY(), bubbleSize, bubbleSize);
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for (GameObject g:collidedGameObjects) {
            if (g instanceof Swordfish) {
                popSound.rewind();
                popSound.play();
                world.deleteGameObject(this);
                world.increaseBubblesPopped();
            }
        }
    }
}

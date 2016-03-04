package nl.han.ica.waterworld;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;

import java.util.Random;

/**
 * @author Ralph Niels
 * Klasse die Bubbles aanmaakt met configureerbare
 * snelheid.
 */
public class BubbleSpawner implements IAlarmListener {

    private float bubblesPerSecond;
    private Random random;
    private WaterWorld world;
    private Sound popSound;

    /** Constructor
     * @param world Referentie naar de wereld
     * @param popSound Geluid dat moet klinken als een bel knapt
     * @param bubblesPerSecond Aantal bellen dat per seconden gemaakt moet worden
     */
    public BubbleSpawner(WaterWorld world,Sound popSound,float bubblesPerSecond) {
        this.bubblesPerSecond=bubblesPerSecond;
        this.world=world;
        this.popSound=popSound;
        random=new Random();
        startAlarm();
    }

    private void startAlarm() {
        Alarm alarm=new Alarm("New bubble",1/bubblesPerSecond);
        alarm.addTarget(this);
        alarm.start();
    }

    @Override
    public void triggerAlarm(String alarmName) {
    		int bubbleSize=random.nextInt(10) + 10;
        Bubble b=new Bubble(bubbleSize,world,popSound);
        world.addGameObject(b, random.nextInt(world.getWidth()), world.getHeight());
        startAlarm();
    }

}

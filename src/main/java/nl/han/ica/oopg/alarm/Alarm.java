package nl.han.ica.oopg.alarm;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The alarm object is used to alert objects that listen to the alarm. Implement
 * the IAlarmListener interface on objects you would like to alert and add them
 * to the alarm object.
 */
public class Alarm {

    private List<IAlarmListener> targets = new ArrayList<IAlarmListener>();
    private Timer timer;

    private String name;
    private double seconds;

    /**
     * Create a new alarm object.
     *
     * @param name    Name of the Alarm
     * @param seconds After how many seconds you want the alarm to alert.
     */
    public Alarm(String name, double seconds) {

        this.name = name;
        this.setSeconds(seconds);
    }

    /**
     * Start the timer of the alarm.
     */
    public void start() {

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                alert();
            }
        }, (int) (seconds * 1000));
    }

    /**
     * Stop the timer of the alarm.
     */
    public void stop() {
        timer.cancel();
    }

    /**
     * This method is triggered by the alarm when the timer has been expired.
     */
    private void alert() {
        for (IAlarmListener alarm : targets) {
            alarm.triggerAlarm(name);
        }

        stop();
    }

    /**
     * Add a new object that implements the IAlarmListener interface to listen
     * to the alarm.
     *
     * @param alarm The IAlarmListener that should be added
     */
    public void addTarget(IAlarmListener alarm) {
        targets.add(alarm);
    }

    /**
     * Remove a object that implements the IAlarmListener interface from the
     * alarm to stop listening.
     *
     * @param alarm The IAlarmListener that should be removed
     */
    public void removeTarget(IAlarmListener alarm) {
        targets.remove(alarm);
    }

    /**
     * Get all the targets which are added to the alarm.
     *
     * @return The list of IAlarmListener that should be notified
     */
    public List<IAlarmListener> getTargets() {
        return targets;
    }

    /**
     * Get the seconds of the timer which is set to the alarm.
     *
     * @return The number of seconds
     */
    public double getSeconds() {
        return seconds;
    }

    /**
     * Get the seconds of the timer of the alarm.
     *
     * @param seconds The number of seconds
     */
    public void setSeconds(double seconds) {
        this.seconds = seconds;
    }
}

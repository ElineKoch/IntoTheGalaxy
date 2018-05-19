package nl.han.ica.oopg.alarm;

/**
 * Implement this interface to mark an object which can listen to an alarm.
 * Add the object that has been marked to the alarm.
 */
public interface IAlarmListener {

    /**
     * This method will be triggered by the alarm when the timer has been expired.
     *
     * @param alarmName The name of the alarm
     */
    void triggerAlarm(String alarmName);
}
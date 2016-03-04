package nl.han.ica.OOPDProcessingEngineHAN.Alarm;

/**
 * Implement this interface to mark an object which can listen to an alarm.
 * Add the object that has been marked to the alarm.
 */
public interface IAlarmListener {

	/**
	 * This method will be triggered by the alarm when the timer has been expired.
	 */
	void triggerAlarm(String alarmName);
}
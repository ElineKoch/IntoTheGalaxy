package nl.han.ica.oopg.alarm;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AlarmTest {

	private Alarm alarm;
	
	@Before
	public void setup()
	{
		this.alarm = new Alarm("TestAlarm", 0);
	}
	
	@Test
	public void addTargetShouldGoSuccessful()
	{
		FakeAlarmTarget target = new FakeAlarmTarget();
		alarm.addTarget(target);
		
		assertTrue(alarm.getTargets().contains(target));
	}
	
	@Test
	public void removeTargetShouldGoSuccessful()
	{
		FakeAlarmTarget target = new FakeAlarmTarget();
		alarm.addTarget(target);
		alarm.removeTarget(target);
		
		assertFalse(alarm.getTargets().contains(target));
	}
	
	@Test
	public void setSecondsShouldSetToOne()
	{
		alarm.setSeconds(1);
		
		assertEquals(1, alarm.getSeconds(), 0);
	}
	
	@Test
	public void alarmShouldTriggerTargets() throws InterruptedException
	{
		alarm.setSeconds(0);
		
		FakeAlarmTarget target = new FakeAlarmTarget();
		alarm.addTarget(target);
		
		alarm.start();
		
		Thread.sleep(100); // small delay because of the the timer object inside the alarm object.
        assertTrue(target.triggered);
	}
	
	private class FakeAlarmTarget implements IAlarmListener {

		public boolean triggered;
		
		@Override
		public void triggerAlarm(String alarmName) {
			
			if(alarmName == "TestAlarm")
				triggered = true;
		}
	}
}

package com.example.twinkle.finitestatemachine;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Checks if all possible state transitions in application are correct.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class StateTransitionTest
{
    /**
     * The main activity of application.
     */
    private MainActivity activity;

    /**
     * View that outputs machine's current state.
     */
    private TextView tvStateOutput;

    /**
     * View that outputs if machine's current state is armed or disarmed.
     */
    private TextView tvArmedDisarmedOutput;

    /**
     * Button that calls machine's transition by `LOCK` action.
     */
    private Button bLock;

    /**
     * Button that calls machine's transition by `LOCKx2` action.
     */
    private Button bLockX2;

    /**
     * Button that calls machine's transition by `UNLOCK` action.
     */
    private Button bUnlock;

    /**
     * Button that calls machine's transition by `UNLOCKx2` action.
     */
    private Button bUnlockX2;

    /**
     * Initializes all components needed for testing.
     */
    @Before
    public void setUp() throws Exception
    {
        activity = Robolectric.buildActivity(MainActivity.class)
                .create()
                .resume()
                .get();

        tvStateOutput = (TextView) activity.findViewById(R.id.current_state);
        tvArmedDisarmedOutput = (TextView) activity.findViewById(R.id.armed_or_not);
        bLock = (Button) activity.findViewById(R.id.lock_action_button);
        bLockX2 = (Button) activity.findViewById(R.id.lockx2_action_button);
        bUnlock = (Button) activity.findViewById(R.id.unlock_action_button);
        bUnlockX2 = (Button) activity.findViewById(R.id.unlockx2_action_button);
    }

    /**
     * Checks if activity is not null.
     */
    @Test
    public void shouldNotBeNull() throws Exception
    {
        assertNotNull(activity);
    }

    /**
     * Checks the initial state of machine.
     */
    @Test
    public void shouldHaveStateOutput() throws Exception
    {
        assertNotNull(tvStateOutput);
        assertEquals(View.VISIBLE, tvStateOutput.getVisibility());
        assertEquals("AlarmDisarmed_AllUnlocked", tvStateOutput.getText());

        assertNotNull(tvArmedDisarmedOutput);
        assertEquals(View.VISIBLE, tvArmedDisarmedOutput.getVisibility());
        assertEquals("Disarmed", tvArmedDisarmedOutput.getText());
    }

    /**
     * Checks if all buttons are on their places.
     */
    @Test
    public void shouldHaveActionButtons() throws Exception
    {
        checkIfButtonsNotNull();
    }

    /**
     * Checks all transitions from AlarmDisarmedAllUnlocked state.
     */
    @Test
    public void checkAllActionsForAlarmDisarmedAllUnlockedState() throws Exception
    {
        assertNotNull(tvStateOutput);
        assertEquals(View.VISIBLE, tvStateOutput.getVisibility());
        assertEquals("AlarmDisarmed_AllUnlocked", tvStateOutput.getText());

        assertNotNull(tvArmedDisarmedOutput);
        assertEquals(View.VISIBLE, tvArmedDisarmedOutput.getVisibility());
        assertEquals("Disarmed", tvArmedDisarmedOutput.getText());

        checkIfButtonsNotNull();

        bLock.performClick();
        assertEquals("AlarmDisarmed_AllLocked", tvStateOutput.getText());
        assertEquals("Disarmed", tvArmedDisarmedOutput.getText());

        bUnlockX2.performClick();
        bLockX2.performClick();
        assertEquals("AlarmArmed_AllLocked", tvStateOutput.getText());
        assertEquals("Armed", tvArmedDisarmedOutput.getText());

        bUnlockX2.performClick();
        bUnlock.performClick();
        assertEquals("AlarmDisarmed_AllUnlocked", tvStateOutput.getText());
        assertEquals("Disarmed", tvArmedDisarmedOutput.getText());

        bUnlockX2.performClick();
        assertEquals("AlarmDisarmed_AllUnlocked", tvStateOutput.getText());
        assertEquals("Disarmed", tvArmedDisarmedOutput.getText());
    }

    /**
     * Checks all transitions from AlarmDisarmedAllLocked state.
     */
    @Test
    public void checkAllActionsForAlarmDisarmedAllLockedState() throws Exception
    {
        bLock.performClick();

        assertNotNull(tvStateOutput);
        assertEquals(View.VISIBLE, tvStateOutput.getVisibility());
        assertEquals("AlarmDisarmed_AllLocked", tvStateOutput.getText());

        assertNotNull(tvArmedDisarmedOutput);
        assertEquals(View.VISIBLE, tvArmedDisarmedOutput.getVisibility());
        assertEquals("Disarmed", tvArmedDisarmedOutput.getText());

        checkIfButtonsNotNull();

        bLock.performClick();
        assertEquals("AlarmArmed_AllLocked", tvStateOutput.getText());
        assertEquals("Armed", tvArmedDisarmedOutput.getText());

        bUnlockX2.performClick();
        bLock.performClick();
        bLockX2.performClick();
        assertEquals("AlarmArmed_AllLocked", tvStateOutput.getText());
        assertEquals("Armed", tvArmedDisarmedOutput.getText());

        bUnlockX2.performClick();
        bLock.performClick();
        bUnlock.performClick();
        assertEquals("AlarmDisarmed_DriverUnlocked", tvStateOutput.getText());
        assertEquals("Disarmed", tvArmedDisarmedOutput.getText());

        bLock.performClick();
        bUnlockX2.performClick();
        assertEquals("AlarmDisarmed_AllUnlocked", tvStateOutput.getText());
        assertEquals("Disarmed", tvArmedDisarmedOutput.getText());
    }

    /**
     * Checks all transitions from AlarmArmedAllLocked state.
     */
    @Test
    public void checkAllActionsForAlarmArmedAllLockedState() throws Exception
    {
        bLockX2.performClick();
        assertNotNull(tvStateOutput);
        assertEquals(View.VISIBLE, tvStateOutput.getVisibility());
        assertEquals("AlarmArmed_AllLocked", tvStateOutput.getText());

        assertNotNull(tvArmedDisarmedOutput);
        assertEquals(View.VISIBLE, tvArmedDisarmedOutput.getVisibility());
        assertEquals("Armed", tvArmedDisarmedOutput.getText());

        checkIfButtonsNotNull();

        bLock.performClick();
        assertEquals("AlarmArmed_AllLocked", tvStateOutput.getText());
        assertEquals("Armed", tvArmedDisarmedOutput.getText());

        bLockX2.performClick();
        assertEquals("AlarmArmed_AllLocked", tvStateOutput.getText());
        assertEquals("Armed", tvArmedDisarmedOutput.getText());

        bUnlockX2.performClick();
        assertEquals("AlarmDisarmed_AllUnlocked", tvStateOutput.getText());
        assertEquals("Disarmed", tvArmedDisarmedOutput.getText());

        bLockX2.performClick();
        bUnlock.performClick();
        assertEquals("AlarmDisarmed_DriverUnlocked", tvStateOutput.getText());
        assertEquals("Disarmed", tvArmedDisarmedOutput.getText());
    }

    /**
     * Checks all transitions from AlarmDisarmedDriverUnlocked state.
     */
    @Test
    public void checkAllActionsForAlarmDisarmedDriverUnlockedState() throws Exception
    {
        bLockX2.performClick();
        bUnlock.performClick();

        assertNotNull(tvStateOutput);
        assertEquals(View.VISIBLE, tvStateOutput.getVisibility());
        assertEquals("AlarmDisarmed_DriverUnlocked", tvStateOutput.getText());

        assertNotNull(tvArmedDisarmedOutput);
        assertEquals(View.VISIBLE, tvArmedDisarmedOutput.getVisibility());
        assertEquals("Disarmed", tvArmedDisarmedOutput.getText());

        checkIfButtonsNotNull();

        bUnlock.performClick();
        assertEquals("AlarmDisarmed_DriverUnlocked", tvStateOutput.getText());
        assertEquals("Disarmed", tvArmedDisarmedOutput.getText());

        bUnlockX2.performClick();
        assertEquals("AlarmDisarmed_DriverUnlocked", tvStateOutput.getText());
        assertEquals("Disarmed", tvArmedDisarmedOutput.getText());

        bLockX2.performClick();
        assertEquals("AlarmArmed_AllLocked", tvStateOutput.getText());
        assertEquals("Armed", tvArmedDisarmedOutput.getText());

        bUnlock.performClick();
        bLock.performClick();
        assertEquals("AlarmDisarmed_AllLocked", tvStateOutput.getText());
        assertEquals("Disarmed", tvArmedDisarmedOutput.getText());
    }

    /**
     * Checks if all buttons have been initialized.
     */
    private void checkIfButtonsNotNull() throws Exception
    {
        assertNotNull(bLock);
        assertEquals(View.VISIBLE, bLock.getVisibility());

        assertNotNull(bLockX2);
        assertEquals(View.VISIBLE, bLockX2.getVisibility());

        assertNotNull(bUnlock);
        assertEquals(View.VISIBLE, bUnlock.getVisibility());

        assertNotNull(bUnlockX2);
        assertEquals(View.VISIBLE, bUnlockX2.getVisibility());
    }
}

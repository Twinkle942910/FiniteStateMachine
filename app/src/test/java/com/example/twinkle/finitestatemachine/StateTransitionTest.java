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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Twinkle on 5/26/2017.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class StateTransitionTest
{
    private MainActivity activity;
    private TextView tvStateOutput;
    private TextView tvArmedDisarmedOutput;
    private Button bLock;
    private Button bLockX2;
    private Button bUnlock;
    private Button bUnlockX2;

    // create and configure mock
    private FiniteStateMachine finiteStateMachine = mock(FiniteStateMachine.class);

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

    @Test
    public void shouldNotBeNull() throws Exception
    {
        assertNotNull(activity);
    }

    @Test
    public void shouldHaveStateOutput() throws Exception
    {
        assertNotNull(tvStateOutput);
        assertEquals(View.VISIBLE, tvStateOutput.getVisibility());
        when(finiteStateMachine.getCurrentStateName()).thenReturn("AlarmDisarmed_AllUnlocked");
        assertEquals(finiteStateMachine.getCurrentStateName(), tvStateOutput.getText());

        assertNotNull(tvArmedDisarmedOutput);
        assertEquals(View.VISIBLE, tvArmedDisarmedOutput.getVisibility());
        assertEquals("Disarmed", tvArmedDisarmedOutput.getText());
    }

    @Test
    public void shouldHaveActionButtons() throws Exception
    {
        checkIfButtonsNotNull();
    }

    @Test
    public void checkAllActionsForAlarmDisarmedAllUnlockedState() throws Exception
    {
        assertNotNull(tvStateOutput);
        assertEquals(View.VISIBLE, tvStateOutput.getVisibility());
        when(finiteStateMachine.getCurrentStateName()).thenReturn("AlarmDisarmed_AllUnlocked");
        assertEquals(finiteStateMachine.getCurrentStateName(), tvStateOutput.getText());

        assertNotNull(tvArmedDisarmedOutput);
        assertEquals(View.VISIBLE, tvArmedDisarmedOutput.getVisibility());
        assertEquals("Disarmed", tvArmedDisarmedOutput.getText());

        checkIfButtonsNotNull();

        bLock.performClick();
        finiteStateMachine.transition(finiteStateMachine.getActionByPosition(0));
        when(finiteStateMachine.getCurrentStateName()).thenReturn("AlarmDisarmed_AllLocked");
        assertEquals(finiteStateMachine.getCurrentStateName(), tvStateOutput.getText());
        assertEquals("Disarmed", tvArmedDisarmedOutput.getText());

        bUnlockX2.performClick();
        finiteStateMachine.transition(finiteStateMachine.getActionByPosition(3));

        bLockX2.performClick();
        finiteStateMachine.transition(finiteStateMachine.getActionByPosition(1));
        when(finiteStateMachine.getCurrentStateName()).thenReturn("AlarmArmed_AllLocked");
        assertEquals(finiteStateMachine.getCurrentStateName(), tvStateOutput.getText());
        assertEquals("Armed", tvArmedDisarmedOutput.getText());

        bUnlockX2.performClick();
        finiteStateMachine.transition(finiteStateMachine.getActionByPosition(3));

        bUnlock.performClick();
        finiteStateMachine.transition(finiteStateMachine.getActionByPosition(2));
        when(finiteStateMachine.getCurrentStateName()).thenReturn("AlarmDisarmed_AllUnlocked");
        assertEquals(finiteStateMachine.getCurrentStateName(), tvStateOutput.getText());
        assertEquals("Disarmed", tvArmedDisarmedOutput.getText());

        bUnlockX2.performClick();
        finiteStateMachine.transition(finiteStateMachine.getActionByPosition(2));
        when(finiteStateMachine.getCurrentStateName()).thenReturn("AlarmDisarmed_AllUnlocked");
        assertEquals(finiteStateMachine.getCurrentStateName(), tvStateOutput.getText());
        assertEquals("Disarmed", tvArmedDisarmedOutput.getText());
    }

    @Test
    public void checkAllActionsForAlarmDisarmedAllLockedState() throws Exception
    {
        bLock.performClick();
        finiteStateMachine.transition(finiteStateMachine.getActionByPosition(0));

        assertNotNull(tvStateOutput);
        assertEquals(View.VISIBLE, tvStateOutput.getVisibility());
        when(finiteStateMachine.getCurrentStateName()).thenReturn("AlarmDisarmed_AllLocked");
        assertEquals(finiteStateMachine.getCurrentStateName(), tvStateOutput.getText());

        assertNotNull(tvArmedDisarmedOutput);
        assertEquals(View.VISIBLE, tvArmedDisarmedOutput.getVisibility());
        assertEquals("Disarmed", tvArmedDisarmedOutput.getText());

        checkIfButtonsNotNull();

        bLock.performClick();
        finiteStateMachine.transition(finiteStateMachine.getActionByPosition(0));
        when(finiteStateMachine.getCurrentStateName()).thenReturn("AlarmArmed_AllLocked");
        assertEquals(finiteStateMachine.getCurrentStateName(), tvStateOutput.getText());
        assertEquals("Armed", tvArmedDisarmedOutput.getText());

        bUnlockX2.performClick();
        finiteStateMachine.transition(finiteStateMachine.getActionByPosition(3));

        bLock.performClick();
        finiteStateMachine.transition(finiteStateMachine.getActionByPosition(0));

        bLockX2.performClick();
        finiteStateMachine.transition(finiteStateMachine.getActionByPosition(1));
        when(finiteStateMachine.getCurrentStateName()).thenReturn("AlarmArmed_AllLocked");
        assertEquals(finiteStateMachine.getCurrentStateName(), tvStateOutput.getText());
        assertEquals("Armed", tvArmedDisarmedOutput.getText());

        bUnlockX2.performClick();
        finiteStateMachine.transition(finiteStateMachine.getActionByPosition(3));

        bLock.performClick();
        finiteStateMachine.transition(finiteStateMachine.getActionByPosition(0));

        bUnlock.performClick();
        finiteStateMachine.transition(finiteStateMachine.getActionByPosition(2));
        when(finiteStateMachine.getCurrentStateName()).thenReturn("AlarmDisarmed_DriverUnlocked");
        assertEquals(finiteStateMachine.getCurrentStateName(), tvStateOutput.getText());
        assertEquals("Disarmed", tvArmedDisarmedOutput.getText());

        bLock.performClick();
        finiteStateMachine.transition(finiteStateMachine.getActionByPosition(0));

        bUnlockX2.performClick();
        finiteStateMachine.transition(finiteStateMachine.getActionByPosition(2));
        when(finiteStateMachine.getCurrentStateName()).thenReturn("AlarmDisarmed_AllUnlocked");
        assertEquals(finiteStateMachine.getCurrentStateName(), tvStateOutput.getText());
        assertEquals("Disarmed", tvArmedDisarmedOutput.getText());
    }

    @Test
    public void checkAllActionsForAlarmArmedAllLockedState() throws Exception
    {
        bLockX2.performClick();
        finiteStateMachine.transition(finiteStateMachine.getActionByPosition(1)); //check if we need this call!

        assertNotNull(tvStateOutput);
        assertEquals(View.VISIBLE, tvStateOutput.getVisibility());
        when(finiteStateMachine.getCurrentStateName()).thenReturn("AlarmArmed_AllLocked");
        assertEquals(finiteStateMachine.getCurrentStateName(), tvStateOutput.getText());

        assertNotNull(tvArmedDisarmedOutput);
        assertEquals(View.VISIBLE, tvArmedDisarmedOutput.getVisibility());
        assertEquals("Armed", tvArmedDisarmedOutput.getText());

        checkIfButtonsNotNull();

        bLock.performClick();
        finiteStateMachine.transition(finiteStateMachine.getActionByPosition(0));
        when(finiteStateMachine.getCurrentStateName()).thenReturn("AlarmArmed_AllLocked");
        assertEquals(finiteStateMachine.getCurrentStateName(), tvStateOutput.getText());
        assertEquals("Armed", tvArmedDisarmedOutput.getText());

        bLockX2.performClick();
        finiteStateMachine.transition(finiteStateMachine.getActionByPosition(1));
        when(finiteStateMachine.getCurrentStateName()).thenReturn("AlarmArmed_AllLocked");
        assertEquals(finiteStateMachine.getCurrentStateName(), tvStateOutput.getText());
        assertEquals("Armed", tvArmedDisarmedOutput.getText());

        bUnlockX2.performClick();
        finiteStateMachine.transition(finiteStateMachine.getActionByPosition(3));
        when(finiteStateMachine.getCurrentStateName()).thenReturn("AlarmDisarmed_AllUnlocked");
        assertEquals(finiteStateMachine.getCurrentStateName(), tvStateOutput.getText());
        assertEquals("Disarmed", tvArmedDisarmedOutput.getText());

        bLockX2.performClick();
        finiteStateMachine.transition(finiteStateMachine.getActionByPosition(1));

        bUnlock.performClick();
        finiteStateMachine.transition(finiteStateMachine.getActionByPosition(2));
        when(finiteStateMachine.getCurrentStateName()).thenReturn("AlarmDisarmed_DriverUnlocked");
        assertEquals(finiteStateMachine.getCurrentStateName(), tvStateOutput.getText());
        assertEquals("Disarmed", tvArmedDisarmedOutput.getText());
    }

    @Test
    public void checkAllActionsForAlarmDisarmedDriverUnlockedState() throws Exception
    {
        bLockX2.performClick();
        finiteStateMachine.transition(finiteStateMachine.getActionByPosition(1)); //check if we need this call!
        bUnlock.performClick();
        finiteStateMachine.transition(finiteStateMachine.getActionByPosition(2));

        assertNotNull(tvStateOutput);
        assertEquals(View.VISIBLE, tvStateOutput.getVisibility());
        when(finiteStateMachine.getCurrentStateName()).thenReturn("AlarmDisarmed_DriverUnlocked");
        assertEquals(finiteStateMachine.getCurrentStateName(), tvStateOutput.getText());

        assertNotNull(tvArmedDisarmedOutput);
        assertEquals(View.VISIBLE, tvArmedDisarmedOutput.getVisibility());
        assertEquals("Disarmed", tvArmedDisarmedOutput.getText());

        checkIfButtonsNotNull();

        bUnlock.performClick();
        finiteStateMachine.transition(finiteStateMachine.getActionByPosition(2));
        when(finiteStateMachine.getCurrentStateName()).thenReturn("AlarmDisarmed_DriverUnlocked");
        assertEquals(finiteStateMachine.getCurrentStateName(), tvStateOutput.getText());
        assertEquals("Disarmed", tvArmedDisarmedOutput.getText());

        bUnlockX2.performClick();
        finiteStateMachine.transition(finiteStateMachine.getActionByPosition(3));
        when(finiteStateMachine.getCurrentStateName()).thenReturn("AlarmDisarmed_DriverUnlocked");
        assertEquals(finiteStateMachine.getCurrentStateName(), tvStateOutput.getText());
        assertEquals("Disarmed", tvArmedDisarmedOutput.getText());

        bLockX2.performClick();
        finiteStateMachine.transition(finiteStateMachine.getActionByPosition(1));
        when(finiteStateMachine.getCurrentStateName()).thenReturn("AlarmArmed_AllLocked");
        assertEquals(finiteStateMachine.getCurrentStateName(), tvStateOutput.getText());
        assertEquals("Armed", tvArmedDisarmedOutput.getText());

        bUnlock.performClick();
        finiteStateMachine.transition(finiteStateMachine.getActionByPosition(2));

        bLock.performClick();
        finiteStateMachine.transition(finiteStateMachine.getActionByPosition(0));
        when(finiteStateMachine.getCurrentStateName()).thenReturn("AlarmDisarmed_AllLocked");
        assertEquals(finiteStateMachine.getCurrentStateName(), tvStateOutput.getText());
        assertEquals("Disarmed", tvArmedDisarmedOutput.getText());
    }

    private void checkIfButtonsNotNull()
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

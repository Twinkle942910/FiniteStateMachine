package com.example.twinkle.finitestatemachine;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Twinkle on 5/26/2017.
 */

public class FiniteStateMachineTest
{
    private static FiniteStateMachine finiteStateMachine;

    private static Action actionLock;
    private static Action actionLockX2;
    private static Action actionUnlock;
    private static Action actionUnlockX2;

    private static final String fileName = "mockFile.json";

    @BeforeClass
    public static void setUp()
    {
        // Creating and configuring mock
        finiteStateMachine = mock(FiniteStateMachine.class);

        actionLock = new Action("LOCK");
        actionLockX2 = new Action("LOCKx2");
        actionUnlock = new Action("UNLOCK");
        actionUnlockX2 = new Action("UNLOCKx2");

        when(finiteStateMachine.getActionByPosition(0)).thenReturn(actionLock);
        when(finiteStateMachine.getActionByPosition(1)).thenReturn(actionLockX2);
        when(finiteStateMachine.getActionByPosition(2)).thenReturn(actionUnlock);
        when(finiteStateMachine.getActionByPosition(3)).thenReturn(actionUnlockX2);

        when(finiteStateMachine.getCurrentStateName()).thenReturn("AlarmDisarmed_AllUnlocked");

        finiteStateMachine.initialize(fileName);
    }

    @Test
    public void testIfFileIsInitialized()
    {
        verify(finiteStateMachine, times(1)).initialize(fileName);
    }

    @Test
    public void testCheckCurrentState()
    {
        String currentState = finiteStateMachine.getCurrentStateName();
        assertNotNull(currentState);
        assertEquals("AlarmDisarmed_AllUnlocked", currentState);
    }

    @Test
    public void testCheckActions()
    {
        Action action1 = finiteStateMachine.getActionByPosition(0);
        assertNotNull(action1);
        assertEquals("LOCK", actionLock.getActionTitle());

        Action action2 = finiteStateMachine.getActionByPosition(1);
        assertNotNull(action2);
        assertEquals("LOCKx2", actionLockX2.getActionTitle());

        Action action3 = finiteStateMachine.getActionByPosition(2);
        assertNotNull(action3);
        assertEquals("UNLOCK", actionUnlock.getActionTitle());

        Action action4 = finiteStateMachine.getActionByPosition(3);
        assertNotNull(action4);
        assertEquals("UNLOCKx2", actionUnlockX2.getActionTitle());
    }


    @Test
    public void testReturnNextStateByTransition()
    {
        Action action = finiteStateMachine.getActionByPosition(0);
        assertNotNull(action);

        finiteStateMachine.transition(action);
        verify(finiteStateMachine).transition(eq(action));
        verify(finiteStateMachine, times(1)).transition(action);

        when(finiteStateMachine.getCurrentStateName()).thenReturn("AlarmDisarmed_AllLocked");
        assertEquals("AlarmDisarmed_AllLocked", finiteStateMachine.getCurrentStateName());
    }

}

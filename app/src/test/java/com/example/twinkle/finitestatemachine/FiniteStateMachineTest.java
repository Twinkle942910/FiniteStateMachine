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
 * Test for FiniteStateMachine class.
 */

public class FiniteStateMachineTest
{
    /**
     * FiniteStateMachine reference.
     */
    private static FiniteStateMachine finiteStateMachine;

    /**
     * Actions of machine.
     */
    private static Action actionLock;
    private static Action actionLockX2;
    private static Action actionUnlock;
    private static Action actionUnlockX2;

    /**
     * Mock file name.
     */
    private static final String fileName = "mockFile.json";

    /**
     * Initializing mock of the machine. Creating mock methods.
     */
    @BeforeClass
    public static void setUp() throws Exception
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

    /**
     * Check if file with states was created, initialized and read.
     */
    @Test
    public void testIfFileIsInitialized() throws Exception
    {
        verify(finiteStateMachine, times(1)).initialize(fileName);
    }

    /**
     * Checks the initial state of the machine.
     */
    @Test
    public void testCheckCurrentState() throws Exception
    {
        String currentState = finiteStateMachine.getCurrentStateName();
        assertNotNull(currentState);
        assertEquals("AlarmDisarmed_AllUnlocked", currentState);
    }

    /**
     * Checks getting of each action.
     */
    @Test
    public void testCheckActions() throws Exception
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

    /**
     * Checks the call of the machine's transition.
     */
    @Test
    public void testReturnNextStateByTransition() throws Exception
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

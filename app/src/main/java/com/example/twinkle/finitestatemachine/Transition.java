package com.example.twinkle.finitestatemachine;

/**
 * The transition between state. Moves from one state to another by given action.
 */

class Transition
{
    /**
     * The state in which is machine at the moment.
     */
    private State startState;

    /**
     * The state to which machine will move by a given action.
     */
    private State endState;

    /**
     * The action shows to which state machine should move.
     */
    private Action action;

    /**
     * Creates a new Transition object that represents the route from one state to another.
     * @param startState The start state(current state) of a machine.
     * @param endState The end state(next state) of a machine.
     * @param action The action that shows to which state machine should move.
     */
    public Transition(State startState, State endState, Action action)
    {
        this.startState = startState;
        this.endState = endState;

        this.action = action;
    }

    /**
     * Gets start state.
     * @return State the start state object
     */
    public State getStartState()
    {
        return startState;
    }

    /**
     * Gets end state.
     * @return State the end state object
     */
    public State getEndState()
    {
        return endState;
    }

    /**
     * Gets an action between states.
     * @return Action the action.
     */
    public Action getAction()
    {
        return action;
    }
}

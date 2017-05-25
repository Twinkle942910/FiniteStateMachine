package com.example.twinkle.finitestatemachine;

/**
 * Description of a common state.
 */

public class State
{
    /**
     * The state's name.
     */
    private String name;

    /**
     * Creates a new State object that represents the state of a state machine.
     * @param name The name of a state.
     */
    public State(String name)
    {
        this.name = name;
    }

    /**
     * Gets an state's name.
     * @return String the name of a state
     */
    public String getName()
    {
        return name;
    }
}

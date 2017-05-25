package com.example.twinkle.finitestatemachine;

/**
 * Action of a state. Makes machine to do transition that changes current state.
 */

class Action
{
    /**
     * The title of an action.
     */
    private String actionTitle;

    /**
     * Creates a new Action object that represents the action of a state.
     * @param actionTitle The action's name.
     */
    public Action(String actionTitle)
    {
        this.actionTitle = actionTitle;
    }

    /**
     * Gets an action title.
     * @return String the title of an action
     */
    public String getActionTitle()
    {
        return actionTitle;
    }
}

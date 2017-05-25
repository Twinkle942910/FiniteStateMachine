package com.example.twinkle.finitestatemachine;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * This class models behavior of a finite state machine.
 * Manages transitions between states.
 */

public class FiniteStateMachine
{
    /**
     * Tag for logging.
     */
    public static String TAG = FiniteStateMachine.class.getSimpleName();

    /**
     * Holds machine's current state.
     */
    private State currentState;

    /**
     * Holding transitions between states.
     */
    private List<Transition> transitions;

    /**
     * All actions, that are available in current state machine.
     */
    private List<Action> availableActions;

    /**
     * The initialization method that gets state machine data from json file
     * and initializes transitions, actions, state with them.
     * @param states_object The json file with description of a state machine.
     * @return Nothing.
     */
    public void initialize(String states_object)
    {
        //initialize transitions and actions
        transitions =  new ArrayList<>();
        availableActions = new ArrayList<>();

        //if we have an json file with states
        if (states_object != null)
        {
            try
            {
                //We're trying to open it
                JSONObject jsonObject = new JSONObject(states_object);

                //Then we are getting a JSON array node
                JSONArray statesJSON = jsonObject.getJSONArray("states");

                //After that we get an initial state
                JSONObject initialStateJSON = statesJSON.getJSONObject(0);
                State initialState = new State(initialStateJSON.getString("initial_state"));

                //Setting this state to current
                setCurrentState(initialState);

                //TODO: remove after debug
                Log.i(TAG, "initial state -> " + currentState.getName());

                //We begin to loop through all states
                for (int i = 1; i < statesJSON.length(); i++)
                {
                    //Then we get each of the states
                    JSONObject stateJSON = statesJSON.getJSONObject(i);

                    //Get his name and
                    String stateName = stateJSON.getString("name");
                    //Create a new State object
                    State state = new State(stateName);

                    //Get transitions of each state
                    JSONArray transitionsJSON = stateJSON.getJSONArray("transition");

                    //Loop through them
                    for(int transition_position = 0; transition_position < transitionsJSON.length(); transition_position++)
                    {
                        //Get transition object
                        JSONObject transitionJSON = transitionsJSON.getJSONObject(transition_position);

                        //Getting it's action
                        String action = transitionJSON.getString("action");
                        //Adding this action to the list
                        addUniqueAction(action);

                        //Getting next state
                        String nextState = transitionJSON.getString("next_state");

                        //Creating object for each transition
                        Transition transition = new Transition(state, new State(nextState), new Action(action));
                        //And adding them to list.
                        transitions.add(transition);
                    }
                }
            }
            //Catching an exception if there's something wrong with JSON objects.
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method adds all unique actions of a state machine.
     * @param action_name name of other action.
     * @return Nothing.
     */
    private void addUniqueAction(String action_name)
    {
        //Flag-variable for checking if given action is already in the list.
        boolean isHere = false;

        //Go through all actions
        for(Action action : availableActions)
        {
            //If the action is already here
            if (action.getActionTitle().equals(action_name))
            {
                //Set flag that it's here.
                isHere = true;
                //Stop checking
                break;
            }
        }

        //if there's no action like that then add it to the list.
        if (!isHere) availableActions.add(new Action(action_name));
    }

    /**
     * Sets given state to the current.
     * @param initialState given state.
     * @return Nothing.
     */
    private void setCurrentState(State initialState)
    {
        currentState = initialState;
    }
}

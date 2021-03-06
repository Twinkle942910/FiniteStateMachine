package com.example.twinkle.finitestatemachine;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.twinkle.finitestatemachine.machine.FiniteStateMachine;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private FiniteStateMachine finiteStateMachine;
    private TextView tvArmedDisarmed;
    private TextView tvCurrentStateOutput;
    private Button bLock;
    private Button bLockX2;
    private Button bUnlock;
    private Button bUnlockX2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initStateMachine();

        initStateOutput();
        initArmedDisarmedOutput();
        initTransitionButtons();
        initTransitionButtonListeners();
        setStateOutput();
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.lock_action_button:
                finiteStateMachine.transition(finiteStateMachine.getActionByPosition(0));
                break;

            case R.id.lockx2_action_button:
                finiteStateMachine.transition(finiteStateMachine.getActionByPosition(1));
                break;

            case R.id.unlock_action_button:
                finiteStateMachine.transition(finiteStateMachine.getActionByPosition(2));
                break;

            case R.id.unlockx2_action_button:
                finiteStateMachine.transition(finiteStateMachine.getActionByPosition(3));
                break;
        }

        setStateOutput();
    }

    private void initStateOutput()
    {
        tvCurrentStateOutput = (TextView) findViewById(R.id.current_state);
    }

    private void initArmedDisarmedOutput()
    {
        tvArmedDisarmed = (TextView) findViewById(R.id.armed_or_not);
    }

    private void initTransitionButtons()
    {
        bLock = (Button) findViewById(R.id.lock_action_button);
        bLockX2 = (Button) findViewById(R.id.lockx2_action_button);
        bUnlock = (Button) findViewById(R.id.unlock_action_button);
        bUnlockX2 = (Button) findViewById(R.id.unlockx2_action_button);
    }

    private void initTransitionButtonListeners()
    {
        bLock.setOnClickListener(this);
        bLockX2.setOnClickListener(this);
        bUnlock.setOnClickListener(this);
        bUnlockX2.setOnClickListener(this);
    }

    /**
     * Initializes a machine and read state information from file.
     */
    private void initStateMachine()
    {
        finiteStateMachine = new FiniteStateMachine();
        finiteStateMachine.initialize(loadJSONFromResources());
    }

    /**
     * Check if state is armed or disarmed.
     * @return String content of a state(is it armed/disarmed).
     */
    private String checkArmedDisarmed()
    {
        return finiteStateMachine.getCurrentStateName().contains("Armed") ? "Armed" : "Disarmed";
    }

    /**
     * Gets a color of a current state.
     * @return int color of a state(is it armed/disarmed).
     */
    private int checkArmedDisarmedColor()
    {
        if(checkArmedDisarmed().equals("Armed")) return ContextCompat.getColor(getApplicationContext(), R.color.colorArmed);
        return ContextCompat.getColor(getApplicationContext(), R.color.colorDisarmed);
    }

    private void setStateOutput()
    {
        tvArmedDisarmed.setText(checkArmedDisarmed());
        tvCurrentStateOutput.setText(finiteStateMachine.getCurrentStateName());
        findViewById(R.id.state_layout).setBackgroundColor(checkArmedDisarmedColor());
    }

    /**
     * Gets a json file of a states.
     * @return String a json file.
     */
    private String loadJSONFromResources()
    {
        String json = null;
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.keyless_entry_system_states);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}

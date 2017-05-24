package com.example.twinkle.finitestatemachine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
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

        initStateOutput();
        initTransitionButtons();
        initTransitionButtonListeners();
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.lock_action_button:

                break;

            case R.id.lockx2_action_button:

                break;

            case R.id.unlock_action_button:

                break;

            case R.id.unlockx2_action_button:

                break;
        }

    }

    private void initStateOutput()
    {
        tvCurrentStateOutput = (TextView) findViewById(R.id.current_state);
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
}

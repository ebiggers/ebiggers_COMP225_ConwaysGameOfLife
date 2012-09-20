package org.example;

import android.app.Activity;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.view.View;

public class MainActivity extends Activity implements OnClickListener
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        findViewById(R.id.continue_button).setOnClickListener(this);
        findViewById(R.id.new_button).setOnClickListener(this);
        findViewById(R.id.about_button).setOnClickListener(this);
        findViewById(R.id.exit_button).setOnClickListener(this);
    }

    public void onClick(View v)
    {
        switch (v.getId()) {
        case R.id.continue_button:
            startActivity(new Intent(this, GameOfLife.class));
            break;
        case R.id.new_button:
            startActivity(new Intent(this, GameOfLife.class));
            break;
        case R.id.about_button:
            startActivity(new Intent(this, About.class));
            break;
        case R.id.exit_button:
            finish();
            break;
        }
    }
}

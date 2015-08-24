package hw2.mc.mananwason.hw2_manan_wason.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import hw2.mc.mananwason.hw2_manan_wason.R;

/**
 * Created by MananWason on 8/24/2015.
 */
public class InfoActivity extends AppCompatActivity {

    public static final String TAG = "Info Activity";


    private String previousState = "No state";
    private String currentState = "No state";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_activity);
        currentState = "Created";
        Log.i(TAG, formattedLog(previousState, currentState));
        previousState = "Created";

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_info);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getResources().getString(R.string.result_activity));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String name = getIntent().getStringExtra(MainActivity.NAME);
        String age = getIntent().getStringExtra(MainActivity.AGE);
        String date = getIntent().getStringExtra(MainActivity.DOB);
        TextView text_name = (TextView) findViewById(R.id.text_name);
        TextView text_dob = (TextView) findViewById(R.id.text_dob);
        TextView text_age = (TextView) findViewById(R.id.text_age);
        text_name.setText(name);
        text_dob.setText(date);
        text_age.setText(age);
    }

    @Override
    protected void onStart() {
        super.onStart();
        currentState = "Started";
        Log.i(TAG, formattedLog(previousState, currentState));
        previousState = "Started";
    }

    @Override
    protected void onResume() {
        super.onResume();
        currentState = "Resumed";
        Log.i(TAG, formattedLog(previousState, currentState));
        previousState = "Resumed";
    }

    @Override
    protected void onPause() {
        super.onPause();
        currentState = "Paused";
        Log.i(TAG, formattedLog(previousState, currentState));
        previousState = "Paused";
    }

    @Override
    protected void onStop() {
        super.onStop();
        currentState = "Stopped";
        Log.i(TAG, formattedLog(previousState, currentState));
        previousState = "Stopped";
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        currentState = "Restarted";
        Log.i(TAG, formattedLog(previousState, currentState));
        previousState = "Restarted";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        currentState = "Destroyed";
        Log.i(TAG, formattedLog(previousState, currentState));
        previousState = "Destroyed";
    }

    public String formattedLog(String previous, String current) {
        String log_raw = "State of activity %s changed from %s to %s";
        String log = String.format(log_raw, TAG, previous, current);
        return log;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
        
    }
}

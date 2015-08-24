package hw2.mc.mananwason.hw2_manan_wason.Activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import hw2.mc.mananwason.hw2_manan_wason.R;

public class MainActivity extends AppCompatActivity {

    public static final String NAME = "NAME";
    public static final String AGE = "AGE";
    public static final String DOB = "DOB";
    public static final String TAG = "Main Activity";
    Calendar myCalendar = Calendar.getInstance();
    private String age;
    private String name;
    private Spinner spinner;
    private DatePickerDialog.OnDateSetListener date;
    private EditText dateEditText;
    private String dateString;
    private EditText nameEditText;
    private TextInputLayout dateInput;
    private String previousState = "No state";
    private String currentState = "No state";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentState = "Created";
        Log.i(TAG, formattedLog(previousState, currentState));
        previousState = "Created";
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        nameEditText = (EditText) findViewById(R.id.username_edit_text);
        dateEditText = (EditText) findViewById(R.id.date_edit_text);
        spinner = (Spinner) findViewById(R.id.spinner);
        Button submitButton = (Button) findViewById(R.id.submit_button);
        Button clearButton = (Button) findViewById(R.id.clear_button);
        dateInput = (TextInputLayout) findViewById(R.id.date_input_layout);

        //Populate ageList arraylist
        final List ageList = new ArrayList<Integer>();
        for (int i = 1; i <= 100; i++) {
            ageList.add(Integer.toString(i));
        }
        //adapter for spinner
        final ArrayAdapter<Integer> spinnerArrayAdapter = new ArrayAdapter<Integer>(
                this, android.R.layout.simple_spinner_item, ageList);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                age = spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });

        dateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });
        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();

            }

        };

        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                dateEditText.setInputType(InputType.TYPE_NULL);
                dateEditText.setFocusable(false);
                new DatePickerDialog(MainActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar today = Calendar.getInstance();
                int caluculatedAge = today.get(Calendar.YEAR) - myCalendar.get(Calendar.YEAR);
                int actualAge = Integer.parseInt(spinner.getSelectedItem().toString());
                name = nameEditText.getText().toString();

                if (today.get(Calendar.DAY_OF_YEAR) <= myCalendar.get(Calendar.DAY_OF_YEAR)) {
                    caluculatedAge--;
                }
                if (caluculatedAge != actualAge) {

                    Toast.makeText(getApplicationContext(), "Either age or DOB is wrong", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                    intent.putExtra(AGE, age);
                    intent.putExtra(NAME, name);
                    intent.putExtra(DOB, dateString);
                    startActivity(intent);
                }
            }
        });
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameEditText.setText(null);
                dateEditText.setText(null);
                spinner.setSelection(0);
            }
        });
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds age to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void updateLabel() {

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        dateString = sdf.format(myCalendar.getTime());
        dateEditText.setText(dateString);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return  super.onOptionsItemSelected(item);
    }

    public String formattedLog(String previous, String current) {
        String log_raw = "State of activity %s changed from %s to %s";
        String log = String.format(log_raw, TAG, previous, current);
        return log;
    }
}

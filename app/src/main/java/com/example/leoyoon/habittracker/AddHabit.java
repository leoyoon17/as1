package com.example.leoyoon.habittracker;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.Boolean.getBoolean;

public class AddHabit extends ActionBarActivity {

    private static final String FILENAME = "file.sav";
    private ArrayAdapter<Habit> adapter;
    private EditText HabitNameText;
    private ArrayList<Habit> habitList;
    private Habit newHabit;
    private String name;
    private ToggleButton SundayButton;
    private ToggleButton MondayButton;
    private ToggleButton TuesdayButton;
    private ToggleButton WednesdayButton;
    private ToggleButton ThursdayButton;
    private ToggleButton FridayButton;
    private ToggleButton SaturdayButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habit);

        habitList = new ArrayList<Habit>();
        HabitNameText = (EditText) findViewById(R.id.habitNameText);
        //HabitNameText.setInputType(InputType.TYPE_CLASS_TEXT);


        SundayButton = (ToggleButton) findViewById(R.id.sundayButton);
        MondayButton = (ToggleButton) findViewById(R.id.mondayButton);
        TuesdayButton = (ToggleButton) findViewById(R.id.tuesdayButton);
        WednesdayButton = (ToggleButton) findViewById(R.id.wednesdayButton);
        ThursdayButton = (ToggleButton) findViewById(R.id.thursdayButton);
        FridayButton = (ToggleButton) findViewById(R.id.fridayButton);
        SaturdayButton = (ToggleButton) findViewById(R.id.saturdayButton);
        Button EveryDayButton = (Button) findViewById(R.id.everyDayButton);
        Button AddHabitButton = (Button) findViewById(R.id.addHabitButton);
        name = HabitNameText.getText().toString();
        newHabit = new Habit(name);
        newHabit.setName(name);


        AddHabitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_OK);
                newHabit.setName(HabitNameText.getText().toString());
                habitList.add(newHabit);
                adapter.notifyDataSetChanged();
                saveInFile();
                finish();
            }
        }
        );

        SundayButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) { newHabit.Sunday = TRUE; }
                else { newHabit.Sunday = FALSE; }
            }
        });
        MondayButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) { newHabit.Monday = TRUE; }
                else { newHabit.Monday = FALSE; }
            }
        });
        TuesdayButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) { newHabit.Tuesday = TRUE; }
                else { newHabit.Tuesday = FALSE; }
            }
        });
        WednesdayButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) { newHabit.Wednesday = TRUE; }
                else { newHabit.Wednesday = FALSE; }

            }
        });
        ThursdayButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) { newHabit.Thursday = TRUE; }
                else { newHabit.Thursday = FALSE; }
            }
        });
        FridayButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) { newHabit.Friday = TRUE; }
                else { newHabit.Friday = FALSE; }
            }
        });
        SaturdayButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) { newHabit.Saturday = TRUE;}
                else { newHabit.Saturday = FALSE; }
            }
        });
        EveryDayButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_OK);
                MondayButton.setChecked(TRUE);
                TuesdayButton.setChecked(TRUE);
                WednesdayButton.setChecked(TRUE);
                ThursdayButton.setChecked(TRUE);
                FridayButton.setChecked(TRUE);
                SaturdayButton.setChecked(TRUE);
                SundayButton.setChecked(TRUE);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        loadFromFile();
        adapter = new ArrayAdapter<Habit>(this, R.layout.list_item, habitList);

    }

    private void loadFromFile() {
        ArrayList<String> habits = new ArrayList<String>();

        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            //Code taken from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt Sept.22,2016
            Type listType = new TypeToken<ArrayList<Habit>>() {
            }.getType();
            habitList = gson.fromJson(in, listType);
        } catch (FileNotFoundException e) {
            habitList = new ArrayList<Habit>();
        } catch (IOException e) {
            System.err.println("Something Else?");
            throw new RuntimeException();
        }
    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, 0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(habitList, writer);
            writer.flush();
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }


}

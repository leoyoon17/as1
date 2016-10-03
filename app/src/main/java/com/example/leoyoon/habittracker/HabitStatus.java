package com.example.leoyoon.habittracker;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.Boolean.TRUE;

//import static com.example.leoyoon.habittracker.HabitListController.getHabitList;
//import static com.example.leoyoon.habittracker.HabitListController.habitList;


public class HabitStatus extends ActionBarActivity {

    private static final String FILENAME = "file.sav";
    private TextView HabitName;
    private TextView NumCompletions;
    private TextView DateAdded;
    private ArrayAdapter<Habit> adapter;
    private ArrayList<Habit> habitList;
    int Completed;
    int index;
    //http://stackoverflow.com/questions/21250339/how-to-pass-arraylistcustomeobject-from-one-activity-to-another code taken on 09/28/2016
    @SuppressWarnings("unchecked")


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_status);
        habitList = (ArrayList<Habit>)getIntent().getSerializableExtra("habitList");
        index = getIntent().getIntExtra("index", 0);


        Button CompleteHabitButton = (Button) findViewById(R.id.completeHabitButton);
        Button ClearCompletionHistoryButton = (Button) findViewById(R.id.clearCompletionHistoryButton);
        Button DeleteHabitButton = (Button) findViewById(R.id.deleteHabitButton);
        CheckBox SundayCheck = (CheckBox) findViewById(R.id.SundayCheck);
        CheckBox MondayCheck = (CheckBox) findViewById(R.id.MondayCheck);
        CheckBox TuesdayCheck = (CheckBox) findViewById(R.id.TuesdayCheck);
        CheckBox WednesdayCheck = (CheckBox) findViewById(R.id.WednesdayCheck);
        CheckBox ThursdayCheck = (CheckBox) findViewById(R.id.ThurdsayCheck);
        CheckBox FridayCheck = (CheckBox) findViewById(R.id.FridayCheck);
        CheckBox SaturdayCheck = (CheckBox) findViewById(R.id.SaturdayCheck);
        DateAdded = (TextView) findViewById(R.id.dateAdded);

        // http://stackoverflow.com/questions/10312889/how-to-get-date-object-in-yyyy-mm-dd-format-in-android code taken on 09/30/2016
        Date cDate = habitList.get(index).getDate();
        String fDate = new SimpleDateFormat("yyyy-MM-dd").format(cDate);

        DateAdded.setText(fDate);
        HabitName = (TextView) findViewById(R.id.HabitName);
        HabitName.setText(habitList.get(index).getName());

        Completed = (habitList.get(index)).getCompleted();
        NumCompletions = (TextView) findViewById(R.id.NumCompletions);
        NumCompletions.setText((habitList.get(index).getCompleted().toString()));

        if ((habitList.get(index)).Sunday) { SundayCheck.setChecked(TRUE); }
        if ((habitList.get(index)).Monday) { MondayCheck.setChecked(TRUE); }
        if ((habitList.get(index)).Tuesday) { TuesdayCheck.setChecked(TRUE); }
        if ((habitList.get(index)).Wednesday) { WednesdayCheck.setChecked(TRUE); }
        if ((habitList.get(index)).Thursday) { ThursdayCheck.setChecked(TRUE); }
        if ((habitList.get(index)).Friday) { FridayCheck.setChecked(TRUE); }
        if ((habitList.get(index)).Saturday) { SaturdayCheck.setChecked(TRUE); }

        CompleteHabitButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                setResult(RESULT_OK);
                Completed = Completed + 1;
                (habitList.get(index)).setCompleted(Completed);
                //Toast.makeText(getApplication().getBaseContext(),((habitList.get(index)).getCompleted()).toString(), Toast.LENGTH_LONG).show();
                saveInFile();
                finish();
            }
        });

        ClearCompletionHistoryButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                setResult(RESULT_OK);
                (habitList.get(index)).clearCompleted();
                saveInFile();
                finish();
            }
        });
        DeleteHabitButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                setResult(RESULT_OK);
                //Toast.makeText(getApplication().getBaseContext(), "Something", Toast.LENGTH_SHORT).show();
                habitList.remove(habitList.get(index));
                saveInFile();
                finish();
            }
        });

    }
    private void saveInFile(){
        try {
            FileOutputStream fos = openFileOutput(FILENAME,0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(habitList, writer);
            writer.flush();
        } catch (FileNotFoundException e){
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }


}

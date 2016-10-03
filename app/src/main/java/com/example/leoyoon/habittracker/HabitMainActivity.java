package com.example.leoyoon.habittracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class HabitMainActivity extends Activity {

    private static final String FILENAME = "file.sav";
    private EditText bodyText;
    private ListView mainHabitList;
    public ArrayList<Habit> habitList = new ArrayList<Habit>();
    private ArrayAdapter<Habit> adapter; // going to be used later when we implement buttons

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habits);

        mainHabitList = (ListView) findViewById(R.id.HabitView);
        Button addHabitPageButton = (Button) findViewById(R.id.addHabitPageButton);

        addHabitPageButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(HabitMainActivity.this, AddHabit.class);
                //intent.putExtra("habitList", habitList);
                startActivity(intent);
            }

        });
//        http://stackoverflow.com/questions/9097723/adding-an-onclicklistener-to-listview-android code taken on 09/28/2016
        mainHabitList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int index = position;
                Intent intent = new Intent(HabitMainActivity.this, HabitStatus.class);
                intent.putExtra("habitList", habitList);
                intent.putExtra("index", index);
//               http://stackoverflow.com/questions/21250339/how-to-pass-arraylistcustomeobject-from-one-activity-to-another code taken on 09/28/2016
                adapter.notifyDataSetChanged();
                startActivity(intent);
                saveInFile();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        loadFromFile();
        adapter = new ArrayAdapter<Habit>(this, R.layout.list_item, habitList);
        mainHabitList.setAdapter(adapter);

    }

    private void loadFromFile() {
        ArrayList<String> habits = new ArrayList<String>();

        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            //Code taken from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt Sept.22,2016
            Type listType = new TypeToken<ArrayList<Habit>>(){}.getType();
            habitList = gson.fromJson(in, listType);
        } catch (FileNotFoundException e) {
            habitList = new ArrayList<Habit>();
        } catch (IOException e) {
            System.err.println("Something Else?");
            throw new RuntimeException();
        }
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

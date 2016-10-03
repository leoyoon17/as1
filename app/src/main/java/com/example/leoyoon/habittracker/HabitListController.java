package com.example.leoyoon.habittracker;

import com.example.leoyoon.habittracker.Habit;
import com.example.leoyoon.habittracker.HabitList;

/**
 * Created by Leo Yoon on 27/09/2016.
 */
    // Lazy Singleton
public class HabitListController {
    private static HabitList habitList = null;
    static public HabitList getHabitList(){
        if (habitList == null){
            habitList = new HabitList();
        }
        return habitList;
    }
    public void addHabit(Habit habit){
        getHabitList().addHabit(habit);
    }
}

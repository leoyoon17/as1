package com.example.leoyoon.habittracker;

import java.util.ArrayList;
import java.util.Collection;
import java.io.Serializable;

/**
 * Created by Leo Yoon on 24/09/2016.
 */
public class HabitList{
    protected ArrayList<Habit> habitList;
    public HabitList(){
        habitList = new ArrayList<Habit>();
    }

    public Collection<Habit> getHabits() {
        return habitList;
    }

    public Habit getHabit(int index) { return habitList.get(index); }
    public void addHabit(Habit habit){
        habitList.add(habit);
    }
    public void removeHabit(Habit habit) {habitList.remove(habit);}
    public boolean hasHabit(Habit habit) { return habitList.contains(habit); }


}

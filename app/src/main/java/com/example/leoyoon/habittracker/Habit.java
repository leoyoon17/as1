package com.example.leoyoon.habittracker;

import android.os.Parcelable;

import java.util.Calendar;
import java.util.Date;
import java.io.Serializable;

import static java.lang.Boolean.FALSE;

/**
 * Created by Leo Yoon on 24/09/2016.
 */
public class Habit implements Serializable{
    private String habitName;
    private Date date;
    protected Boolean Sunday;
    protected Boolean Monday;
    protected Boolean Tuesday;
    protected Boolean Wednesday;
    protected Boolean Thursday;
    protected Boolean Friday;
    protected Boolean Saturday;
    protected Integer Completed; // Number of times that the habit has be completed
    protected Integer NotCompleted;

    public Habit(String habitName){
        this.habitName = habitName;
        this.date = new Date();
        this.Sunday = FALSE;
        this.Monday = FALSE;
        this.Tuesday = FALSE;
        this.Wednesday = FALSE;
        this.Thursday = FALSE;
        this.Friday = FALSE;
        this.Saturday = FALSE;
        this.Completed = 0;
        this.NotCompleted = 0;
    }
    @Override
    public String toString(){
        return habitName;
    }

    public String getName(){
        return this.habitName;
    }

    public void setName(String habitName){
        this.habitName = habitName;
    }

    public Date getDate() { return this.date;}

    public Integer getCompleted() { return this.Completed; }

    public void clearCompleted() { this.setCompleted(Completed = 0); }

    public void setCompleted(Integer Completed) { this.Completed = Completed; }
}

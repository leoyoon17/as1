import android.test.ActivityInstrumentationTestCase2;

import com.example.leoyoon.habittracker.Habit;
import com.example.leoyoon.habittracker.HabitList;

import java.util.ArrayList;

import static android.R.id.list;

/**
 * Created by Leo Yoon on 03/10/2016.
 */

public class HabitListTest extends ActivityInstrumentationTestCase2 {
    public HabitListTest() {super(com.example.leoyoon.habittracker.HabitMainActivity.class);}
    public void testHasHabit(){
        Habit habit = new Habit("test");
        ArrayList<Habit> habitList = new ArrayList<Habit>();
        habitList.add(habit);
        assertTrue(habitList.contains(habit));
    }

    public void testAddHabit() {
        HabitList habitList = new HabitList();
        Habit habit = new Habit("test habit");
        habitList.addHabit(habit);
        assert(habitList.hasHabit(habit));

    }

    public void testRemoveHabit() {
        HabitList habitList = new HabitList();
        Habit habit = new Habit("habit");
        habitList.addHabit(habit);
        habitList.removeHabit(habit);
        assertFalse(habitList.hasHabit(habit));
    }

    public void testGetHabit() {
        HabitList habitList = new HabitList();
        Habit habit = new Habit("test Habit");
        habitList.addHabit(habit);
        Habit returnedHabit = habitList.getHabit(0);
        assertEquals(returnedHabit, habitList.getHabit(0));
    }
}

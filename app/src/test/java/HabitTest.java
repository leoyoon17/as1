import android.test.ActivityInstrumentationTestCase2;

import com.example.leoyoon.habittracker.Habit;

/**
 * Created by Leo Yoon on 03/10/2016.
 */

public class HabitTest extends ActivityInstrumentationTestCase2 {
    public HabitTest() { super(com.example.leoyoon.habittracker.HabitMainActivity.class); }
    public void testToString() {
        Habit habit = new Habit("newHabit");
        //assertTrue()
    }
    public void testGetName() {
        Habit habit = new Habit("test");
        String test = "test";
        assertEquals(test, habit.getName());
    }
    public void testSetName() {
        Habit habit = new Habit("test");
        habit.setName("test2");
        assertEquals("test2", habit.getName());
    }

    public void testGetCompleted() {
        Habit habit = new Habit("test");
        Integer test = 0;
        assertEquals(test, habit.getCompleted());

    }

    public void testClearCompleted() {
        Habit habit = new Habit("test");
        Integer completed = 0;
        habit.clearCompleted();
        assertEquals(completed, habit.getCompleted());
    }

    public void testSetCompleted() {
        Habit habit = new Habit("test");
        Integer completed = 1;
        habit.setCompleted(completed);
        assertEquals(completed, habit.getCompleted());
    }
}

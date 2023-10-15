package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import model.Controller;


import java.text.SimpleDateFormat;
import java.util.Date;

public class ControllerTest {
    private Controller controller;

    @Before
    public void setUp() {
        controller = new Controller();
    }

    @Test
    public void testAddTaskAndShowTasksByPriority() {
        Date deadline = controller.constructDeadline(2023, 10, 31);
        controller.addTask("1", "Task 1", "Description 1", 1, deadline);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String expectedOutput = "Tasks by Priority:\n" +
                "Priority: HIGH_PRIORITY\n" +
                "Id: 1\n" +
                "Title: Task 1\n" +
                "Description: Description 1\n" +
                "Deadline: " + dateFormat.format(deadline) + "\n\n";
        assertEquals(expectedOutput, controller.showTasksByPriority());
    }

    @Test
    public void testShowTasksByDeadline() {
        Date deadline = controller.constructDeadline(2023, 10, 31);
        controller.addTask("1", "Task 1", "Description 1", 1, deadline);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String expectedOutput = "Tasks by Deadline:\n" +
                "Id: 1\n" +
                "Title: Task 1\n" +
                "Description: Description 1\n" +
                "Deadline: " + dateFormat.format(deadline) + "\n\n";
        assertEquals(expectedOutput, controller.showTasksByDeadline());
    }

    @Test
    public void testModifyTask() {
        Date deadline = controller.constructDeadline(2023, 10, 31);
        controller.addTask("1", "Task 1", "Description 1", 1, deadline);

        assertEquals("Task with ID 1 has been modified.", controller.modifyTask("1", 1, "Modified Task", "", new Date(), 2));
    }

    @Test
    public void testRemoveTask() {
        Date deadline = controller.constructDeadline(2023, 10, 31);
        controller.addTask("1", "Task 1", "Description 1", 1, deadline);
        assertEquals("Task with ID 1 has been removed.", controller.removeTask("1"));
    }

    @Test
    public void testUndoAction() {
        Date deadline = controller.constructDeadline(2023, 10, 31);
        controller.addTask("1", "Task 1", "Description 1", 1, deadline);
        assertTrue(controller.undoAction());
        assertFalse(controller.undoAction());
    }

    @Test
    public void testRandomID() {
        String randomID = controller.randomID();
        assertNotNull(randomID);
        assertEquals(5, randomID.length());
    }

    @Test
    public void testShowTasks() {
        Date deadline = controller.constructDeadline(2023, 10, 31);
        controller.addTask("1", "Task 1", "Description 1", 1, deadline);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String expectedOutput = "Task:\n" +
                "Id: 1\n" +
                "Title: Task 1\n" +
                "Description: Description 1\n" +
                "Priority: HIGH_PRIORITY\n" +
                "Deadline: " + dateFormat.format(deadline) + "\n\n";
        assertEquals(expectedOutput, controller.showTasks());
    }
}

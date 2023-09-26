package model;
import java.util.Random;
import java.util.GregorianCalendar;

public class TaskReminderController {
    
    private HashTable<String, TaskReminder> taskTable;

    public TaskReminderController() {
        taskTable = new HashTable<>();
    }

    public boolean registerT_R(String id, String title, String description, int priority, int publicationDay, int publicationMonth, int publicationYear) {
        GregorianCalendar publication_date = new GregorianCalendar(publicationYear, publicationMonth - 1, publicationDay);
        Priority p;

        switch (priority) {
            case 1:
                p = Priority.HIGH_PRIORITY;
                break;
            case 2:
                p = Priority.LOW_PRIORITY;
                break;
            default:
                return false;
        }

        TaskReminder tk = new TaskReminder(id, title, description, publication_date, p);
        return addTaskReminder(tk);
    }

    public boolean addTaskReminder(TaskReminder taskReminder) {
        String id = taskReminder.getId();
        taskTable.agregar(id, taskReminder);
        return true;
    }

    //public boolean check() {
      
    //}

    public TaskReminder getTaskReminder(String id) {
        return taskTable.obtener(id);
    }

    public void removeTaskReminder(String id) {
        taskTable.eliminar(id);
    }

    public String randomID() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        Random rnd = new Random();
        for (int i = 0; i < 5; i++) {
            int index = rnd.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }
  
    
}

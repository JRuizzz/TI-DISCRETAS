package model;
import java.util.Random;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TaskReminderController {
    private Map<String, TaskReminder> taskReminderMap = new HashMap<>();




    public boolean registerT_R(String id, String tiitle, String description, int priority, int publicationDay, int publicationMonth, int publicationYear){
     GregorianCalendar publication_date = new GregorianCalendar(publicationYear, publicationMonth - 1, publicationDay);
        Priority p; 
     switch(priority){
        case 1:
        p = Priority.HIGH_PRIORITY; 
        break; 

        case 2: 
        p = Priority.LOW_PRIORITY;
        break; 

        default:
        return false; 
     }
     TaskReminder tk = new TaskReminder(id,tiitle, description, publication_date, p);
     addTaskReminder(tk);
     return true; 
    }



    public boolean addTaskReminder(TaskReminder taskReminder) {
        
        if(taskReminder!=null){
            taskReminderMap.put(taskReminder.getId(), taskReminder);
            return true; 
        } else {
           return false;
        }
    }

    public boolean check() {
        return !taskReminderMap.isEmpty();
    }

    public TaskReminder getTaskReminder(String id) {
        return taskReminderMap.get(id);
    }

    public void removeTaskReminder(String  id) {
        taskReminderMap.remove(id);
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

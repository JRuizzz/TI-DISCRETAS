package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import util.PriorityQueue;
import java.util.Random;
import util.HashTable;

public class Controller {
    private HashTable<String,TaskReminder> hashTable;
    private PriorityQueue<TaskReminder> priorityQueue;
    public Controller(){
        hashTable = new HashTable<>();
        priorityQueue = new PriorityQueue<>();
    }

    public void addTask(String id,String title,String description,int priority,Date deadline){
         int pI = 0;
        Priority p = null;
        switch(priority){
            case 1:
                p = Priority.HIGH_PRIORITY;
                pI = 3; 
            break;
            case 2:
                p = Priority.MEDIUM_PRIORITY; 
                pI = 2;
            break; 
            case 3:
                p = Priority.LOW_PRIORITY;
                pI = 1;
            break;
        }
        TaskReminder task = new TaskReminder(id, title, description, deadline, p,pI);
        hashTable.add(task);
        priorityQueue.enqueue(task);
    }

    
    
    public String showTasksByPriority() {
        StringBuilder msg = new StringBuilder("Tasks by Priority:\n");
        while (!priorityQueue.isEmpty()) {
            TaskReminder task = priorityQueue.dequeue();
            msg.append("Priority: ").append(task.getPriority()).append("\n");
            msg.append("Id: ").append(task.getId()).append("\n");
            msg.append("Title: ").append(task.getTitle()).append("\n");
            msg.append("Description: ").append(task.getDescription()).append("\n");
            msg.append("Deadline: ").append(task.getDeadline()).append("\n\n");
        }
        return msg.toString();
    }

    public String modifyTask(String id, int option, String newTitle,String newDescription, Date newDeadline, int newPriority) {
        
        String msg = "";
        TaskReminder task = hashTable.get(id);
        
        if (task == null) {
            msg += "Task with ID " + id + " not found.";
        }
        
        Priority p = null;
        switch(newPriority){
            case 1:
                p = Priority.HIGH_PRIORITY;
            break;
            case 2:
                p = Priority.LOW_PRIORITY;
            break;
        }
            
        switch (option) {
            case 1:
                task.setTitle(newTitle);
                break;
            case 2:
                task.setDescription(newDescription);
                break;
            case 3:
                task.setDeadline(newDeadline);
                break;
            case 4:
                task.setPriority(p);
                break;
        }
    
        msg+="Task with ID " + id + " has been modified.";

        return msg;
    }
    
    public String removeTask(String taskId) {
        TaskReminder taskToRemove = hashTable.get(taskId);
        String msg = "";
        if (taskToRemove != null) {
            hashTable.remove(taskId);
            msg += "Task with ID " + taskId + " has been removed.";
        } else {
            msg += "Task with ID " + taskId + " not found.";
        }

        return msg;
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

    public Date constructDeadline(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);

        return calendar.getTime();
    }

    public String showTask() {
        List<TaskReminder> tasks = hashTable.getAllTasks();
        StringBuilder msg = new StringBuilder();
    
        // Ordena las tareas primero por prioridad y luego por fecha límite
        Collections.sort(tasks, new Comparator<TaskReminder>() {
            @Override
            public int compare(TaskReminder task1, TaskReminder task2) {
                int priorityComparison = task1.getPriority().compareTo(task2.getPriority());
                if (priorityComparison == 0) {
                    return task1.getDeadline().compareTo(task2.getDeadline());
                }
                return priorityComparison;
            }
        }
        );
    
        String currentPriority = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
        for (TaskReminder task : tasks) {
            if (!currentPriority.equals(task.getPriority().toString())) {
                msg.append(task.getPriority().toString()).append(":\n");
                currentPriority = task.getPriority().toString();
            }
    
            msg.append("Id: ").append(task.getId()).append("\n");
            msg.append("Title: ").append(task.getTitle()).append("\n");
            msg.append("Description: ").append(task.getDescription()).append("\n");
            msg.append("Deadline: ").append(dateFormat.format(task.getDeadline())).append("\n\n");
        }
    
        return msg.toString();
    }
    
}

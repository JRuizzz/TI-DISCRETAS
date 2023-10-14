package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import util.PriorityQueue;
import java.util.Random;
import util.DynamicStack;
import util.HashTable;

public class Controller {
    private HashTable<String,TaskReminder> hashTable;
    private PriorityQueue<TaskReminder> priorityQueue;
    private DynamicStack<Action> actionStack;

    public Controller(){
        hashTable = new HashTable<>();
        priorityQueue = new PriorityQueue<>();
        actionStack = new DynamicStack<>(null, 0);
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

        Action addAction = new Action(ActionType.ADD_TASK, task);
        actionStack.push(addAction);
    }

    
    
    public String showTasksByPriority() {
        StringBuilder msg = new StringBuilder("Tasks by Priority:\n");
    
        System.out.println("Before dequeuing: Is the priorityQueue empty? Size " + priorityQueue.isEmpty() + (" its Size is " + priorityQueue.size()));
    
        for (TaskReminder task : priorityQueue) {
            msg.append("Priority: ").append(task.getPriority()).append("\n");
            msg.append("Id: ").append(task.getId()).append("\n");
            msg.append("Title: ").append(task.getTitle()).append("\n");
            msg.append("Description: ").append(task.getDescription()).append("\n");
            msg.append("Deadline: ").append(task.getDeadline()).append("\n\n");
        }
    
        if (!priorityQueue.isEmpty()) {
            TaskReminder taskId = priorityQueue.getRoot();
            priorityQueue.dequeue();
            System.out.println("The task " + taskId.getId() + " has been removed.");
        }
    
        System.out.println("After dequeuing: Is the priorityQueue empty? " + priorityQueue.isEmpty() + (" its Size is " + priorityQueue.size()));
    
        return msg.toString();
    }


    public String modifyTask(String id, int option, String newTitle, String newDescription, Date newDeadline, int newPriority) {
        String msg = "";
        TaskReminder task = hashTable.get(id);
    
        if (task == null) {
            msg += "Task with ID " + id + " not found.";
        }
    
        Priority p = null;
        switch (newPriority) {
            case 1:
                p = Priority.HIGH_PRIORITY;
                break;
            case 2:
                p = Priority.LOW_PRIORITY;
                break;
        }
    
        Action modifyAction = null;
    
        switch (option) {
            case 1:
                String oldTitle = task.getTitle();
                task.setTitle(newTitle);
                modifyAction = new Action(ActionType.MODIFY_TASK, task, oldTitle, newTitle);
                break;
            case 2:
                String oldDescription = task.getDescription();
                task.setDescription(newDescription);
                modifyAction = new Action(ActionType.MODIFY_TASK, task, oldDescription, newDescription);
                break;
            case 3:
                Date oldDeadline = task.getDeadline();
                task.setDeadline(newDeadline);
                modifyAction = new Action(ActionType.MODIFY_TASK, task, oldDeadline, newDeadline);
                break;
            case 4:
                Priority oldPriority = task.getPriority();
                task.setPriority(p);
                modifyAction = new Action(ActionType.MODIFY_TASK, task, oldPriority, p);
                break;
        }
    
        if (modifyAction != null) {
            actionStack.push(modifyAction);
        }

        priorityQueue.modifyTaskById(id, newTitle, newDescription, newDeadline, newPriority);
    
        msg += "Task with ID " + id + " has been modified.";
    
        return msg;
    }
    
    public String removeTask(String taskId) {
        TaskReminder taskToRemove = hashTable.get(taskId);
        String msg = "";
        if (taskToRemove != null) {
            hashTable.remove(taskId);       
            priorityQueue.removeTaskById(taskId);
            msg += "Task with ID " + taskId + " has been removed.";
            Action removeAction = new Action(ActionType.REMOVE_TASK, taskToRemove);
            actionStack.push(removeAction);
        } else {
            msg += "Task with ID " + taskId + " not found.";
        }

        return msg;
    }

    public boolean undoAction() {
        boolean confirmUndoAction;
        if (!actionStack.isEmpty()) {
            Action lastAction = actionStack.pop();
            ActionType actionType = lastAction.getActionType();

            switch (actionType) {
                case ADD_TASK:
                    TaskReminder addedTask = lastAction.getTask();
                    hashTable.remove(addedTask.getId());
                    priorityQueue.dequeue();
                    break;

                case MODIFY_TASK:
                    TaskReminder modifiedTask = lastAction.getTask();
                    Object oldValue = lastAction.getOldValue();
                
                    if (oldValue instanceof String) {
                        modifiedTask.setTitle((String) oldValue);
                    } else if (oldValue instanceof Date) {
                        modifiedTask.setDeadline((Date) oldValue);
                    } else if (oldValue instanceof Priority) {
                        modifiedTask.setPriority((Priority) oldValue);
                    }
                break;
                
                case REMOVE_TASK:
                    TaskReminder removedTask = lastAction.getTask();
                    hashTable.add(removedTask);
                    priorityQueue.enqueue(removedTask);
                break;
            }
            confirmUndoAction = true;
        } else {
            confirmUndoAction = false;
        }
        return confirmUndoAction;
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

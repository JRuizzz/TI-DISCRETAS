package model;
import java.util.Date;

public class TaskReminder implements Comparable<TaskReminder> {
    private String id;
    private String title;
    private String description;
    private Date deadline;
    private Priority priority;
    private int priorityIndex;
    
    public TaskReminder(String id, String title, String description, Date deadline, Priority priority, int priorityIndex) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
        this.priorityIndex = priorityIndex;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority newPriority) {
        this.priority = newPriority;
    }

    public int getPriorityIndex() {
        return priorityIndex;
    }

    public void setPriorityIndex(int priorityIndex) {
        this.priorityIndex = priorityIndex;
    }

    @Override
    public int compareTo(TaskReminder other) {
        return Integer.compare(this.getPriorityIndex(), other.getPriorityIndex());
    }


}

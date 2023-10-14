package model;

public class Action {
    private ActionType actionType;
    private TaskReminder task;
    private Object oldValue;
    private Object newValue;

    public Action(ActionType actionType, TaskReminder task) {
        this.actionType = actionType;
        this.task = task;
    }

    public Action(ActionType actionType, TaskReminder task, Object oldValue, Object newValue) {
        this.actionType = actionType;
        this.task = task;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public TaskReminder getTask() {
        return task;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }
}
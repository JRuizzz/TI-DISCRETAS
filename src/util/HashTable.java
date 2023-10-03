package util;

import java.util.ArrayList;
import java.util.List;

import model.TaskReminder;

public class HashTable<K, V> {

    private ArrayList<NodoDoble<TaskReminder>> hash;

    public HashTable() {
        hash = new ArrayList<>(13);
        for (int i = 0; i < 13; i++) {
            hash.add(null);
        }
    }

    public int function(String key) {
        int count = 0;
        char[] chars = key.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            count += chars[i];
        }
        count %= 13;
        return count;
    }

    public void add(TaskReminder task) {
        int key = function(task.getId());
        NodoDoble<TaskReminder> newNode = new NodoDoble<>(task);

        if (hash.get(key) == null) {
            hash.set(key, newNode);
        } else {
            NodoDoble<TaskReminder> current = hash.get(key);
            while (current.getSiguiente() != null) {
                current = current.getSiguiente();
            }
            current.setSiguiente(newNode);
            newNode.setAnterior(current);
        }
    }

    public TaskReminder get(String taskId) {
        int key = function(taskId);
        NodoDoble<TaskReminder> current = hash.get(key);

        while (current != null) {
            TaskReminder task = current.getContenido();
            if (task.getId().equals(taskId)) {
                return task;
            }
            current = current.getSiguiente();
        }

        return null; 
    }
    
     public List<TaskReminder> getAllTasks() {
        List<TaskReminder> tasks = new ArrayList<>();

        for (NodoDoble<TaskReminder> node : hash) {
            NodoDoble<TaskReminder> current = node;

            while (current != null) {
                tasks.add(current.getContenido());
                current = current.getSiguiente();
            }
        }

        return tasks;
    }

    public void remove(String taskId) {
        int key = function(taskId);
        NodoDoble<TaskReminder> current = hash.get(key);
        NodoDoble<TaskReminder> previous = null;
    
        while (current != null) {
            TaskReminder task = current.getContenido();
            if (task.getId().equals(taskId)) {
                if (previous != null) {
                    previous.setSiguiente(current.getSiguiente());
                } else {
                    hash.set(key, current.getSiguiente());
                }
                return; 
            }
            previous = current;
            current = current.getSiguiente();
        }
    }
    
}

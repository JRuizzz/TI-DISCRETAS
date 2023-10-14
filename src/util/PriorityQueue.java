package util;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import model.Priority;
import model.TaskReminder;
public class PriorityQueue<T extends Comparable<T>> implements Iterable<T> {

    private List<T> heap;

    public PriorityQueue() {
        heap = new ArrayList<>();
    }

    public int size() {
        return heap.size();
    }

    public T getRoot() {
        if (isEmpty()) {
            return null;
        }
        return heap.get(0);
    }

    public T getSecond() {
        if (isEmpty() || heap.size() < 2) {
            return null;
        }
        return heap.get(1);
    }
    
    

    public void enqueue(T element) {
        heap.add(element);
        heapifyUp(heap.size() - 1);
    }
    
    
    public T dequeue() {
        if (isEmpty()) {
            return null;
        }
    
        T root = heap.get(0);
    
        if (heap.size() == 1) {
            heap.clear();  // Si hay solo un elemento, simplemente lo eliminamos.
        } else {
            T lastElement = heap.remove(heap.size() - 1);
            heap.set(0, lastElement);
            heapifyDown(0);
        }
    
        return root;
    }
    
    
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (heap.get(index).compareTo(heap.get(parentIndex)) > 0) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    private void heapifyDown(int index) {
        int leftChildIndex;
        int rightChildIndex;
        int largerChildIndex;

        while (index < heap.size() / 2) {
            leftChildIndex = 2 * index + 1;
            rightChildIndex = 2 * index + 2;

            if (rightChildIndex < heap.size() &&
                    heap.get(leftChildIndex).compareTo(heap.get(rightChildIndex)) < 0) {
                largerChildIndex = rightChildIndex;
            } else {
                largerChildIndex = leftChildIndex;
            }

            if (heap.get(index).compareTo(heap.get(largerChildIndex)) < 0) {
                swap(index, largerChildIndex);
                index = largerChildIndex;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    @Override
    public Iterator<T> iterator() {
        return heap.iterator();
    }


     public void removeTaskById(String taskId) {
    Iterator<T> iterator = heap.iterator();

    while (iterator.hasNext()) {
        T task = iterator.next();
        if (task instanceof TaskReminder && ((TaskReminder) task).getId().equals(taskId)) {
            iterator.remove();
            return;
        }
    }
} 

public void modifyTaskById(String taskId, String newTitle, String newDescription, Date newDeadline, int newPriority) {
    Iterator<T> iterator = heap.iterator();

    while (iterator.hasNext()) {
        T task = iterator.next();
        if (task instanceof TaskReminder && ((TaskReminder) task).getId().equals(taskId)) {
            TaskReminder modifiedTask = (TaskReminder) task;

            // Modificar atributos
            modifiedTask.setTitle(newTitle);
            modifiedTask.setDescription(newDescription);
            modifiedTask.setDeadline(newDeadline);
            
            Priority p = null;
            switch (newPriority) {
                case 1:
                    p = Priority.HIGH_PRIORITY;
                    break;
                case 2:
                    p = Priority.LOW_PRIORITY;
                    break;
            }
            modifiedTask.setPriority(p);

            // Mantener la propiedad de la cola de prioridad
            heapifyUp(heap.indexOf(task));
            heapifyDown(heap.indexOf(task));

            return;
        }
    }
}


}

 


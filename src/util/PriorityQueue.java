package util;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import model.Priority;
import model.TaskReminder;

public class PriorityQueue<T extends Comparable<T>> implements Iterable<T> {

    private List<T> priorityHeap;  
    private List<T> deadlineHeap;  

    public PriorityQueue() {
        priorityHeap = new ArrayList<>();
        deadlineHeap = new ArrayList<>();
    }



    public int size() {
        return priorityHeap.size();
    }

    public T getRoot() {
        if (isEmpty()) {
            return null;
        }
        return priorityHeap.get(0);
    }


    public void enqueue(T element) {
        priorityHeap.add(element);
        deadlineHeap.add(element);
        heapifyUp(priorityHeap.size() - 1, priorityHeap);
        heapifyUp(deadlineHeap.size() - 1, deadlineHeap);
    }

    public T dequeue() {
        if (isEmpty()) {
            return null;
        }

        T root = priorityHeap.get(0);

        if (priorityHeap.size() == 1) {
            priorityHeap.clear();  
            deadlineHeap.clear();
        } else {
            T lastElement = priorityHeap.remove(priorityHeap.size() - 1);
            priorityHeap.set(0, lastElement);
            heapifyDown(0, priorityHeap);


            int indexToRemove = deadlineHeap.indexOf(root);
            deadlineHeap.set(indexToRemove, deadlineHeap.remove(deadlineHeap.size() - 1));
            heapifyDown(indexToRemove, deadlineHeap);
        }

        return root;
    }


    public boolean isEmpty() {
        return priorityHeap.isEmpty();
    }


    private void heapifyUp(int index, List<T> heap) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (heap.get(index).compareTo(heap.get(parentIndex)) > 0) {
                swap(index, parentIndex, heap);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    private void heapifyDown(int index, List<T> heap) {
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
                swap(index, largerChildIndex, heap);
                index = largerChildIndex;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j, List<T> heap) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }


    public boolean deadlineIsEmpty() {
        return deadlineHeap.isEmpty();
    }
    
    public int deadlineSize() {
        return deadlineHeap.size();
    }
    
    
    private void heapifyDownForDeadline(int index) {
        int leftChildIndex;
        int rightChildIndex;
        int laterChildIndex;
    
        while (index < deadlineHeap.size() / 2) {
            leftChildIndex = 2 * index + 1;
            rightChildIndex = 2 * index + 2;
    
            if (rightChildIndex < deadlineHeap.size() &&
                    deadlineHeap.get(leftChildIndex).compareTo(deadlineHeap.get(rightChildIndex)) < 0) {
                laterChildIndex = rightChildIndex;
            } else {
                laterChildIndex = leftChildIndex;
            }
    
            if (deadlineHeap.get(index).compareTo(deadlineHeap.get(laterChildIndex)) < 0) {
                swapForDeadline(index, laterChildIndex);
                index = laterChildIndex;
            } else {
                break;
            }
        }
    }
    
    private void swapForDeadline(int i, int j) {
        T temp = deadlineHeap.get(i);
        deadlineHeap.set(i, deadlineHeap.get(j));
        deadlineHeap.set(j, temp);
    }
    
    @Override
    public Iterator<T> iterator() {
        return priorityHeap.iterator();
    }

    public Iterator<T> iteratorForDeadline() {
        return deadlineHeap.iterator();
    }


    public void removeTaskById(String taskId) {
        Iterator<T> iterator = priorityHeap.iterator();
    
        while (iterator.hasNext()) {
            T task = iterator.next();
            if (task instanceof TaskReminder && ((TaskReminder) task).getId().equals(taskId)) {
                iterator.remove();
    
                int indexToRemove = deadlineHeap.indexOf(task);
                deadlineHeap.set(indexToRemove, deadlineHeap.remove(deadlineHeap.size() - 1));
                heapifyDownForDeadline(indexToRemove); 
    
                return;
            }
        }
    }
    

    public void modifyTaskById(String taskId, String newTitle, String newDescription, Date newDeadline, int newPriority) {
        Iterator<T> iterator = priorityHeap.iterator();

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

                heapifyUp(priorityHeap.indexOf(task), priorityHeap);
                heapifyDown(priorityHeap.indexOf(task), priorityHeap);


                int indexToUpdate = deadlineHeap.indexOf(task);
                heapifyUp(indexToUpdate, deadlineHeap);
                heapifyDown(indexToUpdate, deadlineHeap);

                return;
            }
        }
    }
}



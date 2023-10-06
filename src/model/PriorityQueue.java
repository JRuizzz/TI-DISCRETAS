package model;
import java.util.ArrayList;
import java.util.List;

public class PriorityQueue<T extends Comparable<T>> {

    private List<T> heap;

    public PriorityQueue() {
        heap = new ArrayList<>();
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
        T lastElement = heap.remove(heap.size() - 1);

        if (!isEmpty()) {
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

    
}

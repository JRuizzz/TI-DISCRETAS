package model;

public class PriorityQueue<T> {

    private DynamicQueue<T> highPriorityQueue;
    private DynamicQueue<T> mediumPriorityQueue;
    private DynamicQueue<T> lowPriorityQueue;

   

    public PriorityQueue(DynamicQueue<T> highPriorityQueue, DynamicQueue<T> mediumPriorityQueue,
         DynamicQueue<T> lowPriorityQueue) {
        this.highPriorityQueue = highPriorityQueue;
        this.mediumPriorityQueue = mediumPriorityQueue;
        this.lowPriorityQueue = lowPriorityQueue;
    }

    public void enqueue(T element, Priority priority) {
        switch (priority) {
            case HIGH_PRIORITY:
                highPriorityQueue.enqueue(element);
                break;
            case MEDIUM_PRIORITY:
                mediumPriorityQueue.enqueue(element);
                break;
            case LOW_PRIORITY:
                lowPriorityQueue.enqueue(element);
                break;
        }
    }

    public T dequeue() {
        if (!highPriorityQueue.isEmpty()) {
            return highPriorityQueue.dequeue();
        } else if (!mediumPriorityQueue.isEmpty()) {
            return mediumPriorityQueue.dequeue();
        } else {
            return lowPriorityQueue.dequeue();
        }
    }

    public boolean isEmpty() {
        return highPriorityQueue.isEmpty() && mediumPriorityQueue.isEmpty() && lowPriorityQueue.isEmpty();
    }

}

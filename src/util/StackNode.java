package util;

public class StackNode  <T> {
    
    private T element;
    private StackNode  <T> next;
    
    public StackNode (T element, StackNode <T> next) {
        this.element = element;
        this.next = next;
    }
    
    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public StackNode <T> getNext() {
        return next;
    }

    public void setNext(StackNode <T> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return element + "\n";
    }

}


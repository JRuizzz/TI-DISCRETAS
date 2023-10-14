package util;

import java.util.List;

public class DynamicQueue <T> {

    private Node <T> first;
    private Node <T> last; 
    private int size;
    private List<T> addedElements;  // Lista de elementos agregados

    public DynamicQueue(Node<T> first, Node<T> last, int size) {
        this.first = null;
        this.last = null;
        this.size = 0;
    } 

    public boolean isEmpty(){
        return first ==null; 
    }

    public int size(){
        return size; 
    }

    public T first(){    
        
        if(isEmpty()){
            return null;
        } 
        return first.getElement();
    }

    public T dequeue() {
        if (isEmpty()) {
            return null;
        }
    
        T element = first.getElement();
        Node<T> aux = first.getNext();
        first = aux; 
        size--;
    
        if (isEmpty()) {
            last = null;
        }
        
        return element;
    }
    
    public T getLastAddedElement() {
        if (!addedElements.isEmpty()) {
            return addedElements.get(addedElements.size() - 1);
        }
        return null;
    }
    
    public void enqueue(T element) {
        Node<T> aux = new Node<>(element, null);

        if (isEmpty()) {
            first = aux;
            last = aux;
        } else {
            last.setNext(aux);
            last = aux;
        }
        size++;

        addedElements.add(element);
    }

    public String toString(){
        String s = "";
        if(isEmpty()){
            return "The list is empty";
         } else {

            Node <T> aux = first;
            while(aux!=null){
                s += aux; 
                aux = aux.getNext();
            }

         }
        
        return s;
    }

 }



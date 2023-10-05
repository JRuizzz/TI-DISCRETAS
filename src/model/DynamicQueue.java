package model; 
import util.Node;

public class DynamicQueue <T> {

    private Node <T> first;
    private Node <T> last; 
    private int size;
    
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

    public T dequeue(){
        if(isEmpty()){
            return null;
        } 

        T element = first.getElement();
        Node <T> aux = first.getNext();
        first = null; 
        first = aux;
        size--;
        if(isEmpty()){
            last = null;  //opcional poner el if.
        }
        return element;

    }


    public T enqueue(T element){
        Node <T> aux = new Node <> (element, null);  //Estrcutura FIFO

        if(isEmpty()){
            first = aux; 
            last = aux;
        } else {
            if (size()==1){
                first.setNext(last);                
            } else {
                last.setNext(aux);
            }
            last = aux; 
        }
        size++;
        return aux.getElement();
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



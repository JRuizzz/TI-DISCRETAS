package model;
import util.StackNode;

public class DynamicStack <T> {
  
    private  StackNode <T> top; 
    private int size;
   
    public DynamicStack(StackNode<T> top, int size) {
        this.top = null;
        this.size = 0;
    }

    public boolean isEmpty(){
        return top==null;
    }


     public int size() {
        return this.size; 
     }

    
    public T top(){ //
        if(isEmpty()){
            return null; 
        } else {
            return top.getElement();
        }
    }


    public void push (T element){
        StackNode <T> aux = new StackNode<>(element, top);
        top = aux;
        size++;
    }
    

    public T pop(){ 
        if(isEmpty()){
            return null; 
        } else {
            T element = top.getElement();
            StackNode <T> aux = top.getNext();
            top = null; 
            top = aux;
            this.size--;
            return element; 
        }
    }


    public String toString(){
         String result = "";
        if (isEmpty()){
            return "The Stack is empty";
        } else {
            StackNode<T> aux = top; 
            while (aux!=null){
                result += aux.toString();
                aux = aux.getNext();


            }
        }
        return result;
    }


}
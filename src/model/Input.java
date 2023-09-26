package model;

public class Input<K,V> {
    K key;
    V value;
    Input<K, V> siguiente;
    public Object clave;
    public V valor;

    Input(K key, V value) {
        this.key = key;
        this.value = value;
    }
} 
    


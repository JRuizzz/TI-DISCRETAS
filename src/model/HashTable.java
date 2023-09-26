package model;

public class HashTable<K, V> {
    private int CAPACIDAD_INICIAL = 16;
    private Input<K, V>[] tabla;
    private int tamaño;

    @SuppressWarnings("unchecked")
    public HashTable() {
        tabla = new Input[CAPACIDAD_INICIAL];
        tamaño = 0;
    }

    public void agregar(K clave, V valor) {
        int indice = obtenerIndice(clave);
        Input<K, V> nuevaEntrada = new Input<>(clave, valor);

        boolean claveExistente = false;

        if (tabla[indice] == null) {
            tabla[indice] = nuevaEntrada;
            tamaño++;
        } else {
            Input<K, V> entradaActual = tabla[indice];
            while (entradaActual != null) {
                if (entradaActual.key.equals(clave)) {
                    entradaActual.value = valor;
                    claveExistente = true;
                    break;
                }
                entradaActual = entradaActual.siguiente;
            }

            if (!claveExistente) {
                nuevaEntrada.siguiente = tabla[indice];
                tabla[indice] = nuevaEntrada;
                tamaño++;
            }
        }

        if ((1.0 * tamaño) / CAPACIDAD_INICIAL > 0.75) {
            redimensionar();
        }
    }

    public V obtener(K clave) {
        int indice = obtenerIndice(clave);
        Input<K, V> entradaActual = tabla[indice];

        while (entradaActual != null) {
            if (entradaActual.key.equals(clave)) {
                return entradaActual.value;
            }
            entradaActual = entradaActual.siguiente;
        }

        return null;
    }

    public void eliminar(K clave) {
        int indice = obtenerIndice(clave);
        Input<K, V> entradaActual = tabla[indice];
        Input<K, V> entradaAnterior = null;

        boolean claveEncontrada = false;

        while (entradaActual != null) {
            if (entradaActual.key.equals(clave)) {
                if (entradaAnterior == null) {
                    tabla[indice] = entradaActual.siguiente;
                } else {
                    entradaAnterior.siguiente = entradaActual.siguiente;
                }
                tamaño--;
                claveEncontrada = true;
                break;
            }
            entradaAnterior = entradaActual;
            entradaActual = entradaActual.siguiente;
        }

        if (!claveEncontrada) {
            return;
        }
    }

    private int obtenerIndice(K clave) {
        int hashCode = clave.hashCode();
        return (hashCode & 0x7FFFFFFF) % CAPACIDAD_INICIAL;
    }

    @SuppressWarnings("unchecked")
    private void redimensionar() {
        CAPACIDAD_INICIAL *= 2;
        Input<K, V>[] nuevaTabla = (Input<K, V>[]) new Input[CAPACIDAD_INICIAL];

        for (int i = 0; i < tabla.length; i++) {
            Input<K, V> entrada = tabla[i];
            while (entrada != null) {
                K clave = entrada.key;
                V valor = entrada.value;
                int nuevoIndice = obtenerIndice(clave);

                Input<K, V> nuevaEntrada = new Input<>(clave, valor);
                nuevaEntrada.siguiente = nuevaTabla[nuevoIndice];
                nuevaTabla[nuevoIndice] = nuevaEntrada;

                entrada = entrada.siguiente;
            }
        }

        tabla = nuevaTabla;
    }
}

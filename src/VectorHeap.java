
import java.util.Vector;

/**
 * @param <E>
 */

public class VectorHeap<E extends Comparable<E>> implements PriorityQueue<E> {

    protected Vector<E> data; // the data, kept in heap order

    public VectorHeap()
    // post: constructs a new priority queue
    {
        data = new Vector<E>();
    }

    public VectorHeap(Vector<E> v)
    // post: constructs a new priority queue from an unordered vector
    {
        int i;
        data = new Vector<E>(v.size()); // we know ultimate size
        for (i = 0; i < v.size(); i++) { // add elements to heap
            add(v.get(i));
        }
    }

    /**
     * @param i La posición del elemento del cual se quiere saber la referencia del parent.
     * @return La posición en el vector del pariente.
     */
    protected static int parent(int i)
    //función que me dice la posición del elemento que se desea saber, en este caso el "papa"
    // pre: 0 <= i < size
    // post: returns parent of node at location i
    {
        return (i - 1) / 2;
    }

    /**
     * @param i La posición del elemento del cual se quiere saber la referencia del hijo izquierdo.
     * @return La posición en el vector del hijo izquierdo.
     */
    protected static int left(int i)
    // funcion que indica la posición del hijo en la posición izquierda
    // pre: 0 <= i < size
    // post: returns index of left child of node at location i
    {
        return 2 * i + 1;
    }

    /**
     * @param i La posición del elemento del cual se quiere saber la referencia del hijo derecho.
     * @return La posición en el vector del hijo derecho.
     */
    protected static int right(int i)
    // funcion que indica la posición del hijo en la posición derecha
    // pre: 0 <= i < size
    // post: returns index of right child of node at location i
    {
        return (2 * i + 1) + 1;
    }

    /**
     * @param leaf El valor que se encuentra en la hoja.
     */
    protected void percolateUp(int leaf)
    // funcion que ayuda a mover el elemento en la hoja a su posición dependiendo de su valor y orden en que se guardan los valores en el heap
    // pre: 0 <= leaf < size
    // post: moves node at index leaf up to appropriate position
    {
        int parent = parent(leaf);
        E value = data.get(leaf);
        while (leaf > 0 &&
                (value.compareTo(data.get(parent)) < 0)) {
            data.set(leaf, data.get(parent));
            leaf = parent;
            parent = parent(leaf);
        }
        data.set(leaf, value);
    }

    /**
     * @param value
     */
    public void add(E value)
    // funcion que agrega elemento a la hoja y utiliza el percolateUp para ayudar a ordenar el VectorHeap dependiendo del valor del mismo
    // pre: value is non-null comparable
    // post: value is added to priority queue
    {
        data.add(value);
        percolateUp(data.size() - 1);
    }

    /**
     * @param root La raíz del VectorHeap.
     */
    protected void pushDownRoot(int root)
    //funcion para mover el elemento en la raiz a su posicion dependiendo del orden de almacenamiento en el VectrorHeap
    // pre: 0 <= root < size
    // post: moves node at index root down
    // to appropriate position in subtree
    {
        int heapSize = data.size();
        E value = data.get(root);
        while (root < heapSize) {
            int childpos = left(root);
            if (childpos < heapSize) {
                if ((right(root) < heapSize) &&
                        ((data.get(childpos + 1)).compareTo
                                (data.get(childpos)) < 0)) {
                    childpos++;
                }
                // Assert: childpos indexes smaller of two children
                if ((data.get(childpos)).compareTo
                        (value) < 0) {
                    data.set(root, data.get(childpos));
                    root = childpos; // keep moving down
                } else { // found right location
                    data.set(root, value);
                    return;
                }
            } else { // at a leaf! insert and halt
                data.set(root, value);
                return;
            }
        }
    }

    /**
     * @return
     */
    @Override
    public E remove()
    // funcion que remueve el valor del vectorHeap y utiliza el protected de pushDownRaat, para ordenar el VectorHeap despues de añadir un elemento
    // pre: !isEmpty()
    // post: returns and removes minimum value from queue
    {
        E minVal = getFirst();
        data.set(0, data.get(data.size() - 1));
        data.setSize(data.size() - 1);
        if (data.size() > 1) pushDownRoot(0);
        return minVal;
    }

    /**
     * @return Devuelve el primer elemento.
     */
    @Override
    public E getFirst() {
        return data.firstElement();
    } //obtiene el primer elemento del VectorHeap

    /**
     * @return True en caso el VectorHeap esté vació y False en caso el VectorHeap tenga más de algún elemento.
     */
    @Override
    public boolean isEmpty() {
        // revisa el VectorHeap y devuelve un boolean dependiendo si esta vacio ono
        return data.isEmpty();
    }

    /**
     * @return Devuelve la cantidad de elementos en el VectorHeap.
     */
    @Override
    public int size() {
        return data.size();
    } // funcion que nos dice el tamaño del VectorHeap

    @Override
    public void clear() {
        data.clear();
    } //funcion que vacia el VectorHeap
}

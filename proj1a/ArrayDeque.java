import com.sun.security.jgss.AuthorizationDataEntry;

public class ArrayDeque<T> {
    private T[] itemArray;
    private int first = 0;
    private int last = 1;
    private int capacity=10;
    private int size = 0;

    public ArrayDeque(){
        itemArray = (T[]) new Object[capacity];
    }

    public void addFirst(T item) {
        if (isFull()) {
            resize(size * 2);
        }
        first = first==0 ? capacity-1:first-1;
        itemArray[first] = item;
        size++;
    }

    public void addLast(T item) {
        if (isFull()) {
            resize(size * 2);
        }
        last = last==(capacity-1) ? 0:last+1;
        itemArray[last] = item;
        size++;
    }

    public T removeFirst() {
        if (isHalfEmpty()) {
            resize(size/2);
        }
        T removedItem = itemArray[first];
        itemArray[first] = null;
        first = first+1 >= capacity ? 0:first+1;
        size--;
        return removedItem;
    }

    public T removeLast() {
        if (isHalfEmpty()) {
            resize(size/2);
        }
        T removedItem = itemArray[last];
        last = last==0 ? capacity-1:last-1;
        size--;
        return removedItem;
    }
    public void printDeque() {
        for (int i=first;i<capacity;i++) {
            System.out.println(itemArray[i]);
        }
        for (int j=0;j<=last;j++){
            System.out.println(itemArray[j]);
        }
    }

    public T get(int index){
        return itemArray[index+first-capacity];
    }

    public boolean isEmpty() {
        return size==0;
    }
    public int size() {
        return size;
    }

    private boolean isFull() {
        return size==capacity;
    }
    private void resize(int newCapacity) {
        T[] newArray = (T[]) new Object[newCapacity];
        for (int i=0;i<size;i++) {
            newArray[i] = itemArray[i];
        }
        this.capacity = newCapacity;
    }

    private boolean isHalfEmpty(){
        return capacity/2 >= size;
    }

}

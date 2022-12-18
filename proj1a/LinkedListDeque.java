public class LinkedListDeque<T> {
    private class Item{
        public T item;
        public Item before;
        public Item after;
        public Item(T item){
            this.item = item;
            this.before=null;
            this.after=null;
        }
    }
    private Item first;
    private Item last;

    private int size;

    public LinkedListDeque(){
        this.size=0;
        this.first=null;
        this.last = null;
    }

    public void addFirst(T item) {
        Item newItem = new Item(item);
        newItem.after = first;
        first = newItem;
        if (size==0) last = first;
        size++;
    }

    public void addLast(T item) {
        Item newItem = new Item(item);
        newItem.before=last;
        last.after = newItem;
        last = newItem;
        size++;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        Item pointer = first;
        for(int i =0;i<size;i++){
            System.out.print(pointer + " ");
            pointer = pointer.after;
        }
        System.out.println(" ");
    }

    public T removeFirst(){
        if (size==0) return null;
        T itemRemoved = first.item;
        if (size==1) {
            first = null;
            last = null;
            size--;
            return itemRemoved;
        }
        first = first.after;
        size--;
        return itemRemoved;
    }

    public T removeLast(){
        if (size==0) return null;
        T itemRemoved = last.item;
        if (size==1) {
            first = null;
            last = null;
            size--;
            return itemRemoved;
        }
        last = last.before;
        size--;
        return itemRemoved;
    }

    public T get(int index){
        if(index >= size) return null;
        Item pointer;
        if (index < size/2) {
            pointer = first;
            for(int i=0;i<index;i++){
                pointer = pointer.after;
            }
        } else {
            pointer = last;
            for(int i=size-1;i>index;i--){
                pointer = pointer.before;
            }
        }
        return pointer.item;
    }

    public T getRecursive(int index){
        if (index >= size){
            return null;
        }
        Item sentinel = this.first;
        return getRecursiveHelper(index, sentinel);
    }

    private T getRecursiveHelper(int index, Item sentinel){
        if (index==0) {
            return sentinel.item;
        }
        return getRecursiveHelper(index -1,sentinel.after);
    }
}

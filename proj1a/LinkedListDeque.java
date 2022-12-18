public class LinkedListDeque<T> {
    private class Node{
        public T item;
        public Node prev;
        public Node next;
        public Node(T item, Node prev, Node next){
            this.item = item;
            this.prev=prev;
            this.next=next;
        }
        public Node(Node prev, Node next) {
            this.prev = prev;
            this.next = next;
        }

    }
    private Node sentinel;

    private int size;

    public LinkedListDeque(){
        size=0;
        sentinel = new Node(null,null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    public void addFirst(T item) {
        Node newItem = new Node(item,sentinel,sentinel.next);
        newItem.next.prev = newItem;
        sentinel.next = newItem;
        size++;
    }

    public void addLast(T item) {
        Node newItem = new Node(item,sentinel.next,sentinel);
        newItem.prev.next = newItem;
        sentinel.prev = newItem;
        size++;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        if (size==0) {
            return;
        }
        Node pointer = sentinel.next;
        while(pointer != sentinel) {
            System.out.print(pointer.item + " ");
            pointer = pointer.next;
        }
    }

    public T removeFirst(){
        if (size==0) return null;
        Node nodeRemoved = sentinel.next;
        T itemRemoved = nodeRemoved.item;
        sentinel.next = nodeRemoved.next;
        nodeRemoved.next.prev = sentinel;
        size--;
        return itemRemoved;
    }

    public T removeLast(){
        if (size==0) return null;
        Node nodeRemoved = sentinel.prev;
        T itemRemoved = nodeRemoved.item;
        sentinel.prev = nodeRemoved.prev;
        nodeRemoved.prev.next = sentinel;
        size--;
        return itemRemoved;
    }

    public T get(int index){
        if(index >= size) return null;
        Node pointer = sentinel;
        for (int i=0;i<index;i++){
            pointer = pointer.next;
        }
        return pointer.next.item;
    }

    public T getRecursive(int index){
        if (index >= size){
            return null;
        }
        Node pointer = sentinel.next;
        return getRecursiveHelper(index, pointer);
    }

    private T getRecursiveHelper(int index, Node pointer){
        if (index==0) {
            return pointer.item;
        }
        return getRecursiveHelper(index -1,pointer.next);
    }
}

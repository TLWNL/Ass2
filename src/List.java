package src;

public class List<E extends Comparable<E>> implements ListInterface<E>{
    private Node sentinel;
    private Node current;
    private int size;

    private class Node {

        E data;
        Node prior,
                next;

        public Node(E d) {
            this(d, null, null);
        }

        public Node(E data, Node prior, Node next) {
            this.data = data == null ? null : data;
            this.prior = prior;
            this.next = next;
        }

    }


    public List() {
        sentinel = new Node(null);
        sentinel.next = sentinel;
        sentinel.prior = sentinel;
        current = sentinel;
        this.size = 0;
    }

    public ListInterface<E> init () {
        sentinel.next = sentinel;
        sentinel.prior = sentinel;
        current = sentinel;
        size = 0;
        return this;
    }

    public boolean isEmpty () {
        return sentinel.next == sentinel;
    }

    public ListInterface<E> copy () {
        Node tmp = sentinel.next;
        List<E> copiedList = new List<>();
        while(tmp.next != null)
            copiedList.insert(tmp.data);
        return copiedList;
    }


    public int size () {
        return size;
    }


    public ListInterface<E> insert (E d) {
        Node toAdd = new Node(d, null, null);
        if (isEmpty()) {                   // The list is empty
            sentinel.next = toAdd;
            sentinel.prior = toAdd;
            toAdd.next = sentinel;
            toAdd.prior = sentinel;
            current = toAdd;
            size++;
        }
        else {
            this.goToFirst();           // Current is at List index 0
            for (int i = 0; i < this.size; i++) {

                if(size == 1){
                    // ToAdd is larger than the first (and only) element, so add to back of Element 1
                    if(toAdd.data.compareTo(this.retrieve()) > -1){
                        current.next = toAdd;
                        toAdd.prior = current;
                        toAdd.next = sentinel;
                        sentinel.prior = toAdd;
                        current = toAdd;
                        size++;
                    }
                    // ToAdd is smaller than the first element, so add to front
                    else{
                        sentinel.next = toAdd;
                        toAdd.prior = sentinel;
                        toAdd.next = sentinel.prior;
                        current.prior = toAdd;
                        current = toAdd;
                        size++;
                    }
                    break;
                }
                else {
                    // ToAdd is smaller than the first element, so add to front
                    if(current.prior == sentinel && toAdd.data.compareTo(this.retrieve()) < 0){
                        sentinel.next = toAdd;
                        toAdd.prior = sentinel;
                        toAdd.next = current;
                        current.prior = toAdd;
                        current = toAdd;
                        size++;
                        break;
                    }

                    // ToAdd is smaller than the last element
                    if (current.next == sentinel && toAdd.data.compareTo(this.retrieve()) < 0) {
                        toAdd.prior = current.prior;
                        current.prior.next = toAdd;
                        toAdd.next = current;
                        current.prior = toAdd;
                        current = toAdd;
                        size++;
                        break;
                    }

                    // ToAdd is larger than the last element
                    else if (current.next == sentinel && toAdd.data.compareTo(this.retrieve()) > -1) {
                        current.next = toAdd;
                        toAdd.prior = current;
                        toAdd.next = sentinel;
                        sentinel.prior = toAdd;
                        current = toAdd;
                        size++;
                        break;
                    }

                    // ToAdd is smaller than the element at index i
                    if (current.prior != sentinel && current.next != sentinel &&
                            toAdd.data.compareTo(this.retrieve()) < 0) {
                        current.prior.next = toAdd;
                        toAdd.next = current;
                        toAdd.prior = current.prior;
                        current.prior = toAdd;
                        current = toAdd;
                        size++;
                        break;
                    }
                }
                if(current.next != null){
                    current = current.next;
                }

            }

        }

        return this;
    }


    public E retrieve () {
        return current.data;
    }


    public ListInterface<E> remove () {
        // Removal of the first element
        if(sentinel.next == current){
            sentinel.next = current.next;
            current.next.prior = sentinel;
            current.prior = null;           // Removes the pointer to the sentinel
            current = current.next;
            size--;
        }
        // Removal of the last element
        else if(sentinel.prior == current){
            sentinel.prior = current.prior;
            current.prior.next = sentinel;
            current.next = null;
            current = sentinel.prior;
            size--;
        }
        else {
            current.prior.next = current.next;
            current.next.prior = current.prior;
            current = current.next;
            size--;
        }
        return this;
    }


    public boolean find (E d) {
        do {
            goToFirst();
            if (current.data == d)
                return true;
            else
                current = current.next;
        }while(current.next != sentinel);

        return false;
    }


    public boolean goToFirst () {
        if(sentinel.next == sentinel)
            return false;
        else
            current = sentinel.next;
        return true;
    }


    public boolean goToLast () {
        if(sentinel.prior == sentinel)
            return false;
        else
            current = sentinel.prior;
        return true;
    }


    public boolean goToNext () {
        if(current.next == sentinel)
            return false;
        else
            current = current.next;
        return true;
    }


    public boolean goToPrevious () {
        if(current.prior == sentinel)
            return false;
        else
            current = current.prior;
        return true;
    }


}
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
            for (int i = 0; i < this.size(); i++) {

                // ToAdd is smaller than the first (and only) element, so add to front
                if (current.prior == sentinel && current.next == sentinel &&
                        toAdd.data.compareTo(this.retrieve()) == -1) {
                    sentinel.next = toAdd;
                    toAdd.prior = sentinel;
                    toAdd.next = sentinel.prior;
                    current.prior = toAdd;
                    current = toAdd;
                    size++;
                }
                // ToAdd is larger than the first (and only) element, so add to back
                else if (current.prior == sentinel && current.next == sentinel &&
                        toAdd.data.compareTo(this.retrieve()) != -1) {
                    current.next = toAdd;
                    toAdd.prior = current;
                    toAdd.next = sentinel;
                    sentinel.prior = toAdd;
                    size++;

                }

                // ToAdd is smaller than the last element
                if (current.next == sentinel && toAdd.data.compareTo(this.retrieve()) == -1) {
                    toAdd.prior = current.prior;
                    toAdd.prior.next = toAdd;
                    toAdd.next = current;
                    current.prior = toAdd;
                    current = toAdd;
                    size++;
                }

                // ToAdd is larger than the last element
                else if (current.next == sentinel && toAdd.data.compareTo(this.retrieve()) != -1) {
                    current.next = toAdd;
                    toAdd.prior = current;
                    toAdd.next = sentinel;
                    sentinel.prior = toAdd;
                    current = toAdd;
                    size++;
                }

                // Not the first nor the last element, so add in between two elements
                if (current.prior != sentinel && current.next != sentinel &&
                        toAdd.data.compareTo(this.retrieve()) == -1) {
                    toAdd.prior = current.prior;
                    toAdd.prior.next = toAdd;
                    toAdd.next = current;
                    current.prior = toAdd;
                    current = toAdd;
                    size++;
                }
                current = current.next;
            }
        }

        return this;
    }


    public E retrieve () {
        return current.data;
    }


    public ListInterface<E> remove () {
        current.prior.next = current.next;
        current.next.prior = current.prior;
        current = current.next;
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
package src;

public class List<E extends Comparable<E>> implements ListInterface<E>{
    private Node head;
    private Node tail;
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


    public List () {
        List<E> newList = new List<E>();
    }

    public List<E> init () {
        this.head = null;
        this.tail = null;
        this.size = 0;
        return this;
    }

    public boolean isEmpty () {
        return head == null;
    }

    public List<E> copy () {
        Node tmp = head.next;
        List copiedList = new List();
        while(tmp.next != null)
            copiedList.insert(tmp.data);
        return copiedList;
    }


    public int size () {
        return size;
    }


    public List<E> insert (E d) {
        Node toAdd = new Node(d, null, tail);
        if(head.next == null) {             //Node is first element
            head.next = toAdd;
            tail.prior = toAdd;
        }
        else {
            while(toAdd.data.compareTo(toAdd.prior.data) != 0 &&
                    toAdd.prior.data.compareTo(toAdd.prior.prior.data) != 1){
                toAdd = toAdd.prior;
            }
            (tail.prior).next = toAdd;
            tail.prior = toAdd;
        }
        current = toAdd;
        size++;
        return this;
    }


    public E retrieve () {
        return current.data;
    }


    public List<E> remove () {
        current.prior.next = current.next;
        current.next.prior = current.prior;
        current = current.next;
        return this;
    }


    public boolean find (E d) {
        do {
            current = head.next;
            if (current.data == d)
                return true;
            else
                current = current.next;
        }while(current.next != null);

        return false;
    }


    public boolean goToFirst () {
        if(head.next == null)
            return false;
        else
            current = head.next;
        return true;
    }


    public boolean goToLast () {
        if(tail.prior == null)
            return false;
        else
            current = tail.prior;
        return true;
    }


    public boolean goToNext () {
        if(current.next == null)
            return false;
        else
            current = current.next;
        return true;
    }


    public boolean goToPrevious () {
        if(current.prior == null)
            return false;
        else
            current = current.prior;
        return true;
    }


}


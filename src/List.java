package src;

public class List<E extends Comparable> implements ListInterface<E>{
    private Node head;
    private Node tail;
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

    class List<E extends Comparable<E>> implements ListInterface<E> {

        private class Node {
            E data;
            Node prior,
                    next;

            public Node (E data, Node prior, Node next) {
                this.data = data;
                this.prior = prior;
                this.next = next;
            }

            public Node(E data) {
                this(data, null, null);
            }
        }

        public List () {
        }


        public boolean isEmpty () {
        }


        public List<E> init () {
        }


        public List<E> copy () {
        }


        public int size () {
        }


        public List<E> insert (E d) {
        }


        public E retrieve () {
        }


        public List<E> remove () {
        }


        public boolean find (E d) {
        }


        public boolean goToFirst () {
        }


        public boolean goToLast () {
        }


        public boolean goToNext () {
        }


        public boolean goToPrevious () {
        }

    }


}

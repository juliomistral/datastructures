package structures.basic;


public class DoubleLinkedNode<E> {
    private E data;
    private DoubleLinkedNode previous;
    private DoubleLinkedNode next;

    public DoubleLinkedNode(E data) {
        this.data = data;
    }

    public DoubleLinkedNode addNext(E data) {
        if (this.next != null) {
            return this.next.addNext(data);
        }

        DoubleLinkedNode n = new DoubleLinkedNode(data);
        this.next = n;
        n.previous = this;

        return n;
    }

    public DoubleLinkedNode addPrevious(E data) {
        if (this.previous != null) {
            return this.previous.addPrevious(data);
        }

        DoubleLinkedNode n = new DoubleLinkedNode(data);
        this.previous = n;
        n.next = this;

        return n;
    }

    public DoubleLinkedNode next() {
        return this.next;
    }

    public DoubleLinkedNode previous() {
        return this.previous;
    }

    public E data() {
        return this.data;
    }
}

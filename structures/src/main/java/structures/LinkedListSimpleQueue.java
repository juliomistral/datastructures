package structures;


import java.util.NoSuchElementException;

public class LinkedListSimpleQueue<E> implements SimpleQueue<E> {
    private Node head, tail;


    @Override
    public void enqueue(E element) {
        Node newNode = new Node(element);
        if (head == null) {
            head = newNode;
        }

        tail = newNode;

    }

    @Override
    public E dequeue() {
        if (empty()) {
            throw new NoSuchElementException();
        }
        return null;
    }

    @Override
    public E peek() {
        return null;
    }

    @Override
    public boolean empty() {
        return head == null;
    }
}

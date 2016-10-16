package structures;


import structures.basic.DoubleLinkedNode;

import java.util.NoSuchElementException;

public class LinkedListSimpleQueue<E> implements SimpleQueue<E> {
    private DoubleLinkedNode<E> head, tail;


    @Override
    public void enqueue(E element) {
        DoubleLinkedNode newNode;

        if (empty()) {
            head = tail = new DoubleLinkedNode(element);
            return;
        }

        tail = tail.addNext(element);
    }

    @Override
    public E dequeue() {
        if (empty()) {
            throw new NoSuchElementException();
        }

        E dequeued = head.data();
        head = head.next();

        if(empty()) { // If we're empty, clear tail
            tail = null;
        }

        return dequeued;
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

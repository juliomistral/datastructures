package structures;


import structures.basic.DoubleLinkedNode;

import java.util.NoSuchElementException;

public class LinkedListSimpleStack<E> implements SimpleStack<E> {
    private DoubleLinkedNode<E> top;


    @Override
    public E pop() {
        if (empty()) {
            throw new NoSuchElementException();
        }

        E popped = top.data();
        top = top.next();

        return popped;
    }

    @Override
    public E peek() {
        if (empty()) {
            throw new NoSuchElementException();
        }

        return top.data();
    }

    @Override
    public void push(E element) {
        DoubleLinkedNode newNode;

        if (top == null) {
            newNode = new DoubleLinkedNode(element);
        } else {
            newNode = top.addPrevious(element);
        }
        top = newNode;
    }

    @Override
    public boolean empty() {
        return top == null;
    }
}

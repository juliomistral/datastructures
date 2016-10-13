package structures;


import java.util.NoSuchElementException;

public class LinkedListSimpleStack<E> implements SimpleStack<E> {
    private Node<E> top;


    @Override
    public E pop() {
        if (empty()) {
            throw new NoSuchElementException();
        }

        E popped = top.data;
        top = top.next;

        return popped;
    }

    @Override
    public E peek() {
        return top.data;
    }

    @Override
    public void push(E element) {
        Node newNode = new Node(element);
        newNode.next = top;
        top = newNode;
    }

    @Override
    public boolean empty() {
        return top == null;
    }
}

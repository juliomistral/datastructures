package structures;


public interface SimpleStack<E> {
    E pop();
    E peek();

    void push(E element);

    boolean empty();
}

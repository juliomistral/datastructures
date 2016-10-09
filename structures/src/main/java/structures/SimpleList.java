package structures;

public interface SimpleList<E> {
    int size();
    E get(int index);

    void add(E element);

    void set(int index, E element);

    E remove(E element);

    E remove(int index);
}

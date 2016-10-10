package structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizableArraySimpleList<E> implements SimpleList<E>, Iterable<E> {
    private static final int DEFAULT_INITIAL_SIZE = 2;
    private static final int DEFAULT_RESIZE_FACTOR = 2;  // Double array when exceeded

    private int lastIndex;
    private int resizeFactor;
    private E[] elements;


    public ResizableArraySimpleList() {
        this(DEFAULT_INITIAL_SIZE, DEFAULT_RESIZE_FACTOR);
    }

    public ResizableArraySimpleList(int initialSize, int resizeFactor) {
        this.elements = (E[]) new Object[initialSize];
        this.resizeFactor = resizeFactor;
        this.lastIndex = -1;
    }

    @Override
    public int size() {
        return this.lastIndex + 1;
    }

    @Override
    public void add(E element) {
        resizeUpIfNeeded();
        this.elements[++lastIndex] = element;
    }

    private synchronized void resizeUpIfNeeded() {
        if (elements.length != this.size()) return;

        int newSize = this.elements.length * this.resizeFactor;
        E[] cache = (E[]) new Object[newSize];

        swapOutElementsArray(cache);
    }

    @Override
    public E get(int index) {
        return (E) this.elements[index];
    }

    @Override
    public void set(int index, E element) {
        if (index > this.lastIndex) {
            throw new ArrayIndexOutOfBoundsException(index);
        }

        this.elements[index] = element;
    }

    @Override
    public E remove(E element) {
        E removed = null;

        for (int i = 0; i < this.size(); i++) {
            if (this.elements[i].equals(element)) {
                removed = remove(i);
                break;
            }
        }

        return removed;
    }

    @Override
    public E remove(int index) {
        if (index > this.lastIndex) {
            throw new ArrayIndexOutOfBoundsException(index);
        }

        E removed = this.elements[index];
        shiftElementsLeft(index);

        this.lastIndex--;
        resizeDownIfNeeded();

        return removed;
    }

    private void shiftElementsLeft(int startFrom) {
        for (int i = startFrom; i < this.size() - 1; i++) {
            this.elements[i] = this.elements[i + 1];
        }
    }

    private synchronized void resizeDownIfNeeded() {
        if (elements.length / 2 != this.size()) return;

        int newSize = this.elements.length / this.resizeFactor;
        E[] cache = (E[]) new Object[newSize];

        swapOutElementsArray(cache);
    }

    private void swapOutElementsArray(E[] newArray) {
        System.arraycopy(this.elements, 0, newArray, 0, this.size());
        this.elements = newArray;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int current = 0;

            @Override
            public boolean hasNext() {
                return this.current < ResizableArraySimpleList.this.size();
            }

            @Override
            public E next() {
                if (current >= ResizableArraySimpleList.this.size()) {
                    throw new NoSuchElementException();
                }
                return ResizableArraySimpleList.this.elements[current++];
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("[ ");
        for (int i = 0; i < this.size(); i++) {
            if (i != 0) output.append(", ");
            output.append(this.elements[i]);
        }
        output.append(" ]");
        return output.toString();
    }
}

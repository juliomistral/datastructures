package structures;

public class ResizableArray<E> {
    private static final int DEFAULT_INITIAL_SIZE = 2;
    private static final int DEFAULT_RESIZE_FACTOR = 2;  // Double array when exceeded

    private int lastIndex;
    private int resizeFactor;
    private E[] elements;


    public ResizableArray() {
        this(DEFAULT_INITIAL_SIZE, DEFAULT_RESIZE_FACTOR);
    }

    public ResizableArray(int initialSize, int resizeFactor) {
        this.elements = (E[]) new Object[initialSize];
        this.resizeFactor = resizeFactor;
        this.lastIndex = -1;
    }

    public int size() {
        return this.lastIndex + 1;
    }

    public void add(E element) {
        resizeUpIfNeeded();
        this.elements[++lastIndex] = element;
    }

    public E get(int index) {
        return (E) this.elements[index];
    }

    public void set(int index, E element) {
        if (index > this.lastIndex) {
            throw new ArrayIndexOutOfBoundsException(index);
        }

        this.elements[index] = element;
    }

    public E remove(E element) {
        E removed = null;

        for (int i = 0; i < this.size(); i++) {
            if (removed != null) {
                this.elements[i - 1] = this.elements[i];
            } else if (this.elements[i].equals(element)) {
                removed = this.elements[i];
            }
        }

        if (removed != null) {
            this.lastIndex--;
            resizeDownIfNeeded();
        }

        return removed;
    }

    private synchronized void resizeUpIfNeeded() {
        if (elements.length != this.size()) return;

        int newSize = this.elements.length * this.resizeFactor;
        E[] cache = (E[]) new Object[newSize];

        swapArrays(cache);
    }

    private synchronized void resizeDownIfNeeded() {
        if (elements.length / 2 != this.size()) return;

        int newSize = this.elements.length / this.resizeFactor;
        E[] cache = (E[]) new Object[newSize];

        swapArrays(cache);
    }

    private void swapArrays(E[] newArray) {
        for (int i = 0; i < this.size(); i++) {
            newArray[i] = this.elements[i];
        }

        this.elements = newArray;
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

package structures;

public class OpenBucketSimpleMap<K, V> implements SimpleMap<K, V> {
    private static final int DEFAULT_CAPACITY = 20;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private static final int DEFAULT_RESIZE_FACTOR = 2;

    // Create an array size equal to max int minus a buffer
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 1024;

    private SimpleList<Entry>[] table;
    private int valuesSize;
    private float loadFactor;
    private int threshold;


    public OpenBucketSimpleMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public OpenBucketSimpleMap(int initialCapacity, float loadFactor) {
        this.loadFactor = loadFactor;
        this.valuesSize = 0;

        this.table = new ResizableArraySimpleList[initialCapacity];
        calculateThreshold();
    }

    private void calculateThreshold() {
        float loadProduct = this.table.length * loadFactor;
        this.threshold = (int) Math.min(loadProduct, MAX_ARRAY_SIZE);
    }

    @Override
    public V put(K key, V value) {
        int index = hashKey(key);
        SimpleList<Entry> entries = this.table[index];

        if (entries == null) {
            entries = new ResizableArraySimpleList<>();
            this.table[index] = entries;
        }

        Entry existingEntry = findEntry(key, entries);
        if (existingEntry != null) {
            V previousValue = existingEntry.value;
            existingEntry.value = value;
            return previousValue;
        }

        Entry newEntry = new Entry(key, value);
        entries.add(newEntry);

        this.valuesSize++;
        rehashIfRequired();

        return null;
    }

    @Override
    public V get(K key) {
        int index = hashKey(key);
        SimpleList<Entry> entries = this.table[index];
        if (entries == null) return null;

        Entry existingEntry = findEntry(key, entries);
        if (existingEntry != null) {
            return existingEntry.value;
        }

        return null;
    }

    private Entry findEntry(K key, SimpleList<Entry> entries) {
        for (Entry current : entries) {
            if (current.hasKey(key)) return current;
        }
        return null;
    }

    private void rehashIfRequired() {
        if (this.valuesSize < threshold) {
            return;
        }

        // Calculating new table size without overflowing int into a negative number
        double resizeProduct = (double)this.table.length * DEFAULT_RESIZE_FACTOR;
        int newTableSize = (int) Math.min(resizeProduct, MAX_ARRAY_SIZE);
        int oldTableSize = this.table.length;

        // Can't resize array any longer, at max array size.  no op
        if (newTableSize == oldTableSize) {
            return;
        }

        SimpleList<Entry>[] newTable = new ResizableArraySimpleList[newTableSize];
        System.arraycopy(this.table, 0, newTable, 0, oldTableSize);
        this.table = newTable;

        calculateThreshold();

    }

    @Override
    public V remove(K key) {
        int index = hashKey(key);
        SimpleList<Entry> entries = this.table[index];
        if (entries == null) return null;

        Entry removed = findEntry(key, entries);
        if (removed != null) {
            entries.remove(removed);
            this.valuesSize--;

            rehashIfRequired();

            return removed.value;
        }

        return null;
    }

    @Override
    public boolean contains(K key) {
        return false;
    }

    private int hashKey(K key) {
        // NULL keys always resolve to the first bucket "0"
        if (key == null) {
            return 0;
        }

        int hash = key.hashCode();
        int hashedIndex = Math.abs(hash % table.length);

        return hashedIndex;
    }

    @Override
    public int size() {
        return this.valuesSize;
    }

    private class Entry {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public boolean hasKey(K other) {
            if (key == null && other == null) return true;
            return this.key.equals(other);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Entry entry = (Entry) o;

            return key.equals(entry.key);
        }

        @Override
        public int hashCode() {
            return key.hashCode();
        }
    }
}

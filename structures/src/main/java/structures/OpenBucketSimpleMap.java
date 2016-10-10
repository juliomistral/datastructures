package structures;

public class OpenBucketSimpleMap<K, V> implements SimpleMap<K, V> {
    private static final int DEFAULT_CAPACITY = 20;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private ResizableArraySimpleList<Entry>[] buckets;
    private float loadFactor;


    public OpenBucketSimpleMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public OpenBucketSimpleMap(int initialCapacity, float loadFactor) {
        this.loadFactor = loadFactor;
        this.buckets = new ResizableArraySimpleList[initialCapacity];
    }

    @Override
    public V put(K key, V value) {
        int index = hashKey(key);
        ResizableArraySimpleList<Entry> entries = this.buckets[index];

        if (entries == null) {
            entries = new ResizableArraySimpleList<>();
            this.buckets[index] = entries;
        }

        Entry existingEntry = findEntry(key, entries);
        if (existingEntry != null) {
            existingEntry.value = value;
            return existingEntry.value;
        }

        Entry newEntry = new Entry(key, value);
        entries.add(newEntry);

        rehashIfRequired();
        return null;
    }

    @Override
    public V get(K key) {
        int index = hashKey(key);
        ResizableArraySimpleList<Entry> entries = this.buckets[index];
        if (entries == null) return null;

        Entry existingEntry = findEntry(key, entries);
        if (existingEntry != null) {
            return existingEntry.value;
        }

        return null;
    }

    private Entry findEntry(K key, ResizableArraySimpleList<Entry> entries) {
        for (Entry current : entries) {
            if (current.hasKey(key)) return current;
        }
        return null;
    }

    private void rehashIfRequired() {

    }

    @Override
    public V remove(K key) {
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
        return hash % buckets.length;
    }

    @Override
    public int size() {
        return 0;
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
    }
}

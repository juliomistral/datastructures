package structures;

public interface SimpleMap<K, V> {
    /**
     * Put the new value in the map using the provided key.
     *
     * If a value is already mapped to the key, then it's replaced and the previous value is returned.
     *
     * @param key
     * @param value
     * @return
     *      Previous value mapped to the provided key, or <code>null</code> if no previous value existed
     */
    V put(K key, V value);


    V get(K key);

    V remove(K key);

    boolean contains(K key);

    int size();
}

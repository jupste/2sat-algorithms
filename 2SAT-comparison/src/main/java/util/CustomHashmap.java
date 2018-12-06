/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 * A custom hashmap that can take different key and value types. Uses an
 * internal static class for entries.
 *
 * @author jussiste
 * @param <K> type of key
 * @param <V> type of value
 */
public class CustomHashmap<K, V> {

    static class HashMapEntry<K, V> {

        private K key;
        private V value;
        private HashMapEntry<K, V> next;

        public HashMapEntry(K key, V value, HashMapEntry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

    }
    private HashMapEntry values[];
    private int capacity = Integer.MAX_VALUE / 10;
    private int size = 0;

    /**
     * The constructor creates a new hashmap and initializes it with an array
     * that can hold about 400 million entries.
     */
    public CustomHashmap() {
        this.values = new HashMapEntry[capacity];
    }

    /**
     * Method to insert an entry to the hashmap. If there is already an entry at
     * the place the hash points to, this method checks if it has the same key.
     * If the key is the same this method will override the value, if it is not
     * the same the method will find a new place for the entry.
     *
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        HashMapEntry<K, V> entry = new HashMapEntry<>(key, value, null);
        int hash = hash(key);
        HashMapEntry<K, V> previous = values[hash];
        if (previous == null) {
            values[hash] = entry;
            size++;
            return;
        }
        while (previous.next != null) {
            if (previous.key.equals(key)) {
                previous.value = value;
                return;
            }
            previous = previous.next;
        }
        if (previous.key.equals(key)) {
            previous.value = value;
        } else {
            previous.next = entry;
            size++;
        }
    }

    /**
     * A method to fetch an value out of the hashmap using a key.
     *
     * @param key key that searches the value
     * @return value
     */
    public V get(K key) {
        HashMapEntry entry = values[hash(key)];
        if (entry == null) {
            return null;
        }

        while (entry != null) {
            if (entry.getKey().equals(key)) {
                return (V) entry.getValue();
            }
            entry = entry.next;
        }
        return null;
    }

    public boolean containsKey(K key) {
        HashMapEntry entry = values[hash(key)];
        if (entry == null) {
            return false;
        }
        while (entry != null) {
            if (entry.getKey().equals(key)) {
                return true;
            }
            entry = entry.next;
        }
        return false;
    }

    /**
     * Method that removes a given key from the hashmap along with it's value.
     *
     * @param key
     * @return true if the removal was succesfull, false otherwise.
     */
    public boolean remove(K key) {
        HashMapEntry entry = values[hash(key)];
        if (entry == null) {
            return false;
        }
        HashMapEntry previous = null;
        while (entry != null) {
            if (entry.getKey().equals(key)) {
                if (previous == null) {
                    values[hash(key)] = values[hash(key)].next;
                    size--;
                    return true;
                } else {
                    previous.next = entry.next;
                    size--;
                    return true;
                }
            }
            previous = entry;
            entry = entry.next;
        }
        return false;
    }

    private int hash(K key) {
        return key.hashCode() % capacity;
    }

    /**
     *
     * @return size of the hashmap.
     */
    public int size() {
        return this.size;
    }
}

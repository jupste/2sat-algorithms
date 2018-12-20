/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

/**
 *
 * @author jussiste
 */
public class CustomHashSet<K> {

    static class HashSetEntry<K> {
        private K key;
        private HashSetEntry<K> next;

        public HashSetEntry(K key, HashSetEntry<K> next) {
            this.key = key;
            this.next = next;
        }

        public K getKey() {
            return key;
        }
    }
    private HashSetEntry values[];
    private int capacity = Integer.MAX_VALUE / 10;
    private int size = 0;

    /**
     * The constructor creates a new hashset and initializes it with an array
     * that can hold about 400 million entries.
     */
    public CustomHashSet() {
        this.values = new HashSetEntry[capacity];
    }

    /**
     * Method to insert an entry to the hashset. If there is already an entry at
     * the place the hash points to, this method checks if it has the same key.
     * 
     * @param key key to be added
     */
    public void add(K key) {
        HashSetEntry<K> entry = new HashSetEntry<>(key, null);
        int hash = hash(key);
        HashSetEntry<K> previous = values[hash];
        if (previous == null) {
            values[hash] = entry;
            size++;
            return;
        }
        while (previous.next != null) {
            if (previous.key.equals(key)) {
                return;
            }
            previous = previous.next;
        }
        if (!previous.key.equals(key)) {
            previous.next = entry;
            size++;
        }
    }

    /**
     * A method that finds if a given key exists in the set.
     *
     * @param key key to be searched
     * @return true if key exists in set, false otherwise
     */

    public boolean containsKey(K key) {
        HashSetEntry entry = values[hash(key)];
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
     * A method to check if the set is empty
     *
     * @return true if no elements exists in the set, false otherwise
     */
    public boolean isEmpty(){
        return this.size==0;
    }
    /**
     * Method that removes a given key from the hashset.
     *
     * @param key key to be removed
     * @return true if the removal was succesful, false otherwise.
     */
    public boolean remove(K key) {
        HashSetEntry entry = values[hash(key)];
        if (entry == null) {
            return false;
        }
        HashSetEntry previous = null;
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

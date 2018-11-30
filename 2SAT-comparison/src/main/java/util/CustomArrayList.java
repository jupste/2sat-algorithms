/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Custom arraylist data structure that can be used to store different dataclasses.
 * @author jussiste
 * @param <T> A generic dataclass
 */
public class CustomArrayList<T> implements Iterable<T> {

    protected Object[] values;
    private int size;
    private final static int DEFAULT_SIZE = 10;
    private int arraySize = DEFAULT_SIZE;

    /**
     * An inner class that is used to give CustomArrayList the iterable functionality
     */
    class ArrayIterator implements Iterator<T> {

        int current = 0;

        public boolean hasNext() {
            if (current < CustomArrayList.this.size) {
                return true;
            } else {
                return false;
            }
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) values[current++];
        }
    }//subclass

    /**
     * Gives the size of the arraylist
     * @return size of array
     */
    public int size() {
        return this.size;
    }

    /**
     * Method to check if the arraylist is empty
     * @return true if array is empty
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Constructor that initializes the array.
     */
    public CustomArrayList() {
        values = new Object[DEFAULT_SIZE];
    }

    /**
     * Doubles the array size
     */
    public void increaseSize() {
        values = Arrays.copyOf(values, values.length * 2);
    }

    /**
     * Method to check if a given element is found in the arraylist
     * @param t
     * @return true if it is in the arraylist, false otherwise
     */
    public boolean contains(T t) {
        for (int i = 0; i < size; i++) {
            if (values[i].equals(t)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a new element to the arraylist. Also increases the array size if it is nearing it's max capasity
     * @param e
     */
    public void add(T e) {
        if (values.length - size <= 5) {
            increaseSize();
        }
        values[size++] = e;
    }

    /**
     * Add a Collection type entity to the arraylist
     * @param c
     */
    public void addAll(Collection<? extends T> c) {
        for (T t : c) {
            add(t);
        }
    }

    /**
     * Clears the arraylist of all entries
     */
    public void clear() {
        this.size = 0;
        this.values = new Object[DEFAULT_SIZE];
    }

    /**
     * Returns a given index from the arraylist
     * @param index 
     * @return object that the index points to
     */
    public T get(int index) {
        if (index < size) {
            return (T) values[index];
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /**
     * Removes a given index from the arraylist
     * @param index
     * @return the removed object
     */
    public T remove(int index) {
        if (index < size) {
            Object obj = values[index];
            values[index] = null;
            int tmp = index;
            while (tmp < size) {
                values[tmp] = values[tmp + 1];
                values[tmp + 1] = null;
                tmp++;
            }
            size--;
            return (T) obj;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /**
     * Return the index of the given object
     * @param o
     * @return
     */
    public int indexOf(T o) {
        for (int i = 0; i < size; i++) {
            if (values[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

}

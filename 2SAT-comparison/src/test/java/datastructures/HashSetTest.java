/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jussiste
 */
public class HashSetTest {

    static class testObject {
    }

    @Test
    public void hashSetCanStoreIntegers() {
        CustomHashSet<Integer> set = new CustomHashSet<>();
        set.add(1);
        set.add(2);
        assertEquals(set.size(), 2);
    }

    @Test
    public void hashSetCanStoreStrings() {
        CustomHashSet<String> set = new CustomHashSet<>();
        set.add("a");
        set.add("b");
        assertEquals(set.size(), 2);
    }

    @Test
    public void hashMapCanStoreTestObjects() {
        CustomHashSet<testObject> set = new CustomHashSet<>();
        set.add(new testObject());
        set.add(new testObject());
        assertEquals(set.size(), 2);
    }


    @Test(timeout = 10000)
    public void removingKeyDeletesEntryFromHashset() {
        CustomHashSet<Integer> set = new CustomHashSet<>();
        set.add(1);
        set.remove(1);
        assertFalse(set.containsKey(1));
        assertTrue(set.isEmpty());
    }

    @Test
    public void removalAndInsertWorksOnLargeHashsets() {
        CustomHashSet<Integer> set = new CustomHashSet<>();
        for (int i = 0; i < 1000000; i++) {
            set.add(i);
        }
        assertEquals(set.size(), 1000000);
    }

}

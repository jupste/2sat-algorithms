package datastructures;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jussiste
 */
public class HashMapTest {

    static class testObject {
    }

    @Test
    public void hashMapCanStoreIntegers() {
        CustomHashmap<Integer, Integer> map = new CustomHashmap<>();
        map.put(1, 1);
        map.put(2, 1);
        assertEquals(map.size(), 2);
    }

    @Test
    public void hashMapCanStoreStrings() {
        CustomHashmap<String, String> map = new CustomHashmap<>();
        map.put("a", "b");
        map.put("b", "a");
        assertEquals(map.size(), 2);
    }

    @Test
    public void hashMapCanStoreTestObjects() {
        CustomHashmap<testObject, Integer> map = new CustomHashmap<>();
        map.put(new testObject(), 1);
        map.put(new testObject(), 2);
        assertEquals(map.size(), 2);
    }

    @Test
    public void insertingWithExistingKeyOverridesValue() {
        CustomHashmap<Integer, Integer> map = new CustomHashmap<>();
        map.put(1, 3);
        map.put(1, 2);
        assertEquals((long)map.get(1), 2);
        assertEquals(map.size(), 1);
    }
    @Test(timeout=10000)
    public void removingKeyDeletesEntryFromHashmap(){
        CustomHashmap<Integer, Integer> map = new CustomHashmap<>();
        map.put(1,2);
        map.put(2, 3);
        map.remove(1);
        assertNull(map.get(1));
        assertEquals(map.size(), 1);
    }
    @Test
    public void removalAndInsertWorksOnLargeHashmaps(){
        CustomHashmap<Integer, Integer> map = new CustomHashmap<>();
        for(int i=0; i<1000000; i++){
            map.put(i, i);
        }
        assertEquals(map.size(), 1000000);
    }
}

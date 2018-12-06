package datastructures;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import util.CustomArrayList;
import util.CustomArrayList;

/**
 *
 * @author jussiste
 */
public class ArrayListTest {

    class TestObject {
    }

    @Test
    public void alistIsCreated() {
        CustomArrayList<Integer> alist = new CustomArrayList<>();
        assertTrue(alist.isEmpty());
        assertNotNull(alist);
    }

    @Test
    public void alistWorksWithIntegers() {
        CustomArrayList<Integer> alist = new CustomArrayList<>();
        alist.add(5);
        alist.add(100);
        assertEquals((long) alist.size(), 2L);
        assertEquals((long) alist.get(1), 100L);
        assertEquals((long) alist.get(0), 5L);
    }

    @Test
    public void alistWorksWithStrings() {
        CustomArrayList<String> stack = new CustomArrayList<>();
        stack.add("test1");
        stack.add("test2");
        assertEquals((long) stack.size(), 2L);
        assertEquals(stack.get(1), "test2");
        assertEquals(stack.get(0), "test1");
    }

    @Test
    public void alistWorksWithTestObject() {
        CustomArrayList<TestObject> alist = new CustomArrayList<>();
        alist.add(new TestObject());
        alist.add(new TestObject());
        assertEquals((long) alist.size(), 2L);
    }

    @Test
    public void containsMethodWorks() {
        CustomArrayList<String> alist = new CustomArrayList<>();
        alist.add("test");
        assertTrue(alist.contains("test"));
    }

    @Test
    public void canAddCollectibles() {
        ArrayList<String> first = new ArrayList<>();
        CustomArrayList<String> second = new CustomArrayList<>();
        first.add("hello");
        first.add("every");
        first.add("body");
        second.addAll(first);
        assertEquals(second.size(), 3L);
    }

    @Test
    public void clearResetsTheArrayList() {
        CustomArrayList<String> alist = new CustomArrayList<>();
        alist.add("test1");
        alist.add("test2");
        alist.clear();
        assertTrue(alist.isEmpty());
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import util.CustomStack;

/**
 *
 * @author jussiste
 */
public class CustomStackTest {
    static class TestObject{
        
    }
    @Before
    public void setUp() {
    }
    
    @Test
    public void stackIsCreated(){
        CustomStack<Integer> stack=new CustomStack<>();
        assertTrue(stack.isEmpty());
        assertNotNull(stack);
    }
    @Test
    public void stackWorksWithIntegers(){
        CustomStack<Integer> stack= new CustomStack<>();
        stack.push(5);
        stack.push(100);
        assertEquals((long)stack.size(), 2L);
        assertEquals((long)stack.pop(), 100L);
        assertEquals((long)stack.pop(), 5L);
    }
    @Test
    public void stackWorksWithStrings(){
        CustomStack<String> stack= new CustomStack<>();
        stack.push("test1");
        stack.push("test2");
        assertEquals((long)stack.size(), 2L);
        assertEquals(stack.pop(), "test2");
        assertEquals(stack.pop(), "test1");
    }
    @Test
    public void stackWorksWithTestObject(){
        CustomStack<TestObject> stack= new CustomStack<>();
        stack.push(new TestObject());
        stack.push(new TestObject());
        stack.pop();
        assertEquals((long)stack.size(), 1L);
    }
    @Test
    public void whenStackIsFullNoNewItemsCanBeAdded(){
        CustomStack<TestObject> stack=new CustomStack<>();
        assertTrue(stack.isEmpty());
        for(int i=0; i<1000000; i++){
            stack.push(new TestObject());
        }
        assertEquals(stack.size(), 100000);
    }

}

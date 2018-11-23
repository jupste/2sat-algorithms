/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author jussiste
 */
public class CustomStack<T> {
    private static final int MAX = 100000; 
    private int top;
    private int size;
    private T stack[]=null; 
    
    public CustomStack(){
        this.stack = (T[]) new Object[MAX];
        top=-1;
        size=0;
    }
    public int size(){
        return this.size;
    }
    
    public boolean isEmpty(){ 
        return size==0; 
    }
    public T pop(){
        if(this.size==0){
            return null;
        }
        this.size--;
        T ret =this.stack[top];
        this.stack[top]=null;
        this.top--;
        
        return ret;
    }
    
    public void push(T t){
        if(this.size==MAX){
            return;
        }
        this.size++;
        this.stack[++top]= t;
    }
    
}

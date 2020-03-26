/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rnd;

/**
 *
 * @author saradam
 */
public class ConstructorChainingRnd {

    public ConstructorChainingRnd() {
        this(10);
        System.out.println("The Default Constructor !!");
    }
    
    public ConstructorChainingRnd(int x) {
        this(10, 13);
        System.out.println("The second value :: "+x);
    }
    
    public ConstructorChainingRnd(int x, int y) {
        System.out.println("The second value of multification of X & Y:: "+(x*y));
    }
    
    public static void main(String[] args) {
        new ConstructorChainingRnd();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java8.gowithlambda;

/**
 *
 * @author saradam
 */

public class test {
    public static void main(String[] args) {
        funInterface obj = ()->System.out.println("Hello India");  
        obj.test();
        obj.testWithParam(10, 20);
    }
}

interface funInterface{
    public void test();
    default public void testWithParam(int x, int y){
        System.out.println(x+" + "+y+" = "+(x+y));
    }
}


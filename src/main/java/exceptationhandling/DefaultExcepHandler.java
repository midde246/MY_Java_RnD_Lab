/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptationhandling;

/**
 *
 * @author saradam
 */
public class DefaultExcepHandler {
    public static void main(String[] args) {
        doSomeThing();
    }

    private static void doSomeThing() {
        doSomethingMore();
    }

    private static void doSomethingMore() {
       int a = 10/0;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lambdas;

/**
 *
 * @author saradam
 */
public class MyClass {
    public static void main(String[] args) {
        new Thread(()->
        {
            System.out.println("The Thread has been created throw LAMBDA Function !");
        }
        ).start();
                
    }
}

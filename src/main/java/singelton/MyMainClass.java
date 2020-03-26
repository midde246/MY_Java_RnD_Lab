/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package singelton;

/**
 *
 * @author saradam
 */
public class MyMainClass {
    public static void main(String[] args) {
        System.out.println(EsierSingelTon.getInstance());
        EsierSingelTon.getInstance();
    }
}

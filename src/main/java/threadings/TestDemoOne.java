/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadings;

/**
 *
 * @author saradam
 */
public class TestDemoOne {
//    int num = 10;

    public static void main(String[] args) {
//        MyThread1 th1 = new MyThread1();
    }

    class MyThread1 implements Runnable {

        @Override
        public void run() {
//            System.out.println("In first Thread :: "+num++);
        }

    }

    class MyThread2 implements Runnable {

        @Override
        public void run() {
//            System.out.println("In second Thread :: "+num++);
        }

    }
}

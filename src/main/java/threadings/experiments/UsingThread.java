/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadings.experiments;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author saradam
 */
public class UsingThread {

    public static void main(String[] args) {
//        Booking obj1 = new Booking("Hotel");
//        Booking obj2 = new Booking("Car");
//        Booking obj3 = new Booking("Flight");
//        Thread th1 = new Thread(obj1);
//        Thread th2 = new Thread(obj2);
//        Thread th3 = new Thread(obj3);
//        th1.start();
//        th2.start();
//        th3.start();

         new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
//                Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (int i = 0; i < 5; i++) {
                System.out.println("Hotel");
            }
        }).start();

        System.out.println("But I am out of Thread !!");
//        if(th1.isAlive()){
//            try{
//            Thread.sleep(501);
//            } catch(Exception ex){}
//        } else {
//            System.out.println("I am dying ");
//        }
//        if(!th1.isAlive()){
//             System.out.println("I have been killed !!");
//        }
    }
}

//class Booking extends Thread {
//
//    private String serviceName;
//
//    public Booking(String serviceName) {
//        this.serviceName = serviceName;
//    }
//
//    @Override
//    public void run() {
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        for(int i = 0; i < 5 ; i++){
//            System.out.println(serviceName);
//        }
//        
//    }
//
//}

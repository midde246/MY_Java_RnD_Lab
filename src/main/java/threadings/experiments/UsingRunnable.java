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

class BookingService implements Runnable {

    private String serviceName;

    public BookingService(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
//            Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < 5; i++) {
            System.out.println(serviceName);
        }
    }
}


public class UsingRunnable {
    public static void main(String[] args) {
        Thread th1 = new Thread(() -> {
            
        });
    }
}


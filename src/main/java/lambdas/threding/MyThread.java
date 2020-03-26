/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lambdas.threding;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author saradam
 */
public class MyThread implements Runnable{

    @Override
    public void run() {
        long s = System.currentTimeMillis();        
        File file = new File("D:\\Projects\\StaticDataPopulation\\HotelBeds\\Car\\Hotel\\AE_HoteData.xml"); 
  
        BufferedReader br = null; 
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MyThread.class.getName()).log(Level.SEVERE, null, ex);
        }

        String st; 
        try {
            while ((st = br.readLine()) != null) 
                System.out.println(st);
        } catch (IOException ex) {
            Logger.getLogger(MyThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        long f = System.currentTimeMillis();
        System.out.println("Time Taken for Normal Thread is "+(f-s));
    } 
}
   

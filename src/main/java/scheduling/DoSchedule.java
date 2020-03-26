/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduling;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author saradam
 */
public class DoSchedule extends TimerTask {
    
    public void run(){
        
    }
    
    public static void main(String[] args) {
        Timer t1 = new Timer();
        t1.schedule(new DoSchedule(), 0, 1000);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventloop;

import java.io.FileInputStream;

/**
 *
 * @author saradam
 */
public class EventLoop {
    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        while( i == 0){
            try{
               final String filePath = "D:\\Temp_Folder\\EventLoop\\test.txt";
               FileInputStream fin = new FileInputStream(filePath);
               System.out.println("I have got the file....");
               
               break;
            } catch (Exception ex){
                System.out.println(" No file Found in the directory ..");
            }
            Thread.sleep(1000);  
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rnd;

/**
 *
 * @author saradam
 */
public class Breaking {
   /*
    public void main1(String[] args) {
        int a = 0, b = 0;
        aa:
        for(int i = 0 ; i<3; i++){
            bb:
            for(int j = 0 ; j < 8; j++){
                System.out.print(" j : "+j); 
                if(j == 3){
                   break bb;
                }  
            }
            System.out.println(" i : "+i);
        }
    }
    public static void main(String[] args) {

        for(int i = 0 ; i<3; i++){

            for(int j = 0 ; j < 8; j++){
                System.out.println(" inner loop No : "+j+1); 
                if(j == 3){
                   break;
                }  
            }
            System.out.println(" Outer Loop No : "+(i+1));
        }
    }
   */
    public static void main(String[] args) {
        String serviceId = "3356";
        serviceId = serviceId+"#@#"+"Paid";
        System.out.println("Service ID : "+serviceId);
        System.out.println("Service ID Split [0]: "+serviceId.split("#@#")[0]);
        System.out.println("Service ID Split [1]: "+serviceId.split("#@#")[1]);
    }
}

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
public class ExceptionTrCatch {
    
    public static void main(String[] args) {
        String test = "Hey ! Midde How are you ? ";
        String needTobeSet = "";
        
        try{
            needTobeSet = test;
        } catch(Exception Ex){}
        System.out.println("Test Data ::: "+test);
        System.out.println("Need To be Set Data ::: "+needTobeSet);
    }
    
}

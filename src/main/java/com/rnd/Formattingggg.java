/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rnd;

import java.text.DecimalFormat;

/**
 *
 * @author saradam
 */
public class Formattingggg {
    public static void main(String[] args) {
        DecimalFormat decFormat = new DecimalFormat("##.00");
        double testValue = 0.6459321;
        String no = String.format("%.2f", testValue); 
        System.out.printf(no);
    
        System.out.println("My formated value :: "+decFormat.format(testValue));
    }
}

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
public class IntParsing {
    public static void main(String[] args) {
        String str = "12.32";
        String str1 = "12.32";
        System.out.println((int) Double.parseDouble(str));
        if((int) Double.parseDouble(str) == (int) Double.parseDouble(str1)){
            System.out.println("Both are same data");
        }
    }
}

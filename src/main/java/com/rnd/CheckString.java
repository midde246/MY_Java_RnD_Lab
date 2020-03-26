/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rnd;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author saradam
 */
public class CheckString {

    public static void main(String[] args) {
       
        List<String> textList = new ArrayList<>();
        textList.add("7_Midde");
        textList.add("6_Sarada");
        textList.add("Prasad");
        textList.add("12_Prasad");

        for (String text : textList) {
//            if (Character.isDigit(text.charAt(0))) {
//                System.out.println(text);
//            }
            text = text.substring(2, text.length());
            System.out.println("Text :: "+text);
        }
        
    }
}

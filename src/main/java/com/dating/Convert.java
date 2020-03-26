/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dating;

/**
 *
 * @author saradam
 */
public class Convert {
    public static void main(String[] args) {
        String confirmationDate = "2018-12-17 20:40:31.993";
        int spacePos = confirmationDate.indexOf(" ");
        System.out.println("Space Position :: "+spacePos);
        confirmationDate = confirmationDate.substring(0, spacePos);
        System.out.println("Confirmation Date :: "+confirmationDate);
        System.out.println("String Length :: "+confirmationDate.length());
    }
}

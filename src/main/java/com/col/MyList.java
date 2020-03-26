/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.col;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author saradam
 */
public class MyList {
    public static void main(String[] args) {
        List<String> myList = new ArrayList<>();
        myList.add("First");
        myList.add("Second");
        myList.add("Third");
        System.out.println("My List :: "+myList);
        myList.remove("second");
        System.out.println("My New List :: "+myList);
    }
}

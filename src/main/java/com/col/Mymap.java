/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.col;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author saradam
 */
public class Mymap {
    public static void main(String[] args) {
        Map<String, String> myMap = new HashMap<>();
        long kStart, kEnd;
        myMap.put("1", "This is First Value");
        myMap.put("2", "This is Second Value");
        myMap.put("3", "This is Third Value");
        myMap.put("4", "This is Fourth Value");
        
        System.out.println("-------- Iterating by Key Set ---------");
        
        kStart = System.nanoTime();
        for(String key : myMap.keySet()){
            System.out.println(key+" : "+myMap.get(key));
        }
        kEnd = System.nanoTime();
        System.out.println("Time Taken :"+(kEnd-kStart));
        
        System.out.println("-------- Iterating by Entry Key ---------");
        
        kStart = System.nanoTime();
        for(Entry<String, String> entry : myMap.entrySet()){
            System.out.println(entry.getKey()+" : "+entry.getValue());
        }
        kEnd = System.nanoTime();
        System.out.println("Time Taken :"+(kEnd-kStart));
    }
}

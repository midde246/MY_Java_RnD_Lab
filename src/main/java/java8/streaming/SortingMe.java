/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java8.streaming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author saradam
 */
public class SortingMe {
    public static void main(String[] args) {
        List<String> myList = Arrays.asList("a1", "Z10", "c3", "a2", "b1", "c2", "c1");
        List<String> tempListstore = new ArrayList<>();
        myList
        .stream()
//        .filter(s -> s.startsWith("c"))
        .map(String::toUpperCase)
        .sorted()
        .forEach(i -> tempListstore.add(i));
        
        for(String listData : tempListstore){
            System.out.println("listData :: "+listData);
        }
        
    }
}

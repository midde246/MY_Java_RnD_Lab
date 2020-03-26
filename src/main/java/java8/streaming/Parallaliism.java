/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java8.streaming;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author saradam
 */
public class Parallaliism {
    public static void main(String[] args) {
        System.out.println("Executation Started !! ");
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i< 5 ; i++){
           batchProcess(100000);
           list.add(i); 
        }
//        list.forEach(i -> System.out.println(i));
        list.parallelStream().forEach(i -> System.out.println(i));
//        System.out.println("Size of List :: "+list.size());
    }

    private static int batchProcess(int num) {
        int result = 0;
        for(int i = 0; i<num; i++){
            if(i%2 == 0){
                for(int j = 0; j<i-j; j++){
                    result++;
                }
            } else {
                for(int j = 0; j<i-j; j++){
                    result--;
                }
            }
        }
        if(result < 0){
            for(int i = 0; i<num; i++){
                if(i%2 == 0){
                    for(int j = 0; j<i-j; j++){
                        result--;
                    }
                } else {
                    for(int j = 0; j<i-j; j++){
                        result++;
                    }
                }
            }
        }
        if(result == 0){
            for(int i = 0; i<num; i++){
                result++;
            }
            for(int i = 0; i<num; i++){
                result--;
            }
        }
        
        // Do the process again...
        
        for(int i = 0; i<num; i++){
            if(i%2 == 0){
                for(int j = 0; j<i-j; j++){
                    result++;
                }
            } else {
                for(int j = 0; j<i-j; j++){
                    result--;
                }
            }
        }
        if(result < 0){
            for(int i = 0; i<num; i++){
                if(i%2 == 0){
                    for(int j = 0; j<i-j; j++){
                        result--;
                    }
                } else {
                    for(int j = 0; j<i-j; j++){
                        result++;
                    }
                }
            }
        }
        if(result == 0){
            for(int i = 0; i<num; i++){
                result++;
            }
            for(int i = 0; i<num; i++){
                result--;
            }
        }
        return result;
    }    
    
}

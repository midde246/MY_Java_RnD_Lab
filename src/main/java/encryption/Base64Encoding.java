/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encryption;

import java.util.Base64;

/**
 *
 * @author saradam
 */
public class Base64Encoding {
    public static void main(String[] args) {
        
        // create a sample String to encode 
        String sample = "India Team will win the Cup"; 
  
        // print actual String 
        System.out.println("Sample String: "+ sample); 
  
        // Encode into Base64 format 
        String BasicBase64format = Base64.getEncoder().encodeToString(sample.getBytes()); 
  
        // print encoded String 
        System.out.println("Encoded String: "+ BasicBase64format); 
    }
}

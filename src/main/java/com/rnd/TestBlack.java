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
public class TestBlack {
    public static void main(String[] args) {
//        String str = "Prat Del VerdÃº,, Carretera General, D'Arinsal,S/N  ";
//        str = replaceSingleQuoteByDoubleQuote(str);
//        System.out.println("New String :: "+str);

          testString();
    }
    
    private static String replaceSingleQuoteByDoubleQuote(String str) {
        String replacedStr = str;
        if(str != null && str.contains("'")) {
            replacedStr=str.replaceAll("'", "''");
        }          
        return replacedStr;
    }
    
    private static void testString(){
//            String Lat1 = "31.26168700";
//            String Lng1 = "35.21487100";
            String lat2 = "31.261687";
            String lng2 = "35.214871";
        
            String lat2_abs = lat2.substring(0,lat2.indexOf("."));
            String lat2_dec = lat2.substring(lat2.indexOf(".")+1,lat2.length());
            String lng2_abs = lng2.substring(0,lng2.indexOf("."));
            String lng2_dec = lng2.substring(lng2.indexOf(".")+1,lng2.length());  
            
            System.out.println("Lat2 :: "+lat2);
            System.out.println("Lan2 :: "+lng2);
            if(lat2_dec.length() < 8){
                for(int i = 0; i < (8 -lat2_dec.length()+1); i++){
                    lat2_dec = lat2_dec + "0";
                }
            }
            System.out.println("Lat2 :: "+lat2_dec);
            System.out.println("Lan2 :: "+lng2_dec);
            
    }
}

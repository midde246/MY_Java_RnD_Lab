/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hackerrank;

import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author saradam
 */
public class Anagram {
    public static void main(String[] args) {
       String a = "CATT";
       String b = "TATc";
//       boolean isAnagram = isAnagramWithMap(a,b);   
       boolean isAnagram = isAnagram(a,b);  
       System.out.println( (isAnagram) ? "Anagrams" : "Not Anagrams" );
    }

    private static boolean isAnagram(String a, String b) {
        boolean isAnagra = false;
        
        if(a.length() != b.length()) return false;
        a = a.toLowerCase();
        b = b.toLowerCase();
        char[] aAry = a.toCharArray();
        char[] bAry = b.toCharArray();
        
        for(int i =0 ; i<aAry.length ; i++){
            for(int j=0 ; j<aAry.length-i-1 ; j++){
                if(aAry[j] > aAry[j+1]){
                    char temp = aAry[j];
                    aAry[j] = aAry[j+1];
                    aAry[j+1] = temp;
                }
            }
        }
        for(int i =0 ; i<bAry.length ; i++){
            for(int j=0 ; j<bAry.length-i-1 ; j++){
                if(bAry[j] > bAry[j+1]){
                    char temp = bAry[j];
                    bAry[j] = bAry[j+1];
                    bAry[j+1] = temp;
                }
            }
        }
        String aStr = "", bStr = "";
        for(int i =0 ; i<aAry.length; i++){
            aStr = aStr + aAry[i];
        }
        for(int i =0 ; i<bAry.length; i++){
            bStr = bStr + bAry[i];
        }
        if(aStr.equalsIgnoreCase(bStr)) return true;
        
        return isAnagra;
    }
    
    
    private static boolean isAnagramWithMap(String a, String b) {
        boolean isAnagra = false;
        
        Map<Character, Integer> charNummap = new HashMap<>(); 
        if(a.length() != b.length())
            return false;
        
        for(int i=0 ; i<a.length() ; i++){
           char temp = a.charAt(i);
           if(charNummap.containsKey(temp)){
               int num = charNummap.get(temp);
               num++;
               charNummap.put(temp, num);
           } else {
               charNummap.put(temp, 1);
           }
        }     
        
        for(int i=0 ; i<b.length() ; i++){
           char temp = b.charAt(i);
           if(charNummap.containsKey(temp)){
               int num = charNummap.get(temp);
               if(num == 0){
                  return false;   
               }
               num--;
               charNummap.put(temp, num);
           } else {
               return false;
           }
           isAnagra = true;
        } 
       
        return isAnagra;
    }
    
            
}

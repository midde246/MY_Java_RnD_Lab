/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stringing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author saradam
 */
public class StringToArrayList {
    public static void main(String[] args) {
        String ticketNumbers = "1235, 897, 456,569,7894 ";
        String[] split = ticketNumbers.split(",");
        List<String> ticketNumberList = Arrays.asList(split); 
        System.out.println(ticketNumberList);
    }
    
    
}


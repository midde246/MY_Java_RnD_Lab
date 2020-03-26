/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java8.streaming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author saradam
 */
public class NewLine {
    public static void main(String[] args) {
        String[] nameArr = {"Subham", "Monojit", "Anis", "Anup", "Sayantan"};
        List<String> nameList = new ArrayList<>();
        Collections.addAll(nameList, nameArr);
        System.out.println(nameList);
//        final String finalresult = nameList.stream().map(row -> String.join("\n", row));
        
    }
}

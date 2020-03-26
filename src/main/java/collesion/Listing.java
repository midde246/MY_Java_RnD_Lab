/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collesion;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author saradam
 */
public class Listing {
    public static void main(String[] args) {
       List<String> queryList = new ArrayList<>();
        queryList.add("I Am :countryName");
        queryList.add("I Am :countryName");
        queryList.add("I Am :countryName");
        queryList.add("I Am :countryName");
        queryList.add("I Am :countryName");
        int i = 0;
        for (String query : queryList) {
            switch(i){
                case 0 :
                    query = query.replaceAll(":countryName",  "Indian");
                    break;
                case 1 :
                    queryList.get(i).replaceAll(":countryName",  "Bangladeshi");
                    break;
                case 2 :
                    queryList.get(i).replaceAll(":countryName",  "American");
                    break;
                case 3 :
                    queryList.get(i).replaceAll(":countryName",  "Pakistani");
                    break;
                case 4 :
                    queryList.get(i).replaceAll(":countryName",  "Nepali");    
                    break;
            }
            i++;
        }
        for(String query : queryList){
            System.out.println(query);
        }
    }
}

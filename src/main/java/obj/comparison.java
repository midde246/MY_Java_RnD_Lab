/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author saradam
 */
public class comparison {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        Map<String, Person> personMap = new HashMap<>();
        Person p1 = new Person();
        Person p2 = new Person();
        p1.setName("Sarada Prasad Midde");
        p1.setAge("25");
        p2.setName("Sarada Prasad Midde");
        p2.setAge("25");
        
        /**
        *  Deep Comparison.
        */
        if(p1.equals(p2)){
            System.out.println("Both Objects Content Same Data");
        } else {
            System.out.println("The Objects Content Different Data");
        }
        
        /**
        *  Putting distinct data into the List.
        */
        if(!personList.contains(p1)){
           personList.add(p1);
        }
        if(!personList.contains(p2)){
           personList.add(p2);
        }
        
        
        /**
        *  Shallow Comparison.
        */
        Person p3 = p1;
        if(p1.equals(p3)){
            System.out.println("Both Objects are same !! ");
        }
        
        System.out.println("Hash Code :: "+p1.hashCode());
        
        /**
        *  Printing the List
        */
        for(int i = 0; i < personList.size(); i++){
            System.out.println("Name Of Person "+(i+1)+" :: "+personList.get(i).getName());
            System.out.println("Age Of Person "+(i+1)+" :: "+personList.get(i).getAge());
        }
        
        /**
        *  Populating Map Data
        */
        personMap.put("1", p1);
        personMap.put("2", p2);
        
        /**
        *  Printing Map Data
        */
        for(Map.Entry<String, Person> mapData : personMap.entrySet()){
            System.out.println("Name Of Person "+mapData.getKey()+" :: "+mapData.getValue().getName());
            System.out.println("Age Of Person "+mapData.getKey()+" :: "+mapData.getValue().getAge());
        }
        
        System.out.println(personMap);
    }
}

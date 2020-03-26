/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rnd;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author saradam
 */
public class ArrayObjects {
    public static void main(String[] args) {
        List<Person> resultList = new ArrayList<>();
        
        for(int i=0; i<5; i++){
            Person person = new Person();
            person.setName("Sarada Prasad Midde");
            person.setAge("Age");
            if(!resultList.contains(person)){
                resultList.add(person);
            }
        }
        
        
        System.out.println("Result List Size :: "+resultList.size());
        
    }

    private static class Person {

        public Person() {
        }
        
        private String name;
        private String age;

        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name the name to set
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * @return the age
         */
        public String getAge() {
            return age;
        }

        /**
         * @param age the age to set
         */
        public void setAge(String age) {
            this.age = age;
        }
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rnd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author saradam
 */
public class sorting {
    public static void main(String[] args) {
        List<MyModel> myModelList = new ArrayList<>();
        MyModel myModel1 = new MyModel();
        myModel1.setName("Zabra");
        myModel1.setAge("25");
        MyModel myModel2 = new MyModel();
        myModel2.setName("Lakshman");
        myModel2.setAge("21");
        MyModel myModel3 = new MyModel();
        myModel3.setName("Sita");
        myModel3.setAge("25");
        MyModel myModel4 = new MyModel();
        myModel4.setName("Raban");
        myModel4.setAge("45");
        
        myModelList.add(myModel1);
        myModelList.add(myModel2);
        myModelList.add(myModel3);
        myModelList.add(myModel4);
        
        sort(myModelList);
        for(MyModel myModel : myModelList){
            System.out.println("Name : "+myModel.getName());
            System.out.println("Age : "+myModel.getAge());
        }
    }

    private static void sort(List<MyModel> myModelList) {
        Collections.sort(myModelList, new Comparator<MyModel>(){
            @Override
            public int compare(MyModel o1, MyModel o2) {
               return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        
        Collections.sort(myModelList, new Comparator<MyModel>(){
            @Override
            public int compare(MyModel o1, MyModel o2) {
               return o1.getAge().compareToIgnoreCase(o2.getAge());
            }
        });
                    
    }
}

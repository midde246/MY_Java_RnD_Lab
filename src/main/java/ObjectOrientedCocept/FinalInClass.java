/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjectOrientedCocept;

/**
 *
 * @author saradam
 */



/**
 * Final Method can not be overridden.
 * @author saradam
 */
public class FinalInClass {

    public static void main(String[] args) {

    }
}

class myFinalClass {

    String myName = "Sarada Prasad Midde";

    void myMethod(String myName) {
        this.myName = myName;
        System.out.println("Name :: " + myName);
    }
}

class myChldClass extends myFinalClass {

//    void final myMethod(String myName) {
//   
//    }
}

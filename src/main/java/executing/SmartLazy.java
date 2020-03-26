/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package executing;

/**
 *
 * @author saradam
 */
public class SmartLazy {
    public static void main(String[] args) {
        isSmartTrue();
        isLazyTrue();
    }
    
    public static boolean isSmartTrue() {
        boolean a = false;
        boolean b = true;
        return b || (a && b); // (a && b) is not evaluated since b is true
    }

    public static boolean isLazyTrue() {
//        boolean a = isATrue();
//        boolean b = isBTrue();
//        return b || a;
        return isBTrue() || isATrue();
    }

    private static boolean isATrue() {
        System.out.println("I am inside A");
        return false;
    }

    private static boolean isBTrue() {
        System.out.println("I am inside B");
        return true;
    }
    
}


   
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java8.gowithlambda;

/**
 *
 * @author saradam
 */

interface Arithmetic{
    public int doSum(int a, int b);
}

interface Arithmetic1{
    public void doSum(int a, int b);
}

public class Summing {
    public static void main(String[] args) {
        
        // Without   using Lambda Expression....
        Arithmetic arith = new Arithmetic(){
            @Override
            public int doSum(int a , int b) {
                return a+b;
            }

            public int doSub(int a, int b) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        
        //With using Lambda Expression....
        Arithmetic arith1 =(int a, int b)->{
            return a+b;
            
        };
        
        Arithmetic1 arith2 = (a, b ) -> System.out.println("Sum : "+(a+b));
        arith2.doSum(10, 20);
        
        int sum = arith1.doSum(12, 45);
//        System.out.println("Sum :: "+sum);
    }
}
  
//    private static int doSum(int a, int b) {
//      return a+b;  
//    }
  
//interface Drawable{  
//    public void draw();  
//}  
//  
//public class LambdaExpressionExample2 {  
//    public static void main(String[] args) {  
//        int width=10;  
//          
//        //with lambda  
//        Drawable d2=()->{  
//            System.out.println("Drawing "+width);  
//        };  
//        d2.draw();  
//    }  
//}  
//
//interface Drawable{  
//    public void draw();  
//}  
//public class LambdaExpressionExample {  
//    public static void main(String[] args) {  
//        int width=10;  
//  
//        //without lambda, Drawable implementation using anonymous class  
//        Drawable d=new Drawable(){  
//            public void draw(){System.out.println("Drawing "+width);}  
//        };  
//        d.draw();  
//    }  
//}  
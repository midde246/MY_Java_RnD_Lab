/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defaultmethod;

/**
 *
 * @author saradam
 */
public class MethodIsDefaultImpl implements MehodDefaulter{

    public static void main(String[] args) {
       MethodIsDefaultImpl obj = new MethodIsDefaultImpl();
       obj.print();
       obj.printing();
    }
    
    @Override
    public void print() {
        System.out.println("This test print !!");
    }
    
}

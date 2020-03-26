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
public interface MehodDefaulter {
    public void print();
    public default void printing(){
        System.out.println("This a default Printing !!");
    }
}

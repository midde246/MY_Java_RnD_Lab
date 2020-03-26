/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package singelton;

/**
 *
 * @author saradam
 */
public class EsierSingelTon {
    
    private static final EsierSingelTon singelTon = new EsierSingelTon();

    public EsierSingelTon() {}
    
    public static EsierSingelTon getInstance(){
        return singelTon;
    }
    
}

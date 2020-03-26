/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hackerrank;

/**
 *
 * @author saradam
 */
public class ArrayLeftRotation {
    public static void main(String[] args) {
        int d = 4, n = 5;
        int[] arr = {1, 2, 3, 4, 5};
        
        int lRotate = 0;
        while(lRotate < d){
            int temp = arr[0];
            shiftOneStepBefore(arr);
            arr[n-1] = temp;
            lRotate++;
        }
        for(int i=0; i<n; i++){
            System.out.print(arr[i]+" ");
        }
        
    }

    private static void shiftOneStepBefore(int[] arr) {
        for(int i=1; i<arr.length; i++){
            arr[i-1] = arr[i];
        }
    }
}

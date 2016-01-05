/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package APAC;

import java.util.Scanner;

/**
 *
 * @author hitarthk
 */ 
public class R3B {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int T=in.nextInt();
        for(int t=1;t<=T;t++){
            int n=in.nextInt();
            int[][] a=new int[2][n];
            for(int i=0;i<n;i++){
                a[0][i]=in.nextInt();
                a[1][i]=in.nextInt();
                
            }
            long l=0;
            long r = (long) 1e15;
            while (r - l > 1) {
                long m = (l + r) >> 1;
                long per = (100 * a[1][n - 1]) / m;
                if (per <= a[1][0]) {
                    l = m;
                } else {
                    r = m;
                }
            }
            
        }
    }
}

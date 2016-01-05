/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_C;

import java.util.Scanner;

/**
 *
 * @author hitarthk
 */
public class C_230_1A {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        double n=in.nextDouble();
        double x=1;
        double y=n;
        long ans=0;
        while(x<=n/2){
            while(x*x+y*y>=n*n){
                y--;
            }
            System.out.println(y+" "+x);
            x++;
            ans++;
        }
        y=1;
        x=n;
        while(y>=n/2){
            while(x*x+y*y<=n*n){
                x--;
            }
            System.out.println(y+" "+x);
            y++;
            ans++;
        }
        System.out.println(4*ans+4);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hackerrank;

import java.util.Scanner;

/**
 *
 * @author hitarthk
 */
public class Coyoye {
    static long mod=(long)10e9+7;
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        long n=in.nextLong();
        long m=in.nextLong();
        long k=in.nextLong();
        if(k==1 || k>n){
            System.out.println(pow(m,n));
            return;
        }
        if(k==n){
            System.out.println(pow(m,(n+1)/2));
            return;
        }
        if(k%2==0){
            System.out.println(m);
        }
        else{
            System.out.println(m*m);
        }
    }
    
    public static long pow(long b,long e){
        long ans=1;
        for(int i=1;i<=e;i++){
            ans = (ans*b)%mod;
        }
        return ans;
    }
}

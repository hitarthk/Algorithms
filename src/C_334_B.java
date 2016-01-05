
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hitarthk
 */
public class C_334_B {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int k=in.nextInt();
        long[] a=new long[n+1];
        for(int i=1;i<=n;i++){
            a[i]=in.nextLong();
        }
        if(k>=n){
            System.out.println(a[n]);
            return;
        }
        else{
            int x=n-k;
            int y=k-x;
            long max=Integer.MIN_VALUE;
            for(int i=1;i<=x;i++){
                if(a[i]+a[2*x+1-i]>max){
                    max=a[i]+a[2*x+1-i];
                }
            }
            System.out.println(Math.max(max, a[n]));
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hackerrank;

import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author hitarthk
 */
public class StockMaximize {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int t=in.nextInt();
        while(t>0){
            t--;
            int n=in.nextInt();
            long[] a=new long[n+1];
            long[] ans=new long[n+1];
            Stack<Long> s=new Stack<>();
            int[] nearestGreater=new int[n+1];
            nearestGreater[1]=0;
            s.push(a[1]);
            for(int i=2;i<=n;i++){
                while(!s.isEmpty() && s.peek()<a[i]){
                    s.pop();
                }
                //if(!s.isEmpty())
            }
            ans[1]=0;
            for(int i=2;i<=n;i++){
                long count=0;
                long sum=0;
                for(int j=i-1;j>=1;j--){
                   
                }
            }
        }
    }
}

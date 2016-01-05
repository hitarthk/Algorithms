/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author hitarthk
 */
public class Temp {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int N=n+1;
        int k=in.nextInt();
        int p=in.nextInt();
        int x=in.nextInt();
        int y=in.nextInt();
        int[] a=new int[k+1];
        int sum=0;
        for(int i=1;i<=k;i++){
            a[i]=in.nextInt();
            sum+=a[i];
        }
        
        Arrays.sort(a);
        int ind=1;
        while(ind<=k && a[ind]<y){
            ind++;
        }
        if(ind==k+1 && k>=N/2){
            System.out.println(-1);
            return;
            
        }
        if(ind>=N/2+1 ){
            System.out.println(-1);
            return;
        }
        else
        {
                int so=Math.min(N/2-ind, n-k);
                
                sum+=(so);
                
                sum+=y*(n-(k+so));
                int filled=k+1;
                //System.out.println(sum);
                if(sum<=x){
                    for(int i=1;i<=N/2-ind && filled<=n;i++,filled++){
                        System.out.print(1+" ");
                    }
                    for(int i=1;i<=n-(k+N/2-ind)&& filled<=n;i++){
                        System.out.print((y)+" ");
                    }
                }
                else{
                    System.out.println(-1);
                }
                
        }
    }
}

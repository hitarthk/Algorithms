/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IPC_01;

import java.util.Scanner;

/**
 *
 * @author hitarthk
 */
public class Solution3 {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int t=in.nextInt();
        while(t>0){
            
        t--;
        long a=in.nextLong();
        long b=in.nextLong();
        long c=in.nextLong();
        long k=in.nextLong();
        if(c>=k){
            System.out.println(0);
            continue;
        }
        long l=0;
        long r=(long)Math.sqrt(k);
        //long r=(long)10e9;
        //System.out.println(r);
        while(r-l>1){
            long mid=(l+r)/2;
            long v=(a*mid*mid)+(b*mid)+c;
            if(v>k){
                r=mid;
            }
            else if(v<k){
                l=mid;
            }
            else{
                r=mid;
            }
                
        }
        System.out.println(r);
        System.out.println((a*r*r+b*r+c)+" "+(a*(r-1)*(r-1)+b*(r-1)+c));
        }
    }
    
}

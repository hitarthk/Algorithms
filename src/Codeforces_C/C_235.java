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
public class C_235 {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int t=in.nextInt();
        while(t>0){
            t--;
            int n=in.nextInt();
            int p=in.nextInt();
            int pr=0;
            int r=2*n+p;
            int jump=1;
            while(pr<r){
                for(int i=0;i<n;i++){
                    System.out.println((i+1)+" "+(((i+jump)%n)+1));
                    pr++;
                    if(pr==r)
                        break;
                }
                jump++;
            }
        }
    }
}

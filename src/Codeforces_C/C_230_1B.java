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
public class C_230_1B {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        
        long[][] c=new long[3][3];
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                c[i][j]=in.nextLong();
            }
        }
        int n=in.nextInt();
        long[][][] ans=new long[n+1][3][3];
        ans[1][0][1]=Math.min(c[0][1], c[0][2]+c[2][1]);
        ans[1][0][2]=Math.min(c[0][2], c[0][1]+c[1][2]);
        ans[1][1][0]=Math.min(c[1][0], c[1][2]+c[2][0]);
        ans[1][1][2]=Math.min(c[1][2], c[1][0]+c[0][2]);
        ans[1][2][0]=Math.min(c[2][0], c[2][1]+c[1][0]);
        ans[1][2][1]=Math.min(c[2][1], c[2][0]+c[0][1]);
        for(int i=2;i<=n;i++){
            for(int j=0;j<=2;j++){
                for(int k=0;k<=2;k++){
                    if(j!=k){
                        ans[i][j][k]=Math.min(ans[i-1][j][3-(j+k)]+c[j][k]+ans[i-1][3-(j+k)][k], 2*ans[i-1][j][k]+c[j][3-(j+k)]+c[3-(j+k)][k]+ans[i-1][k][j]);
                        //System.out.println(i+" "+j+" "+k+" "+ans[i][j][k]);
                    }
                }
            }
        }
        System.out.println(ans[n][0][2]);
    }
}

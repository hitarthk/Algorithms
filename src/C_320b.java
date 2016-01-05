
import java.util.ArrayList;
import java.util.Arrays;
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
public class C_320b {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        long[][] a=new long[2*n+2][2*n+2];
        int[] ans=new int[2*n+2];
        long max=0;
        int mi=-1;
        int mj=-1;
        for(int i=2;i<=2*n;i++){
            for(int j=1;j<=i-1;j++){
                long x=in.nextLong();
                a[i][j]=x;
                a[j][i]=x;
                if(x>max){
                    max=x;
                    mi=i;
                    mj=j;
                }
            }
        }
        
        while(max>0){
            ans[mi]=mj;
            ans[mj]=mi;
            a[mi][mj]=0;
            a[mj][mi]=0;
            max=0;
            mi=-1;
            mj=-1;
            for(int i=1;i<=n;i++){
                for(int j=1;j<=n;j++){
                    if(a[i][j]>max){
                        max=a[i][j];
                        mi=i;
                        mj=j;
                    }
                }
            }
        }
        for(int i=1;i<=2*n;i++){
            System.out.print(ans[i]+" ");
        }
    }
    
   
    
}


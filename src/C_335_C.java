
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
public class C_335_C {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int[] a=new int[n+1];
        for(int i=1;i<=n;i++){
            a[i]=in.nextInt();
        }
        int[] p=new int[n+1];
        for(int i=1;i<=n;i++){
            p[a[i]]=i;
        }
        
        int[] ans=new int[n+1];
        
        for(int i=1;i<=n;i++){
            ans[a[i]]=ans[a[i]-1]+1;
        }
        
        int max=0;
        //System.out.println(Arrays.toString(ans));
        for(int i=1;i<=n;i++){
            if(ans[i]>max){
                max=ans[i];
            }
        }
        System.out.println(n-max);
    }
}

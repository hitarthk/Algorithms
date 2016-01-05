
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
public class ICPC3 {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int[] a=new int[n];
        for(int i=0;i<n;i++){
            a[i]=in.nextInt();
        }
        int[] f=new int[100001];
        for(int i=0;i<n;i++){
            int sum=0;
            for(int l=i;l>=0;l--){
                sum+=a[l];
                f[sum]++;
                int p=i+1;
                int q=i+1;
                while(q<n){
                    int tsum=0;
                    while(q<n && tsum<sum){
                        tsum+=a[q];
                        q++;
                    }
                    if(tsum==sum){
                        f[sum]++;
                    }
                }
            }
        }
    }
}

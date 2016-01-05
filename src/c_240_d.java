
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
public class c_240_d {
    public static void main(String[] args) {
        long mod=(long)1e9+7;
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int k=in.nextInt();
        long[] f=new long[n+1];
        Arrays.fill(f, 1);
        for(int l=2;l<=k;l++){
            long[] temp=new long[n+1];
            for(int i=1;i<=n;i++){
                for(int j=i;j<=n;j+=i){
                    temp[j]=(temp[j]+f[i])%mod;
                }
            }
            f=Arrays.copyOf(temp, n+1);
        }
        long ans=0;
        for(int i=1;i<=n;i++){
            ans = (ans+f[i])%mod;
        }
        System.out.println(ans);
    }
}

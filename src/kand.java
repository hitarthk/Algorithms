
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
public class kand {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int m=in.nextInt();
        long[][] a=new long[n+2][m+2];
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                a[i][j]=in.nextLong();
            }
        }
        long mod=(long)1e9+7;
        long[][] dp=new long[n+2][m+2];
        dp[0][0]=1l;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){        
                dp[i][j]=((dp[i-1][j]+dp[i][j-1]+dp[i-1][j-1])*a[i][j])%mod;
            }
        }
        System.out.println(dp[n][m]);
    }
}

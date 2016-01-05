
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
public class ExpectedMatches {
    public static void main(String[] args) throws IOException {
        BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(in.readLine());
        while(t-->0){
            String input[]=in.readLine().split(" ");
            int n=Integer.parseInt(input[0]);
            int m=Integer.parseInt(input[1]);
            double[][] p=new double[n+2][m+2];
            for(int i=1;i<=n;i++){
                input=in.readLine().split(" ");
                for(int j=1;j<=m;j++){
                    p[i][j]=Double.parseDouble(input[j-1]);
                }
            }
            //System.out.println(Arrays.deepToString(p));
            double[][] dp=new double[n+2][m+2];
            dp[n][m]=1;
            for(int i=n;i>=1;i--){
                for(int j=m;j>=1;j--){    
                    dp[i][j]=p[i][j]*dp[i][j+1]+(1-p[i][j])*dp[i+1][j]+1;
                }
            }
            //System.out.println(Arrays.deepToString(dp));
            System.out.printf("%.6f\n",dp[1][1]);
        }
    }
}

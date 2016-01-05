
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
 class vault {
    public static void main(String[] args) {
         Scanner in=new Scanner(System.in);
         String w=" "+in.nextLine();
         String s=" "+in.nextLine();
         long[][] dp=new long[s.length()][w.length()];
         int lenw=w.length()-1;
         int lens=s.length()-1;
        
         
         for(int i=1;i<=lens;i++){
             for(int j=1;j<=lenw;j++){
                 dp[i][j]=dp[i-1][j];
                 if(w.charAt(j)==s.charAt(i)){
                     if(j!=1)
                     dp[i][j]+=dp[i-1][j-1];
                     else
                         dp[i][j]++;
                 }
             }
         }
         System.out.println(dp[lens][lenw]);
     }
}

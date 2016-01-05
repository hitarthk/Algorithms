
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
public class C_233_B {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        in.nextLine();
        String s=in.nextLine();
        long ans=0;
        long[] dp=new long[51];
        dp[1]=1;
        for(int i=2;i<=50;i++){
            dp[i]=dp[i-1]+1+dp[i-1];
        }
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='B'){
                ans+=1+dp[i];
            }
        }
        System.out.println(ans);
    }
    
    static long pow(long b,long e){
        long ans=1;
        for(int i=1;i<=e;i++){
            ans*=b;
        }
        return ans;
    }
    
}

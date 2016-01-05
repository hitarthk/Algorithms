
import java.math.BigInteger;
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
public class SK1 {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        String s=in.next();
        int n=s.length();
        
        s=" "+s;
        //System.out.println(s);
        int k=in.nextInt();
        StringBuilder ans=new StringBuilder();
        int i=1;
        int j=1;
        int maxI=0;
        int max=-1;
        while(k>0){
            max=s.charAt(i)-48;
            maxI=i;
            while(j-i+1<k+1 && j<=n){
                
                j++;
                
            }
        }
//        BigInteger[][] dp=new BigInteger[2][n-k+1];
//        for(int i=0;i<2;i++){
//            for(int j=0;j<=n-k;j++){
//                dp[i][j]=new BigInteger("0");
//            }
//        }
//        for(int i=1;i<=n;i++){
//            for(int j=1;j<=n-k && j<=i;j++){
//                if(j==1){
//                    dp[1][1]=new BigInteger(s.charAt(i)+"");
//                }
//                
//                BigInteger temp=dp[0][j-1].multiply(BigInteger.TEN).add(new BigInteger(s.charAt(i)+""));
//                BigInteger temp2=dp[0][j];
//                if(temp.toString().compareTo(temp2.toString())>=0){
//                    dp[1][j]=temp;
//                }
//                else{
//                    dp[1][j]=temp2;
//                }
//                
//                //System.out.println(i+" "+j+" "+dp[i][j]+" "+s.charAt(i));
//            }
//            dp[0]=Arrays.copyOf(dp[1], dp[1].length);
//        }
//        System.out.println(dp[1][n-k]);
    }
    
    
    
}

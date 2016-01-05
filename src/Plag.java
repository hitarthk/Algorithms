
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
public class Plag {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        String a=in.nextLine();
        String b=in.nextLine();
        
//        String A="";
//        for(int i=0;i<a.length();i++){
//            if(a.charAt(i)!=' ')
//                A+=a.charAt(i);
//        }
//        String B="";
//        for(int i=0;i<b.length();i++){
//            if(b.charAt(i)!=' '){
//                B+=b.charAt(i);
//            }
//        }
//        a=A;
//        b=B;
        int lena=a.length();
        int lenb=b.length();
        a=" "+a;
        b=" "+b;
        //System.out.println(a);
        //System.out.println(b);
        
        long[][] dp=new long[lena+1][lenb+1];
        for(int i=1;i<=lena;i++){
            dp[i][0]=i;
        }
        for(int i=1;i<=lenb;i++){
            dp[0][i]=i;
        }
        for(int i=1;i<=lena;i++){
            for(int j=1;j<=lenb;j++){
                if(a.charAt(i)==b.charAt(j))
                {
                    dp[i][j]=dp[i-1][j-1];
                }
                else{
                    dp[i][j]=Math.min(dp[i][j-1]+1, dp[i-1][j]+1);
                    dp[i][j]=Math.min(dp[i][j], dp[i-1][j-1]+2);
                }
                //System.out.println(i+" "+j+" "+dp[i][j]);
            }
        }
        System.out.println(dp[lena][lenb]);
    }
}

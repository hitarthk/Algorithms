/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_C;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author hitarthk
 */
public class C_232C {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        long mod=1000000007;
        
        int[] a=new int[n];
        HashMap<Integer,Integer> h=new HashMap<>();
        for(int i=0;i<n;i++){
            a[i]=in.nextInt();
        }
        long max=1;
        for(int i=0;i<n;i++){
            int x=a[i];
            int p=2;
            for(;p*p<=x;p++){
                while(x%p==0){
                    int ans=0;
                    if(h.get(p)!=null)
                        ans=h.get(p);
                    ans++;
                    if(ans>max)
                        max=ans;
                    h.put(p, ans);
                    x=x/p;
                }
                //p++;
            }
            if(x>1){
                Integer ans=h.get(x);
                if(ans==null){
                    h.put(x, 1);
                }
                else{
                    h.put(x, ans+1);
                }
            }
        }
//        long[][] c=new long[(int)max+501][n+2];
//        c[0][0]=1;
//        for(int i=1;i<=max+n;i++){
//            c[i][0]=1;
//            for(int j=1;j<=n;j++){
//                c[i][j] = (c[i-1][j] + c[i-1][j-1])%mod;
//            }
//        }
        //System.out.println(c[2][0]);
        long fans=1;
        long num=1;
        long den=1;
        Set<Map.Entry<Integer,Integer>> entries= h.entrySet();
        for(Map.Entry e:entries){
            //System.out.println(e.getKey()+" "+e.getValue());
            //fans = (fans*c[(Integer)e.getValue()+n-1][n-1])%mod;
            int x=(int) e.getValue();
            
            for(int i=1;i<=n-1;i++){
                num = (num*(x+n-i))%mod;
                den = (den*i)%mod;
            }
             
        }
        //System.out.println(num+" "+den);
        fans = (num*BigInteger.valueOf(den).modInverse(BigInteger.valueOf(mod)).longValue())%mod;
        System.out.println(fans);
    }
}

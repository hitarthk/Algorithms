/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

import java.util.Scanner;

/**
 *
 * @author hitarthk
 */
public class Sieve {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int max=1000010;
        boolean[] b=new boolean[max+1];
        for(int i=2;i*i<=max;i++){
            if(!b[i]){
                for(int j=0;i*(i+j)<=max;j++){
                    b[i*(i+j)]=true;
                }
            }
        }
        
        int[] primes=new int[max+1];
        int numprimes = 0;
        
        for(int i=2;i<=max;i++){
            if(!b[i]){
                primes[numprimes++]=i;
            }
        }
        
        
        int t=in.nextInt();
        while(t>0){
            t--;
            int M=in.nextInt();
            int N=in.nextInt();
            boolean[] isprime = new boolean[200001];
            for (int j = 0; j < 200001; j++)
            {
                        isprime[j] = true;
            }

            for (int i = 0; i < numprimes; i++) 
            {
                int p = primes[i];
                int start;

                if (p >= M) start = p*2;
                else start = M + ((p - M % p)%p);

                for (int j = start; j <= N; j += p) 
                {
                       isprime[j - M] = false;
                }
            }
            int ans=0;
            //System.out.println("Here");
            for(int i=M;i<=N;i++){
                //System.out.println(i+" "+h.contains(i));
                if(isprime[i-M]){
                    ans++;
                    //System.out.println(i);
                }
            }
            //out.println(ans);

        }
        //out.close();
        
        
    }
}

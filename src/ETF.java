
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
public class ETF {
    public static void main(String[] args) {
        int max=1000010;
        int[] b=new int[max+1];
        for(int i=2;i*i<=max;i++){
            if(b[i]==0){
                for(int j=0;i*(i+j)<=max;j++){
                    b[i*(i+j)]=i;
                }
            }
        }
        
        int[] primes=new int[max+1];
        int numprimes = 0;
        
        for(int i=2;i<=max;i++){
            if(b[i]==0){
                primes[numprimes++]=i;
            }
        }
        
        Scanner in=new Scanner(System.in);
        int t=in.nextInt();
        while(t-->0){
            int M=in.nextInt();
            int N=in.nextInt();
            
        }
        
    }
}

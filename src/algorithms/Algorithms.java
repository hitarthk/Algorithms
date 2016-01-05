/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

/**
 *
 * @author hitarthk
 */
public class Algorithms {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println(isPrime(1922597));
    }
    
    public static long f1(int n){
        if(n==0){
            return 0;
        }
        if(n==1){
            return 1;
        }
        return f1(n-1)+f1(n-2);
    }
    
    public static long f2(int n){
        
        if(n==0)
            return 0;
        if(n==1)
            return 1;
        int fn=1;
        int b=0;
        for(int i=2;i<=n;i++){
            int temp=fn;
            fn=b+fn;
            b=temp;
        }
        return fn;
    }
    public static boolean isPrime(long a){
        int i=2;
        while(i*i<=a){
            if(a%i==0)
                return false;
            i++;
        }
        return true;
    }
}

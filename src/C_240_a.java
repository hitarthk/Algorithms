
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
public class C_240_a {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        long a=in.nextLong();
        long b=in.nextLong();
        long[] x;
        x = new long[n];
        for(int i=0;i<n;i++){
            x[i]=in.nextLong();
        }
        for(int i=0;i<n;i++){
            if(x[i]*a<b){
                System.out.print(x[i]+" ");
            }
            else{
                long l=0;
                long r=x[i];
                long am=(x[i]*a)/b;
                while(r-l>1){
                    long m=(l+r)/2;
                    if((m*a/b)<am){
                        l=m;
                    }
                    else{
                        r=m;
                    }
                }
                System.out.print((x[i]-r)+" ");
            }
        }
    }  
}

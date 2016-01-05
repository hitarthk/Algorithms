
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
public class Pmean {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        long[] a=new long[n+2];
        for(int i=1;i<=n;i++){
            a[i]=in.nextLong();
        }
        long[] pre=new long[n+2];
        for(int i=1;i<=n;i++){
            pre[i]=pre[i-1]+a[i];
        }
        long x=0;
        for(int i=1;i<=n;i++){
            x+=i*a[i];
        }
        long max=x;
        for(int i=1;i<=n;i++){
            x-=pre[n];
            x+=n*a[i];
            //System.out.println(x);
            if(x>max){
                max=x;
            }
        }
        System.out.println(max);
    }
    
}

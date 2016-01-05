
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
public class C_324_B {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        if(n==1){
            System.out.println("20");
            System.exit(0);
        }
        long m=(long)1e9+7;
        long ans=0;
        long pans=20;
        for(int i=2;i<=n;i++){
            ans = ((27*pans + (20*pow(3,3*(i-1),m))%m -(20*pans)%m)%m + m)%m;
            pans=ans;
        }
        System.out.println(pans);
    }
    
    static long pow(long b,long e,long m){
        if(e==0)
            return 1;
        else{
            long ans=pow(b,e/2,m);
            long fans=(ans*ans)%m;
            if(e%2==0){
                return fans;
            }
            else{
                return (fans*b)%m;
            }
        }
    }
    
}

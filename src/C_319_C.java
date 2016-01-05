
import java.io.PrintWriter;
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
public class C_319_C {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        if(n==1){
            System.out.println(0);
            System.exit(0);
        }
                
        PrintWriter out=new PrintWriter(System.out);
        int[] p=new int[1101];
        int size=0;
        for(int i=2;i<=1100;i++){
            if(isPrime(i))
                p[size++]=i;
        }
        StringBuilder ans=new StringBuilder();
        int nans=0;
        for(int i=0;i<=size && p[i]<=n;i++){
            int curr=p[i];
            while(curr<=n){
                ans.append(curr+" ");
                nans++;
                curr*=p[i];
            }
        }
        out.println(nans);
        out.println(ans);
        out.close();
    }
    
    public static boolean isPrime(int n){
        int i=2;
        while(i*i<=n){
            if(n%i==0)
                return false;
            i++;
        }
        return true;
    }
}


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
public class c_275_c {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int k=in.nextInt();
        int[] a=new int[n+1];
        for(int i=1;i<=n;i++){
            a[i]=i;
        }
        if(k==1){
            print(a,n);
            return;
        }
        else{
            int p=n;
            int q=n-k+1;
            boolean t=true;
            for(int i=n-k+1;i<=n;i++){
                if(t){
                    a[i]=p;
                    p--;
                }
                else{
                    a[i]=q;
                    q++;
                }
                t=!t;
            }
            print(a,n);
        }
    }
    
    static void print(int[] a,int n){
        for(int i=1;i<=n;i++){
            System.out.print(a[i]+" ");
        }
    }
    
}

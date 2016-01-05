
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
public class c_240_c {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int k=in.nextInt();
        if(n==1){
            if(k==0){
                System.out.println("1");
            }
            else{
                System.out.println("-1");
            }
            System.exit(0);
        }
        int rem=n%2;
        n=n-rem;
        StringBuilder s=new StringBuilder();
        int m=n/2;
        int i=1000000000;
        int[] ans=new int[n+1+rem];
        int f=1;
        while(m>1){
            ans[f]=i;
            ans[f+1]=i-1;
            k--;
            m--;
            i-=2;
            f+=2;
        }
        if(k<=0 && n+rem>1){
            System.out.println(-1);
        }
        else{
            ans[f]=k;
            ans[f+1]=2*k;
            if(rem>0)
                ans[f+2]=i;
            for(int j=1;j<=n+rem;j++){
                System.out.print(ans[j]+" ");
            }
        }
        
    }
}

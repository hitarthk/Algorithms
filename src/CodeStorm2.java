
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
public class CodeStorm2 {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int t=in.nextInt();
        while(t-->0){
            int n=in.nextInt();
            int[] a=new int[n+2];
            for(int i=1;i<=n;i++){
                a[i]=in.nextInt();
                
            }
            int ans=0;
            for(int i=1;i<=n;){
                int count=0;
                int j;
                for( j=i;j<=n;j++){
                    if(a[j]==1){
                        break;
                    }
                    else{
                        count++;
                    }
                }
                ans+=(count/2);
                i=j+1;
            }
            System.out.println(ans);
        }
    }
}

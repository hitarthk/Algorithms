
import java.util.Arrays;
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
public class C_191_A {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int[] a=new int[n+2];
        boolean check=false;
        for (int i = 1; i <= n; i++) {
            a[i]=in.nextInt();
            if(a[i]==0){
                check=true;
                //a[i]=a[i];
            }
            
        }
        if(!check){
            System.out.println(n-1);
            System.exit(0);
        }
        int[] dpl=new int[n+2];
        int[] dpr=new int[n+2];
        int[] pl=new int[n+2];
        int[] pr=new int[n+2];
        for(int i=1;i<=n;i++){
            if(a[i]==0){
            if(dpl[i-1]+1>=0){
                pl[i]=pl[i-1]+1;
                dpl[i]=dpl[i-1]+1;
            }
            else{
                dpl[i]=0;
                pl[i]=0;
            }
            }
            else{
                if(dpl[i-1]-1>=0){
                pl[i]=pl[i-1]+1;
                dpl[i]=dpl[i-1]-1;
            }
            else{
                dpl[i]=0;
                pl[i]=0;
            }
            }
        }
        
        for(int i=n;i>=1;i--){
            
            if(a[i]==0){
            if(dpr[i+1]+1>=0){
                pr[i]=pr[i+1]+1;
                dpr[i]=dpr[i+1]+1;
            }
            else{
                dpr[i]=0;
                pr[i]=0;
            }
            }
            else{
                if(dpr[i+1]-1>=0){
                pr[i]=pr[i+1]+1;
                dpr[i]=dpr[i+1]-1;
            }
            else{
                dpr[i]=0;
                pr[i]=0;
            }
            }
        }
        System.out.println(Arrays.toString(dpl));
        System.out.println(Arrays.toString(pl));
        int[] sum=new int[n+2];
        for(int i=1;i<=n+1;i++){
            sum[i]=sum[i-1]+a[i];
        }
        //System.out.println(Arrays.toString(sum));
        int max=Integer.MIN_VALUE;
        for(int i=1;i<=n;i++){
            if(a[i]==0){
            int temp=dpl[i]+dpr[i]-1+sum[i-pl[i]]-sum[0]-sum[i+pr[i]-1]+sum[n];
            if(temp>max)
                max=temp;
            }
            else{
                
            }
        }
        System.out.println(max);
        
    }
            
}


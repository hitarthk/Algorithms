
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
public class CodeStorm3 {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int[] a=new int[n];
        for(int i=0;i<n;i++){
            a[i]=in.nextInt();
        }
        Arrays.sort(a);
        //System.out.println(Arrays.toString(a));
        long ac=0;
        long ob=0;
        long ri=0;
        for(int i=0;i<n;i++){
            int k=i+2;
            int l=i+2;
            int m=i+2;
            for(int j=i+1;j<n;j++){
                while(k<n && a[k]<a[i]+a[j]){
                    k++;
                }
                
                while(l<k && a[i]*a[i]+a[j]*a[j]>a[l]*a[l]){
                    l++;
                }
                while(m<k && a[i]*a[i]+a[j]*a[j]>=a[m]*a[m]){
                    m++;
                }
                //System.out.println(i+" "+j+" "+l+" "+m+" "+k);
                ac+=l-j-1;
                ri+=m-l;
                ob+=k-m;
                //System.out.println(ac+" "+ri+" "+ob);
            }
        }
        System.out.println(ac+" "+ri+" "+ob);
    }
}

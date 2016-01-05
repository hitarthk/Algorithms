
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
public class C_332_B {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int m=in.nextInt();
        int[] freq=new int[n+1];
        int[] f=new int[n+1];
        for(int i=1;i<=n;i++){
            f[i]=in.nextInt();
            freq[f[i]]++;
        }
        int[] b=new int[m+1];
        for(int i=1;i<=m;i++){
            b[i]=in.nextInt();
            if(freq[b[i]]==0){
                System.out.println("Impossible");
                return;
            }
        }
        int[] a=new int[m+1];
        int[] pos=new int[n+1];
        int[] posfreq=new int[n+1];
        for(int i=1;i<=n;i++){
            if(pos[f[i]]==0)
                pos[f[i]]=i;
            posfreq[f[i]]++;
        }
        //System.out.println(Arrays.toString(pos));
        for(int i=1;i<=m;i++){
            if(posfreq[b[i]]>1){
                System.out.println("Ambiguity");
                return;
            }
            else{
                a[i]=pos[b[i]];
            }
        }
        System.out.println("Possible");
        for(int i=1;i<=m;i++)
            System.out.print(a[i]+" ");
    }
}

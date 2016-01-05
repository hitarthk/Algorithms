
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
public class HCJ1 {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int[] a=new int[n];
        for(int i=0;i<n;i++){
            a[i]=in.nextInt();
        }
        Arrays.sort(a);
        int t=0;
        for(int i=0;i<n;i++){
            t=Math.max(t+1, a[i]);
        }
        System.out.println(t);
    } 
}

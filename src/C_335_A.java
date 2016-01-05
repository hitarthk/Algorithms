
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
public class C_335_A {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int[] a=new int[3];
        int[] b=new int[3];
        for(int i=0;i<3;i++){
            a[i]=in.nextInt();
        }
        for(int i=0;i<3;i++){
            b[i]+=in.nextInt();
        }
        int[] extra=new int[3];
        int req=0;
        for(int i=0;i<3;i++){
            if(a[i]-b[i]<0){
                req+=b[i]-a[i];
            }
            else{
                extra[i]+=a[i]-b[i];
            }
        }
        if(req<=extra[0]/2+extra[1]/2+extra[2]/2){
            System.out.println("Yes");
        }
        else{
            System.out.println("No");
        }
    }
}

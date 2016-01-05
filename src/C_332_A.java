
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
public class C_332_A {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int[] d=new int[3];
        d[0]=in.nextInt();
        d[1]=in.nextInt();
        d[2]=in.nextInt();
        int min=Integer.MAX_VALUE;
        if(d[0]+d[0]+d[1]+d[1]<min){
            min=d[0]+d[0]+d[1]+d[1];
        }
        if(d[0]+d[2]+d[1]<min){
            min=d[0]+d[2]+d[1];
        }
        if(d[0]+d[2]+d[2]+d[0]<min){
            min=d[0]+d[2]+d[2]+d[0];
        }
        if(d[1]+d[2]+d[2]+d[1]<min){
            min=d[1]+d[2]+d[2]+d[1];
        }
        System.out.println(min);
    }
}

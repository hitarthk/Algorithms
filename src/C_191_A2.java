
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
public class C_191_A2 {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int total=0;
        int max=Integer.MIN_VALUE;
        int curmax=0;
        for(int i=0;i<n;i++){
            int x=in.nextInt();
            if(x==0){
                curmax++;
            }
            else{
                curmax--;
                if(curmax<0){
                    curmax=0;
                }
                total++;
            }
            if(max<curmax)
                max=curmax;
        }
        if(max==0){
            System.out.println(n-1);
        }
        else{
            System.out.println(total+max);
        }
    }
}

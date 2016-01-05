
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
public class Trouble {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int k=in.nextInt();
        int[] ans=new int[k+1];
        boolean[] b=new boolean[k+1];
        b[0]=true;
        int ind=0;
        for(int i=1;i<=k;i++){
            int x=in.nextInt();
            while(x>0){
                
                ind=(ind+1)%(k+1);
                if(!b[ind]){
                    x--;
                }
            }
            b[ind]=true;
            System.out.println(ind);
        }
    }
}

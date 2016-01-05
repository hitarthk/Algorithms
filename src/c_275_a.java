
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
public class c_275_a {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        long l=in.nextLong();
        long r=in.nextLong();
        if(r-l+1<3){
            System.out.println(-1);
            return;
        }
        else{
            long start=l+(l%2);
            long sec=start+1;
            long third=sec+1;
            if(third<=r){
                System.out.println(start+" "+sec+" "+third);
            }
            else{
                System.out.println(-1);
            }
        }
    }
    
    
    
}

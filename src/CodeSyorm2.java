
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
public class CodeSyorm2 {
    
    public static void main(String[] args) throws Exception
    {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        long sum=1;
        for(int i=2;i<=t;i++){
            if(i%2==0){
                sum+=(i+2)/2;
            }
            else{
                sum+=(i+1)/2;
            }
        }
        System.out.println(sum);
        
    }
}

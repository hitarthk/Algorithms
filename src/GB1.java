
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
public class GB1 {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        String[] s=in.nextLine().split(" ");
        int x=Integer.parseInt(s[0]);
        if(s[2].equals("month")){
            if(x==30){
                System.out.println(11);
            }
            else if(x==31){
                System.out.println(7);
            }
            else{
                System.out.println(12);
            }
        }
        else{
            
            if(x==1 || x==2 || x==3 || x==7){
                System.out.println(52);
            }
            else if(x==4){
                System.out.println(52);
            }
            
            else if(x==6 || x==5){
                System.out.println(53);
            }
            else{
                System.out.println(51);
            }
        }
    }
}

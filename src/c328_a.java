
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
public class c328_a {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        char[][] b=new char[8][8];
        for(int i=0;i<8;i++){
            b[i]=in.nextLine().toCharArray();
        }
        boolean[] f=new boolean[8];
        int wans=0;
        //System.out.println(Arrays.deepToString(b));
        A:for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(b[i][j]=='W' && !f[j]){
                    wans=i;
                    //System.out.println("Here "+wans);
                    break A;
                }
                else if(b[i][j]=='B'){
                    f[j]=true;
                }
           }
        }
        int bans=7;
        f=new boolean[8];
        B:for(int i=7;i>=0;i--){
            for(int j=7;j>=0;j--){
                if(b[i][j]=='B' && !f[j]){
                    bans=i;
                    break B;
                }
                else if(b[i][j]=='W'){
                    f[j]=true;
                }
            }
        }
        //System.out.println(wans+" "+bans);
        if(wans <= (7-bans)){
            System.out.println("A");
        }
        else{
            System.out.println("B");
        }
    }
}


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
public class C_233_D {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int m=in.nextInt();
        boolean[][] b=new boolean[n+2][n+2];
        for(int i=1;i<=m;i++){
            b[in.nextInt()][in.nextInt()]=true;
        }
        
    }
}

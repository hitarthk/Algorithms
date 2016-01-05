
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
public class C_335_B {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int x=in.nextInt();
        int y=in.nextInt();
        boolean[][] f=new boolean[x+2][y+2];
        int x0=in.nextInt();
        int y0=in.nextInt();
        int nextx=x0;
        int nexty=y0;
        int count=0;
        in.nextLine();
        String s=in.nextLine();
        for(int i=0;i<s.length();i++){
            if(!f[nextx][nexty]){
                System.out.print("1 ");
                f[nextx][nexty]=true;
                count++;
            }
            else{
                System.out.print("0 ");
            }
            if(s.charAt(i)=='D'){
                nextx=Math.min(x, nextx+1);
            }
            if(s.charAt(i)=='U'){
                nextx=Math.max(1, nextx-1);
            }
            if(s.charAt(i)=='L'){
                nexty=Math.max(1, nexty-1);
            }
            if(s.charAt(i)=='R'){
                nexty=Math.min(y, nexty+1);
            }
        }
        System.out.println(x*y-count);
    }
}

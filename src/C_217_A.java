
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
//import sun.misc.Queue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hitarthk
 */
public class C_217_A {
    public static void main(String[] args) throws InterruptedException {
        Scanner in=new Scanner(System.in);
        int r1=in.nextInt();
        int c1=in.nextInt();
        int r2=in.nextInt();
        int c2=in.nextInt();
        int ra=0;
        if(Math.abs(r1-r2)>0){
            ra++;
        }
        if(Math.abs(c1-c2)>0){
            ra++;
        }
        System.out.println(ra);
        int color1=(r1%2+c1%2)%2;
        int color2=(r2%2+c2%2)%2;
        if(color1==color2){
            if(Math.abs(r1-r2)==Math.abs(c1-c2)){
                System.out.println(1);
            }
            else{
                System.out.println(2);
            }
        }
        else{
            System.out.println(0);
        }
        Queue<Integer> qr=new LinkedList<>();
        Queue<Integer> qc=new LinkedList<>();
        boolean[][] v=new boolean[9][9];
        int[][] d=new int[9][9];
        qr.add(r1);
        qc.add(c1);
        v[r1][c1]=true;
        while(!v[r2][c2]){
            Integer r=qr.poll();
            Integer c=qc.poll();
            //System.out.println(r+" "+c);
            for(int i=r-1;i<=r+1;i++){
                for(int j=c-1;j<=c+1;j++){
                    if(i<=8 && i>=1 && j<=8 && j>=1 && !v[i][j] ){
                        d[i][j]=d[r][c]+1;
                        v[i][j]=true;
                        qr.add(i);
                        qc.add(j);
                    }
                }
            }
        }
        System.out.println(d[r2][c2]);
    }
    
    static class Node{
        int r;
        int c;
        int d;
    }
    
}

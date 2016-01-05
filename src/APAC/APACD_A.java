/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package APAC;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author hitarthk
 */
public class APACD_A {
    static char[][] a;
    static boolean[][] v;
    static int count=0;
    static int r;
    static int c;
    
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int T=in.nextInt();
        for(int t=1;t<=T;t++){
            r=in.nextInt();
            c=in.nextInt();
            a=new char[r+2][c+2];
            in.nextLine();
            for(int i=1;i<=r;i++){
                String s=" "+in.nextLine()+" ";
                a[i]=s.toCharArray();
                a[i][0]='0';
                a[i][c+1]='0';
            }
            for(int j=0;j<=c+1;j++){
                a[0][j]='0';
                a[r+1][j]='0';
            }
            
            //System.out.println(Arrays.deepToString(a));
            dfs();
            int n=in.nextInt();
            System.out.println("Case #"+t+":");
            while(n-->0){
                String q=in.next();
                if(q.equals("Q")){
                    count=0;
                    dfs();
                    System.out.println(count);
                }
                else{
                    int x=in.nextInt()+1;
                    int y=in.nextInt()+1;
                    int z=in.nextInt();
                    if(z==0){
                        a[x][y]='0';
                    }
                    else{
                        a[x][y]='1';
                    }
                    
                }
            }
        }
    }
    
    static void dfs(){
        v=new boolean[r+2][c+2];
        count=0;
        //System.out.println(Arrays.deepToString(a));
        for(int i=1;i<=r;i++){
            for(int j=1;j<=c;j++){
                if(!v[i][j] && a[i][j]=='1'){
                    count++;
                    visit(i, j);
                }
            }
        }
    }
    
    static void visit(int i,int j){
        v[i][j]=true;
        if(!v[i-1][j] && a[i-1][j]=='1'){
            visit(i-1,j);
        }
        if(!v[i+1][j] && a[i+1][j]=='1'){
            visit(i+1,j);
        }
        if(!v[i][j-1] && a[i][j-1]=='1'){
            visit(i,j-1);
        }
        if(!v[i][j+1] && a[i][j+1]=='1'){
            visit(i,j+1);
        }
    }
    
    
}

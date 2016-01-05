
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hitarthk
 */
public class DOM1 {
    static int[] v;
    static ArrayList[] adj;
    static int n;
    
    public static void main(String[] args) throws IOException {
        BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
        String str=in.readLine();
        int t=Integer.parseInt(str);
        while(t-->0){
            String[] s=in.readLine().split(" ");
            n=Integer.parseInt(s[0]);
            int m=Integer.parseInt(s[1]);
            v=new int[n+1];
//            adj=new ArrayList[n+1];
//            for(int i=n;i>=2;i--){
//                adj[i]=new ArrayList<Integer>();
//                adj[i].add(i-1);
//            }
            for(int i=1;i<=n;i++){
                v[i]=n-i;
            }
            for(int i=0;i<m;i++){
                s=in.readLine().split(" ");
                int x=Integer.parseInt(s[0]);
                int y=Integer.parseInt(s[1]);
                int min=Math.min(x, y);
                int max=Math.max(x, y);
                v[max]++;
                v[min]--;
            }
            boolean check=false;
            int ans=0;
            for(int i=1;i<=n;i++){
                if(v[i]==0){
                    check=true;
                    ans=i;
                    break;
                }
            }
            if(check){
                System.out.println(2+" "+ans);
            }
            else{
                System.out.println(1);
            }
        }
    }
}

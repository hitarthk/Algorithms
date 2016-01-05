
import java.util.ArrayList;
import java.util.Collections;
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
public class HCJ2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int[] p = new int[n + 1];
            boolean check = true;
            for (int i = 1; i <= n; i++) {
                p[in.nextInt()] = i;

            }
            boolean[] v = new boolean[n + 1];
            int ans = 0;

            for (int i = 1; i <= n; i++) {
                int len = 0;
                int cur = i;
                ArrayList<Integer> l = new ArrayList<>();
                while (!v[cur]) {
                    v[cur] = true;
                    cur = p[cur];
                    len++;
                    l.add(cur);
                }
                //System.out.println(i+" "+len);
                if (len > 3) {
                    check = false;
                    break;
                } else {
                    if (len > 0) {
                        Collections.sort(l);
                        for(int j=1;j<l.size();j++){
                            if(l.get(j)!=l.get(j-1)+1){
                                check=false;
                                break;
                            }
                        }
                        ans += len - 1;
                    }
                }
            }
            if (check) {
                System.out.println(ans);
            } else {
                System.out.println("Too chaotic");
            }
        }
    }
}

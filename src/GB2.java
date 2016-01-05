
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
public class GB2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long a = in.nextLong();
        long b = in.nextLong();
        long ans[] = new long[2];
        String[] s = new String[2];
        s[0] = Long.toBinaryString((a - 1));
        s[1] = Long.toBinaryString(b);
        //System.out.println(Arrays.toString(s));
        int count;

        for (int l = 0; l < 2; l++) {
            if (s[l].length() != 1) {
                int x=s[l].length()-1;
                ans[l]=(x*(x-1))/2 ;
                for(int i=1;i<s[l].length();i++){
                    if(s[l].charAt(i)=='0'){
                        break;
                    }
                    else{
                        ans[l]++;
                    }
                }
                count=0;
                for(int i=0;i<s[l].length();i++){
                    if(s[l].charAt(i)=='0'){
                        count++;
                    }
                }
                if(count==1){
                    ans[l]++;
                }
            }
        }
        System.out.println(ans[1] - ans[0]);
    }
}

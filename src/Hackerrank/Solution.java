/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hackerrank;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author hitarthk
 */
public class Solution {
    static long count=0;
    static long oc=0;
    static int n;
    static HashSet<Stack<Character>> set=new HashSet<Stack<Character>>();
    static Stack<Character> o=new Stack<>();
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        long[] c=new long[11];
        c[1]=1;
        c[0]=1;
        for(int i=2;i<=10;i++){
            for(int j=0;j<=i-1;j++){
                c[i]+=c[j]*c[i-1-j];
            }
        }
        String s=in.nextLine();
        n=s.length();
        
        Stack<Character> s2=new Stack<>();
        //System.out.println(Arrays.toString(c));
        Stack<Character> s1=new Stack<>();
        Stack<Character> s3=new Stack<>();
        for(int i=0;i<s.length();i++){
            s1.add(s.charAt(i));
            
        }
        for(int i=s.length()-1;i>=0;i--){
            o.add(s.charAt(i));
        }
        //System.out.println(s1.toString());
        cnt(s1,s2,s3);
        System.out.println(oc+" "+count);
        
    }
    
    public static void cnt(Stack<Character> s1, Stack<Character> s2,Stack<Character> s3){
        if(s3.size()==n){
            //System.out.println(s3.toString());
            if(!set.contains(s3)){
                count++;
                set.add(s3);
            }
            if(s3.toString().equals(o.toString())){
                oc++;
            }
            return;
        }
        if(!s2.isEmpty()){
            s3.add(s2.pop());
            cnt(s1,s2,s3);
            s2.add(s3.pop());
        }

        if(!s1.isEmpty()){
            s2.add(s1.pop());
            cnt(s1,s2,s3);
            s1.add(s2.pop());
        }
        
                
    }
    
    static boolean eq(Stack<Character> s1,Stack<Character> s2){
        System.out.println(s1.size()+" "+s2.size());
        if(s1.size()!= s2.size()){
            return false;
        }
        else{
            if(s1.size()==0)
                return true;
            Character c1=s1.pop();
            Character c2=s2.pop();
                  
            if(c1.equals(c2)){
                
                boolean ans= eq(s1,s2);
                s1.add(c1);
                s2.add(c2);
                return ans;
            }
            s1.add(c1);
            s2.add(c2);
            return false;
        }
    }
    
}

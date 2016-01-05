/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hackerrank;

/**
 *
 * @author hitarthk
 */
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;


public class Q2 {

	static String ss;
	static long count=0,count1=0;
	static HashSet<String>set=new HashSet<>();
	public static void main(String cd[]){
		Scanner in=new Scanner(System.in);
		PrintWriter out=new PrintWriter(System.out);
		 ss=in.next();
		LinkedList<Character> list=new LinkedList<>();
		 for(int i=0;i<ss.length();i++){
			list.add(ss.charAt(i));
		}
		 fun(list,new Stack<Character>(),new StringBuilder());
		System.out.println(count+" "+count1);
		
		out.close();
	}
	public static void fun(LinkedList<Character> q,Stack<Character> s,StringBuilder ans){
	//	System.out.println(q.size()+" "+s.size());
		if(ans.length()==ss.length()){
			System.out.println(ss+" "+ans);
			if(ss.equals(ans.toString())){
				count++;
			}
			if(!set.contains(ans.toString())) {
				
				count1++;
				set.add(ans.toString());
			}
			return;
		}
		Stack<Character> p=(Stack<Character>)s.clone();
		LinkedList<Character> x=(LinkedList<Character>)q.clone();
		StringBuilder y=new StringBuilder();
		y.append(ans);
		if(!q.isEmpty()){
		char c=q.removeFirst();
		s.add(c);
		fun(q,s,ans);
		}
		if(!p.isEmpty()){
		char d=p.pop();
		y.append(d);
		fun(x,p,y);
		}
		
		
		
	}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_C;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;

/**
 *
 * @author hitarthk
 */
public class C_237 {
    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        int n=in.nextInt();
        int k=in.nextInt();
        Node[] a=new Node[n];
        for(int i=0;i<n;i++){
            int d=in.nextInt();
            a[i]=new Node(i+1, d);
        }
        PrintWriter out=new PrintWriter(System.out);
        Arrays.sort(a);
        ArrayList<Node> curr=new ArrayList<>();
        ArrayList<Node> prev=new ArrayList<>();
        if((n>=2 && a[0].d==0 && a[1].d==0)||a[0].d>0){
            System.out.println(-1);
            return;
        }
        prev.add(a[0]);
        boolean check=true;
        int i=1;
        int m=0;
        int level=1;
        A:while(i<n){
            int j=0;
            if(prev.size()==0){
                check=false;
                break A;
            }
            while(i<n && a[i].d==prev.get(j).d+1){
                prev.get(j).c.add(a[i].id);
                if((level>1 && prev.get(j).c.size()==k)||(level==1 && prev.get(j).c.size()==k+1)){
                    check=false;
                    break A;
                }
                j = (j+1)%prev.size();
                curr.add(a[i]);
                m++;
                i++;
            }
            level++;
            prev=curr;
            curr=new ArrayList<>();
        }
        if(check){
            out.println(m);
            for(i=0;i<n;i++){
                for(int j=0;j<a[i].c.size();j++){
                    out.println(a[i].id+" "+a[i].c.get(j));
                }
            }
        }
        else{
            out.println(-1);
        }
        out.close();
    }
    
    static class Node implements Comparable<Node>{
        int id;
        ArrayList<Integer> c;
        int d;
        public Node(int id, int d){
            this.id=id;
            this.d=d;
            c=new ArrayList<>();
        }
        @Override
        public int compareTo(Node o) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            return this.d-o.d;
        }
    }
    
    
    public static class FasterScanner {

        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;

        public int read() {
            if (numChars == -1) {
                throw new InputMismatchException();
            }
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = System.in.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0) {
                    return -1;
                }
            }
            return buf[curChar++];
        }

        public String nextLine() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isEndOfLine(c));
            return res.toString();
        }

        public String nextString() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        public long nextLong() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public int nextInt() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public int[] nextIntArray(int n) {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = nextInt();
            }
            return arr;
        }

        public long[] nextLongArray(int n) {
            long[] arr = new long[n];
            for (int i = 0; i < n; i++) {
                arr[i] = nextLong();
            }
            return arr;
        }

        private boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        private boolean isEndOfLine(int c) {
            return c == '\n' || c == '\r' || c == -1;
        }
    }

    
}

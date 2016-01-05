
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hitarthk
 */
public class c_291_b {
    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        int n=in.nextInt();
        int x=in.nextInt();
        int y=in.nextInt();
        HashSet<Node> h=new HashSet<>();
        int[][] a=new int[n][2];
        for(int i=0;i<n;i++){
            a[i][0]=in.nextInt()-x;
            a[i][1]=in.nextInt()-y;
        }
        for(int i=0;i<n;i++){
            if(a[i][0]!=0 && a[i][1]!=0){
               
                
                Node cr=new Node(a[i][0],a[i][1]);
                if(!h.contains(cr)){
                    h.add(cr);
                }
                
            }
        }
        int ans=0;
        for(int i=0;i<n;i++){
            if(a[i][0]==0){
                ans++;
                break;
            }
        }
        for(int i=0;i<n;i++){
            if(a[i][1]==0){
                ans++;
                break;  
            }
        }
        //System.out.println(h);
        ans+=h.size();
        System.out.println(ans);
    }
    
    static int gcd(int a, int b) {
        if(a<0){
            a=-a;
        }
        if(b<0){
            b=-b;
        }
        if (a < b) {
            int temp = a;
            a = b;
            b = temp;
        }
        while (b > 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
    
    static class Node {
        int x;
        int y;
        public Node(int x,int y){
            this.x=x;
            this.y=y;
        }
        public String toString(){
            return String.format("%d %d", x,y);
        }
        public boolean equals(Object o){
            Node n=(Node)o;
            return n.x*this.y==n.y*this.x;
        }
        
        public int hashCode(){
            int x=this.x/gcd(this.x,y)+this.y/(gcd(this.x,y));
            return x<0?-x:x;
        }
        
    }
    
    
    static class FasterScanner {

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

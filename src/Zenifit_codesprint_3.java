
import java.io.IOException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
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
public class Zenifit_codesprint_3 {
    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        int n=in.nextInt();
        HashMap<Tuple,Integer> lh=new HashMap<>();
        HashMap<Tuple,Integer> rh=new HashMap<>();
        for(int i=0;i<n;i++){
            String c=in.nextString();
            int size=in.nextInt();
            String color=in.nextString();
            String type=in.nextLine();
            Tuple t=new Tuple(c, size, color);
            if(type.charAt(0)=='L'){
                Integer x=lh.get(t);
                if(x==null){
                    lh.put(t,1);
                }
                else{
                    lh.put(t, x+1);
                }
            }
            else{
                Integer x=rh.get(t);
                if(x==null){
                    rh.put(t,1);
                }
                else{
                    rh.put(t, x+1);
                }
            }
        }
//        System.out.println(lh);
//        System.out.println(rh);
        int ans=0;
        for(Map.Entry e:lh.entrySet()){
            Integer x=(Integer)e.getValue();
            Tuple t=(Tuple)e.getKey();
            Integer y=rh.get(t);
            System.out.println((Tuple)e.getKey()+" "+x+" "+y);
            if(y==null){
                y=0;
            }
            ans+=Math.min(x, y);
        }
        System.out.println(ans);
    }
    
    static class Tuple{
        String c;
        String color;
        int size;
        public Tuple(String c,int size,String color){
            this.c=c;
            this.size=size;
            this.color=color;
        }
        public String toString(){
            return c+" "+size+" "+color;
        }
        
        public int hashCode(){
            return c.hashCode()+size+color.hashCode();
        }
        
        public boolean equals(Object o){
            Tuple t=(Tuple)o;
            return c.equals(t.c)&&size==t.size&&color.equals(t.color);
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

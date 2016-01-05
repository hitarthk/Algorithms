
import java.io.IOException;
import java.io.PrintWriter;
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
public class c_291_c {

    public static void main(String[] args) {
        FasterScanner in = new FasterScanner();
        Trie.TrieNode root=new Trie.TrieNode();
        int n=in.nextInt();
        int m=in.nextInt();
        for(int i=0;i<n;i++){
            String s=in.nextLine();
            Trie.insertString(root, s);
        }
        PrintWriter out=new PrintWriter(System.out);
        //Trie.printSorted(root, "");
        for(int i=0;i<m;i++){
            String s=in.nextLine();
            String ans=Trie.find(root, s, 0, true)?"YES":"NO";
            out.println(ans);
        }
        out.close();;
    }

    static class Trie {

        static class TrieNode {
            TrieNode[] children = new TrieNode[3];
            boolean leaf;
        }
        
        public static void insertString(TrieNode root, String s) {
            TrieNode cur = root;
            for (char ch : s.toCharArray()) {
                if (cur.children[ch-'a'] == null) {
                    cur.children[ch-'a'] = new TrieNode();
                }
                cur = cur.children[ch-'a'];
            }
            cur.leaf = true;
        }
        /*
        3 2
aaa
abc
acd
acd
adc*/
        public static boolean find(TrieNode root,String s,int i,boolean allow){
            //System.out.println(i+" "+allow);
            if(i==s.length() && root.leaf){
                //System.out.println("Here");
                return !allow;
            }
            if(i==s.length()&& !root.leaf){
                return false;
            }
            else{
                boolean ret=false;
                for(int j='a';j<='c';j++){
                    if(root.children[j-'a']!=null){
                        if(allow){
                            if(s.charAt(i)==j){
                                ret|=find(root.children[j-'a'], s, i+1, allow);
                            }
                            else{
                                ret|=find(root.children[j-'a'], s, i+1, !allow);
                            }
                        }
                        else{
                            if(s.charAt(i)==j){
                                ret|=find(root.children[j-'a'], s, i+1, allow);
                            }
                            
                        }
                    }
                }
                return ret;
            }
            
                
        }
        
        
        public static void printSorted(TrieNode node, String s) {
            for (char ch = 0; ch < node.children.length; ch++) {
                TrieNode child = node.children[ch];
                if (child != null) {
                    printSorted(child, s + ch);
                }
            }
            if (node.leaf) {
                System.out.println(s);
            }
        }

	// Usage example
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

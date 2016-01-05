/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hackerrank;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;

/**
 *
 * @author hitarthk
 */
public class CoyoteRoads {

    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        int n=in.nextInt();
        Node[] nodes=new Node[n];
        for(int i=0;i<n;i++){
            long x=in.nextLong();
            long y=in.nextLong();
            nodes[i]=new Node(x,y,i);
        }
        
        Arrays.sort(nodes, new Comparator<Node>() {

            @Override
            public int compare(Node o1, Node o2) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                if(o1.x<o2.x)
                    return -1;
                else if(o1.x>o2.x)
                    return 1;
                else
                    return 0;
            }
        });
        
        for(int i=1;i<n;i++){
            nodes[i].addEdge(nodes[i].id, nodes[i-1].id, Math.abs(nodes[i].x-nodes[i-1].x));
            nodes[i-1].addEdge(nodes[i-1].id, nodes[i].id, Math.abs(nodes[i].x-nodes[i-1].x));
        }
        
        Arrays.sort(nodes, new Comparator<Node>() {

            @Override
            public int compare(Node o1, Node o2) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                if(o1.y<o2.y)
                    return -1;
                else if(o1.x>o2.x)
                    return 1;
                else
                    return 0;
            }
        });
        
        for(int i=1;i<n;i++){
            if(!(nodes[i-1].removeEdge(nodes[i].id, nodes[i-1].id, (Math.abs(nodes[i].y-nodes[i-1].y))))){
                nodes[i].addEdge(nodes[i].id, nodes[i-1].id, Math.abs(nodes[i].y-nodes[i-1].y));
                nodes[i-1].addEdge(nodes[i-1].id, nodes[i].id, Math.abs(nodes[i].y-nodes[i-1].y));
            }
        }
        
        Arrays.sort(nodes, new Comparator<Node>() {

            @Override
            public int compare(Node o1, Node o2) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                if(o1.id<o2.id)
                    return -1;
                else if(o1.x>o2.x)
                    return 1;
                else
                    return 0;
            }
        });
        
        ArrayList<Edge> edges=new ArrayList<>();
        for(int i=0;i<n;i++){
            edges.addAll(nodes[i].edges);
        }
        
        Collections.sort(edges);
        UF set=new UF(n);
        long ans=0;
        boolean[] visited=new boolean[n];
        for(Edge e:edges){
//            if(!(visited[e.vid]&&visited[e.uid])){
//                visited[e.vid]=true;
//                visited[e.uid]=true;
//                
//            }
            if(!set.connected(e.uid, e.vid)){
                ans += e.w;
                set.union(e.uid, e.vid);
            }
        }
        System.out.println(ans);
    }
    
    static class Node{
        long x;
        long y;
        int id;
        ArrayList<Edge> edges=new ArrayList<>();
        public Node(long x,long y,int id){
            this.x=x;
            this.y=y;
            this.id=id;
        }
        
        public void addEdge(int uid,int vid,long w){
            edges.add(new Edge(uid,vid,w));
        }
        
        public boolean removeEdge(int uid,int vid,long w){
            for(Edge e:edges){
                if(((e.uid==uid && e.vid==vid)||(e.vid==uid && e.uid==vid))){
                    if(e.w>w){
                        e.w=w;
                    }
                    return true;
                }
            }
            return false;
        }
        
    }
    
    static class Edge implements Comparable<Edge>{
        public Edge(int uid,int vid,long w){
            this.uid=uid;
            this.vid=vid;
            this.w=w;
        }
        int uid;
        int vid;
        long w;

        @Override
        public int compareTo(Edge o) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            if(this.w<o.w)
                return -1;
            else if(this.w==o.w)
                return 1;
            else
                return 0;
        }
        
    }

    static class UF {

        public int max = 1;
        private int[] size;
        private int[] parent;  // parent[i] = parent of i
        private byte[] rank;   // rank[i] = rank of subtree rooted at i (never more than 31)
        private int count;     // number of components

        public UF(int N) {
            if (N < 0) {
                throw new IllegalArgumentException();
            }
            count = N;
            parent = new int[N];
            rank = new byte[N];
            size = new int[N];
            for (int i = 0; i < N; i++) {
                parent[i] = i;
                rank[i] = 0;
                size[i] = 1;
            }
        }

        public void print(int id) {
            for (int i = 0; i < parent.length; i++) {
                if (parent[i] == parent[id]) {
                    System.out.print(i + " ");
                }
            }
            System.out.println();
        }

        public int find(int p) {
            if (p < 0 || p >= parent.length) {
                return -1;
            }
            while (p != parent[p]) {
                parent[p] = parent[parent[p]];    // path compression by halving
                p = parent[p];
            }
            return p;
        }

        public int count() {
            return count;
        }

        public boolean connected(int p, int q) {
            if (p >= rank.length || q >= rank.length) {
                return false;
            }

            return find(p) == find(q);
        }

        public boolean union(int p, int q) {

            if (p >= rank.length || q >= rank.length) {
                return false;
            }
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) {
                return false;
            }

            // make root of smaller rank point to root of larger rank
            if (rank[rootP] < rank[rootQ]) {
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
                if (size[rootQ] > max) {
                    max = size[rootQ];
                }
            } else if (rank[rootP] > rank[rootQ]) {
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
                if (size[rootP] > max) {
                    max = size[rootP];
                }
            } else {
                parent[rootQ] = rootP;
                rank[rootP]++;
                size[rootP] += size[rootQ];
                if (size[rootP] > max) {
                    max = size[rootP];
                }
            }
            count--;
            return true;
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

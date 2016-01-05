
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
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
public class C_318_BV2 {
    static int n;
    static Vertex[] v;
    static ArrayList<CC> comps=new ArrayList<>();
    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        PrintWriter out=new PrintWriter(System.out);
        n=in.nextInt();
        v=new Vertex[n];
        for(int i=0;i<n;i++){
            v[i]=new Vertex(i);
        }
        int m=in.nextInt();
        
        for(int i=0;i<m;i++){
            int f=in.nextInt()-1;
            int s=in.nextInt()-1;
            v[f].l.add(s);
            v[s].l.add(f);
        }
//        
//        for(int i=0;i<n;i++){
//            Collections.sort(v[i].l);
//        }
        int min=Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(v[i].l.contains(j))
                for(int k=j+1;k<n;k++){
                    if(v[k].l.contains(i) && v[k].l.contains(j)){
                        int temp=v[i].l.size()+v[j].l.size()+v[k].l.size()-6;
                        if(temp<min)
                            min=temp;
                    }
                }
            }
        }
        if(min==Integer.MAX_VALUE){
            out.println(-1);
        }
        else{
            out.println(min);
        }
//        dfs();
//        
//        if(comps.size()==0){
//            out.println(-1);
//        }
//        else{
//            int min=Integer.MAX_VALUE;
//            for(CC component:comps){
//                Collections.sort(component.nodes);
//                int temp= component.nodes.get(0).l.size()+component.nodes.get(1).l.size()+component.nodes.get(2).l.size();
//                temp-=component.nodes.get(0).cont(component.nodes.get(1).id);
//                temp-=component.nodes.get(0).cont(component.nodes.get(2).id);
//                temp-=component.nodes.get(1).cont(component.nodes.get(0).id);
//                temp-=component.nodes.get(1).cont(component.nodes.get(2).id);
//                temp-=component.nodes.get(2).cont(component.nodes.get(0).id);
//                temp-=component.nodes.get(2).cont(component.nodes.get(1).id);
//                if(temp<min){
//                    min=temp;
//                }
//            }
//            out.println(min);
//        }
        
        
        out.close();
    }
    
    static void dfs(){
        for(int i=0;i<n;i++){
            if(!v[i].visited){
                CC component=new CC();
                visit(v[i],component);
                if(component.nodes.size()>2){
                    comps.add(component);
                }
            }
        }
    }
    
    static void visit(Vertex u,CC component){
        component.nodes.add(u);
        u.visited=true;
        for(Integer c:u.l){
            if(!v[c].visited){
                visit(v[c],component);
            }
        }
    }
    
    static class Vertex implements Comparable<Vertex>{
        int id;
        ArrayList<Integer> l;
        boolean visited;
        public Vertex(int id){
            this.id=id;
                visited=false;
            l=new ArrayList<>();
        }
        int cont(int u){
            return l.contains(u)?1:0;
        }
        @Override
        public int compareTo(Vertex o) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            return this.l.size()-o.l.size();
        }
    }
    
    static class CC{
        ArrayList<Vertex> nodes;
        public CC(){
            nodes=new ArrayList<>();
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

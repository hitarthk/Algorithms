
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hitarthk
 */
public class c_329_b {

    public static void main(String[] args) {
        FasterScanner in = new FasterScanner();
        PrintWriter out = new PrintWriter(System.out);
        int n = in.nextInt();
        long x1 = in.nextInt();
        long x2 = in.nextInt();
        Node[] a = new Node[n];
        for (int i = 0; i < n; i++) {
            long m = in.nextLong();
            long c = in.nextLong();
            a[i] = new Node(m, c);
            a[i].id = i;
            a[i].x1=x1;
            a[i].x2=x2;
        }
        Arrays.sort(a, new Comparator<Node>() {

            @Override
            public int compare(Node o1, Node o2) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                long y1 = o1.m * o1.x2 + o1.c;
                long y2 = o2.m * o1.x2 + o2.c;
                if (y1 > y2) {
                    return -1;
                } else if (y1 == y2) {
                    return o1.id - o2.id;
                } else {
                    return 1;
                }
            }

        });

        TreeSet<Node> t = new TreeSet<>(new Comparator<Node>() {

            @Override
            public int compare(Node o1, Node o2) {
                //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                long y1 = o1.m * o1.x1 + o1.c;
                long y2 = o2.m * o1.x1 + o2.c;
                if (y1 > y2) {
                    return -1;
                } else if (y1 < y2) {
                    return 1;
                } else {
                    return o2.id - o1.id;
                }
            }

        });
        for (int i = 0; i < n; i++) {
            t.add(a[i]);
        }
        boolean check = false;
        A:for (int i = 0; i < n - 1; i++) {
            if(t.contains(a[i])){
                t.remove(a[i]);
            }
            Node cur=a[i];
            long ycur1=cur.m*x1+cur.c;
            long ycur2=cur.m*x2+cur.c;
            while(!t.isEmpty()){
                Node first=t.first();
                t.remove(first);
                long yfirst1=first.m*x1+first.c;
                long yfirst2=first.m*x2+first.c;
                if(yfirst1>ycur1 && yfirst2<ycur2){
                    check=true;
                    break A;
                }
                if(yfirst1<ycur1){
                    break;
                }
            }
        }

        if (check) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

    }

    static class Node {

        long m;
        long c;
        int id;
        long x1;
        long x2;
        public Node(long m, long c) {
            this.m = m;
            this.c = c;
        }

        public String toString() {
            return String.format("%d %d %d", id, m, c);
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

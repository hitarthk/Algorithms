

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.TreeSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author hitarthk
 */
public class FloodMonk {
    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        PrintWriter out=new PrintWriter(System.out);
        int n=in.nextInt();
        UF uf=new UF(n);
        int q=in.nextInt();
        while(q>0){
            q--;
            int a=in.nextInt()-1;
            int b=in.nextInt()-1;
            uf.union(a, b);
            out.println(uf.mx()-uf.mn());
        }
        out.close();
    }
    static class UF {

        public int max = 1;
        
        TreeSet<Integer> t;
        HashMap<Integer, Integer> h;
        private int[] size;
        private int[] parent;  // parent[i] = parent of i
        private byte[] rank;   // rank[i] = rank of subtree rooted at i (never more than 31)
        private int count;     // number of components

        public UF(int N) {
            if (N < 0) {
                throw new IllegalArgumentException();
            }
            count = N;
            h=new HashMap<>();
            t=new TreeSet<>();
            parent = new int[N];
            rank = new byte[N];
            size = new int[N];
            for (int i = 0; i < N; i++) {
                parent[i] = i;
                rank[i] = 0;
                size[i] = 1;
                if(t.add(size[i])){
                    h.put(size[i], 1);
                }
                else{
                    h.put(size[i], h.get(size[i])+1);
                }
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
                if(h.get(size[rootP])==1){
                    h.put(size[rootP], 0);
                    t.remove(size[rootP]);
                }
                else{
                    h.put(size[rootP], h.get(size[rootP])-1);
                }
                if(h.get(size[rootQ])==1){
                    h.put(size[rootQ], 0);
                    t.remove(size[rootQ]);
                }
                else{
                    h.put(size[rootQ], h.get(size[rootQ])-1);
                }
                size[rootQ] += size[rootP];
                if(t.add(size[rootQ])){
                    h.put(size[rootQ], 1);
                }
                else{
                    h.put(size[rootQ], h.get(size[rootQ])+1);
                }
                if (size[rootQ] > max) {
                    max = size[rootQ];
                }
            } else if (rank[rootP] > rank[rootQ]) {
                parent[rootQ] = rootP;
                if(h.get(size[rootP])==1){
                    h.put(size[rootP], 0);
                    t.remove(size[rootP]);
                }
                else{
                    h.put(size[rootP], h.get(size[rootP])-1);
                }
                if(h.get(size[rootQ])==1){
                    h.put(size[rootQ], 0);
                    t.remove(size[rootQ]);
                }
                else{
                    h.put(size[rootQ], h.get(size[rootQ])-1);
                }
                size[rootP] += size[rootQ];
                if(t.add(size[rootP])){
                    h.put(size[rootP], 1);
                }
                else{
                    h.put(size[rootP], h.get(size[rootP])+1);
                }
                if (size[rootP] > max) {
                    max = size[rootP];
                }
            } else {
                parent[rootQ] = rootP;
                rank[rootP]++;
                if(h.get(size[rootP])==1){
                    h.put(size[rootP], 0);
                    t.remove(size[rootP]);
                }
                else{
                    h.put(size[rootP], h.get(size[rootP])-1);
                }
                if(h.get(size[rootQ])==1){
                    h.put(size[rootQ], 0);
                    t.remove(size[rootQ]);
                }
                else{
                    h.put(size[rootQ], h.get(size[rootQ])-1);
                }
                size[rootP] += size[rootQ];
                if(t.add(size[rootP])){
                    h.put(size[rootP], 1);
                }
                else{
                    h.put(size[rootP], h.get(size[rootP])+1);
                }
                if (size[rootP] > max) {
                    max = size[rootP];
                }
            }
            count--;
            return true;
        }
        
        public int mx(){
            return max;
        }
        public int mn(){
            return t.first();
        }
    }
    
    static class BinaryHeap {

        /**
         * The number of children each node has *
         */
        private static final int d = 2;
        private int heapSize;
        private int[] heap;

        /**
         * Constructor *
         */
        public BinaryHeap(int capacity) {
            heapSize = 0;
            heap = new int[capacity + 1];
            Arrays.fill(heap, -1);
        }

        /**
         * Function to check if heap is empty *
         */
        public boolean isEmpty() {
            return heapSize == 0;
        }

        /**
         * Check if heap is full *
         */
        public boolean isFull() {
            return heapSize == heap.length;
        }

        /**
         * Clear heap
         */
        public void makeEmpty() {
            heapSize = 0;
        }

        /**
         * Function to get index parent of i *
         */
        private int parent(int i) {
            return (i - 1) / d;
        }

        /**
         * Function to get index of k th child of i *
         */
        private int kthChild(int i, int k) {
            return d * i + k;
        }

        /**
         * Function to insert element
         */
        public void insert(int x) {
            if (isFull()) {
                throw new NoSuchElementException("Overflow Exception");
            }
            /**
             * Percolate up *
             */
            heap[heapSize++] = x;
            heapifyUp(heapSize - 1);
        }

        /**
         * Function to find least element *
         */
        public int findMin() {
            if (isEmpty()) {
                throw new NoSuchElementException("Underflow Exception");
            }
            return heap[0];
        }

        /**
         * Function to delete min element *
         */
        public int deleteMin() {
            int keyItem = heap[0];
            delete(0);
            return keyItem;
        }

        /**
         * Function to delete element at an index *
         */
        public int delete(int ind) {
            if (isEmpty()) {
                throw new NoSuchElementException("Underflow Exception");
            }
            int keyItem = heap[ind];
            heap[ind] = heap[heapSize - 1];
            heapSize--;
            heapifyDown(ind);
            return keyItem;
        }

        /**
         * Function heapifyUp  *
         */
        private void heapifyUp(int childInd) {
            int tmp = heap[childInd];
            while (childInd > 0 && tmp > heap[parent(childInd)]) {
                heap[childInd] = heap[parent(childInd)];
                childInd = parent(childInd);
            }
            heap[childInd] = tmp;
        }

        /**
         * Function heapifyDown *
         */
        private void heapifyDown(int ind) {
            int child;
            int tmp = heap[ind];
            while (kthChild(ind, 1) < heapSize) {
                child = minChild(ind);
                if (heap[child] > tmp) {
                    heap[ind] = heap[child];
                } else {
                    break;
                }
                ind = child;
            }
            heap[ind] = tmp;
        }

        /**
         * Function to get smallest child *
         */
        private int minChild(int ind) {
            int bestChild = kthChild(ind, 1);
            int k = 2;
            int pos = kthChild(ind, k);
            while ((k <= d) && (pos < heapSize)) {
                if (heap[pos] > heap[bestChild]) {
                    bestChild = pos;
                }
                pos = kthChild(ind, k++);
            }
            return bestChild;
        }

        /**
         * Function to print heap *
         */
        public void printHeap() {
            System.out.print("\nHeap = ");
            for (int i = 0; i < heapSize; i++) {
                System.out.print(heap[i] + " ");
            }
            System.out.println();
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


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author hitarthk
 */
public class C_318_A {

    public static void main(String[] args) {
        FasterScanner in = new FasterScanner();
        PrintWriter out = new PrintWriter(System.out);
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        BinaryHeap h = new BinaryHeap(n + 3);
        for (int i = 1; i < n; i++) {
            h.insert(a[i]);
        }
        if(h.findMin()<a[0]){
            out.println(0);
        }
        else{
            int ans=0;
            while(h.findMin()>=a[0]){
                ans++;
                a[0]++;
                int e=h.deleteMin();
                e--;
                h.insert(e);
            }
            out.println(ans);
        }
        out.close();
    }

    /**
     * Java Program to Implement Binary Heap
     */
    /**
     * Class BinaryHeap *
     */
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

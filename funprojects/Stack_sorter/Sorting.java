/*
 * Stack Sorter v0.1.0 (http://techguyification.com)
 * Copyright 2014-2015 Techguyification
 * MIT License
 *
 * TERMS OF USE - Stack Sorter
 *
 * Open source under the BSD License.
 *
 * Copyright © 2015 Matthew Paletta and Ali Siddiqui
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list of
 * conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list
 * of conditions and the following disclaimer in the documentation and/or other materials
 * provided with the distribution.
 *
 * Neither the name of the author nor the names of contributors may be used to endorse
 * or promote products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 *  COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 *  EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE
 *  GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED
 * AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */

import java.util.*;


public class Sorting {
    private static int N; // for heapsort

    public static void main(String[] args) {
        
        int numtests = 12;
        
        // number of algorithms | number of tests
        double[][] avg = new double[8][numtests];
        
        // run each test 100 times
        int numtimesrun = 100;
        
        for (int K = 1; K <= avg[0].length; K++) {
            System.out.println("Currently Testing: " + (K * 10000));
            
            long[][] thisrun = new long[avg.length][numtimesrun];
            
            for (int M = 0; M < numtimesrun; M++) {
                
                int[] numbers = new int[K * 10000];
                
                for (int i = 0; i < numbers.length; i++) {
                    numbers[i] = (int) (Math.random()*2147483646)+1;
                }
                
                
                for (int i = 0; i <= 7; i++) {
                    int[] forSorting = Arrays.copyOf(numbers, numbers.length);
                    
                    if (i==2) {
                        StackSorterV1 L = new StackSorterV1();
                        long timeIn = System.currentTimeMillis();
                        L.sort(forSorting);
                        long timeOut = System.currentTimeMillis();
                        thisrun[i][M] = (timeOut-timeIn);
                        L = null;
                    } else if (i==0) {
                        long timeIn = System.currentTimeMillis();
                        mergeSort(forSorting);
                        long timeOut = System.currentTimeMillis();
                        thisrun[i][M] = (timeOut-timeIn);
                    } else if (i==1) {
                        long timeIn = System.currentTimeMillis();
                        insertionSort(forSorting, forSorting.length);
                        long timeOut = System.currentTimeMillis();
                        thisrun[i][M] = (timeOut-timeIn);
                    } else if (i==3) {
                        StackSorterV2 L = new StackSorterV2();
                        long timeIn = System.currentTimeMillis();
                        L.sort(forSorting);
                        long timeOut = System.currentTimeMillis();
                        thisrun[i][M] = (timeOut-timeIn);
                        L = null;
                    } else if (i==4) {
                        StackSorterV3 L = new StackSorterV3();
                        long timeIn = System.currentTimeMillis();
                        L.sort(forSorting);
                        long timeOut = System.currentTimeMillis();
                        thisrun[i][M] = (timeOut-timeIn);
                        L = null;
                    } else if (i == 5) {
                        long timeIn = System.currentTimeMillis();
                        heapsort(forSorting);
                        long timeOut = System.currentTimeMillis();
                        thisrun[i][M] = (timeOut-timeIn);
                    } else if (i == 6) {
                        long timeIn = System.currentTimeMillis();
                        selectionSort(forSorting);
                        long timeOut = System.currentTimeMillis();
                        thisrun[i][M] = (timeOut-timeIn);
                    } else if (i == 7) {
                        long timeIn = System.currentTimeMillis();
                        quickSort(forSorting, 0, forSorting.length-1);
                        long timeOut = System.currentTimeMillis();
                        thisrun[i][M] = (timeOut-timeIn);
                    }
                }
            }
            
            // Compute Average for each algoritm at that size.
            for (int i = 0; i < thisrun.length; i++) {
                long sum = 0;
                for (int j = 0; j < thisrun[0].length; j++) {
                    sum += thisrun[i][j];
                }
                
                avg[i][K-1] = (double) sum / thisrun[0].length;
            }
        }
        
        String[] algs = {"Merge Sort", "Insertion Sort", "Stack Sorter V1", "Stack Sorter V2", "Stack Sorter V3", "Heap Sort", "Selection Sort", "Quick Sort"};
        
        for (int i = 0; i < avg.length; i++) {
            System.out.println();
            for (int j = 0; j < avg[0].length; j++) {
                System.out.println(algs[i]+ " ("+((j+1)*10000)+"): " +  avg[i][j]);
            }
        }
        System.out.println("Ran Each Test: " + numtimesrun + " times");
    }
    
    static void dump(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
    
    static int[] mergeSort(int[] A) {
        if (A.length > 1) {
            int q = A.length/2;

            int[] leftArray = Arrays.copyOfRange(A, 0, q);
            int[] rightArray = Arrays.copyOfRange(A,q,A.length);

            mergeSort(leftArray);
            mergeSort(rightArray);

            merge(A,leftArray,rightArray);
        }
        
        return A;
    }

    static void merge(int[] a, int[] l, int[] r) {
        int totElem = l.length + r.length;
        int i,li,ri;
        i = li = ri = 0;
        while ( i < totElem) {
            if ((li < l.length) && (ri<r.length)) {
                if (l[li] < r[ri]) {
                    a[i] = l[li];
                    i++;
                    li++;
                }
                else {
                    a[i] = r[ri];
                    i++;
                    ri++;
                }
            }
            else {
                if (li >= l.length) {
                    while (ri < r.length) {
                        a[i] = r[ri];
                        i++;
                        ri++;
                    }
                }
                if (ri >= r.length) {
                    while (li < l.length) {
                        a[i] = l[li];
                        li++;
                        i++;
                    }
                }
            }
        }
        //return a;

    }
    
    static int[] insertionSort(int array[], int n) {
        for (int i = 1; i < n; i++) {
            int next = array[i];
            int  loc  = i;

            while (loc > 0 && array[loc-1] > next) {
                array[loc] = array[loc-1];
                loc--;
            }
            array[loc] = next;
        }
        
        return array;
    }
    
    
    public static void heapsort(int arr[])
    {
        heapify(arr);
        for (int i = N; i > 0; i--)
        {
            swapheap(arr,0, i);
            N = N-1;
            maxheap(arr, 0);
        }
    }
    /* Function to build a heap */
    public static void heapify(int arr[])
    {
        N = arr.length-1;
        for (int i = N/2; i >= 0; i--)
            maxheap(arr, i);
    }
    /* Function to swap largest element in heap */
    public static void maxheap(int arr[], int i)
    {
        int left = 2*i ;
        int right = 2*i + 1;
        int max = i;
        if (left <= N && arr[left] > arr[i])
            max = left;
        if (right <= N && arr[right] > arr[max])
            max = right;
        
        if (max != i)
        {
            swapheap(arr, i, max);
            maxheap(arr, max);
        }
    }
    /* Function to swap two numbers in an array */
    public static void swapheap(int arr[], int i, int j)
    {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    
    public static int[] selectionSort(int[] arr){
        
        for (int i = 0; i < arr.length - 1; i++)
        {
            int index = i;
            for (int j = i + 1; j < arr.length; j++)
                if (arr[j] < arr[index])
                    index = j;
            
            int smallerNumber = arr[index];
            arr[index] = arr[i];
            arr[i] = smallerNumber;
        }
        return arr;
    }
    
    
    public static void quickSort(int[] arr, int low, int high) {
        if (arr == null || arr.length == 0)
            return;
        
        if (low >= high)
            return;
        
        // pick the pivot
        int middle = low + (high - low) / 2;
        int pivot = arr[middle];
        
        // make left < pivot and right > pivot
        int i = low, j = high;
        while (i <= j) {
            while (arr[i] < pivot) {
                i++;
            }
            
            while (arr[j] > pivot) {
                j--;
            }
            
            if (i <= j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
        
        // recursively sort two sub parts
        if (low < j)
            quickSort(arr, low, j);
        
        if (high > i)
            quickSort(arr, i, high);
    }
    
}
/*
 * Stack Sorter v3.1.0 (http://techguyification.com)
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
import java.lang.*;

public class StackSorterV3 {
    nodeS head;
    
    public void StackSorterV3() {
        head = null;
        
    }
    
    boolean empty() {
        return head == null;
    }
    
    int[] sort(int[] a) {
        
        int max = a[0];
        
        for (int i = 0; i < a.length; i++) {
            if (a[i] > max) {
                max = a[i];
            }
        }
        
        int exp = 0;
        
        if (max != 0) {
            exp = (int) Math.log10(max);
        }
        
        StackSorterV3[] ARR = new StackSorterV3[exp+1];
        for (int i = 0; i < ARR.length; i++) {
            ARR[i] = new StackSorterV3();
        }
        
        for (int i = 0; i < a.length; i++) {
            if (a[i] != 0) {
                exp = (int) Math.log10(a[i]);
            } else {
                exp = 0;
            }
            ARR[exp].insert(a[i]);
        }
        
        
        int j = 0;
        for (int i = 0; i < ARR.length; i++) {
            
            while(!ARR[i].empty()) {
                int m = 0;
                int[] B = ARR[i].remove();
                if (B.length > 0) {
                    a[j] = B[m];
                    j++;
                    m++;
                }
            }
        }
        
        return a;
    }
    
    
    int[] remove() {
        nodeS min;
        
        nodeS prev = null;
        
        min = head;
        
        for (nodeS curr = head; curr.next != null; curr = curr.next) {
            if (min.s.peek() > curr.next.s.peek()) {
                min = curr.next;
                prev = curr;
            }
        }
        
        int a = min.s.pop();
        
        if (min.s.empty()) {
            if (min == head) {
                head = head.next;
            } else if(min.next == null) {
                prev.next = null;
            } else {
                prev.next = min.next;
            }
            
        }
        
        int[] arr = {a};
        return arr;
    }
    
    
    
    void insert (int value) {
        nodeS n = new nodeS();
        
        if (head == null) {
            n.s.push(value);
            head = n;
        } else {
            nodeS curr;
            
            if (head.s.peek() >= value) {
                head.s.push(value);
                return;
            }
            
            
            for (curr = head; curr.next != null; curr = curr.next) {
                // Add to middle
                if (curr.s.peek() >= value) {
                    curr.s.push(value);
                    return;
                }
            }
            
            // Add to end
            n.s.push(value);
            curr.next = n;
            
        }
        
    }
    
    public static void main(String[] args) {
        int[] numbers = new int[10];
        
        for (int i = 0; i < numbers.length; i++){
            numbers[i] = (int) (Math.random()*500);
        }
        
        int[] forSorting = Arrays.copyOf(numbers, numbers.length);
        
        for (int i = 0; i < forSorting.length; i++) {
            System.out.print(forSorting[i] + " ");
        }
        System.out.println();
        
        StackSorterV3 L = new StackSorterV3();
        long timeIn = System.currentTimeMillis();
        forSorting = L.sort(forSorting);
        long timeOut = System.currentTimeMillis();
        System.out.println("Stack Sorter V3: " + (timeOut-timeIn));
        
        for (int i = 0; i < forSorting.length; i++) {
            System.out.print(forSorting[i] + " ");
        }
        System.out.println();
    }
    
    void $O(int s) {
        System.out.print(s + " ");
    }
    
    
    
    
    
}
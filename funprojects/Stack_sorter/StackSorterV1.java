/*
 * Stack Sorter v1.1.0 (http://techguyification.com)
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



import java.lang.*;

public class StackSorterV1{
    node head;
    StackSorterV1() {
        head = null;
        
    }
    
    int[] sort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            insert(a[i]);
        }
        
        
        for (int i = 0; i < a.length; i++) {
            a[i] = remove();
        }
        
        return a;
    }
    
    
    int remove() {
        node min;
        
        node prev = null;
        
        min = head;
        
        for(node curr = head; curr.next != null; curr = curr.next) {
            if ((int) min.s.peek() > (int) curr.next.s.peek()) {
                min = curr.next;
                prev = curr;
                
            }
            
            
        }

        int a = (int) min.s.pop();
        
        if (min.s.empty()) {
            if (min == head) {
                head = head.next;
            } else if(min.next == null) {
                prev.next = null;
            } else {
                prev.next = min.next;
            }
            
        }
        
        return a;
        
        
    }
    
    
    
    void insert (int value){
        node n = new node();
        
        if (head == null) {
            n.s.push(value);
            head = n;
        } else {
            node curr;
            
            if ((int) head.s.peek() >= value) {
                head.s.push(value);
                return;
            }
            
            
            for (curr = head; curr.next != null; curr = curr.next) {
                // Add to middle
                if ((int) curr.s.peek() >= value) {
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
        int[] a = {14, 13, 71, 64, 79, 33, 70, 30, 18, 33};
        
        StackSorterV1 L = new StackSorterV1();
        
        for(int i = 0; i < a.length; i++) {
            L.$O(a[i]);
        }
        System.out.println();
        
        a = L.sort(a);
        
        for(int i = 0; i < a.length; i++) {
            L.$O(a[i]);
        }
        System.out.println();
        
    }
    
    void $O(int s) {
        System.out.print(s + " ");
    }
    
    
    
    
    
}
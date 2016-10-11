/*
 * Sudoku Solver v0.1.0 (http://techguyification.com)
 * Copyright 2014-2015 Techguyification
 * MIT License
 *
 * TERMS OF USE - Sudoku Solver
 *
 * Open source under the BSD License.
 *
 * Copyright © 2015 Matthew Paletta
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


var a =[
    [0,0,0, 0,0,0, 0,0,0],
    [0,0,0, 0,0,0, 0,0,0],
    [0,0,0, 0,0,0, 0,0,0],
    
    [0,0,0, 0,0,0, 0,0,0],
    [0,0,0, 0,0,0, 0,0,0],
    [0,0,0, 0,0,0, 0,0,0],
    
    [0,0,0, 0,0,0, 0,0,0],
    [0,0,0, 0,0,0, 0,0,0],
    [0,0,0, 0,0,0, 0,0,0]
];

init();
    
//createPuzzle();

function init() {
    for (var i = 0; i < 9; i++) {
        for (var j = 0; j < 9; j++) {
 
            document.body.innerHTML += "<input type='number' id='"+i+"-"+j+"'>";
            
            if (j % 3 == 2 && j != 8) {
                document.body.innerHTML += " | ";
            }
        }
        if (i % 3 == 2 && i != 8) {
            document.body.innerHTML += "<hr>";
        } else {
            document.body.innerHTML += "<br>";
        }
    }
    
    document.body.innerHTML += "<a class='btn-large waves-effect waves-light blue' onclick='solve()'>Solve</a>";
}
    
function createPuzzle() {
    
    for (var i = 0; i < a.length; i++) {
        for (var j = 0; j < a[0].length; j++) {
            if (document.getElementById(i+"-"+j).value == "") {
                a[i][j] = 0;
            } else {
                a[i][j] = document.getElementById(i+"-"+j).value;
            }
            
        }
    }

}
    
function solve() {
    createPuzzle();
    
    //solvepuzzle(0,0, a);
    if (!solvepuzzle(a)) {
        alert("No Solution Found.");
    }
    printPuzzle(a);
    //alert("DONE SOLVING PUZZLE!!")
}

    
function solvepuzzle(arr) {
    for (var i = 0; i < arr.length; i++) {
        for (var j = 0; j < arr[0].length; j++) {
            if (arr[i][j] != 0) {
                continue;
            }
            for (var num = 1; num <= arr.length; num++) {
                if (legalsquare(num, i, j, arr)) {
                    arr[i][j] = num;
                    if (solvepuzzle(arr)) {
                        return true;
                    } else {
                        arr[i][j] = 0;
                    }
                }
            }
            return false;
        }
    }
    return true;
}
    
function legalsquare(vl, cols, rows, arr) {
    return checkSquare(vl, cols, rows, arr) && checkRow(vl, rows, arr) && checkCol(vl, cols, arr);
} 
    
function checkSquare(vl, cols, rows, arr) {
    var col, row;
    col = Math.floor(cols/3) * 3;
    row = Math.floor(rows/3) * 3;
    
    for (var i = col; i < (col+3); i++) {
        for (var j = row; j < (row+3); j++) {
            if (arr[i][j] == vl) {
                return false;
            }
        }
    }
    return true;
    
}

function checkRow(vl, rows, arr) {
    for (var i = 0; i < arr.length; i++) {
        if (arr[i][rows] == vl) {
            return false;
        }
    }
    return true;
}

function checkCol(vl, cols, arr) {
    for (var i = 0; i < arr[0].length; i++) {
        if (arr[cols][i] == vl) {
            return false;
        }
    }
    return true;
}
    
function printPuzzle(arr) {
    for(var i = 0; i < arr.length; i++) {
        for (var j = 0; j < arr[0].length; j++) {
            document.getElementById(i+"-"+j).value = arr[i][j];
        }
    }
}

function Array2D(x, y) {
    var array2D = new Array(x);

    for (var i = 0; i < array2D.length; i++) {
        array2D[i] = new Array(y);
    }

    return array2D;
}
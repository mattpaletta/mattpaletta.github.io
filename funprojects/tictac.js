/*
 * Tic Tac Toe v0.1.0 (http://techguyification.com)
 * Copyright 2014-2015 Techguyification
 * MIT License
 *
 * TERMS OF USE - Tic Tac Toe
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

inittictac();

function inittictac() {
    for (var i = 0; i < 3; i++) {
        for (var j = 0; j < 3; j++) {
            document.body.innerHTML += "<a class='btn-floating btn-large waves-effect waves-light red' onclick='makemove.call(this,event)' id='tictac"+i+"-"+j+"'>-</a>";
        }
        document.body.innerHTML += "<br>";
    }
}

function makemove(event) {
    var a = [
        [0, 0, 0],
        [0, 0, 0],
        [0, 0, 0]
    ];
    //console.log(this.innerHTML.charAt(0));
    if (this.innerHTML.charAt(0) != "-") {
        alert("Illegal Move!");
        return;
    }

    event.preventDefault();
    this.innerHTML = "O";

    for (var i = 0; i < a.length; i++) {
        for (var j = 0; j < a[0].length; j++) {
            if (document.getElementById("tictac"+i+"-"+j).innerHTML.charAt(0) == "-") {
                a[i][j] = 0; // free square
            } else if (document.getElementById("tictac"+i+"-"+j).innerHTML.charAt(0) == "O"){
                a[i][j] = 1; // player 
            } else if (document.getElementById("tictac"+i+"-"+j).innerHTML.charAt(0) == "X") {
                a[i][j] = 2; // computer
            }
        }
    }
    if (gameover(a) || won(1, a) || won(2, a)) {
        endgame(a);
        return;
    }
    
    var result = move(a, 0, "computer");
    document.getElementById("tictac"+result[0]+"-"+result[1]).innerHTML = "X";
    
    a[result[0]][result[1]] = 2;
    
    if (gameover(a) || won(1, a) || won(2, a)) {
        endgame(a);
        return;
    }
}

function playagain() {
    document.body.innerHTML = "";
    inittictac();
}

function endgame(game) {
    if (won(1, game)) {
        alert("Player 1 Won!");
    } else if (won(2, game)) {
        alert("The Computer Won!");
    } else {
        alert("The Game Ended In A Draw!");
    }
    document.body.innerHTML += "<br><br><a class='btn-large waves-effect waves-light blue' onclick='playagain()'>Play Again</a>";
}
    

function move(game) {
    var result = minmax(game, 0, "computer");
    //console.log(result);
    
    return [result[1], result[2]]; // row, col
}

function minmax(game, depth, player) {
    var moves = get_available_moves(game);
    var currScore;
    var bestRow = -1;
    var bestCol = -1;
    var bestscore;
    
    if (won(1, game) != 0 || won(2, game) != 0 || gameover(game)) {
        bestscore = score(game, depth);
    } else {
        if (player == "computer") {
            bestscore = -Infinity;

            for (var i = 0; i < moves.length; i++) {
                var row = moves[i][0];
                var col = moves[i][1];
                game[row][col] = 2;
                currScore = minmax(game, depth+1, "opponent")[0];
                if (currScore > bestscore) {
                    bestscore = currScore;
                    bestRow = row;
                    bestCol = col;
                }
                game[row][col] = 0;
            }
        } else { // player is opponenent
            bestscore = Infinity;
            for (var i = 0; i < moves.length; i++) {
                var row = moves[i][0];
                var col = moves[i][1];
                game[row][col] = 1;
                
                currScore = minmax(game, depth+1, "computer")[0];
                if (currScore < bestscore) {
                    bestscore = currScore;
                    bestRow = moves[i][0];
                    bestCol = moves[i][1];
                }
                game[row][col] = 0;
            }
        }
    }
    var ret = [bestscore, bestRow, bestCol];
    return ret;
}

function get_available_moves(grid) {
    if (won(2, grid) || won(1, grid)) {
        return [];
    }
    
    var moves = [];
    for (var i = 0; i < grid.length; i++) {
        for (var j = 0; j < grid[0].length; j++) {
            if (grid[i][j] == 0) {
                var ret = [i,j];
                moves.push(ret);
            }
        }
    }
    return moves;
}

function gameover(game) {
    for (var i = 0; i < game.length; i++) {
        for (var j = 0; j < game[0].length; j++) {
            if (game[i][j] == 0) {
                return false;
            }
        }
    }
    return true;
}
    

function score(game, depth) {
    
    if (won(1, game)) { // Opponent Won
        return depth-100;
    } else if (won(2, game)) { // I won
        return 100-depth;
    } else {
        return 0;
    }
}
    
function won(val, arr) {
    return checkCol(val, arr) || checkRow(val, arr) || diag(val, arr) || diag2(val, arr);
}
    
function checkCol(val, arr) {
    for (var i = 0; i < arr.length; i++) {
        var check = true;
        for (var j = 0; j < arr[0].length; j++) {
            if (arr[j][i] != val) {
                check = false;
            }
        }
        if (check == true) {
            return true;
        }
    }
    return false;
}
    
function checkRow(val, arr) {
    for (var i = 0; i < arr.length; i++) {
        var check = true;
        for (var j = 0; j < arr[0].length; j++) {
            if (arr[i][j] != val) {
                check = false;
            }
        }
        if (check == true) {
            return true;
        }
    }
    return false;
}

function diag(val, arr) {
    for (var i = 0; i < arr.length; i++) {
        if (arr[i][i] != val) {
            return false;
        }
    }
    return true;
}
    
function diag2(val, arr) {
    for (var i = 0; i < arr.length; i++) {
        if (arr[arr.length - i-1][i] != val) {
            return false;
        }
    }
    return true;
}
    
function createArr(rows) {
    var arr = [];

    for (var i=0;i < rows; i++) {
        arr[i] = [];
    }

    return arr;
}
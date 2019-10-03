package com.example.tictactoecanvas.GameEngine;

import android.util.Log;

import java.util.Arrays;
import java.util.Random;

public class GameEngine {

    private static final Random RANDOM = new Random();
    private char[] elts;
    private char currentPlayer;
    private boolean ended;

    public GameEngine() {
        elts = new char[] {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
        newGame();
    }

    public boolean isEnded() {
        return ended;
    }

    public char play(int x, int y) {
        if (!ended && elts[3 * y + x] == ' ') {
            elts[3 * y + x] = currentPlayer;
            changePlayer();
        }

        return checkEnd();
    }

    public void changePlayer() {
        currentPlayer = (currentPlayer == 'X' ? 'O' : 'X');
    }

    public char elt(int x, int y) {
        Log.i("elt x", Integer.toString(x));
        Log.i("elt y", Integer.toString(y));
        Log.i("elt total", Integer.toString(3 * y + x));
        Log.i("elt result", Character.toString(elts[ 3 * y + x ]));
        return elts[3 * y + x];
    }

    public void newGame() {
        for (int i  = 0; i < 3; i++) {
            elts[i] = ' ';
        }
        currentPlayer = 'X';
        ended = false;
    }
    public char checkEnd() {
        Log.i("What is elts", Arrays.toString(elts));
        for (int i = 0; i < 3; i++) {
            if (elt(i, 0) != ' ' && elt(i, 0) == elt(i, 1) && elt(i, 1) == elt(i, 2)) {
                Log.i ("I am:", "1" );
                ended = true;
                return elt(i, 0);
            } else if (elt(0, i) != ' ' && elt(0, i) == elt(1, i) && elt(1, i) == elt(2, i)) {
                Log.i ("I am:", "2" );
                Log.i("2 - i is", Integer.toString(i));
                Log.i("2 - boolean", Boolean.toString(elt(0, i) == elt(1, i)));
                Log.i("2 - part one", Character.toString(elt(0,i)));
                ended = true;
                return elt(0, i);
            }
        }
        if (elt(0, 0) != ' ' && elt(0, 0) == elt(1, 1) && elt(1, 1) == elt(2, 2)) {
            ended = true;
            Log.i ("I am:", "3" );
            return elt(0, 0);
        } else if (elt(0, 2) != ' ' && elt(0, 2) == elt(1, 1) && elt(1, 1) == elt(2, 0)) {
            ended = true;
            Log.i ("I am:", "4" );
            return elt(1, 1);
        }

        for (int i = 0; i < 9; i++) {
            if (elts[i] == ' '){
                Log.i ("I am:", "5" );
                return ' ';
            }
        }
        Log.i ("I am:", "6" );
        return 'T';
    }

    public char computer() {
        if (!ended) {
            int position = -1;

            do {
                position = RANDOM.nextInt(9);
            } while (elts[position] != ' ');

            elts[position] = currentPlayer;
            changePlayer();
        }
        return checkEnd();
    }
}

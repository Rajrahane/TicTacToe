/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.raj.tictactoe.model;

import com.raj.tictactoe.constants.GameConstants;

/**
 *
 * @Rajvaibhav Rahane
 * 12 June 2018
 */
public class Board {

    private int board[];//board of nine elements
    public static final int COMPUTER_WINS=25;
    public static final int PLAYER_WINS=-25;
    public static final int TOTAL_MOVES=9;    
    public static final int BLANK = 2;     

    public Board(){
        board = new int[TOTAL_MOVES];
        for (int i = 0; i < TOTAL_MOVES; i++) {
            board[i] = BLANK;//BLANK
        }        
    }
    public void printBoard() {
        for (int i = 0; i < TOTAL_MOVES; i++) {
            if (i % 3 == 0) {
                System.out.println();
            }
            if (board[i] == BLANK) {
                System.out.print(" ?");
            } else if (board[i] == GameConstants.Move.X.getValue()) {
                System.out.print(" X");
            } else {
                System.out.print(" O");
            }
        }
        System.out.println();
    }
    private int decideWinner(int ch,Player player,Player computer){
        if(ch==computer.getMoveValue()){
            return COMPUTER_WINS;
        }
        else if(ch==player.getMoveValue()){
            return PLAYER_WINS;
        }        
        return -1;
    } 
    public int evaluateBoard(Player player,Player computer){
        int result;
        if(board[0]==board[4]&& board[4]==board[8]){          //diag1
            result=decideWinner(board[0],player,computer);
            if(result!=-1)
                return result;
        }
        if(board[2]==board[4]&& board[4]==board[6]){        //diag2
            result=decideWinner(board[2],player,computer);
            if(result!=-1)
                return result;
        }
        if(board[0]==board[1]&& board[1]==board[2]){         //123
            result=decideWinner(board[0],player,computer);
            if(result!=-1)
                return result;
        }
        if(board[3]==board[4]&& board[4]==board[5]){        //456
            result=decideWinner(board[3],player,computer);
            if(result!=-1)
                return result;
        }
        if(board[6]==board[7]&& board[7]==board[8]){        //789
            result=decideWinner(board[6],player,computer);
            if(result!=-1)
                return result;
        }
        if(board[0]==board[3]&& board[3]==board[6]){         //147
            result=decideWinner(board[0],player,computer);
            if(result!=-1)
                return result;
        }
        if(board[1]==board[4]&& board[4]==board[7]){         //258
            result=decideWinner(board[1],player,computer);
            if(result!=-1)
                return result;
        }
        if(board[2]==board[5]&& board[5]==board[8]){          //369
            result=decideWinner(board[2],player,computer);
            if(result!=-1)
                return result;
        }
        return 0;                                             //draw
    }
    public boolean isBlank(int i) {                        // 0 based Indexing
        return board[i] == BLANK ? true : false;       
    }
    public boolean isMovesLeft(){
        for (int i = 0; i<TOTAL_MOVES; i++)        
            if (board[i]==BLANK)
                return true;
        return false;
    }           
        
    public int[] getBoard(){
        return board;
    }
    public void setBoardIndex(int index,int player){
        board[index]=player;
    }
}

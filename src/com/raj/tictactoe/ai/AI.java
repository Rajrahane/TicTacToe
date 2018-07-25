/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.raj.tictactoe.ai;

import com.raj.tictactoe.model.Board;
import com.raj.tictactoe.model.Player;
import java.util.Random;

/**
 *
 * @Rajvaibhav Rahane
 * 12 June 2018
 */
public class AI {
    public int findBestMove(Board gameBoard,Player player,Player computer){
        int []board=gameBoard.getBoard();
        int bestValue=-1000;
        int bestMove=-1;
        int moveValue;
        for(int i=0;i<Board.TOTAL_MOVES;i++){
            if(gameBoard.isBlank(i)){
                board[i]=computer.getMoveValue();
                moveValue=minimax(board,0,false,gameBoard,player,computer);
                board[i]=Board.BLANK;
                
                if(moveValue>bestValue){                    //***if multiple moves available, randomise
                    bestValue=moveValue;
                    bestMove=i;
                }
            }
        }
        return bestMove;
    }
    private int minimax(int[] board,int depth,boolean isMaximiserTurn,Board gameBoard,Player player,Player computer){
        int score=gameBoard.evaluateBoard(player,computer);
        if(score>0){                            //computer wins
            return score-depth;
        }
        if(score<0){
            return score+depth;
        }        
        
        if(!gameBoard.isMovesLeft()){
            return 0;
        }
        int best;
        if(isMaximiserTurn){
            best=-1000;
            for(int i=0;i<Board.TOTAL_MOVES;i++){
                if(gameBoard.isBlank(i)){
                    board[i]=computer.getMoveValue();
                    best=Math.max(best,minimax(board,depth+1,!isMaximiserTurn,gameBoard,player,computer));
                    board[i]=Board.BLANK;
                }
            }
            return best-depth;
        }
        else{
            best=1000;
            int worstForUser=-1000;                                               //user may or may not play ideally,hence the variable to maximise win chances
            for(int i=0;i<Board.TOTAL_MOVES;i++){
                if(gameBoard.isBlank(i)){
                    board[i]=player.getMoveValue();
                    best=Math.min(best,minimax(board,depth+1,!isMaximiserTurn,gameBoard,player,computer));
                    worstForUser=Math.max(worstForUser,minimax(board,depth+1,!isMaximiserTurn,gameBoard,player,computer));
                    board[i]=Board.BLANK;
                }
            }
            if(best>0){
                return worstForUser-depth;
            }
        }        
        return best+depth;
    }
    public int getRandomCorner() {                             //selects random corner-1,3,7 or 9
        int corners[]={1,3,7,9};                                // 1 based indexing
        int rnd=new Random().nextInt(corners.length);
        return corners[rnd];
    }
}

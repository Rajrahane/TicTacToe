/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aitictactoe;

import java.util.Random;
import java.util.Scanner;

/**
 *01 April 2018
 * @Raj Rahane
 */
public class TicTacToeExpert {
    private int board[];//board of nine elements
    private static final int totalMoves=9;
    private static final int computerWins=25;
    private static final int playerWins=-25;
    private static final int BLANK = 2;
    private static final int X = 3;
    private static final int O = 5;
    private boolean isCompX=false;
    private int player=X;
    private int computer=O;
    private int turn;
    public TicTacToeExpert(){
        board = new int[totalMoves];
        for (int i = 0; i < totalMoves; i++) {
            board[i] = BLANK;//BLANK
        }
        turn = 1;
    }
    public static void main(String args[]){
    Scanner sc = new Scanner(System.in);
        TicTacToeExpert obj = new TicTacToeExpert();
        System.out.println("Enter type:2-O or 1-X?");
        int option = sc.nextInt();
        if (option == 2) {
            obj.setPlayers();
        }
        int result = obj.play();
        obj.printBoard();
        if (result == 0) {
            System.out.println("draw");
        } else if(result==computerWins){
            System.out.println("CPU wins");
        }
        else{
            System.out.println("You win");
        }
        sc.close();
    }
    
    private int play(){        
        if(isCompX){                                        // Comp plays odd turns           
            for(;turn<=totalMoves;turn++){                           //9 turns
                printBoard();
                if (turn % 2 == 0)                          //PLayer's turn
                {                                                                  
                    board[getInput()] = O;
                }
                else{                                       //main logic here,CPU's turn
                    
                }
            }
        }
        else{                                               //Comp plays even turns            
            for(;turn<=totalMoves;turn++){                           //9 turns
                printBoard();
                if (turn % 2 == 1)                          //PLayer's turn
                {                   
                    board[getInput()] = X;
                }
                else{                                       //main logic here,CPU's turn
                    switch(turn){
                        case 2:{
                            if(!isBlank(4)){                        //center taken
                                go(getRandomCorner()-1);
                                                            //take any corner
                            }
                            else if(!isBlank(0)||!isBlank(2)||!isBlank(6)||!isBlank(8)){    //corner taken
                                go(4);                              //take center
                            }
                            else{                                   //edge taken
                                if(!isBlank(1)||!isBlank(5))
                                    go(2);
                                else
                                    go(6);
                            }
                            break;
                        }
                        case 4:
                        case 6:
                        case 8:{       
                            /*nextMove=isPossWin();                   //if block at next move possible,go for it
                            if(nextMove!=-1)
                                go(nextMove-1);*/
                            //else{
                                go(findBestMove(board));
                            //}
                            if(evaluateBoard(board)==computerWins){
                                    return computerWins;
                            }
                            else if(evaluateBoard(board)==playerWins){
                                    return playerWins;
                            }
                        }    
                    }
                }
            }
        }
        return 0;
    }
    
    private int getInput(){                             // returns 0 based moveValue
        int nextMove;
        Scanner sc = new Scanner(System.in);
        do{
               nextMove=sc.nextInt();
               if(!isBlank(nextMove-1))
                    System.out.println("Invalid Input,Re-enter");
        }while(!isBlank(nextMove-1));
        //sc.close();
        return nextMove-1;
    }
    
    private void printBoard() {
        for (int i = 0; i < totalMoves; i++) {
            if (i % 3 == 0) {
                System.out.println();
            }
            if (board[i] == BLANK) {
                System.out.print(" ?");
            } else if (board[i] == X) {
                System.out.print(" X");
            } else {
                System.out.print(" O");
            }
        }
        System.out.println();
    }
    
    private void setPlayers() {
        isCompX = true;//cpu is X
        player=O;
        computer=X;
    }
        
    private boolean isBlank(int i) {                        // 0 based Indexing
        return board[i] == BLANK ? true : false;       
    }      
    private void go(int i) {                                        //0 based indexing
            board[i] = isCompX ? X : O;        
    }    
    private int getRandomCorner() {                             //selects random corner-1,3,7 or 9
        int corners[]={1,3,7,9};                                // 1 based indexing
        int rnd=new Random().nextInt(corners.length);
        return corners[rnd];
    }
    private int decideWinner(int ch){
        if(isCompX){
            if(ch==X)
                return computerWins;
            else if(ch==O)
                return playerWins;
        }
        else{
            if(ch==O)
                return computerWins;
            else if(ch==X)
                return playerWins;
        }
        return -1;
    } 
    private int evaluateBoard(int []board){
        int result;
        if(board[0]==board[4]&& board[4]==board[8]){          //diag1
            result=decideWinner(board[0]);
            if(result!=-1)
                return result;
        }
        if(board[2]==board[4]&& board[4]==board[6]){        //diag2
            result=decideWinner(board[2]);
            if(result!=-1)
                return result;
        }
        if(board[0]==board[1]&& board[1]==board[2]){         //123
            result=decideWinner(board[0]);
            if(result!=-1)
                return result;
        }
        if(board[3]==board[4]&& board[4]==board[5]){        //456
            result=decideWinner(board[3]);
            if(result!=-1)
                return result;
        }
        if(board[6]==board[7]&& board[7]==board[8]){        //789
            result=decideWinner(board[6]);
            if(result!=-1)
                return result;
        }
        if(board[0]==board[3]&& board[3]==board[6]){         //147
            result=decideWinner(board[0]);
            if(result!=-1)
                return result;
        }
        if(board[1]==board[4]&& board[4]==board[7]){         //258
            result=decideWinner(board[1]);
            if(result!=-1)
                return result;
        }
        if(board[2]==board[5]&& board[5]==board[8]){          //369
            result=decideWinner(board[2]);
            if(result!=-1)
                return result;
        }
        return 0;                                             //draw
    }
    private int findBestMove(int []board){
        int bestValue=-1000;
        int bestMove=-1;
        int moveValue;
        for(int i=0;i<totalMoves;i++){
            if(isBlank(i)){
                board[i]=computer;
                moveValue=minimax(board,0,false);
                board[i]=BLANK;
                
                if(moveValue>bestValue){                    //***if multiple moves available, randomise
                    bestValue=moveValue;
                    bestMove=i;
                }
            }
        }
        return bestMove;
    }
    private int minimax(int[] board,int depth,boolean isMaximiserTurn){
        int score=evaluateBoard(board);
        if(score>0){                            //computer wins
            return score-depth;
        }
        if(score<0){
            return score+depth;
        }        
        
        if(!isMovesLeft(board)){
            return 0;
        }
        int best;
        if(isMaximiserTurn){
            best=-1000;
            for(int i=0;i<totalMoves;i++){
                if(isBlank(i)){
                    board[i]=computer;
                    best=Math.max(best,minimax(board,depth+1,!isMaximiserTurn));
                    board[i]=BLANK;
                }
            }
            return best-depth;
        }
        else{
            best=1000;
            int worstForUser=-1000;                                               //user may or may not play ideally,hence the variable to maximise win chances
            for(int i=0;i<totalMoves;i++){
                if(isBlank(i)){
                    board[i]=player;
                    best=Math.min(best,minimax(board,depth+1,!isMaximiserTurn));
                    worstForUser=Math.max(worstForUser,minimax(board,depth+1,!isMaximiserTurn));
                    board[i]=BLANK;
                }
            }
            if(best>0){
                return worstForUser-depth;
            }
        }        
        return best+depth;
    }
    private boolean isMovesLeft(int board[]){
        for (int i = 0; i<totalMoves; i++)        
            if (board[i]==BLANK)
                return true;
        return false;
    }

}
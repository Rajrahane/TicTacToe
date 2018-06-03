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
    private final int totalMoves=9;
    private final int BLANK = 2;
    private final int X = 3;
    private final int O = 5;
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
        } else if(result==25){
            System.out.println("CPU wins");
        }
        else{
            System.out.println("You win");
        }
        sc.close();
    }
    
    private int play(){
        int nextMove;
        if(isCompX){                                        // Comp plays odd turns           
            for(;turn<=totalMoves;turn++){                           //9 turns
                printBoard();
                if (turn % 2 == 0)                          //PLayer's turn
                {                                                                  
                    board[getInput()-1] = O;
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
                    board[getInput()-1] = X;
                }
                else{                                       //main logic here,CPU's turn
                    switch(turn){
                        case 2:{
                            if(!isBlank(5)){                        //center taken
                                go(getRandomCorner());
                                                            //take any corner
                            }
                            else if(!isBlank(1)||!isBlank(3)||!isBlank(7)||!isBlank(9)){    //corner taken
                                go(5);                              //take center
                            }
                            else{                                   //edge taken
                                if(!isBlank(2)||!isBlank(6))
                                    go(3);
                                else
                                    go(7);
                            }
                            break;
                        }
                        case 4:
                        case 6:
                        case 8:{       
                            /*nextMove=isPossWin();                   //if block at next move possible,go for it
                            if(nextMove!=-1)
                                go(nextMove);*/
                            //else{
                                go(findBestMove(board)+1);
                            //}
                            if(evaluateBoard(board)==25){
                                    return 25;
                            }
                            else if(evaluateBoard(board)==-25){
                                    return -25;
                            }
                        }    
                    }
                }
            }
        }
        return 0;
    }
    
    private int getInput(){
        int nextMove;
        Scanner sc = new Scanner(System.in);
        do{
               nextMove=sc.nextInt();
               if(!isBlank(nextMove))
                    System.out.println("Invalid Input,Re-enter");
        }while(!isBlank(nextMove));
        //sc.close();
        return nextMove;
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
        
    private boolean isBlank(int i) {
        return board[i - 1] == BLANK ? true : false;       
    }
    
    private int isPossWin() {
        int total;
        if (!isCompX) {
            total = X*X*BLANK;
        } else {
            total = O*O*BLANK;
        }
        if (board[0] * board[1] * board[2] == total)//sleeping
        {
            if (isBlank(1)) {
                return 1;
            } else if (isBlank(2)) {
                return 2;
            } else {
                return 3;
            }
        } else if (board[0] * board[3] * board[6] == total)//straight
        {
            if (isBlank(1)) {
                return 1;
            } else if (isBlank(4)) {
                return 4;
            } else {
                return 7;
            }
        } else if (board[0] * board[4] * board[8] == total) {       //diag
            if (isBlank(1)) {
                return 1;
            } else if (isBlank(5)) {
                return 5;
            } else {
                return 9;
            }
        } else if (board[2] * board[5] * board[8] == total)//369
        {
            if (isBlank(3)) {
                return 3;
            } else if (isBlank(6)) {
                return 6;
            } else {
                return 9;
            }
        } else if (board[2] * board[4] * board[6] == total)//357
        {
            if (isBlank(3)) {
                return 3;
            } else if (isBlank(5)) {
                return 5;
            } else {
                return 7;
            }
        } else if (board[6] * board[7] * board[8] == total)//789
        {
            if (isBlank(7)) {
                return 7;
            } else if (isBlank(8)) {
                return 8;
            } else {
                return 9;
            }
        } else if (board[3] * board[4] * board[5] == total) {//456
            if (isBlank(4)) {
                return 4;
            } else if (isBlank(5)) {
                return 5;
            } else {
                return 6;
            }
        } else if (board[1] * board[4] * board[7] == total) {//258
            if (isBlank(2)) {
                return 2;
            } else if (isBlank(5)) {
                return 5;
            } else {
                return 8;
            }
        }
        
        return -1;//not possible
    }
     private void go(int i) {        
            board[i - 1] = isCompX ? X : O;        
    }

    private boolean opponentHasPlace(int i) {
        return board[i-1]==player;
    }

    private int getRandomCorner() {                             //selects random corner-1,3,7 or 9
        int corners[]={1,3,7,9}; 
        int rnd=new Random().nextInt(corners.length);
        return corners[rnd];
    }
    private int decideWinner(int ch){
        if(isCompX){
            if(ch==X)
                return 25;
            else if(ch==O)
                return -25;
        }
        else{
            if(ch==O)
                return 25;
            else if(ch==X)
                return -25;
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
            if(isBlank(i+1)){
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
                if(isBlank(i+1)){
                    board[i]=computer;
                    best=Math.max(best,minimax(board,depth+1,!isMaximiserTurn));
                    board[i]=BLANK;
                }
            }
            return best-depth;
        }
        else{
            best=1000;
            for(int i=0;i<totalMoves;i++){
                if(isBlank(i+1)){
                    board[i]=player;
                    best=Math.min(best,minimax(board,depth+1,!isMaximiserTurn));
                    board[i]=BLANK;
                }
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
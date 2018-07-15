/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package TicTacToe;

import java.util.Scanner;

/**
 *
 * @Raj Rahane
 */
public class UI {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        Board board = new Board();
        UI ui=new UI();
        AI ai=new AI();
        System.out.println("Enter type:1-X? or 2-O");
        int option = sc.nextInt();
        if (option == 1) {
            board.setPlayerToX(true);
        }
        System.out.println("Play First?:Yes-1,No-0");
        option=sc.nextInt();
        if(option==1){
            board.letPlayerPlayFirst(true);
        }
        int result = ui.play(board,ai);
        board.printBoard();
        if (result == 0) {
            System.out.println("draw");
        } else if(result==Board.COMPUTER_WINS){
            System.out.println("CPU wins");
        }
        else{
            System.out.println("You win");
        }
        sc.close();
    }
    
    private int play(Board board,AI ai){
        int turn=1;
        if(!board.isPlayerFirst()){                                        // Comp plays odd turns           
            for(;turn<=Board.TOTAL_MOVES;turn++){                           //9 turns
                board.printBoard();
                if (turn % 2 == 0)                          //PLayer's turn
                {                                                                  
                    board.setBoardIndex(this.getInput(board), board.getPlayer());                    
                }
                else{                                       //main logic here,CPU's turn
                    if(turn==1){
                        board.setBoardIndex(ai.getRandomCorner()-1,board.getComputer());
                    }
                    else{
                        board.setBoardIndex(ai.findBestMove(board), board.getComputer());
                        if(board.evaluateBoard()==Board.COMPUTER_WINS){
                                return Board.COMPUTER_WINS;
                        }
                        else if(board.evaluateBoard()==Board.PLAYER_WINS){
                                return Board.PLAYER_WINS;
                        }
                    }
                }
            }
        }
        else{                                               //Comp plays even turns            
            for(;turn<=Board.TOTAL_MOVES;turn++){                           //9 turns
                board.printBoard();
                if (turn % 2 == 1)                          //PLayer's turn
                {                   
                    board.setBoardIndex(this.getInput(board), board.getPlayer()); 
                }
                else{                                       //main logic here,CPU's turn
                    switch(turn){
                        case 2:{
                            if(!board.isBlank(4)){                        //center taken
                                board.setBoardIndex(ai.getRandomCorner()-1, board.getComputer());//take any corner                        
                            }
                            else if(!board.isBlank(0)||!board.isBlank(2)||!board.isBlank(6)||!board.isBlank(8)){    //corner taken
                                board.setBoardIndex(4, board.getComputer());                               //take center
                            }
                            else{                                   //edge taken
                                if(!board.isBlank(1)||!board.isBlank(5))
                                    board.setBoardIndex(2, board.getComputer());                                    
                                else
                                    board.setBoardIndex(6, board.getComputer());  
                            }
                            break;
                        }
                        case 4:
                        case 6:
                        case 8:{  
                            board.setBoardIndex(ai.findBestMove(board), board.getComputer());                                
                            
                            if(board.evaluateBoard()==Board.COMPUTER_WINS){
                                    return Board.COMPUTER_WINS;
                            }
                            else if(board.evaluateBoard()==Board.PLAYER_WINS){
                                    return Board.PLAYER_WINS;
                            }
                        }    
                    }
                }
            }
        }
        return 0;
    }
    
    private int getInput(Board board){                             // returns 0 based moveValue
        int nextMove;
        Scanner sc = new Scanner(System.in);
        do{
               nextMove=sc.nextInt();
               if(!board.isBlank(nextMove-1))
                    System.out.println("Invalid Input,Re-enter");
        }while(!board.isBlank(nextMove-1));
        //sc.close();
        return nextMove-1;
    }
}

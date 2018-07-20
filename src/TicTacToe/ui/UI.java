/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tictactoe.ui;

import tictactoe.ai.AI;
import tictactoe.model.Board;
import java.util.Scanner;
import tictactoe.model.GameConstants;
import tictactoe.model.Player;

/**
 *
 * @Rajvaibhav Rahane
 */
public class UI {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        Board board = new Board();
        UI ui=new UI();
        AI ai=new AI();
        Player player=new Player(),computer=new Player();
        
        System.out.println("Enter type:1-X? or 2-O");
        int type = sc.nextInt();        
        System.out.println("Play First?:Yes-1,No-2");
        int chanceNo=sc.nextInt();
        
        ui.createPlayers(player,computer,type,chanceNo);        
        int result = ui.play(board,ai,player,computer);
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
    
    private int play(Board board,AI ai,Player player,Player computer){
        int turn=1;
        if(computer.getChanceNo()==GameConstants.firstChance){                                        // Comp plays odd turns           
            for(;turn<=Board.TOTAL_MOVES;turn++){                           //9 turns
                board.printBoard();
                if (turn % 2 == 0)                          //PLayer's turn
                {                                                                  
                    board.setBoardIndex(this.getInput(board), player.getType());                    
                }
                else{                                       //main logic here,CPU's turn
                    if(turn==1){
                        board.setBoardIndex(ai.getRandomCorner()-1,computer.getType());
                    }
                    else{
                        board.setBoardIndex(ai.findBestMove(board,player,computer), computer.getType());
                        if(board.evaluateBoard(player,computer)==Board.COMPUTER_WINS){
                                return Board.COMPUTER_WINS;
                        }
                        else if(board.evaluateBoard(player,computer)==Board.PLAYER_WINS){
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
                    board.setBoardIndex(this.getInput(board), player.getType()); 
                }
                else{                                       //main logic here,CPU's turn
                    switch(turn){
                        case 2:{
                            if(!board.isBlank(4)){                        //center taken
                                board.setBoardIndex(ai.getRandomCorner()-1, computer.getType());//take any corner                        
                            }
                            else if(!board.isBlank(0)||!board.isBlank(2)||!board.isBlank(6)||!board.isBlank(8)){    //corner taken
                                board.setBoardIndex(4, computer.getType());                               //take center
                            }
                            else{                                   //edge taken
                                if(!board.isBlank(1)||!board.isBlank(5))
                                    board.setBoardIndex(2, computer.getType());                                    
                                else
                                    board.setBoardIndex(6, computer.getType());  
                            }
                            break;
                        }
                        case 4:
                        case 6:
                        case 8:{  
                            board.setBoardIndex(ai.findBestMove(board,player,computer), computer.getType());                                
                            
                            if(board.evaluateBoard(player,computer)==Board.COMPUTER_WINS){
                                    return Board.COMPUTER_WINS;
                            }
                            else if(board.evaluateBoard(player,computer)==Board.PLAYER_WINS){
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

    private void createPlayers(Player player,Player computer,int type,int chanceNo) {
        if(type==1){           
            computer.setType(GameConstants.O);
            player.setType(GameConstants.X);
        }
        else{            
            computer.setType(GameConstants.X);
            player.setType(GameConstants.O);
        }
        player.setChanceNo(chanceNo);
        if(chanceNo==1){
            computer.setChanceNo(GameConstants.secondChance);
        }
        else{
            computer.setChanceNo(GameConstants.firstChance);
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package TicTacToe;

/**
 *
 * @Raj Rahane
 * 12 June 2018
 */
public class Board {

    private int board[];//board of nine elements
    public static final int COMPUTER_WINS=25;
    public static final int PLAYER_WINS=-25;
    public static final int TOTAL_MOVES=9;    
    public static final int BLANK = 2;
    public static final int X = 3;
    public static final int O = 5;
    private boolean isPlayerFirst;
    private int player;
    private int computer;    

    public Board(){
        board = new int[TOTAL_MOVES];
        for (int i = 0; i < TOTAL_MOVES; i++) {
            board[i] = BLANK;//BLANK
        }
        setPlayerToX(false);
        letPlayerPlayFirst(false);
    }
    public void printBoard() {
        for (int i = 0; i < TOTAL_MOVES; i++) {
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
    private int decideWinner(int ch){
        if(ch==computer){
            return COMPUTER_WINS;
        }
        else if(ch==player){
            return PLAYER_WINS;
        }        
        return -1;
    } 
    public int evaluateBoard(){
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
    public boolean isBlank(int i) {                        // 0 based Indexing
        return board[i] == BLANK ? true : false;       
    }
    public boolean isMovesLeft(){
        for (int i = 0; i<TOTAL_MOVES; i++)        
            if (board[i]==BLANK)
                return true;
        return false;
    }
    public void setPlayerToX(boolean set) {
        if(set){
            player=X;
            computer=O;
        }
        else{
            player=O;
            computer=X;
        }            
    }
    public void letPlayerPlayFirst(boolean let){
        isPlayerFirst=let;
    }
    public boolean isPlayerFirst(){
        return isPlayerFirst;
    }
    public int getComputer(){
        return computer;
    }
    public int getPlayer(){
        return player;
    }    
    public int[] getBoard(){
        return board;
    }
    public void setBoardIndex(int index,int player){
        board[index]=player;
    }
}

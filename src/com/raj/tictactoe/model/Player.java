/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.raj.tictactoe.model;

import com.raj.tictactoe.constants.GameConstants;

/**
 *
 * @Rajvaibhav Rahane
 */
public class Player {
    private int chanceNo;           //1-First or 2-Second               
    private GameConstants.Move moveType;        //X or O
    
    public void setChanceNo(int chanceNo){
        this.chanceNo=chanceNo;
    }
    
    public void setMoveType(GameConstants.Move moveType){
            this.moveType=moveType;
    }
    public int getChanceNo(){
        return chanceNo;
    }
    
    public int getMoveValue(){
        return this.moveType.getValue();
    }    
}

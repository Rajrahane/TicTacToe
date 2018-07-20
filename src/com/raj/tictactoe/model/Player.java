/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.raj.tictactoe.model;

/**
 *
 * @Rajvaibhav Rahane
 */
public class Player {
    private int chanceNo;           //1-First or 2-Second
    private int type;               //X or O
    
    /*public Player(int chanceNo,int type){
        this.chanceNo=chanceNo;
        this.type=type;
    }*/
    public void setChanceNo(int chanceNo){
        this.chanceNo=chanceNo;
    }
    public void setType(int type){
        this.type=type;
    }
    public int getChanceNo(){
        return chanceNo;
    }
    public int getType(){
        return type;
    }
}

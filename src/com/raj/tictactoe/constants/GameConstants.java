/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.raj.tictactoe.constants;

/**
 *
 * @Rajvaibhav Rahane
 */
public class GameConstants {
    public static final int FIRST_CHANCE = 1;
    public static final int SECOND_CHANCE = 2;
    public enum Move{X(3),O(5);
        private int value;
        Move(int value){
            this.value=value;
        }
        public int getValue(){
            return value;
        }
    }
}

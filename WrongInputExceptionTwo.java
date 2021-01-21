/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group4.grizzlygarageinventory;

/**
 *
 * @author evan
 */
public class WrongInputExceptionTwo extends Exception {
    
    public WrongInputExceptionTwo(){
        super("You must have at least one value for the following fields: item name, make");
    }
}

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
public class WrongInputException extends Exception {
    
    public WrongInputException(){
        super("You must have a item name, model and make to proceed");
    }
    
}

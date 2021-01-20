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

//stack class to read in and write out objects 
public class Stack {
    private int top;
    private int nodesInStructure;
    private Item[] array;
    
    
    public Stack(){
        top = -1;
        nodesInStructure = 50;
        array = new Item[nodesInStructure];
    }
    

    public Item[] returnArray(){
        
        Item[] newArray = new Item[top + 1];
        
        for(int i = 0; i < newArray.length; i++){
            newArray[i] = array[(newArray.length - 1) - i];
        }
        
        
        return newArray;
    }

    
    public int getTop(){
        return top;
    }
    
    public void push(Item newItem){
        top++;
        array[top] = newItem;
        //System.out.println(array[top].toString());
        
        if(top == nodesInStructure - 1){
            Item[] temp = array;
            
            Item[] newArray = new Item[nodesInStructure + 50];
            
            array = newArray;
            
            for(int i = 0; i < temp.length; i++){
                array[i] = temp[i];
            }
            
            nodesInStructure = nodesInStructure + 50;
        }
    }
    
    public Item pop(){
        
        if(top < 0){
            return null;
        }

        int topIndex = top;
        top--;
        return array[topIndex];
        
    }
    
    public Item[] getArray(){
        return array;
    }
    
    public boolean isEmpty(){
        if(top < 0){
            return true;
        }
        else{
            return false;
        }
    }
    
    public void eraseArray(){
        for(int i = top; i > -1; i--){
            array[i] = null;
        }
    }
}

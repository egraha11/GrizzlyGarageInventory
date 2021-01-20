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

import java.util.Comparator;

public class Item{
    
    private String itemName;
    private double price;
    private int vin;
    private String make;
    private String model;
    private int year;
    
    public Item(String name, double startingPrice, int vinNumber, String startingMake
    ,String startingModel, int startingYear){
        itemName = name;
        price = startingPrice;
        vin = vinNumber;
        make = startingMake;
        model = startingModel;
        year = startingYear;
    }
    
    public Item(Item node){
        itemName = node.itemName;
        price = node.price;
        vin = node.vin;
        make = node.make;
        model = node.model;
        year = node.year;
    }
    
    public Item(String name){
        itemName = name; 
    }
    
    
    public void setName(String newName){
        
        itemName = newName;
    }
    
    public static Item createItem(String newItemString){
       String[] newItemArray = newItemString.split(", ");
       
       double newPrice = Double.parseDouble(newItemArray[1]);
       int newVin = Integer.parseInt(newItemArray[2]);
       int newYear = Integer.parseInt(newItemArray[5]);
       
       Item newItem = new Item(newItemArray[0], newPrice, newVin, newItemArray[3],
       newItemArray[4], newYear);
       
       return newItem;
    }
    
    public void setVin(int newVin){
        vin = newVin;
    }
    
    public void setPrice(double newPrice){
        price = newPrice;
    }
    
    public void setMake(String newMake){
        make = newMake;
    }
    
    public void setModel(String newModel){
        model = newModel;
    }
    
    public void setYear(int newYear){
        year = newYear;
    }
    
    public String getName(){
        return this.itemName;
    }
    
    public double getPrice(){
        return this.price;
    }
    
    public int getVin(){
        return this.vin;
    }
    
    public String getMake(){
        return this.make;
    }
    
    public String getModel(){
        return this.model;
    }
    
    public int getYear(){
        return this.year;
    }
            
    public int compare(String name){
        return (itemName.compareToIgnoreCase(name));
    }
    
    public int compareMake(String name){
        return (make.compareToIgnoreCase(name));
    }
    
    public int compareModel(String name){
        return (model.compareToIgnoreCase(name));
    }
     
    public boolean itemEquals(Object object){
        
        if(getClass() != object.getClass()){
            return false;
        }
        else{
            Item compareObject = (Item)object;
            
            if(this == compareObject){
                return true;
            }
            else{
                return false;
            }
        }   
    }
    
    public String toString(){
        return itemName + ", " + price + ", " + vin + ", " + make + ", " + model + ", " + year + ", ";
    }
    
    public Item deepCopy(Item Node){
        Item copiedItem = new Item(Node);
        
        return copiedItem;
    }
    
    //compares every field of two items
    public boolean matchItem(Item compareItem){
        
        if(this.getPrice() == 0 || this.getPrice() == compareItem.getPrice()){
            if(this.getVin() == 0 || this.getVin() == compareItem.getVin()){
                if((this.getMake().equalsIgnoreCase("none") == true) 
                        || (this.getMake().equalsIgnoreCase(compareItem.getMake()) == true)){
                    if((this.getModel().equalsIgnoreCase("none") == true) 
                        || (this.getModel().equalsIgnoreCase(compareItem.getModel()) == true)){
                        if(this.getYear() == 9999 
                            || this.getYear() == compareItem.getYear()){
                                return true;       
                               }
                           } 
                        }
                        
                    }
                    
                }        
        return false;
    }
   
}

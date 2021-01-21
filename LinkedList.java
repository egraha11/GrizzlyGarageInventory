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

//linked list to store all items with similar make
public class LinkedList {
    private ItemWrapper rootNode;

    public LinkedList(Item newNode){
        rootNode = new ItemWrapper(newNode);
    }
    
    public LinkedList(){
        rootNode = null;
    }
    
    //insert into linked list
    public void insert(Item newItem){
        if(newItem == null){
            return;
        }
        
        if(rootNode == null){
            rootNode = new ItemWrapper(newItem);
        }
        else{
            ItemWrapper newNode = new ItemWrapper(newItem);
        
            ItemWrapper walker = rootNode;
        
            while (walker.nextItem != null){
                walker = walker.nextItem;
            }
        
            walker.nextItem = newNode;
            }
    }
    
    //fetch from linked list
    public Item[] fetch(Item itemToFind){
        ItemWrapper walker = new ItemWrapper(null);
        
        Item[] arrayToBeReturned = new Item[50];
        int i = 0;
        
        walker = rootNode;
        
        
        while(walker != null){
            if(i == arrayToBeReturned.length - 1){//there are more items to add, a larger array needs to be created
                Item[] temp = arrayToBeReturned;
                Item[] newArray = new Item[arrayToBeReturned.length + 50];
                
                arrayToBeReturned = newArray;
                
                for(int k = 0; k < temp.length; k++){
                    arrayToBeReturned[k] = temp[k];
                } 
            }
            
            if(itemToFind.matchItem(walker.getData()) == true){
                arrayToBeReturned[i] = walker.data;
                i++;
            }
            walker = walker.nextItem;
        }
     
        return arrayToBeReturned;
    }
    
    public ItemWrapper getRootNode(){
        return rootNode;
    }
    
    
    public boolean delete(Item targetItem){
        
        if(rootNode.nextItem == null){//Binary tree node needs to be deleted(there are no other items in the linked list)
            return false;
        }

        ItemWrapper previousNode = rootNode;
        ItemWrapper walker = rootNode;
        
        while(!(walker.getData().toString().equals(targetItem.toString()))){
            previousNode = walker;
            walker = walker.nextItem;
        }
        
        if(walker == rootNode){//root Node needs to be deleted and there are other nodes in the list 
            rootNode = rootNode.nextItem;
            return true;
        }
        else{
            previousNode.nextItem = walker.nextItem;
            return true;   
        }   
    }

    
    public Item[] returnAll(){//acts as a fetch for returning all objects that match the root nodes make variable
        ItemWrapper walker = new ItemWrapper(null);
        
        Item[] arrayToBeReturned = new Item[50];
        int i = 0;
        
        walker = rootNode;
        
        while(walker != null){
            if(i == arrayToBeReturned.length - 1){//there are more items to add, a larger array needs to be created
                Item[] temp = arrayToBeReturned;
                Item[] newArray = new Item[arrayToBeReturned.length + 50];
                
                arrayToBeReturned = newArray;
                
                for(int k = 0; k < temp.length; k++){
                    arrayToBeReturned[k] = temp[k];
                } 
            }
            
            arrayToBeReturned[i] = walker.data;
            i++;
            walker = walker.nextItem;
        }

        return arrayToBeReturned;
    }
    
    //wrapper that contains item data and indexing 
    public class ItemWrapper{
        private Item data;
        private ItemWrapper nextItem;
        
        public ItemWrapper(Item newItem){
            data = newItem;
            nextItem = null;
        }
        
        public boolean hasNextItem(){
            if(nextItem == null){
                return false;
            }
            else{
                return true;
            }
        } 
        
        public Item getData(){
            return data;
        }
        
    }
    

}

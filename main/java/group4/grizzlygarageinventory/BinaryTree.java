
package group4.grizzlygarageinventory;

/**
 *
 * @author evan
 */

//class that will group all items of similar make into linked lists that can be traversed via a binary tree 
public class BinaryTree {
    private BinaryTree leftNode;
    private BinaryTree rightNode;
    private LinkedList nextNode;
    
    
    public BinaryTree(){
        
        leftNode = null;
        rightNode = null;
        nextNode = null;
        
    }

    public BinaryTree(Item newItem){
        
        leftNode = null;
        rightNode = null;
        nextNode = new LinkedList(newItem);
        
    }
    //constructor for a pre-existing inventory
    public BinaryTree(Item[] inventoryArray, boolean firstTime){
        
        if(inventoryArray == null){
            leftNode = null;
            rightNode = null;
            nextNode = null;   
        }
        
        if(inventoryArray.length == 0){
            return;
        }
        
        if (inventoryArray.length == 1){
           leftNode = null;
           rightNode = null;
           nextNode = new LinkedList(inventoryArray[0]);
           return;
        }

        //quick sort existing array
        if(firstTime == true){
            mergeSort(inventoryArray);
        }
       
        int middle = inventoryArray.length / 2;
           
        //create a linkled list with the middle item as the root 
        nextNode = new LinkedList(inventoryArray[middle]);
        
        //if items were added to the list store as a variable 
        int changedNodes = findNodesToLink(inventoryArray, nextNode);

        //middle of the array is now changed find new middle minus the nodes taken out and the middle node
        Item[] newInventoryArray = new Item[(inventoryArray.length - changedNodes) - 1];
 
        //create a new array excluding the root node 
        int k = 0;
        int j = 0;
        
        while(j < newInventoryArray.length){
            if(inventoryArray[k] != nextNode.getRootNode().getData()){
                newInventoryArray[j] = inventoryArray[k];
                j++;
                k++;
            }
            else{
                k++;
            }
        }
        
        //if array cannot be split
        if(newInventoryArray.length / 2 == 0){
            middle = 0;
        }
        else{
            middle = (newInventoryArray.length / 2);
            
            while(newInventoryArray[middle].compareMake(nextNode.getRootNode().getData().getMake()) < 0){
                middle = middle + 1;
                //System.out.println(middle);
            }
            while(newInventoryArray[middle].compareMake(nextNode.getRootNode().getData().getMake()) > 0){
                middle = middle - 1;
            }
        }
        
        if(newInventoryArray.length == 0){
            return;
        }
        //split the final array
        Item[] leftArray = new Item[middle + 1];
        for(int i = 0; i < leftArray.length; i++){
            leftArray[i] = newInventoryArray[i];
        }  

        Item[] rightArray = new Item[newInventoryArray.length - (middle + 1)];
        for(int i = 0; i < rightArray.length; i++){
            rightArray[i] = newInventoryArray[(middle + 1) + i];
        }
        
        if(newInventoryArray[0].compareMake(nextNode.getRootNode().getData().getMake()) > 0){
            Item[] temp = leftArray;
            leftArray = rightArray;
            rightArray = temp;
        }

        leftNode = new BinaryTree(leftArray, false);
        rightNode = new BinaryTree(rightArray, false);
    }
    
    private int findNodesToLink(Item[] array, LinkedList rootNode){
        
        //binary search the sorted array for items to add to the linked list
        int changedNodes = 0;
        int high = array.length - 1;
        int low = 0;
        int i = ((high + low) / 2);
        
        while(low != high){
            if(array[i].getMake().equalsIgnoreCase(rootNode.getRootNode().getData().getMake()) && 
                    array[i] != rootNode.getRootNode().getData()){//add to the appropriate linked list
                

                rootNode.insert(array[i]);
                
                //delete node from array
                for(int j = i; j < array.length - 1; j++){
                    array[j] = array[j + 1]; 
                }
                
                array[array.length - 1] = null;
                
                //reset high and low 
                changedNodes++;
                high = (array.length - 1) - changedNodes;
                low = 0;
                i = ((high + low) / 2);
                
            }
            else{
                if(array[i] == rootNode.getRootNode().getData()){
                    i++;
                }
                else if(array[i].compareMake(rootNode.getRootNode().getData().getMake()) < 0){
                    low = i + 1; 
                    i = ((high + low) / 2);
                }
                else{
                    high = i - 1;
                    i = ((high + low) / 2);
                }
            }
        }
        return changedNodes;  
    }
    
    
    
    public static void mergeSort(Item[] array){
        
        if(array.length <= 1){
            return;
        }

        Item[] low = new Item[array.length/2];
        for(int i = 0; i < low.length; i++){
            low[i] = array[i];
        }
        
        Item[] high = new Item[array.length - low.length];
        for(int i = 0; i < high.length; i++){
            high[i] = array[i + low.length];
        }

        mergeSort(low);
        mergeSort(high);

        sort(low, high, array);
    }
    
    
    
    public static void sort(Item[] low, Item[] high, Item[] array){
        
        int counter = 0;
        int i = 0;
        int k = 0;
        
        while(i < low.length && k < high.length){
            if(low[i].compareMake(high[k].getMake()) <= 0){
                array[counter] = low[i];
                i++;
            }
            else if(high[k].compareMake(low[i].getMake()) < 0){
                array[counter] = high[k];
                k++;
            }
            counter++;
        }
        if(i < low.length){
            while(i < low.length){
                array[counter] = low[i];
                counter++;
                i++;
            }
        }
        else if(k < high.length){
            while(k < high.length){
                array[counter] = high[k];
                counter++;
                k++;
            }
        }       
    }
    
    //inserts a new Item object into the tree
    public void insert(Item newItem){
        
        //Binary Tree is empty
        if(nextNode == null){
            nextNode = new LinkedList(newItem);
            return;
        }
        
        BinaryWrapper child = new BinaryWrapper();
        BinaryWrapper parent = new BinaryWrapper();
        
        
        boolean newList = findNode(child, parent, newItem.getMake());

        BinaryTree newTree;
        
        if(newList == true){
            child.getWrapper().nextNode.insert(newItem);
        }
        else if(newItem.compareMake(parent.getWrapper().nextNode.getRootNode().getData().getMake()) < 0){
            newTree = new BinaryTree(newItem);
            parent.getWrapper().leftNode = newTree;
        }
        else if(newItem.compareMake(parent.getWrapper().nextNode.getRootNode().getData().getMake()) > 0){
            newTree = new BinaryTree(newItem);
            parent.getWrapper().rightNode = newTree;
        }   
    }
    
    //locates a node in the binary tree
    public boolean findNode(BinaryWrapper child, BinaryWrapper parent, String targetKey){
        
        child.setTree(this);
        parent.setTree(this);

        while(child.getWrapper() != null && !(child.getWrapper().nextNode.getRootNode().getData().getMake().equalsIgnoreCase(targetKey))){
            if(targetKey.compareTo(child.getWrapper().nextNode.getRootNode().getData().getMake()) < 0){
                parent.setTree(child.getWrapper());
                child.setTree(child.getWrapper().leftNode);
            }
            else if(targetKey.compareTo(child.getWrapper().nextNode.getRootNode().getData().getMake()) > 0){
                parent.setTree(child.getWrapper());
                child.setTree(child.getWrapper().rightNode);
            }
        }
        
        if(child.getWrapper() != null){//add the new item to a currently existing linked list
            return true;
        }
        else{//a new binary tree is needed
            return false;
        }
    }
    
    //deletes a node from the binary tree
    public boolean delete(Item targetItem){
        
        if(nextNode == null){//tree is empty
            return false;
        }
        
        BinaryTree child = new BinaryTree();
        BinaryTree parent = new BinaryTree();
        
        child = this;
        parent = child;
        
        while(child != null && !(targetItem.getMake().equalsIgnoreCase(child.nextNode.getRootNode().getData().getMake()))){
            if(targetItem.compareMake(child.nextNode.getRootNode().getData().getMake()) < 0){
                parent = child;
                child = child.leftNode;
            }
            else if(targetItem.compareMake(child.nextNode.getRootNode().getData().getMake()) > 0){
                parent = child;
                child = child.rightNode;
            }
        }
        if(targetItem.compareMake(child.nextNode.getRootNode().getData().getMake()) == 0){
            boolean nodeDeleted = child.nextNode.delete(targetItem);
            //node to be deleted has only a root node
            if(nodeDeleted == false){
                if(targetItem.toString().equals(child.nextNode.getRootNode().getData().toString()) == true 
                        && child.nextNode.getRootNode().hasNextItem() == false){
                    //delete the binary tree
                    if(child.leftNode == null && child.rightNode == null){//no sub trees
                        if(parent.leftNode == child){
                            parent.leftNode = null;
                        }
                        else if(parent.rightNode == child){
                            parent.rightNode = null;
                        }
                    }
                    //node to be deleted has one sub tree
                    else if((child.leftNode != null && child.rightNode == null)||
                            child.rightNode != null && child.leftNode == null){
                        if(parent.leftNode == child){
                            if(child.leftNode != null){
                                parent.leftNode = child.leftNode;
                            }
                            else if(child.rightNode != null){
                                parent.leftNode = child.rightNode;
                            }
                        }
                        else{
                            if(child.leftNode != null){
                                parent.rightNode = child.leftNode;
                            }
                            else if(child.rightNode != null){
                                parent.rightNode = child.rightNode;
                            }
                        }
                    }
                    //node to be deleted has two sub trees
                    else{
                        BinaryTree nextLargest = child.leftNode;
                        BinaryTree largest = nextLargest.rightNode;
                        
                        if(largest != null){//left child of child has a right sub tree
                            while(largest.rightNode != null){
                                nextLargest = largest;
                                largest = largest.rightNode;
                            }
                            child = largest;
                            nextLargest.rightNode = largest.leftNode; 
                        }
                        else{//left child of node to be deleted does not have a right sub tree
                            nextLargest.rightNode = child.rightNode;
                            
                            if(parent.leftNode == child){
                                parent.leftNode = nextLargest;
                            }
                            else if(parent.rightNode == child){
                                parent.rightNode = nextLargest;
                            }
                        }
                    } 
                }
                else{
                    return false;
                }
            }
            else{
                return true;
            }
        }
        return false;
    }
        
    //update an item in the tree
    public void update(Item updateItem, Item newItem){
        delete(updateItem);
        insert(newItem);
    }
        

    //return all items that match a particular make from the tree
    public Item[] fetch(Item itemToFind){
        BinaryWrapper child = new BinaryWrapper();
        BinaryWrapper parent = new BinaryWrapper();
        
        //method that returns all items in linked list
        if(findNode(child, parent, itemToFind.getMake()) == true){
            Item[] array = child.getWrapper().nextNode.fetch(itemToFind);
            
            return(child.getWrapper().nextNode.fetch(itemToFind));
        }
        else{
            return null;
        }
    }
    
    //writes out all itesm included in the binary tree to a stack so that it can be written to 
    //a file 
    public void writeOut(Stack stack){
        
        if(leftNode != null){
            leftNode.writeOut(stack);
        }

        if(nextNode != null){
        Item[] pushArray = nextNode.returnAll();
        for(int i = 0; i < pushArray.length; i++){
            if(pushArray[i] != null){
                //System.out.println(pushArray[i].toString());
                stack.push(pushArray[i]);
            }
        } 
        }
        if(rightNode != null){
            rightNode.writeOut(stack);   
        }
    }
    
    //wrapper class so you can change the fetch and delete child object from a
    //utility method 
    private class BinaryWrapper{
        BinaryTree originalTree;
        
        BinaryWrapper(BinaryTree tree){
            originalTree = tree;
        }
        
        BinaryWrapper(){
            originalTree = null;
        }
        
        public BinaryTree getWrapper(){
            return originalTree;
        }
        
        public void setTree(BinaryTree newTree){
            originalTree = newTree;
        }
    }
}

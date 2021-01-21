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

class SortByModel implements Comparator<Item>{
    public int compare(Item a, Item b){
        return a.getModel().compareTo(b.getModel());
    }
}

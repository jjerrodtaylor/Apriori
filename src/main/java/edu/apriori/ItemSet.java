package edu.uba.filters.Apriori;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by jamaaltaylor on 5/25/16.
 */
public class ItemSet {

    public double support;
    public double confidence;

    private Set<String> items;

    public ItemSet(){

        super();
        items = new HashSet<String>();
    }

    public ItemSet(double support){
        this.support = support;
        items = new HashSet<String>();
    }

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    public double getSupport() {
        return support;
    }

    public void setSupport(double support) {
        this.support = support;
    }

    public boolean addItem(String item){
        boolean added = false;

        added = items.add(item);
        return added;
    }

    public Set<String> getItems(){
        Set<String> items = new HashSet<String>();
        Iterator<String> myIterator = this.items.iterator();
        while(myIterator.hasNext()){
            items.add(myIterator.next());
        }
        return items;
    }

    public void addItems(Set<String> items){
        this.items = items;
    }

    public int getSizeDifference(ItemSet set2){
        Set<String> thisCopy = this.getItems();
        Set<String> thatCopy = set2.getItems();

        thisCopy.removeAll(thatCopy);
        return  thisCopy.size();
    }

    public ItemSet intersection(ItemSet set2){
        ItemSet newIset = new ItemSet();
        Set<String> intersection = new HashSet<String>();
        Set<String> thisCopy = this.getItems();
        thisCopy.retainAll(set2.getItems());
        newIset.addItems(thisCopy);
        return newIset;
    }

    public ItemSet difference(ItemSet set2){
        ItemSet theDifference = new ItemSet();
        Set<String> thisCopy = this.getItems();
        Set<String> thatCopy = set2.getItems();
        thisCopy.removeAll(thatCopy);
        theDifference.addItems(thisCopy);

        return theDifference;
    }

}

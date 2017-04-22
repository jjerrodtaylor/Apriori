package edu.apriori;

import java.util.*;

/**
 * Created by jamaaltaylor on 4/22/17.
 */
public class FPTree {

    public FPTree(){
        super();

    }

    private class node{

        private String itemName;
        private int itemCount;
        private List<node> nodeLinks;

        public node(){
            super();
            nodeLinks = new ArrayList<node>();
        }

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public int getItemCount() {
            return itemCount;
        }

        public void setItemCount(int itemCount) {
            this.itemCount = itemCount;
        }

    }

    private PriorityQueue headerTable;

    public List<ItemSet> create_basket(List<String> lines){

        List<ItemSet> itemsets = new LinkedList<ItemSet>();

        for(int i =0; i<lines.size();i++){
            ItemSet items = new ItemSet();
            String[] pieces =  lines.get(i).split(",");

            for(String s: pieces){
                items.addItem(s);
            }

            itemsets.add(items);
        }

        return itemsets;
    }


    /*
    * Function used to return the counts of all of the items in the transactions
    * on the first pass
    * */
    public Map<String,Integer> generateCounts(List<ItemSet> transactions){
        Map<String,Integer> counts = new HashMap<String,Integer>();

        for(ItemSet s :transactions){
            Set<String> theSet = s.getItems();

            while (theSet.iterator().hasNext()){
                String current = theSet.iterator().next();

                if(!counts.containsKey(current)){
                    counts.put(current,1);
                    theSet.remove(current);
                }
                else{
                    int num = counts.get(current);
                    num = num+1;
                    counts.put(current,num);
                    theSet.remove(current);
                }
            }
        }

        return counts;
    }

    public void createHeaderTable(Map<String,Integer> itemCounts){

    }

}

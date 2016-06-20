package edu.apriori;

import java.util.*;
import edu.apriori.ItemSet;

/**
 * Created by jamaaltaylor on 4/30/16.
 */
public class Apriori {

    public double minsuppport;
    public double minconf;

    private Map<Integer,List<ItemSet>> priorSubsets = new HashMap<Integer,List<ItemSet>>();

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

    public List<ItemSet> generateLarge1ItemSets(Map<String,Integer> counts, List<ItemSet> baskets, double support){
        Iterator<String> keyIterator = counts.keySet().iterator();
        int totalCount = baskets.size();
        Set<String> keys = counts.keySet();
        List<ItemSet> l1ItemSets = new LinkedList<ItemSet>();

        int itemCount = 0;
        double itemSupport = 0.0;
        while(keyIterator.hasNext()){
            String item = keyIterator.next();
            itemCount = counts.get(item);
            itemSupport = (double)itemCount/(double)totalCount;
            if(itemSupport > support){
                ItemSet iSet = new ItemSet(itemSupport);
                iSet.addItem(item);
                l1ItemSets.add(iSet);
            }
        }

        return l1ItemSets;
    }

    public ItemSet cross_join(ItemSet set1, ItemSet set2){
        ItemSet isCandidate = new ItemSet();
        Set<String> candidate = new HashSet<String>();
        ItemSet candidatePrefix = set1.intersection(set2);
        candidate.addAll(candidatePrefix.getItems());
        ItemSet can1Iset = set1.difference(set2);
        candidate.addAll(can1Iset.getItems());
        isCandidate.addItems(candidate);
        return isCandidate;
    }


    public List<ItemSet> apriori_gen(List<ItemSet> L_1_frequentItemsets){
        List<ItemSet> newCandidates = new LinkedList<ItemSet>();
        Set<String> test1 = new HashSet<String>();
        Set<String> test2 = new HashSet<String>();

        for(int i = 0; i< L_1_frequentItemsets.size(); i++){
            for(int j=i; j< L_1_frequentItemsets.size(); j++){
                if(i != j){
                    if(L_1_frequentItemsets.get(i).getSizeDifference(L_1_frequentItemsets.get(j)) == 1){
                        ItemSet cand = cross_join(L_1_frequentItemsets.get(i),L_1_frequentItemsets.get(j));
                        newCandidates.add(cand);
                    }
                }
            }
        }

        return newCandidates;
    }

    /*
    public boolean has_infrequent_subset(List<Set<String>> candidates, int k){
        boolean returnvalue = false;

        List<Set<String>> priors = priorSubsets.get(k-1);
        Iterator<Set<String>> iterator = priors.iterator();
        while(iterator.hasNext()){
            for(Set<String> s: candidates){
                if(get_difference(iterator.next(), s)==1){
                    returnvalue = true;
                }
            }

        }

        return returnvalue;
    }   */
}

package edu.apriori;

import javafx.util.Pair;


/**
 * Created by jamaaltaylor on 4/22/17.
 */
public interface PriorityQueue {
    public boolean empty();
    public void insert(Pair<String, Integer> p);
    public Pair<String, Integer> removeMax();
}

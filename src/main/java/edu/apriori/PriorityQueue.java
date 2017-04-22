package edu.apriori;

import java.util.Map;

/**
 * Created by jamaaltaylor on 4/22/17.
 */
public interface PriorityQueue {
    public boolean empty();
    public void insert(Map<String, Integer> p);
    public String removeMax();
}

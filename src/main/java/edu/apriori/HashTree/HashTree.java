package edu.apriori.HashTree;

import edu.apriori.ItemSet;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by jamaaltaylor on 6/19/16.
 */
public class HashTree<ItemSet> {

    private class HTNode<ItemSet>
    {
        private String hash;
        private List<String> data;
        private List<HTNode<ItemSet>> children;

        //remember, when you write a constructor for a parameterized type
        //you don't include the angle brackets
        public HTNode(String data, String hash)
        {
            this.data = new LinkedList<String>();
            this.data.add(data);

            this.hash = hash;
        }

        public HTNode(String data, HTNode<ItemSet> child)
        {
            this.data = new LinkedList<String>();
            this.data.add(data);
            this.children = new LinkedList<HTNode<ItemSet>>();
            this.children.add(child);
        }

    }

    private HTNode<ItemSet> root;

    public boolean isEmpty(){
        return root == null;
    }

    public void insert(T data){

    }
}

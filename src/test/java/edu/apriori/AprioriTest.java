package edu.uba.filters;

import edu.uba.filters.Apriori.Apriori;
import edu.uba.filters.Apriori.ItemSet;
import edu.uba.filters.Search.BreadthFirst;
import edu.uba.filters.Graph.Graph;
import edu.uba.filters.Graph.UndirectedGraph;
import edu.uba.filters.Tools.FileHelper;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class AprioriTest {

    @Test
    public void testCreateItemSet(){
         ItemSet iSet = new ItemSet();
        iSet.setConfidence(.5);
        iSet.setSupport(.3);

        assertEquals(.3, iSet.getSupport(),0.0);
        assertEquals(.5, iSet.getConfidence(),0.0);

        FileHelper fileHelper = new FileHelper();
        List<String> results = fileHelper.readLines("/Users/jamaaltaylor/Documents/filters/src/test/java/edu/uba/filters/apriori.csv");
        Apriori apriori = new Apriori();
        List<ItemSet> basket = apriori.create_basket(results);

        assertEquals(5, basket.size());
    }

    @Test
    public void testGenerateCounts(){

        FileHelper fileHelper = new FileHelper();
        List<String> results = fileHelper.readLines("/Users/jamaaltaylor/Documents/filters/src/test/java/edu/uba/filters/apriori.csv");
        Apriori apriori = new Apriori();
        List<ItemSet> basket = apriori.create_basket(results);

        Map<String, Integer> counts = apriori.generateCounts(basket);

        assertEquals((Integer)4, (Integer) counts.get("Bread"));
    }

    @Test
    public void testInsersection(){
        ItemSet iSet = new ItemSet();
        ItemSet iset2 = new ItemSet();

        iSet.addItem("a");
        iSet.addItem("b");
        iSet.addItem("c");

        iset2.addItem("a");
        iset2.addItem("b");
        iset2.addItem("d");

        ItemSet intersection = iSet.intersection(iset2);

        assertEquals(2, intersection.getItems().size());
        assertEquals(true, intersection.getItems().contains("a"));
    }

    @Test
    public void testSizeDifference(){

        ItemSet iSet = new ItemSet();
        ItemSet iSet2 = new ItemSet();

        iSet.addItem("a");
        iSet.addItem("b");
        iSet.addItem("c");
        iSet.addItem("d");

        iSet2.addItem("a");
        iSet2.addItem("b");

        ItemSet diff1 = iSet.difference(iSet2);
        ItemSet diff2 = iSet2.difference(iSet);
        assertEquals(2, diff1.getItems().size());
        assertEquals(0, diff2.getItems().size());

        int sizeDifference = iSet.getSizeDifference(iSet2);
        assertEquals(2, sizeDifference);

        int otherSizeDifference = iSet2.getSizeDifference(iSet);
        assertEquals(0, otherSizeDifference);
    }

    @Test
    public void testCrossJoin(){
        Apriori apriori = new Apriori();

        ItemSet iSet = new ItemSet();
        ItemSet iSet2 = new ItemSet();

        iSet.addItem("a");
        iSet.addItem("b");
        iSet.addItem("c");


        iSet2.addItem("a");
        iSet2.addItem("b");
        iSet.addItem("d");

        ItemSet newCandidate = apriori.cross_join(iSet, iSet2);
        assertEquals(true,newCandidate.getItems().contains("a"));
        assertEquals(true,newCandidate.getItems().contains("b"));
        assertEquals(true,newCandidate.getItems().contains("c"));
        assertEquals(true,newCandidate.getItems().contains("d"));
    }

    @Test
    public void testGenL1(){

        FileHelper fileHelper = new FileHelper();
        List<String> results = fileHelper.readLines("/Users/jamaaltaylor/Documents/filters/src/test/java/edu/uba/filters/apriori.csv");
        Apriori apriori = new Apriori();
        List<ItemSet> basket = apriori.create_basket(results);
        Map<String, Integer> counts = apriori.generateCounts(basket);

        List<ItemSet> largeIsets = apriori.generateLarge1ItemSets(counts,basket,.2);
        assertEquals(5, largeIsets.size());
    }

    @Test
    public void testAprioriGen(){

    }

}

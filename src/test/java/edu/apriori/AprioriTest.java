package edu.apriori;

import edu.apriori.ItemSet;
import java.util.List;
import java.util.Map;
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

}

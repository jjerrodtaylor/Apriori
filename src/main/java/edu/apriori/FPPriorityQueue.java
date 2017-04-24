package edu.apriori;
import java.util.ArrayList;
import java.util.Map;
import javafx.util.Pair;


/**
 * Created by jamaaltaylor on 4/23/17.
 */
public class FPPriorityQueue implements PriorityQueue {

    private ArrayList<Pair<String, Integer>> heapArray;

    public FPPriorityQueue(){
        super();
        heapArray = new ArrayList<Pair<String, Integer>>();
    }

    public boolean empty(){
         return true;
    }

    private Integer getParentData(int index){
        int parentIndex = index/2;
        Pair<String, Integer> parentData = heapArray.get(parentIndex);
        return parentData.getValue();
    }

    private void swap(Integer old, Integer knew){
        Pair<String, Integer> temp = heapArray.get(old);
        heapArray.add(old, heapArray.get(knew));
        heapArray.add(knew,temp);
    }

    private void shiftUp(int index){

        int parentIndex;

        if(index != 1){
            parentIndex = index/2;

            if(getParentData(index) > heapArray.get(index).getValue()){
                swap(parentIndex,index);
                shiftUp(parentIndex);
            }
        }
    }

    private void shiftDown(int index){
        int leftChildValue = heapArray.get(index*2).getValue();
        int rightChildValue = heapArray.get((index*2)+1).getValue();
        int indexValue = heapArray.get(index).getValue();

        if(indexValue < leftChildValue || indexValue < rightChildValue)
        {
            if(leftChildValue > rightChildValue){
                int leftChildIndex = index*2;
                swap(index,leftChildIndex);
                shiftDown(leftChildIndex);
            }
            else{
                int rightChildIndex = (index*2)+1;
                swap(index,rightChildIndex);
                shiftDown(rightChildIndex);
            }
        }

    }

    public void insert(Pair<String, Integer> element){

        //add the element to the last position in the list
        if(heapArray.isEmpty()){
            heapArray.add(1,element);
        }
        else{
            heapArray.add(heapArray.size(), element);
            shiftUp(heapArray.size());
        }

    }

    public Pair<String, Integer> removeMax(){

        Pair<String, Integer> returnValue = heapArray.remove(1);
        Pair<String, Integer> lastElement = heapArray.remove(heapArray.size());
        heapArray.add(1,lastElement);
        shiftDown(1);

        return returnValue;

    }
}

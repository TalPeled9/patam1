package test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class LFU implements CacheReplacementPolicy{
    private HashMap<String, Item> cache;
    private PriorityQueue<Item> minHeap;
    private int insertionCounter;

    public LFU() {
        this.insertionCounter = 0;
        this.cache = new HashMap<String, Item>();
        this.minHeap = new PriorityQueue<>(Comparator.comparingInt((Item a) -> a.frequency)
                                            .thenComparingInt(a -> a.insertionIndex));
    }
    
    public void add(String word){
        this.insertionCounter++;
        if (cache.containsKey(word))
            this.increment(word);
        else
            this.insert(word);
    }

    public String remove(){
        Item lfuItem = minHeap.poll();
        cache.remove(lfuItem.value);
        return lfuItem.value;
    }

    public void insert(String word) {
        Item newItem = new Item(word, 1, this.insertionCounter);
        cache.put(word, newItem);
        minHeap.offer(newItem);
    }

    public void increment(String word) {
        Item item = cache.get(word);
        this.minHeap.remove(item);
        this.cache.remove(word);
        item.frequency += 1;
        item.insertionIndex = this.insertionCounter;
        this.minHeap.offer(item);
        this.cache.put(word, item);
    }
    
    class Item{
        String value;
        int frequency;
        int insertionIndex;

        public Item(String value, int frequency, int insertionIndex) {
            this.value = value;
            this.frequency = frequency;
            this.insertionIndex = insertionIndex;
        }
}


}

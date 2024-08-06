package test;

import java.util.HashMap;
import java.util.PriorityQueue;

import test.LFU.Pair;

public class LFU implements CacheReplacementPolicy{
    HashMap<String, Pair> cache;
    PriorityQueue<Pair> minHeap;

    public LFU() {
        this.cache = new HashMap<String, Pair>();
        this.minHeap = new PriorityQueue<>((a, b) -> a.frequency - b.frequency);
    }
    
    public void add(String word){
        if (cache.containsKey(word))
            this.increment(word);
        else
            this.insert(word);
    }

    public String remove(){
        Pair lfuPair = minHeap.poll();
        cache.remove(lfuPair.value);
        return lfuPair.value;
    }

    public void insert(String word) {
        Pair newPair = new Pair(word, 1);
        cache.put(word, newPair);
        minHeap.offer(newPair);
    }

    public void increment(String word) {
        Pair pair = cache.get(word);
        this.minHeap.remove(pair);
        this.cache.remove(word);
        pair.frequency += 1;
        this.minHeap.offer(pair);
        this.cache.put(word, pair);
    }
    
    class Pair{
        String value;
        int frequency;

        public Pair(String value, int frequency) {
            this.value = value;
            this.frequency = frequency;
        }
}


}

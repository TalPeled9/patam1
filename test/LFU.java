package test;

import java.util.HashMap;
import java.util.PriorityQueue;

import test.LFU.Pair;

public class LFU {
    HashMap<String, Pair> cache;
    PriorityQueue<Pair> minHeap;

    public LFU() {
        this.cache = new HashMap<String, Pair>();
        this.minHeap = new PriorityQueue<>((a, b) -> a.frequency - b.frequency);
    }
    
    public void add(String word){
        if(cache.containsKey(word))
            cache.remove(word);
        cache.add(word);
    }

    public String remove(){
        Pair lfuPair = minHeap.poll();
        cache.remove(lfuPair.value);
        return lfuPair.value;
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

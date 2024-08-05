package test;

import java.util.LinkedHashSet;

public class LRU implements CacheReplacementPolicy {
    LinkedHashSet<String> cache;  

    public LRU(){  
        this.cache = new LinkedHashSet<String>();   
    }  

    public void add(String word){
        if(cache.contains(word))
            cache.remove(word);
        cache.add(word);
    } 

    public String remove(){
        String lruWord = cache.iterator().next();  
        cache.remove(lruWord);
        return lruWord;
    }

}

package test;

import java.util.HashSet;

public class CacheManager {
	int size;
    CacheReplacementPolicy crp;
    HashSet<String> cache;

    public CacheManager(int size, CacheReplacementPolicy crp) {
        this.size = size;
        this.crp = crp;
        this.cache = new HashSet<String>();
    }

    public boolean query(String word){
        return cache.contains(word);
    }

    public void add(String word){
        if(this.query(word))
            crp.add(word);
        else{
            if(cache.size() == this.size){
                String toRemove = crp.remove();
                cache.remove(toRemove);
            }
            cache.add(word);
            crp.add(word);
        }
        
    }
	
}

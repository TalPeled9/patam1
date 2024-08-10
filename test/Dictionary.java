package test;


public class Dictionary {
    private CacheManager existingWords;
    private CacheManager nonExistingWords;
    private BloomFilter bloomFilter;

    public Dictionary(String...fileNames){
        this.existingWords = new CacheManager(400, new LRU());
        this.nonExistingWords = new CacheManager(100, new LFU());
        this.bloomFilter = new BloomFilter(256, "MD5","SHA1");
    }

}

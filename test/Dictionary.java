package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.List;
import java.util.Scanner;

public class Dictionary {
    private CacheManager existingWords;
    private CacheManager nonExistingWords;
    private BloomFilter bloomFilter;
    private String[] fileNames;

    private void insertWordsFromFile(String fileName) throws Exception{
        try{
            Scanner myScaner = new Scanner(new BufferedReader(new FileReader(fileName)));
            while(myScaner.hasNext())
                this.bloomFilter.add(myScaner.next());
            myScaner.close();
        }catch (IOException e){
            throw e;
        }
    }

    public Dictionary(String...fileNames){
        this.fileNames = fileNames;
        this.existingWords = new CacheManager(400, new LRU());
        this.nonExistingWords = new CacheManager(100, new LFU());
        this.bloomFilter = new BloomFilter(256, "MD5","SHA1");
        for (String fileName : this.fileNames) {
            try{
                this.insertWordsFromFile(fileName);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public boolean query(String word){
        if(this.existingWords.query(word))
            return true;
        if(this.nonExistingWords.query(word))
            return false;
        if(this.bloomFilter.contains(word)){
            this.existingWords.add(word);
            return true;
        }
        else{
            this.nonExistingWords.add(word);
            return false;
        }
    }

    public boolean challenge(String word){
        try{
            if(IOSearcher.search(word, this.fileNames)){
                this.existingWords.add(word);
                return true;
            }
            else{
                this.nonExistingWords.add(word);
                return false;
            }
        }catch(Exception e){
            return false;
        }
        }
    }


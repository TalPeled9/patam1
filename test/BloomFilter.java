package test;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.BitSet;

public class BloomFilter {
	public BitSet bs;
    public String[] hashFuncs;

    public BloomFilter(int size, String... algs){
        this.bs = new BitSet(size);
        this.hashFuncs = algs;
    }

    private MessageDigest getMD(String s){
        try{MessageDigest md = MessageDigest.getInstance(s);
            return md;
        } catch(NoSuchAlgorithmException x){
            System.out.println(s + " - this function is not valid");
            return null;
        }
    }

    private boolean isValidAlgs(){
        for (String hash : hashFuncs) {
            if(this.getMD(hash) == null)
                return false;
        }
        return true;
    }

    private int wordToIntVal(String word, String hash){
        MessageDigest md = this.getMD(hash);
        md.update(word.getBytes());
        byte[] bts= md.digest(word.getBytes());
        return Math.abs(new BigInteger(bts).intValue());
    }
    
    public void add(String word){
        if (this.isValidAlgs()) {
            for (String hash : hashFuncs)
                this.bs.set(this.wordToIntVal(word, hash) % this.bs.size());
            }
        else
            System.out.println("Could not add word");
            
    }

    public boolean contains(String word){
        if (this.isValidAlgs()) {
            for (String hash : hashFuncs) {
                if(!this.bs.get(this.wordToIntVal(word, hash) % this.bs.size()))
                    return false;
                }
                return true;
            }
        else{
            System.out.println("Could not check contains");
            return false;
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < this.bs.size(); i++){
            if (this.bs.get(i))
                str.append("1");
            else
                str.append("0");
        }
        return str.toString();
    }
  
}

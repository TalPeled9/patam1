package test;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class BloomFilter {
	private BitSet bitSet;
    private int bitSetSize;
    private List<MessageDigest> hashFunctions;

    public BloomFilter(int size, String... algs) {
        this.bitSetSize = size;
        this.bitSet = new BitSet(size);
        this.hashFunctions = new ArrayList<>();
        for (String alg : algs) {
            try {
                MessageDigest md = MessageDigest.getInstance(alg);
                this.hashFunctions.add(md);
            } catch (NoSuchAlgorithmException e) {
                throw new IllegalArgumentException("Invalid hash algorithm: " + alg, e);
            }
        }
    }

    private int getHashValue(String word, MessageDigest md){
        byte[] bytes = word.getBytes();
        byte[] hash = md.digest(bytes);
        BigInteger hashBigInteger = new BigInteger(hash);
        int index = Math.abs(hashBigInteger.intValue())%this.bitSetSize;
        return index;
    }

    public void add(String word) {
        for (MessageDigest md : hashFunctions)
            bitSet.set(this.getHashValue(word, md));
    }

    public boolean contains(String word) {
        for (MessageDigest md : hashFunctions) {
            if (!bitSet.get(this.getHashValue(word, md)))
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bitSet.length(); i++) {
            sb.append(bitSet.get(i) ? '1' : '0');
        }
        return sb.toString();
    }
  
}

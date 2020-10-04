package pa1;

import java.util.BitSet;

public class BaseBloomFilter {
    protected int filterSize;
    protected int dataSize;
    protected int numHashes;
    protected BitSet[] filter;
    protected BaseHashGenerator hashGenerator;
    protected int numberofFilters=1;

    BaseBloomFilter(int setSize, int bitsPerElement) throws Exception
    {
        if (setSize <= 0) {
            throw new Exception("Set Size should be greater than 0");
        }
        if (bitsPerElement <= 0) {
            throw new Exception("Bits per elements should be greater than 0");
        }
        dataSize = 0;
        filterSize = setSize * bitsPerElement;
        numHashes = (int) Math.ceil((filterSize / setSize) * Math.log(2));
    }
    public void initFilter(int k)
    {
        numberofFilters=k;
        filter=new BitSet[k];
        for(int i=0;i<k;i++)
            filter[i]=new BitSet(filterSize);
    }

    public void add(String s) {
        if (s == null)
            throw new IllegalArgumentException("s cannot be null");

        s = s.toLowerCase();
        dataSize = dataSize + 1;
        hashGenerator.init(s);
        for (int k = 0; k < numHashes; k++) {
            int index = (int) (hashGenerator.getHash(k) % filterSize); //sine filterSize is int, index will fit in int
            filter[k%numberofFilters].set(index, true);
        }
    }

    public boolean appears(String s) {
        if (s == null) {
            throw new IllegalArgumentException("s cannot be null");
        }
        s = s.toLowerCase();

        hashGenerator.init(s);
        for (int k = 0; k < numHashes; k++) {
            int index = (int) (hashGenerator.getHash(k) % filterSize);
            if (!filter[k%numberofFilters].get(index)) {
                return false;
            }
        }
        return true;
    }

    public int filterSize() { return filterSize; }

    public int dataSize() {
        return dataSize;
    }

    public int numHashes() {
        return numHashes;
    }
}

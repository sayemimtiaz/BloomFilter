package pa1;

public class BloomFilterFactory {
    public static BaseBloomFilter getFilter(int setSize, int bitsPerElement, BloomFilterType bloomFilterType) throws Exception {
        switch (bloomFilterType)
        {
            case FNV:
                return new BloomFilterFNV(setSize,bitsPerElement);
            case RANDOM:
                return new BloomFilterRan(setSize,bitsPerElement);
            case MULTI:
                return new MultiMultiBloomFilter(setSize,bitsPerElement);
            case NAIVE:
                return new NaiveBloomFilter(setSize,bitsPerElement);
        }
        return null;
    }
}

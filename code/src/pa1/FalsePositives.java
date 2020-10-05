package pa1;

import static java.util.Arrays.sort;

import java.util.Arrays;
import java.util.Random;

public class FalsePositives {
    int NumTrials = 10000;
    String allowedCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    int numFilterType = 4;

    BaseBloomFilter[] bloomFilters;
    String[] setElements;

    int setSize;

    FalsePositives(int setSize, int bitsPerElement) throws Exception {
        this.setSize = setSize;
        setElements = new String[setSize];
        bloomFilters = new BaseBloomFilter[numFilterType];

        bloomFilters[0] = new BloomFilterRan(setSize, bitsPerElement);
        bloomFilters[1] = new BloomFilterFNV(setSize, bitsPerElement);
        bloomFilters[2] = new MultiMultiBloomFilter(setSize, bitsPerElement);
        bloomFilters[3] = new NaiveBloomFilter(setSize, bitsPerElement);
    }

    public static void main(String[] args) throws Exception {

        for (int bits : Constants.BITS_PER_ELEMENT) {
            float expectedRate = expectedRate(bits);
            for (int n : Constants.SET_SIZE) {
                System.out.println("Trial: set size = " + n + " and bits per element = " + bits);
                System.out.println("Expected false positive rate: "+ expectedRate);
                FalsePositives falsePositives = new FalsePositives(n, bits);

                for (BaseBloomFilter bloomFilter : falsePositives.bloomFilters) {
                    falsePositives.createFilter(bloomFilter);
                    falsePositives.findFalsePositives(bloomFilter);
                }
            }
        }
    }

    private void findFalsePositives(BaseBloomFilter bloomFilter) {
        long numFalsePositive = 0;

        String s = "";
        for (int i = 0; i < NumTrials; i++) {
            int j;
            do {
                Random random = new Random();
                int stringLength = random.nextInt(20);
                s = generateString(stringLength);
                j = Arrays.binarySearch(this.setElements, s);
            } while (j >= 0 && j < this.setElements.length);

            if (bloomFilter.appears(s)) {
                numFalsePositive++;
            }
        }
        System.out.println(bloomFilter.getClass().getSimpleName() + ": " + (float) numFalsePositive / (float) NumTrials);
    }

    private void createFilter(BaseBloomFilter bloomFilter) {

        for (int i = 0; i < setSize; i++) {
            Random random = new Random();
            int stringLength = random.nextInt(20);
            String s = generateString(stringLength);
            setElements[i] = s;

            bloomFilter.add(s);

            if (!bloomFilter.appears(s))
                System.out.println("False negative found for " + bloomFilter.getClass().getSimpleName());
        }
    }

    public String generateString(int stringLength) {
        char[] retStr = new char[stringLength];
        Random random = new Random();
        for (int i = 0; i < stringLength; i++) {
            retStr[i] = allowedCharacters.charAt(random.nextInt(allowedCharacters.length()));
        }
        return new String(retStr);
    }

    public static float expectedRate(int bitsPerElement) {
        return (float) Math.pow(0.618, bitsPerElement);
    }

}

package pa1;

public class MultiMultiBloomFilter extends BaseBloomFilter {

	public MultiMultiBloomFilter(int setSize, int bitsPerElement) throws Exception {
		super(setSize, bitsPerElement);

		hashGenerator = new RandomHashGenerator(numHashes, filterSize);

		initFilter(numHashes);
	}
}

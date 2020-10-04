package pa1;

public class NaiveBloomFilter extends BaseBloomFilter {

	public NaiveBloomFilter(int setSize, int bitsPerElement) throws Exception {
		super(setSize, bitsPerElement);
		numHashes=1;
		hashGenerator = new RandomHashGenerator(numHashes, filterSize);
		initFilter(1);
	}
}

package pa1;

import static java.lang.Math.log;
import static java.lang.Math.ceil;

public class BloomFilterRan extends BaseBloomFilter {

	public BloomFilterRan(int setSize, int bitsPerElement) throws Exception {
		super(setSize, bitsPerElement);

		hashGenerator = new RandomHashGenerator(numHashes, filterSize);

		initFilter(1);
	}
}

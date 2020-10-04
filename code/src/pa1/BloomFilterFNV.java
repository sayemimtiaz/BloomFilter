package pa1;

public class BloomFilterFNV extends BaseBloomFilter{

	public BloomFilterFNV(int setSize, int bitsPerElement) throws Exception {
		super(setSize, bitsPerElement);
		hashGenerator=new FNVHashGenerator();
		initFilter(1);
	}
}

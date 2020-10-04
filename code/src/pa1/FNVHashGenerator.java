package pa1;

public class FNVHashGenerator extends BaseHashGenerator {
	
	private static final long MASK = (1L << 32) - 1L; 
    private long low;
    private long high;

    @Override
    public long getHash(int iteration) {
        return (low + iteration * high) & MASK;
    }

    @Override
    public void init(String s) {
        long orig = FNVHash.hash64(s);
        low = orig & MASK;
        high = orig >> 32;
    }
}

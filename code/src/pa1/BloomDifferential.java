package pa1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

public class BloomDifferential extends BaseDifferential {
    private static final int BITS_PER_ELEMENT = 8;

    private BaseBloomFilter bloomFilter;

    public BloomDifferential createFilter(BloomFilterType filterType) throws Exception {

        int setSize = (int) Util.getNumLinesFile(Constants.DIFF_FILE);
        this.bloomFilter = BloomFilterFactory.getFilter(setSize, BITS_PER_ELEMENT, filterType);

        if (this.bloomFilter == null)
            throw new Exception("Couldn't initialize bloom filter");

        try (Stream<String> stream = Files.lines(Paths.get(Constants.DIFF_FILE))) {
            stream.forEach((line) -> bloomFilter.add(Util.getKeyFromLine(line)));
        }
        return this;
    }

    @Override
    public String retrieveRecord(String key) throws Exception {

        if (bloomFilter.appears(key)) {
            return Util.getRecordWithKey(key, Constants.DIFF_FILE);
        }

        return Util.getRecordWithKey(key, Constants.DATABASE_FILE);
    }

    @Override
    public String getType() {
        return bloomFilter.getClass().getSimpleName();
    }

}

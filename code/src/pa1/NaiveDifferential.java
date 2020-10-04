package pa1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;


public class NaiveDifferential extends BaseDifferential
{
    public String retrieveRecord(String key) throws IOException {
        String record = Util.getRecordWithKey(key, Constants.DIFF_FILE);
        if (record != null) return record;
        return Util.getRecordWithKey(key, Constants.DATABASE_FILE);
    }

    @Override
    public String getType() {
        return this.getClass().getSimpleName();
    }
}

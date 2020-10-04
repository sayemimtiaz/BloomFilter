package pa1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

public class Util {
    public static long getNumLinesFile(String fileName) throws IOException {
        long lineCount = 0;
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            lineCount = stream.count();
        }
        return lineCount;
    }

    public static String getKeyFromLine(String line) {
        int spaceCount = 0;
        String key = "";
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == ' ') spaceCount++;
            if (spaceCount == 4 || line.charAt(i) == '\n') break;
            key += line.charAt(i);
        }
        return key;
    }

    public static String getRecordWithKey(String key, String fileName) throws IOException {
        Optional<String> record;
        try (Stream<String> stream = Files.lines(Paths.get(Constants.DIFF_FILE))) {
            record = stream.filter((l) -> key.equals(getKeyFromLine(l))).findFirst();
        }
        if (record.isPresent())
            return record.get();
        return null;
    }
}

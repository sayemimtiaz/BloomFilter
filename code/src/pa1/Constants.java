package pa1;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Constants {

	public static String DATABASE_FILE = "data/database.txt";
	public static String DIFF_FILE = "data/DiffFile.txt";
    public static String GRAMS_FILE = "data/grams.txt";
  
    
    public static int[] BITS_PER_ELEMENT = { 4, 8, 10 };

    public static int[] SET_SIZE = {
        20000, 30000, 40000, 50000
    };
}

enum BloomFilterType
{
    FNV,
    NAIVE,
    RANDOM,
    MULTI
}

class Pair
{
    public Integer first;
    public Integer second;

    Pair(Integer f, Integer s)
    {
        first=f;
        second=s;
    }
}